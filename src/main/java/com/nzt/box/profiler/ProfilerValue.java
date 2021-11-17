package com.nzt.box.profiler;

import com.nzt.gdx.counter.IntCounter;

public class ProfilerValue {

    public IntCounter step;
    public IntCounter iteration;


    public ProfilerValue() {
        this.step = new IntCounter();
        this.iteration = new IntCounter();
    }

    public void inc() {
        iteration.current++;
    }

    public void endIteration() {
        this.step.current += iteration.current;
        this.iteration.addCurrent();
    }

    public void endStep() {
        this.step.addCurrent();
    }

    public String toString(String fieldName) {
        return fieldName + "{" +
                "step=" + step.toString() +
                ", iteration=" + iteration.toString() +
                '}';
    }
    @Override
    public String toString() {
        return "ProfilerValue{" +
                "step=" + step.toString() +
                ", iteration=" + iteration.toString() +
                '}';
    }
}
