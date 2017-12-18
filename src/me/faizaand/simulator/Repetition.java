package me.faizaand.simulator;

import java.util.HashMap;
import java.util.Map;

/**
 * Stores the frequency of each outcome in a simulation, for each repetition.
 */
public class Repetition {

    private Map<Outcome, Integer> outcomeFrequencies;

    public Repetition() {
        this.outcomeFrequencies = new HashMap<>();
    }

    public void add(Outcome o) {
        outcomeFrequencies.putIfAbsent(o, 0);
        outcomeFrequencies.compute(o, (outcome, freq) -> freq + 1);
    }

    public Map<Outcome, Integer> getOutcomeFrequencies() {
        return outcomeFrequencies;
    }

}
