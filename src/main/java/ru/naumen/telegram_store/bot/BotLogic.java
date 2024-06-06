package ru.naumen.telegram_store.bot;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.naumen.telegram_store.domains.Order;
import ru.naumen.telegram_store.domains.Product;
import ru.naumen.telegram_store.domains.ProductsList;
import ru.naumen.telegram_store.domains.message.MessageFromUser;
import ru.naumen.telegram_store.domains.message.MessageToUser;
import ru.naumen.telegram_store.services.OrderService;
import ru.naumen.telegram_store.services.ProductListService;
import ru.naumen.telegram_store.services.ProductService;

import java.io.IOException;
import static ru.naumen.telegram_store.bot.Constants.*;

@Component
@RequiredArgsConstructor
public class BotLogic {
    private final BotMessageCreator botMessageCreator;
    private final ProductService productService;
    private final ProductListService productListService;
    private final OrderService orderService;

    /**
     * Точка входа, куда будут поступать сообщения от пользователей. Отсюда будет идти вся новая логика.
     *
     * @return
     */
    public MessageToUser onUpdateReceived(MessageFromUser messageFromUser) throws IOException {
        if (messageFromUser.getMessage() == null || messageFromUser.getMessage().isEmpty()) {
            return null;
        }
        String messageText = messageFromUser.getMessage();
        long chatId = messageFromUser.getChatId();
        switch (messageText) {
            case COMMAND_START -> {
                return botMessageCreator.createMessageStartWorkBot(chatId, messageFromUser.getUserName());
            }
            case COMMAND_PRODUCTS -> {
                return botMessageCreator.createMessageNamesProducts(chatId);
            }
            case COMMAND_EXIT -> {
                return botMessageCreator.createMessageExit(chatId);
            }
            case COMMAND_HELP -> {
                return botMessageCreator.createMessageAccessButtons(chatId);
            }
            case COMMAND_PRODUCTS_LIST -> {
                return botMessageCreator.createMessageListProducts(chatId);
            }
            case COMMAND_DELETE -> {
                return botMessageCreator.createMessageDelete(chatId);
            }
            default -> {
                for(Product product: productService.getAll()){
                    if (messageText.equals(product.getId().toString())){
                        return botMessageCreator.createMessageAboutProduct(chatId, product.getId());
                    }
                }
                if ((messageText.split(" ")[0] + " " + messageText.split(" ")[1]).equals("Добавить продукт")){
                    productListService.addProduct(new ProductsList(chatId, messageFromUser.getUserName(),
                            productService.getProductNameById(Long.valueOf(messageFromUser.getMessage().split(" ")[2])),
                            productService.getProductPriceById(Long.valueOf(messageFromUser.getMessage().split(" ")[2]))),
                            chatId);
                    return botMessageCreator.addProductInList(chatId);
                }
                return botMessageCreator.createMessageNotFoundCommand(chatId);
            }
        }
    }
}
