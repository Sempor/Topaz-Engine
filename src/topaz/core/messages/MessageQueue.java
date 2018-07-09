package topaz.core.messages;

public class MessageQueue {

    private Node first;

    public MessageQueue() {
    }

    public void push(Message message) {
        Node newNode = new Node(message, null);
        getLastNode().setNext(newNode);
    }

    public Message pop() {
        if (first == null) {
            return null;
        }
        Message received = first.getMessage();
        first = first.getNext();
        return received;
    }

    private Node getLastNode() {
        Node last = first;
        if (last == null) {
            return null;
        }
        while (last.getNext() != null) {
            last = last.getNext();
        }
        return last;
    }

    private class Node {

        private Message message;
        private Node next;

        public Node(Message message, Node next) {
            this.message = message;
            this.next = next;
        }

        public void setMessage(Message message) {
            this.message = message;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Message getMessage() {
            return message;
        }

        public Node getNext() {
            return next;
        }
    }
}
