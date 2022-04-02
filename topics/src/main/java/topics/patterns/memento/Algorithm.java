package topics.patterns.memento;

interface Algorithm<S extends AlgorithmState> {

    boolean hasNextStep();

    void nextStep();

    S getState();

    void setState(S state);
}
