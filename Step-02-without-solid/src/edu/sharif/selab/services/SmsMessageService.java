package edu.sharif.selab.services;

import edu.sharif.selab.models.Message;
import edu.sharif.selab.models.SmsMessage;

public class SmsMessageService implements MessageService {
    @Override
    public void sendMessage(Message message) {
        if (message instanceof SmsMessage) {
            SmsMessage smsMessage = (SmsMessage) message;
            if (validateReceiverAddress(smsMessage.getTargetPhoneNumber())) {
                System.out.println("Sending an SMS from " + smsMessage.getSourcePhoneNumber() + " to " + smsMessage.getTargetPhoneNumber() + " with content: " + smsMessage.getContent());
            } else {
                throw new IllegalArgumentException("Invalid phone number!");
            }
        }
    }

    @Override
    public boolean validateReceiverAddress(String address) {
        // Check if the phone number is exactly 11 characters long and contains only digits
        return address.length() == 11 && address.chars().allMatch(Character::isDigit);
    }
}
