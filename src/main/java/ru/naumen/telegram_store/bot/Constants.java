package ru.naumen.telegram_store.bot;

import org.springframework.stereotype.Component;

@Component
public class Constants {
    public static final String COMMAND_START = "/start";
    public static final String COMMAND_EXIT = "/exit";
    public static final String COMMAND_HELP = "/help";
    public static final String COMMAND_PRODUCTS = "/products";
    public static final String COMMAND_PRODUCTS_LIST = "/products_list";
    public static final String COMMAND_DELETE = "/delete";
    public static final String HELP = """
            Справка о доступных командах:
            /products - Выводится список доступных товаров.
            /products_list - Выводится список добавленных продуктов.
            /delete - очищается список добавленных продуктов.
            /exit - Полностью очищаются все данные о пользователе
            /help""";
    public static final String TEXT_START = """
            Доступны следующие команды:
            /products – Перейти в каталог товаров.
            /products_list - Вывести список добавленнх товаров.
            /delete - Удалить все содержимое списка товаров.
            /exit - Выйти из каталога товаров.
            /help - Справка.
            """;
}
