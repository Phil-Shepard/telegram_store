package ru.naumen.telegram_store.bot;

import ru.naumen.telegram_store.domains.message.MessageToUser;

/**
 * Интерфейс бота
 */
public interface Bot {
    /**
     * Выводит сообщение
     * @param message класс сообщения
     */
    void sendMessage(MessageToUser message);
}
