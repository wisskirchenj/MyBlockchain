package topics.patterns.memento.twodimarray;

class Caretaker {
    private final Originator originator;
    private Originator.Memento snapshot = null;

    public Caretaker(Originator originator) {
        this.originator = originator;
    }

    public void save() {
        snapshot = originator.getMemento();
    }

    public void restore() {
        if (snapshot != null) {
            originator.setMemento(snapshot);
        }
    }
}
