import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Main {
    private final Agenda agenda;
    private final ScheduledExecutorService scheduler;
    private final DateParserStrategy dateParser;

    public Main() {
        this.agenda = new Agenda();
        this.scheduler = Executors.newScheduledThreadPool(1);
        this.dateParser = new SimpleDateParser();
    }

    public void run() {
        JFrame frame = new JFrame("Agenda de Compromissos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new GridLayout(3, 2));

        JLabel nameLabel = new JLabel("Nome do compromisso:");
        JTextField nameField = new JTextField();

        JLabel dateLabel = new JLabel("Data e hora (dd/MM/yyyy HH:mm):");
        JTextField dateField = new JTextField();

        JButton addButton = new JButton("Adicionar Compromisso");
        addButton.addActionListener(e -> {
            String name = nameField.getText();
            String dateInput = dateField.getText();
            try {
                Date date = dateParser.parse(dateInput);
                agenda.addCompromisso(name, date, scheduler);
                Notifier notifier = new Notifier(new NotificationCommand("Compromisso: " + name));
                agenda.addObserver(notifier);
                JOptionPane.showMessageDialog(frame, "Compromisso adicionado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(frame, "Data inválida. Use o formato dd/MM/yyyy HH:mm.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.add(nameLabel);
        frame.add(nameField);
        frame.add(dateLabel);
        frame.add(dateField);
        frame.add(new JLabel()); // Empty space
        frame.add(addButton);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().run());
    }
}
