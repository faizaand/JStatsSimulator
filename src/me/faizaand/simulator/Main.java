package me.faizaand.simulator;

import java.text.DecimalFormat;
import java.util.Map;

public class Main {

    public static final boolean VERBOSE = false;
    private static final DecimalFormat PERCENT_FORMAT = new DecimalFormat("##.##%");

    public static void main(String[] args) {
        new Main();
    }

    private Main() {
        // We're gonna generate 500 two-digit random numbers
        RandomSet randomSet = new RandomSet(0, 99, 500);
        Simulation simulation = new Simulation(randomSet);

        // Let #2 represent a home run, so two consecutive home runs would be 22
        simulation.addPossibleOutcome(new Outcome("hits two home runs", (rand) -> rand == 22));

        // Run it 10 times
        simulation.run(1000);

        Map<Outcome, Double> outcomeFrequencies = simulation.calculateAverageFrequencies();
        outcomeFrequencies.forEach((outcome, freq) -> System.out.printf("%s -> %s", outcome.getName(), toPercentage(freq, randomSet.getAmount())));
    }

    private String toPercentage(double numerator, double denominator) {
        return PERCENT_FORMAT.format(numerator / denominator);
    }


}
