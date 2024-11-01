package edu.sharif.selab.services;

import edu.sharif.selab.models.Message;
import edu.sharif.selab.models.TelegramMessage;

import java.util.regex.Pattern;

public class TelegramMessageService implements MessageService {
    @Override
    public void sendMessage(Message message) {
        if (message instanceof TelegramMessage) {
            TelegramMessage telegramMessage = (TelegramMessage) message;
            if (validateReceiverAddress(telegramMessage.getTargetUsername())) {
                System.out.println("Sending a Telegram message from " + telegramMessage.getSourceUsername()
                        + " to " + telegramMessage.getTargetUsername()
                        + " with content: " + telegramMessage.getContent());
            } else {
                throw new IllegalArgumentException("Invalid Telegram username!");
            }
        }
    }

    @Override
    public boolean validateReceiverAddress(String address) {
        // Regular expression pattern for validating Telegram usernames
        String usernameRegex = "^[a-zA-Z0-9_]{5,32}$"; // Telegram usernames are between 5-32 characters long.

        // Compile and match the regex pattern
        return Pattern.compile(usernameRegex).matcher(address).matches();
    }
}
