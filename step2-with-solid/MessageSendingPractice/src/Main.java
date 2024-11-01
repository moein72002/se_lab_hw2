import edu.sharif.selab.models.EmailMessage;
import edu.sharif.selab.models.Message;
import edu.sharif.selab.models.SmsMessage;
import edu.sharif.selab.models.TelegramMessage;
import edu.sharif.selab.services.EmailMessageService;
import edu.sharif.selab.services.MessageService;
import edu.sharif.selab.services.SmsMessageService;
import edu.sharif.selab.services.TelegramMessageService;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Map<Integer, MessageService> serviceRegistry = new HashMap<>();

    static {
        serviceRegistry.put(1, new SmsMessageService());
        serviceRegistry.put(2, new EmailMessageService());
        serviceRegistry.put(3, new TelegramMessageService());
    }

    public static void main(String[] args) {
        System.out.println("Hello and Welcome to SE Lab Messenger.");
        int userAnswer;
        do {
            System.out.println("In order to send an SMS, enter 1");
            System.out.println("In order to send an Email, enter 2");
            System.out.println("In order to send a Telegram message, enter 3");
            System.out.println("In order to Exit, enter 0");

            userAnswer = scanner.nextInt();
            if (userAnswer == 0) {
                break;
            }

            if (!serviceRegistry.containsKey(userAnswer)) {
                System.out.println("Invalid option. Please try again.");
                continue;
            }

            MessageService messageService = serviceRegistry.get(userAnswer);
            Message message = createMessageBasedOnUserInput(userAnswer);

            if (message != null) {
                messageService.sendMessage(message);
            }
        } while (true);
    }

    private static Message createMessageBasedOnUserInput(int type) {
        String source;
        String target;
        String content;

        switch (type) {
            case 1:
                SmsMessage smsMessage = new SmsMessage();
                System.out.print("Enter source phone: ");
                source = scanner.next();
                smsMessage.setSourcePhoneNumber(source);
                System.out.print("Enter target phone: ");
                target = scanner.next();
                smsMessage.setTargetPhoneNumber(target);
                System.out.print("Write your message: ");
                content = scanner.next();
                smsMessage.setContent(content);
                return smsMessage;
            case 2:
                EmailMessage emailMessage = new EmailMessage();
                System.out.print("Enter source email: ");
                source = scanner.next();
                emailMessage.setSourceEmailAddress(source);
                System.out.print("Enter target email: ");
                target = scanner.next();
                emailMessage.setTargetEmailAddress(target);
                System.out.print("Write your message: ");
                content = scanner.next();
                emailMessage.setContent(content);
                return emailMessage;
            case 3:
                TelegramMessage telegramMessage = new TelegramMessage();
                System.out.print("Enter source username: ");
                source = scanner.next();
                telegramMessage.setSourceUsername(source);
                System.out.print("Enter target username: ");
                target = scanner.next();
                telegramMessage.setTargetUsername(target);
                System.out.print("Write your message: ");
                content = scanner.next();
                telegramMessage.setContent(content);
                return telegramMessage;
            default:
                return null;
        }
    }
}
