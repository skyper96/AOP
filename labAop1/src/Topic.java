import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Topic implements Subject {

    private List<Observer> observers;
    private String message;
    private boolean changed;
    private final Object MUTEX = new Object();
    private static final Logger LOGGER = Logger.getLogger(Topic.class.getName());

    Topic(){
        this.observers=new ArrayList<>();
    }
    @Override
    public void register(Observer obj) {

        if(obj == null) throw new NullPointerException("Null Observer");
        synchronized (MUTEX) {
            if(!observers.contains(obj)) observers.add(obj);
        }
    }

    @Override
    public void unregister(Observer obj) {
        synchronized (MUTEX) {
            observers.remove(obj);
        }
    }

    @Override
    public void notifyObservers() {
        List<Observer> localObservers = null;
        //synchronization is used to make sure any observer registered after message is received is not notified
        synchronized (MUTEX) {
            if (!changed)
                return;
            localObservers = new ArrayList<>(this.observers);
            this.changed=false;
        }
        for (Observer obj : localObservers) {
            obj.update();
        }
    }

    @Override
    public Object getUpdate(Observer obj) {
        return this.message;
    }

    //method to post message to the topic
    void postMessage(String msg){
        System.out.println("New Topic:"+msg);
        this.message=msg;
        this.changed=true;
        notifyObservers();
    }
}
