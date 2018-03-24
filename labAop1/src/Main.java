import db.DbContract;
import db.PostgresHelper;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        //create subject
        Topic topic = new Topic();
        Topic topic2 = new Topic();

        //create observers
        Observer reader1 = new Reader("Reader1");
        Observer reader2 = new Reader("Reader2");
        Observer reader3 = new Reader("Reader3");

        //register observers to the subject
        topic.register(reader1);
        topic.register(reader2);
        topic.register(reader3);

        topic2.register(reader2);
        topic2.register(reader3);

        //attach observer to subject
        reader1.setSubject(topic);
        reader2.setSubject(topic);
        reader3.setSubject(topic);

        reader2.setSubject(topic2);
        reader3.setSubject(topic2);

        //check if any update is available
        reader1.update();

        //now send message to subject
        topic.postMessage("New Topic posted");
        topic2.postMessage("PSD wants to go down. Let's help them");

        PostgresHelper client = new PostgresHelper(
                DbContract.HOST,
                DbContract.DB_NAME,
                DbContract.USERNAME,
                DbContract.PASSWORD);
        try {
            if (client.connect()) {
                System.out.println("DB connected");
                
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
