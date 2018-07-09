package topaz.core.messages;

import java.util.ArrayList;

public class MessageBus {

    private static ArrayList<MessageQueue> queues = new ArrayList<>();

    public static void sendMessage(Message message) {
        for (MessageQueue q : queues) {
            q.push(message);
        }
    }

    public static void addQueue(MessageQueue q) {
        queues.add(q);
    }
}