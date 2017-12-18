package me.faizaand.simulator;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Contains the logic for the actual simulation.
 */
public class Simulation {

    private RandomSet randomSet;
    private List<Outcome> possibleOutcomes;
    private List<Repetition> repetitions;

    public Simulation(RandomSet randomSet) {
        this.randomSet = randomSet;
        this.possibleOutcomes = new LinkedList<>(); // LL so we can keep the order
        this.repetitions = new LinkedList<>();
    }

    /**
     * Runs the simulation with a certain number of repetitions.
     * @param count the number of repetitions to run the simulation.
     */
    public void run(int count) {
        for (int i = 0; i < count; i++) repetitions.add(runRepetition(i));
    }

    /**
     * Runs a specific repetition. This is for internal class use.
     * @param repetitionNumber Which repetition this is (for debug logging).
     * @return the Repetition object that was created.
     */
    private Repetition runRepetition(int repetitionNumber) {
        // Housekeeping, get our random digits set up
        int[] randoms = randomSet.generate();
        Repetition repetition = new Repetition();

        // Run outcomes on each digit and increment each outcome's frequency if necessary
        for (int random : randoms)
            possibleOutcomes.forEach(outcome -> {
                if (outcome.getCheck().test(random))
                    repetition.add(outcome);
            });

        // Done with the repetition! Now for some verbose logging
        if(Main.VERBOSE) {
            StringBuilder builder = new StringBuilder("Repetition #" + repetitionNumber + " -> {");
            repetition.getOutcomeFrequencies().forEach((outcome, frequency) -> {
                builder.append("\n\t" + outcome.getName() + "-> " + frequency + " / " + randomSet.getAmount());
            });
            builder.append("\n}\n");
            System.out.println(builder.toString());
        }

        // Boom, that wasn't so hard, was it?
        return repetition;
    }

    public RandomSet getRandomSet() {
        return randomSet;
    }

    public List<Outcome> getPossibleOutcomes() {
        return possibleOutcomes;
    }

    public void addPossibleOutcome(Outcome outcome) {
        possibleOutcomes.add(outcome);
    }

    public List<Repetition> getRepetitions() {
        return repetitions;
    }

    /**
     * Calculates the average frequencies of each outcome over <b>all</b> repetitions.
     * @return A map of each outcome to its average frequency (decimal value).
     */
    public Map<Outcome, Double> calculateAverageFrequencies() {
        Map<Outcome, Double> ret = new HashMap<>();

        // Average = sum / total amount

        // Go through each repetition and add its frequencies for each outcome together
        for (Repetition repetition : repetitions) {
            for (Map.Entry<Outcome, Integer> entry : repetition.getOutcomeFrequencies().entrySet()) {
                Outcome outcome = entry.getKey();
                int frequency = entry.getValue();

                ret.putIfAbsent(outcome, 0.0D);
                ret.put(outcome, ret.get(outcome) + frequency);
            }
        }

        // Now, divide each outcome's sum frequency by the amount of repetitions to get the average!
        for(Outcome finalKey : ret.keySet()) {
            ret.put(finalKey, ret.get(finalKey) / (double) repetitions.size());
        }

        // Viola.
        return ret;

    }

}
