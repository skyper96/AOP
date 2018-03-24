import java.util.logging.Logger;

public class Reader implements Observer {

    private String name;
    private Subject Topic;
    private static final Logger LOGGER = Logger.getLogger(Reader.class.getName());

    Reader(String worker_name){
        this.name = worker_name;
    }
    @Override
    public void update() {
        String msg = (String) Topic.getUpdate(this);
        if(msg == null){
            LOGGER.info("No topics.");
        }
        else
            LOGGER.info(name +  ": Aware of a topic:" + msg);
    }

    @Override
    public void setSubject(Subject sub) {
        this.Topic = sub;
    }
}
