package topics.memento.list;

import java.util.ArrayList;
import java.util.List;

/**
 * Mutations
 * This task is pretty simple: you must finish the implementation of the Memento pattern. The class named Originator
 * has only one field that contains a List of integer numbers. The class named Caretaker implements the logic of saving
 * the state of an instance of Originator to a variable named snapshot and restore the state of the Originator instance
 * from snapshot. The only part missing is the Memento component.
 * Your task is to create a class that will embody a snapshot of the state of the Originator class and implement the
 * logic of the getMemento and setMemento methods in Originator. You may use any name for your class but do not forget
 * to specify the proper type of the snapshot variable in Caretaker, as well as the return type of the getMemento method
 * and the type of the parameter of the setMemento method in Originator.
 */
class Originator {
    private List<Integer> numbers = new ArrayList<>();

    public void addNumber(Integer number) {
        numbers.add(number);
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public List<Integer> getMemento() {
        return new ArrayList<>(numbers);
    }

    public void setMemento(List<Integer> memento) {
        numbers = memento;
    }

}

