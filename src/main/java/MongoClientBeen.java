import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class MongoClientBeen {
    private static final String URL = "mongodb+srv://dev:dev123@dev-cr9bn.mongodb.net/dev?retryWrites=true&w=majority";
    private static final String DB_NAME = "dev";
    private static final String EJB_LAB_COLLECTION_NAME = "ejb_lab";

    private MongoClient mongoClient;
    private MongoDatabase database;

    public MongoClientBeen() {
    }

    @PostConstruct
    private void initDB() {
        mongoClient = new MongoClient(new MongoClientURI(URL));
        database = mongoClient.getDatabase(DB_NAME);
    }

    @PreDestroy
    private void closeDB() {
        mongoClient.close();
    }

    public MongoCollection<Document> getEjbLabCollection() {
        return database.getCollection(EJB_LAB_COLLECTION_NAME);
    }
}
