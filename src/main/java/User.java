import org.bson.Document;
import org.bson.types.ObjectId;

public class User {
    private ObjectId id;
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private String phoneNumber;

    public User() {
    }

    public User(ObjectId id, String firstName, String lastName, int age, String email, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Document toDocument() {
        Document document = new Document();

        if (id != null) document.put("_id", id);
        if (firstName != null) document.put("firstName", firstName);
        if (lastName != null) document.put("lastName", lastName);
        if (age != 0) document.put("age", age);
        if (email != null) document.put("email", email);
        if (phoneNumber != null) document.put("phoneNumber", phoneNumber);

        return document;
    }

    public static User fromDocument(Document document) {
        return new User(
                document.getObjectId("_id"),
                document.getString("firstName"),
                document.getString("lastName"),
                document.getInteger("age"),
                document.getString("email"),
                document.getString("phoneNumber")
        );
    }
}
