package edu.sharif.selab.services;

import edu.sharif.selab.models.TelegramMessage;
import edu.sharif.selab.models.SmsMessage;
import edu.sharif.selab.models.EmailMessage;

import java.util.regex.Pattern;

public class TelegramMessageService implements MessageService {
    @Override
    public void sendSmsMessage(SmsMessage smsMessage) {
        // Empty Body
    }

    @Override
    public void sendEmailMessage(EmailMessage emailMessage) {
        // Empty Body
    }

    public void sendTelegramMessage(TelegramMessage telegramMessage) {
        if (validateTelegramUsername(telegramMessage.getSourceUsername())
                && validateTelegramUsername(telegramMessage.getTargetUsername())) {
            System.out.println("Sending a Telegram message from " + telegramMessage.getSourceUsername()
                    + " to " + telegramMessage.getTargetUsername()
                    + " with content: " + telegramMessage.getContent());
        } else {
            throw new IllegalArgumentException("Telegram username is not correct!");
        }
    }

    public boolean validateTelegramUsername(String username) {
        // Regular expression pattern for validating Telegram usernames
        String usernameRegex = "^[a-zA-Z0-9_]{5,32}$"; // Telegram usernames can be 5-32 characters long, containing letters, numbers, and underscores.

        // Compile the pattern into a regex Pattern object
        Pattern pattern = Pattern.compile(usernameRegex);

        // Check if the username string matches the regex pattern
        return pattern.matcher(username).matches();
    }
}
