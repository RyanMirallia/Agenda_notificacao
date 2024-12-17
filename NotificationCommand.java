import javax.swing.*;

class NotificationCommand implements Command {
    private final String message;

    public NotificationCommand(String message) {
        this.message = message;
    }

    @Override
    public void execute() {
        JOptionPane.showMessageDialog(null, message, "Notification", JOptionPane.INFORMATION_MESSAGE);
    }
}
