package ru.naumen.telegram_store.bot;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.naumen.telegram_store.domains.Product;
import ru.naumen.telegram_store.domains.message.MessageToUser;
import ru.naumen.telegram_store.repositories.ProductListRepository;
import ru.naumen.telegram_store.services.ProductListService;
import ru.naumen.telegram_store.services.ProductService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import static ru.naumen.telegram_store.bot.Constants.*;

@Component
@RequiredArgsConstructor
public class BotMessageCreator {
    private final ProductService productService;
    private final ProductListRepository productListRepository;
    private final ProductListService productListService;

    /**
     * Создается сообщение для пользователя с текстом приветствия и списком возможных команд бота.
     */
    public MessageToUser createMessageStartWorkBot(long chatId, String name) {
        String answer = "Привет, " + name + ", это телеграм бот магазина товаров.\n" +
                TEXT_START;
        return new MessageToUser(chatId, answer, "false");
    }

    /**
     * Создается сообщение для пользователя с текстом о доступных командах бота.
     */
    public MessageToUser createMessageAccessButtons(long chatId) {
        return new MessageToUser(chatId, HELP,"false");
    }

    /**
     * Создается сообщение для пользователя с текстом о закрытии каталога товаров
     */
    public MessageToUser createMessageDeleteButtons(long chatId) {
        String answer = "Вы закрыли каталог товаров";
        return new MessageToUser(chatId, answer,"false");
    }

    /**
     * Создается сообщение для пользователя, в котором выводятся названия товаров и кнопки
     */
    public MessageToUser createMessageNamesProducts(long chatId) {
        String answer = "Список товаров:";
        return new MessageToUser(chatId, answer, "products");
    }

    /**
     * Создается сообщение для пользователя с текстом о недоступной команде.
     */
    public MessageToUser createMessageNotFoundCommand(long chatId) {
        String answer = "Команда не найдена";
        return new MessageToUser(chatId, answer, "false");
    }

    /**
     * Создается сообщение для пользователя с текстом о товаре,а также создается кнопка "Добавить"
     */
    public MessageToUser createMessageAboutProduct(long chatId, long productId) {
        Product product = productService.getProductById(productId).get();
        String answer = "Имя: " + product.getName() + "\n"
                + "Описание: " +  new String(product.getDescription(), StandardCharsets.UTF_8) + "\n"
                + "Цена: " + product.getPrice();
        return new MessageToUser(chatId, answer, "add", productId);
    }

    public MessageToUser addProductInList(long chatId) {
        String answer = "Продукт добавлен";
        return new MessageToUser(chatId, answer, "false");
    }

    public MessageToUser createMessageListProducts(Long chatId) throws IOException {
        ArrayList<Long> productNumbers = productListService.getAddNumbersProducts(chatId);
        String answer = "";
        for (Long productNumber : productNumbers) {
            Product product = productService.getProductById(productNumber).get();
            answer += "Имя: " + product.getName() + "\n"
                    + "Описание: " +  new String(product.getDescription(), StandardCharsets.UTF_8) + "\n"
                    + "Цена: " + product.getPrice()
                    + "\n"
            + "\n";
        }
        return new MessageToUser(chatId, answer, "false");
    }
}
