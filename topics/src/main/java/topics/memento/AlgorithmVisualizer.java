package topics.memento;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * seee SelectionSort
 * @param <T> algorithm state type
 */
class AlgorithmVisualizer<T extends AlgorithmState> {
    private final Algorithm<T> algorithm;
    private final Deque<T> states = new ArrayDeque<>();

    AlgorithmVisualizer(Algorithm<T> algorithm) {
        this.algorithm = algorithm;
    }

    public void nextStep() {
        if (algorithm.hasNextStep()) {
            states.push(algorithm.getState());
            algorithm.nextStep();
        }
    }

    public void prevStep() {
        if (!states.isEmpty()) {
            algorithm.setState(states.pop());
        }
    }

    public void showCurrentStep() {
        System.out.println(algorithm);
    }
}
