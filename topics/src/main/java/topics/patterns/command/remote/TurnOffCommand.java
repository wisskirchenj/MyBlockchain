package topics.patterns.command.remote;

class TurnOffCommand implements Command {
    private TV tv;

    TurnOffCommand(TV tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        tv.turnOff();
    }
}
