import java.util.ArrayList;
import java.util.List;

public class TimerSubject {
    private static TimerSubject uniqueInstance = null;
    private static List<ITimerObserver> observers = new ArrayList<>();
    private int minutes =0;

    private TimerSubject() {
    }

    public static TimerSubject instance() {
        if (null == uniqueInstance) {
            uniqueInstance = new TimerSubject();
        }
        return uniqueInstance;
    }

    public void attachObserver(ITimerObserver observer) {
        this.observers.add(observer);
    }
    public void detachObserver(ITimerObserver observer) {
        this.observers.remove(observer);
    }
    public void notifySubjects() {
        minutes+=1;
        for (ITimerObserver observer : observers) {
            observer.timeElapsed(minutes);
        }
    }
}
