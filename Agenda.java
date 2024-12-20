import javax.swing.*;
import java.util.*;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


class Agenda implements Subject {
    private final List<Observer> observers = new ArrayList<>();

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
    
    public void addCompromisso(String name, Date date, ScheduledExecutorService scheduler) {
        long delay = date.getTime() - System.currentTimeMillis();
        if (delay > 0) {
            scheduler.schedule(() -> notifyObservers(name), delay, TimeUnit.MILLISECONDS);
        } else {
            JOptionPane.showMessageDialog(null, "data e hora precisam estar no futuro", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
