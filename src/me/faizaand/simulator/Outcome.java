package me.faizaand.simulator;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * An outcome is a named, open-ended check on a specific value of the random set.
 */
public class Outcome {

    private String name;
    private Predicate<Integer> check;

    public Outcome(String name, Predicate<Integer> check) {
        this.name = name;
        this.check = check;
    }

    public String getName() {
        return name;
    }

    public Predicate<Integer> getCheck() {
        return check;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Outcome) && ((Outcome) obj).getName().equals(getName());
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

}
