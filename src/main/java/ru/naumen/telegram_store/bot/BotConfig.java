package ru.naumen.telegram_store.bot;

import org.springframework.stereotype.Component;

@Component
public class BotConfig {
    private static final String BOT_NAME = "Store0706_bot";
    private static final String BOT_TOKEN = "7080126036:AAEPqL3LEl5E0ykUSZshH-zmYe2uGNyrRpc";

    public String getBOT_NAME() {
        return BOT_NAME;
    }

    public String getBOT_TOKEN() {
        return BOT_TOKEN;
    }
}
