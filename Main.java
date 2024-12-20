import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Main {
    private final Agenda agenda;
    private final ScheduledExecutorService agendador;
    private final DateParserStrategy dateParser;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().run());
    }

    public Main() {
        this.agenda = new Agenda();
        this.agendador = Executors.newScheduledThreadPool(1);
        this.dateParser = new SimpleDateParser();
    }

    public void run() {
        JFrame frame = new JFrame("Agenda de Compromissos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 700);
        frame.setLayout(new BorderLayout(10, 10));

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel nameLabel = new JLabel("Nome do compromisso:");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        JTextField nameField = new JTextField();
        nameField.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel dateLabel = new JLabel("Data e hora (dd/MM/yyyy HH:mm):");
        dateLabel.setFont(new Font("Arial", Font.BOLD, 14));
        JTextField dateField = new JTextField();
        dateField.setFont(new Font("Arial", Font.PLAIN, 14));
        

        JButton addButton = new JButton("Adicionar Compromisso");
        addButton.setFont(new Font("Arial", Font.BOLD, 14));
        addButton.setBackground(Color.LIGHT_GRAY);
        addButton.setForeground(Color.WHITE);
        addButton.addActionListener(e -> {
            String name = nameField.getText();
            String dateInput = dateField.getText();
            try {
                Date date = dateParser.parse(dateInput);
                agenda.addCompromisso(name, date, agendador);
                Notifier notifier = new Notifier(new NotificationCommand("Compromisso: " + name));
                agenda.addObserver(notifier);
                JOptionPane.showMessageDialog(frame, "Compromisso adicionado com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(frame, "Data invalida. Use o formato dd/MM/yyyy HH:mm.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(dateLabel);
        panel.add(dateField);
        panel.add(new JLabel());
        panel.add(addButton);

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
