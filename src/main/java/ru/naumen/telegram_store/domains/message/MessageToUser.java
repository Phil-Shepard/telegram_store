package ru.naumen.telegram_store.domains.message;

import lombok.Getter;

public class MessageToUser {

    @Getter
    private final long chatId;
    @Getter
    private final String text;
    @Getter
    private final String inlineKeyboardMarkup;
    @Getter
    private final Long callBackProductId;

    public MessageToUser(long chatId, String text, String inlineKeyboardMarkup) {
        this.chatId = chatId;
        this.text = text;
        this.inlineKeyboardMarkup = inlineKeyboardMarkup;
        this.callBackProductId = null;
    }

    public MessageToUser(long chatId, String text, String inlineKeyboardMarkup, Long callBackProductId) {
        this.chatId = chatId;
        this.text = text;
        this.inlineKeyboardMarkup = inlineKeyboardMarkup;
        this.callBackProductId = callBackProductId;
    }

}
