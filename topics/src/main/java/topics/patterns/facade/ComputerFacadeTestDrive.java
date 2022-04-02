package topics.patterns.facade;

class ComputerFacadeTestDrive {
    public static void main(String[] args) {
        /* Your subsystems */

        ComputerFacade computerFacade = new ComputerFacade(new Processor(), new Monitor(), new Keyboard());

        computerFacade.turnOnComputer();
        computerFacade.turnOffComputer();
    }
}
