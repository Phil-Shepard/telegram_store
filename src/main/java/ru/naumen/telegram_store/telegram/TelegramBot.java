package ru.naumen.telegram_store.telegram;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.naumen.telegram_store.bot.Bot;
import ru.naumen.telegram_store.bot.BotConfig;
import ru.naumen.telegram_store.bot.BotLogic;
import ru.naumen.telegram_store.domains.Product;
import ru.naumen.telegram_store.domains.message.MessageFromUser;
import ru.naumen.telegram_store.domains.message.MessageToUser;
import ru.naumen.telegram_store.services.ProductService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class TelegramBot extends TelegramLongPollingBot implements Bot {

    private final BotConfig botConfig;
    private final BotLogic botLogic;
    private final ProductService productService;

    public TelegramBot(BotConfig botConfig, BotLogic botLogic, ProductService productService) {
        this.botConfig = botConfig;
        this.botLogic = botLogic;
        this.productService = productService;
        List<BotCommand> listOfCommands = new ArrayList<>();
        listOfCommands.add(new BotCommand("/start", "Это телеграмм бот товаров."));
        listOfCommands.add(new BotCommand("/products", "Перейти в каталог товаров."));
        listOfCommands.add(new BotCommand("/products_list", "Вывести список добавленнх товаров"));
        listOfCommands.add(new BotCommand("/delete", "Очистить корзину"));
        listOfCommands.add(new BotCommand("/exit", "Выйти из каталога товаров"));
        listOfCommands.add(new BotCommand("/help", "Справка"));
        try {
            this.execute(new SetMyCommands(listOfCommands, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void onUpdateReceived(Update update) {
//        try {
//            parserPageService.getAllProducts();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        if (productService.getAll().isEmpty()) {
                productService.AddProducts();
        }
        if (update.hasCallbackQuery()) {
            MessageFromUser message = new MessageFromUser(
                    update.getCallbackQuery().getMessage().getChatId(),
                    update.getCallbackQuery().getData(),
                    update.getCallbackQuery().getFrom().getUserName()
            );
            MessageToUser messageToUser = null;
            try {
                messageToUser = botLogic.onUpdateReceived(message);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (messageToUser == null) {
                return;
            }
            this.sendMessage(messageToUser);
        }
        else {
            MessageFromUser message = new MessageFromUser(
                    update.getMessage().getChatId(),
                    update.getMessage().getText(),
                    update.getMessage().getChat().getFirstName()
            );
            MessageToUser messageToUser = null;
            try {
                messageToUser = botLogic.onUpdateReceived(message);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (messageToUser == null) {
                return;
            }
            this.sendMessage(messageToUser);
        }
    }

    @Override
    public String getBotUsername() {
        return botConfig.getBOT_NAME();
    }
    @Override
    public String getBotToken() {
        return botConfig.getBOT_TOKEN();
    }

    @Override
    public void sendMessage(MessageToUser messageToUser) {
        try {
            execute(convertMessageToUserToSendMessage(messageToUser));
        } catch (TelegramApiException e) {
            throw new RuntimeException("Не удалось отправить сообщение. "
                    + e.getMessage(), e);
        }
    }

    private InlineKeyboardMarkup getKeyBoardAllProducts() {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboardRows = new ArrayList<>();
        for (Product product: productService.getAll()) {
            List<InlineKeyboardButton> row = new ArrayList<>();
            InlineKeyboardButton newButton = new InlineKeyboardButton();
            newButton.setText(product.getName());
            newButton.setCallbackData(product.getId().toString());
            row.add(newButton);
            keyboardRows.add(row);
        }
        keyboardMarkup.setKeyboard(keyboardRows);
        return keyboardMarkup;
    }

    private InlineKeyboardMarkup AddButton(Long productId){
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboardRows = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();
        InlineKeyboardButton newButton = new InlineKeyboardButton();
        newButton.setText("Добавить продукт");
        newButton.setCallbackData("Добавить продукт " + productId.toString());
        row.add(newButton);
        keyboardRows.add(row);
        keyboardMarkup.setKeyboard(keyboardRows);
        return keyboardMarkup;
    }

    private SendMessage convertMessageToUserToSendMessage(MessageToUser messageToUser) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(messageToUser.getChatId());
        if (messageToUser.getText() != null) sendMessage.setText(messageToUser.getText());
        if (messageToUser.getInlineKeyboardMarkup().equals("products")) sendMessage.setReplyMarkup(getKeyBoardAllProducts());
        if (messageToUser.getInlineKeyboardMarkup().equals("add")) sendMessage.setReplyMarkup(AddButton(messageToUser.getCallBackProductId()));
        return sendMessage;
    }

}
