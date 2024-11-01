package edu.sharif.selab.services;

import edu.sharif.selab.models.Message;
import edu.sharif.selab.models.EmailMessage;

import java.util.regex.Pattern;

public class EmailMessageService implements MessageService {
    @Override
    public void sendMessage(Message message) {
        if (message instanceof EmailMessage) {
            EmailMessage emailMessage = (EmailMessage) message;
            if (validateReceiverAddress(emailMessage.getTargetEmailAddress())) {
                System.out.println("Sending an Email from " + emailMessage.getSourceEmailAddress() + " to " + emailMessage.getTargetEmailAddress() + " with content: " + emailMessage.getContent());
            } else {
                throw new IllegalArgumentException("Invalid email address!");
            }
        }
    }

    @Override
    public boolean validateReceiverAddress(String address) {
        // Regular expression pattern for validating email addresses
        String emailRegex = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
        return Pattern.compile(emailRegex).matcher(address).matches();
    }
}
