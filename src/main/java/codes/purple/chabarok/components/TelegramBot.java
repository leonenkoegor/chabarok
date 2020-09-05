package codes.purple.chabarok.components;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TelegramBot extends TelegramLongPollingBot {
    public void sendMessage(String message) {
        SendMessage response = new SendMessage();
        response.setChatId(getChatId());
        response.setText(message);
        try {
            execute(response);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
    }

    @Override
    public String getBotUsername() {
        return "Chabarok Bot";
    }

    @Override
    public String getBotToken() {
        return "1069551365:AAFzkvMe2vxWQvgs086WymMfYroF_KyKCAg";
    }

    public Long getChatId() {
        return -456604003L;
    }
}
