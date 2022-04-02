package topics.patterns.memento.twodimarray;

import java.util.Arrays;

/**
 * Reference type
 * Creating snapshots of the internal state of an object is pretty straightforward, but sometimes its fields may be
 * of reference types. That's why you should be careful not to overlook such situations. This time your task is to
 * implement the Memento class which acts as a storage for the single field of the Originator class, as well as the
 * corresponding methods to get and restore the state. The Caretaker logic is already implemented.
 */
class Originator {
    private int[][] array;

    public Originator(int[][] array) {
        this.array = array.clone();
    }

    public void setNumber(int position, int number) {
        array[position / array[0].length][position % array[0].length] = number;
    }

    public int[][] getArray() {
        return array.clone();
    }

    public Memento getMemento() {
        return new Memento(getArray());
    }

    public void setMemento(Memento memento) {
        array = memento.array;
    }

    static class Memento {
        private final int[][] array;

        private Memento(int[][] array) {
            this.array = array;
            Arrays.setAll(this.array, i -> array[i].clone());
        }
    }
}

