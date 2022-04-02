package topics.patterns.command.remote;

class Controller {
    private Command command;

    void setCommand(Command command) {
        this.command = command;
    }

    void executeCommand() {
        command.execute();
    }
}
