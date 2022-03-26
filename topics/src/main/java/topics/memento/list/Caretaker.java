package topics.memento.list;

import java.util.List;

class Caretaker {
    private final Originator originator;
    private List<Integer> snapshot = null;

    Caretaker(Originator originator) {
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
