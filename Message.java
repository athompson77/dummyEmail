import java.time.LocalDateTime;
import java.util.Set;

/**
 * Represents a Message object, consisting of a sender, subject, body,
 * recipients, and date/time sent.
 *
 * @author Alex Thompson
 * @version 1
 */
public class Message implements Comparable<Message> {

    protected Person sender;
    protected String subject, body;
    protected LocalDateTime date;
    protected Set<Person> recipients;

    /**
     * Constructs a Message object (an email, for all intents and purposes).
     *
     * @param sender the Person who sent the message
     * @param subject the String title of the message
     * @param body a String of message's contents (main written body)
     * @param date a LocalDateTime representing when
     * the message was sent (created)
     * @param recipients a Set of Person objects to whom the message was sent
     */
    public Message(Person sender, String subject, String body,
        LocalDateTime date, Set<Person> recipients) {
        this.sender = sender;
        this.subject = subject;
        this.date = date;
        this.body = body;
        this.recipients = recipients;
    }

    /**
     * Returns a String of the message sender's name and subject line.
     *
     * @return a String representing the message's main points:
     * sender and subject line.
     */
    public String toString() {
        return String.format("%s:%n %s", this.getSenderName(),
                        this.getSubject());
    }

    /**
     * Returns a String of the Message's sender's name.
     *
     * @return a String representing the Person's name who sent the Message.
     */
    public String getSenderName() {
        return this.sender.getName();
    }

    /**
     * Returns a String of the Message's sender's email.
     *
     * @return a String representing the Person's email who sent the Message.
     */
    public String getSenderEmail() {
        return this.sender.getEmail();
    }

    /**
     * Returns a String of the Message's subject line.
     *
     * @return a String representing the Message's subject line/"title".
     */
    public String getSubject() {
        return this.subject;
    }

    /**
     * Returns a String of the Message's main body/text.
     *
     * @return a String representing the Message's main body/text.
     */
    public String getBody() {
        return this.body;
    }

    /**
     * Returns a String representing the date/time the Message was sent.
     *
     * @return a String representing the Message's sent-time/date.
     */
    public String getDate() {
        String simpleDate = new String(this.date.getMonth() + "-"
            + this.date.getDayOfMonth() + "-" + this.date.getYear()
            + " " + this.date.getHour() + ":" + this.date.getMinute()
            + "." + this.date.getSecond());
        return simpleDate;
    }

    /**
     * Gives the listing of Person recipients of the Message.
     *
     * @return a set of the Message's recipients
     */
    public Set<Person> getRecipients() {
        return this.recipients;
    }

    /**
     * Overrides the default equals method to check for equality in Message
     * objects based on their sent-dates, senders, subjects, and message body.
     *
     * @param other the Object to which the
     * Message instance is being compared against
     * @return A boolean stating whether two Messages are the same:
     * true if they are, false if they aren't.
     */
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (!(other instanceof Message)) {
            return false;
        }
        Message that = (Message) other;
        return (this.getDate().equals(that.getDate())
            && this.getSubject().equals(that.getSubject())
            && this.sender.equals(that.sender)
            && this.getBody().equals(that.getBody()));
    }

    /**
     * Overrides the default hashcode method to create unique hash codes
     * for Message objects, that differ based on their dates, subject line,
     * sender's name, and message body.
     *
     * @return the int hashcode
     */
    public int hashCode() {
        int count = 17;
        count = count + 32 * this.getDate().hashCode();
        count = count + 32 * this.getSubject().hashCode();
        count = count + 32 * this.getSenderName().hashCode();
        count = count + 32 * this.getBody().hashCode();
        return count;
    }

    /**
     * Compares a Message with another Message based on sent date.
     *
     * @param other the other Message to be compared with
     * @return an int; negative if this is sent after other, 0 if equal,
     * positive if sent before.
     */
    public int compareTo(Message other) {
        return this.getDate().compareTo(other.getDate());
    }
}