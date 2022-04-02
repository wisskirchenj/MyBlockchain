package topics.patterns.memento;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Visualization of state
 * This time your team decided to make a prototype of an algorithm visualization application. For starters, your teammates
 * created a class called SelectionSort to run a selection sort algorithm and a class called AlgorithmVisualizer to control
 * the algorithm execution step by step both forward and backward. All visualization logic is finalized but one required
 * class and some methods in SelectionSort have not been implemented yet, and this will be your task.
 * Look carefully at the SelectionSort class and decide which fields represent its state, then implement the
 * SelectionSort.SortState class to store snapshots of the SelectionSort state, and after that implement the getState
 * and setState methods in SelectionSort to actually save and restore its state. That's it!
 * @param <T> algorithm state
 */
class SelectionSort<T extends Comparable<T>> implements Algorithm<SelectionSort.SortState<T>> {
    private T[] array;
    private int currentIndex = 0;
    private int comparedIndex = 0;
    private int currentMinIndex = 0;

    SelectionSort(T[] array) {
        this.array = array.clone();
    }

    @Override
    public void nextStep() {
        if (comparedIndex == array.length - 1 && currentIndex != currentMinIndex) {
            T tmp = array[currentIndex];
            array[currentIndex] = array[currentMinIndex];
            array[currentMinIndex] = tmp;
            currentMinIndex = currentIndex;
        } else if (comparedIndex == array.length - 1) {
            currentIndex++;
            if (currentIndex < array.length - 1) {
                comparedIndex = currentIndex + 1;
            }
            currentMinIndex = array[currentIndex]
                    .compareTo(array[comparedIndex]) > 0 ? comparedIndex : currentIndex;
        } else {
            comparedIndex++;
        }

        if (array[comparedIndex].compareTo(array[currentMinIndex]) < 0) {
            currentMinIndex = comparedIndex;
        }
    }

    @Override
    public boolean hasNextStep() {
        return currentIndex < array.length - 1;
    }

    @Override
    public String toString() {
        return IntStream.range(0, array.length).mapToObj(i -> {
            String s = String.valueOf(array[i]);
            if (i == currentIndex) {
                s = "{" + s + "}"; // final place for min item in range
            }
            if (i == comparedIndex) {
                s = "[" + s + "]"; // candidate for min item
            }
            if (i == currentMinIndex) {
                s = "(" + s + ")"; // current min item in range
            }
            return s;
        }).collect(Collectors.joining(" "));
    }

    @Override
    public void setState(SortState<T> state) {
        array = state.array;
        currentIndex = state.currentIndex;
        comparedIndex = state.comparedIndex;
        currentMinIndex = state.currentMinIndex;
    }

    @Override
    public SortState<T> getState() {
        return new SortState<>(array, currentIndex, comparedIndex, currentMinIndex);
    }

    static class SortState<T> implements AlgorithmState {
        private final T[] array;
        private final int currentIndex;
        private final int comparedIndex;
        private final int currentMinIndex;

        public SortState(T[] array, int currentIndex, int comparedIndex, int currentMinIndex) {
            this.array = array.clone();
            this.currentIndex = currentIndex;
            this.comparedIndex = comparedIndex;
            this.currentMinIndex = currentMinIndex;
        }
    }
}

