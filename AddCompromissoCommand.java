import javax.swing.*;
import java.util.Date;
import java.util.concurrent.ScheduledExecutorService;

class AddCompromissoCommand implements Command {
    private final Agenda agenda;
    private final String name;
    private final Date date;
    private final ScheduledExecutorService scheduler;

    public AddCompromissoCommand(Agenda agenda, String name, Date date, ScheduledExecutorService scheduler) {
        this.agenda = agenda;
        this.name = name;
        this.date = date;
        this.scheduler = scheduler;
    }

    @Override
    public void execute() {
        agenda.addCompromisso(name, date, scheduler);
        JOptionPane.showMessageDialog(null, "Compromisso \"" + name + "\" agendado para " + date, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }
}
