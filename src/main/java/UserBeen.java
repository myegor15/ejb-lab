import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class UserBeen {

    @EJB
    private MongoClientBeen mongoClientBeen;

    public void addUser(User user) {
        MongoCollection<Document> collection = mongoClientBeen.getEjbLabCollection();
        collection.insertOne(user.toDocument());
    }

    public List<User> getAllUser() {
        MongoCollection<Document> collection = mongoClientBeen.getEjbLabCollection();
        List<User> userList = new ArrayList<>();

        FindIterable<Document> documents = collection.find();
        for (Document document : documents) {
            userList.add(User.fromDocument(document));
        }

        return userList;
    }

    public void deleteUser(User user) {
        MongoCollection<Document> collection = mongoClientBeen.getEjbLabCollection();
        collection.deleteOne(user.toDocument());
    }

    public List<User> findByUserData(User user) {
        MongoCollection<Document> collection = mongoClientBeen.getEjbLabCollection();
        List<User> userList = new ArrayList<>();

        FindIterable<Document> documents = collection.find(user.toDocument());
        for (Document document : documents) {
            userList.add(User.fromDocument(document));
        }

        return userList;
    }
}
