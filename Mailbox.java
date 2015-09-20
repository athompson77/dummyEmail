import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.Iterator;
import java.util.Comparator;

/**
 * Represents a Mailbox object, which is a group of messages
 * (inbox, important, or trash).
 *
 * @author Alex Thompson
 * @version 1
 */
public class Mailbox implements Iterable<Message> {

    private ObservableList<Message> messages;
    protected String name;

    /**
     * Constructs a Mailbox object (a named collection of Messages).
     *
     * @param name the String of the Mailbox's name
     */
    public Mailbox(String name) {
        this.name = name;
        this.messages = FXCollections.observableArrayList();
    }

    /**
     * Returns a String with the Mailbox's name.
     *
     * @return a String representing the Mailbox's name.
     */
    public String toString() {
        return this.name;
    }

    /**
     * Gives the listing of Messages in the Mailbox.
     *
     * @return an ObservableList of Messages in the Mailbox.
     */
    public ObservableList<Message> getList() {
        return this.messages;
    }

    /**
     * Sorts the Mailbox's Messages alphabetically by their subject line.
     *
     * @return an ObservableList of Messages in the Mailbox, sorted by subject.
     */
    public ObservableList<Message> sortSubject() {
        Comparator<Message> comparator
            = (m1, m2) -> m1.getSubject().compareTo(m2.getSubject());
        ObservableList<Message> list = this.getList();
        ObservableList<Message> finished = list.sorted(comparator);
        return finished;
    }

    /**
     * Sorts the Mailbox's Messages alphabetically by their senders' names.
     *
     * @return an ObservableList of Messages in the Mailbox, sorted by sender.
     */
    public ObservableList<Message> sortSender() {
        Comparator<Message> comparator
            = (m1, m2) -> m1.getSenderName().compareTo(m2.getSenderName());
        ObservableList<Message> list = this.getList();
        ObservableList<Message> finished = list.sorted(comparator);
        return finished;
    }

    /**
     * Sorts the Mailbox's Messages by date sent.
     *
     * @return an ObservableList of Messages in the Mailbox,
     * sorted by sent-date.
     */
    public ObservableList<Message> sort() {
        Comparator<Message> comparator
            = (m1, m2) -> m1.compareTo(m2);
        ObservableList<Message> list = this.getList();
        ObservableList<Message> finished = list.sorted(comparator);
        return finished;
    }

    /**
     * Returns an iterator over the Messages in the Mailbox.
     *
     * @return an iterator
     */
    @Override
    public Iterator<Message> iterator() {
        return messages.iterator();
    }

    /**
     * Adds a new random Message to the Mailbox's ObservableList.
     *
     * @return the added Message
     */
    public Message add() {
        Message next = Server.getMessage();
        this.messages.add(next);
        return next;
    }

    /**
     * Adds the specified Message to the Mailbox's ObservableList.
     *
     * @param m the Message to be added
     * @return the added Message
     */
    public Message add(Message m) {
        this.messages.add(m);
        return m;
    }

    /**
     * Takes out a Message from the Mailbox instance and returns it.
     *
     * @param m the Message object to remove/take
     * @return the Message removed
     */
    public Message remove(Message m) {
        this.messages.remove(m);
        return m;
    }
}