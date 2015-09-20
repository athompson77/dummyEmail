import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
import java.time.LocalDateTime;

/**
 * Represents the backing Server, which creates instances of messages
 * through randomizing methods.
 *
 * @author Alex Thompson
 * @version 2
 */
public abstract class Server {

    private static ArrayList<Person> senderList
        = new ArrayList<>(Arrays.asList(
        new Person("John Wayne", "duke@yahoo.com"),
        new Person("Cisco Kid", "goodall@yahoo.com"),
        new Person("Mom", "swimmom@hotmail.com"),
        new Person("Uncle Ned", "flanders@newlife.com"),
        new Person("Matthew Murdock", "n&m@aol.com"),
        new Person("Ivan Ivanovich", "dosvedanya@aol.com"),
        new Person("Nicholas Angel", "thefuzz@hotmail.com"),
        new Person("Wong Fu Productions", "niceguys@aol.com"),
        new Person("Cecil Gershwin Palmer", "voice@wtnv.com")));

    private static ArrayList<String> subjectList
        = new ArrayList<>(Arrays.asList(
        "Your Time is Up...",
        "88 Miles per Hour!",
        "You call this a mailbox?",
        "RE: Your Brains",
        "RE: math 2605 project",
        "Merry belated birthday!",
        "The easy way or the hard way",
        "About your cat...",
        "To the family of Intern Alex"));

    private static ArrayList<Person> recipientList
        = new ArrayList<>(Arrays.asList(
        new Person("Radar O'Reilly", "choppers@4077.com"),
        new Person("Clark Kent", "glasses@dailyplanet.com"),
        new Person("Gustavo Sorola", "simmons@roosterteeth.com"),
        new Person("Barack Obama", "2termz@pennsylvaniaave.com"),
        new Person("Kevin Bacon", "eggsandbacon@gmail.com"),
        new Person("Steve Buscemi", "yolo@hotmail.com"),
        new Person("Mike Moustakas", "moose@kcroyals.com")));

    /**
     * Creates a Message instance using a combination of random parameters.
     *
     * @return a Message, random and newly created
     */
    public static Message getMessage() {
        Message m = new Message(randSender(), randSubject(), randBody(),
            LocalDateTime.now(), randRecipients());
        return m;
    }

    /**
     * Randomly gives a Person out of a list of possible Senders
     * for use in creating an instance of Message.
     *
     * @return a Person sender of the Message
     */
    private static Person randSender() {
        Random r = new Random();
        int sender = r.nextInt(9);
        return senderList.get(sender);
    }

    /**
     * Randomly gives a String out of a list of possible subject lines
     * for use in creating an instance of Message.
     *
     * @return a String subject line
     */
    private static String randSubject() {
        Random r = new Random();
        int subject = r.nextInt(9);
        return subjectList.get(subject);
    }

    /**
     * Gives a random String for use in a Message's main message body.
     *
     * @return a String of random characters.
     */
    private static String randBody() {
        String choices = "1234567890 abcdefghijklmnopqrstuvwxyz"
            + "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String ans = "";
        Random r = new Random();
        for (int i = 0; i < 30; i = i + 1) {
            ans = ans + choices.charAt(r.nextInt(choices.length()));
        }
        return ans;
    }

    /**
     * Gives a random Hashset of Person objects for use in a
     * Message's list of recipients.
     *
     * @return a random Set of Persons
     */
    private static Set<Person> randRecipients() {
        Random r = new Random();
        Set<Person> recipients = new HashSet<Person>();
        recipients.add(new Person("Me", "kyzersoze@hotmail.com"));
        for (int i = 0; i < 2; i = i + 1) {
            Person recipient = recipientList.get(r.nextInt(7));
            recipients.add(recipient);
        }
        return recipients;
    }

}