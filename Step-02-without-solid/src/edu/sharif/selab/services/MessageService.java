package edu.sharif.selab.services;

import edu.sharif.selab.models.Message;

public interface MessageService {
    public void sendMessage(Message message);
    public boolean validateReceiverAddress(String receiverAddress);
}
