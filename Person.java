/**
 * Represents a person, which can act as sender or recipient of a message.
 * Consists of a name and an email.
 *
 * @author Alex Thompson
 * @version 2
 */
public class Person implements Comparable<Person> {

    private String name, email;

    /**
     * Constructs a Person object (either the sender or receiver of a message).
     *
     * @param name the String of the Person's name
     * @param email the String of the Person's address
     */
    public Person(String name, String email) {
        this.name = name;
        this.email = email;
    }

    /**
     * Returns a String with the Person's name.
     *
     * @return a String representing the Persons's name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns a String with the Person's email address.
     *
     * @return a String representing the Person's email address.
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Returns a String with the Person's name and email address.
     *
     * @return a String representing the Person's name and email address.
     */
    public String toString() {
        String ans = new String(this.getName() + " (" + this.getEmail() + ")");
        return ans;
    }

    /**
     * Overrides the default equals method to check for equality in Person
     * objects based on their names.
     *
     * @param other The object to which you are comparing the Person to.
     * @return A boolean stating whether two Persons are the same: true if they
     * are, false if they aren't.
     */
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (!(other instanceof Person)) {
            return false;
        }
        Person that = (Person) other;
        return (this.getName().equals(that.getName()));
    }

    /**
     * Overrides the default hashcode method to create unique hash codes
     * for Person objects, that differ based on their names.
     *
     * @return the int hashcode
     */
    public int hashCode() {
        int count = 17;
        count = count + 32 * this.getName().hashCode();
        return count;
    }

    /**
     * Compares a Person with another Person based on name.
     *
     * @param other the other Person to be compared with.
     * @return an int; negative if this' name is alphabetically after other's,
     * 0 if equal, positive if alphabetically before.
     */
    public int compareTo(Person other) {
        return this.getName().compareTo(other.getName());
    }
}