class Notifier implements Observer {
    private final Command command;

    public Notifier(Command command) {
        this.command = command;
    }

    @Override
    public void update(String message) {
        command.execute();
    }
}
