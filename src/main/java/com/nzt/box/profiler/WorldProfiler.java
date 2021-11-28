package com.nzt.box.profiler;

import com.badlogic.gdx.math.FloatCounter;
import com.badlogic.gdx.utils.Array;
import com.nzt.gdx.counter.IntCounter;

public class WorldProfiler {

    private long timeStartStep, timeStartIteration;
    public FloatCounter timerStep, timerIteration;
    public IntCounter iterationPerStep;

    public ProfilerValue bodyMove, bodyContactCheck, fixtureContactCheck;

    public ProfilerValue beginContact, endContact;

    public ProfilerValue testContact, replaceContact, collisionData, computeContact;

    private Array<ProfilerValue> profilerValues;

    public WorldProfiler() {
        timerStep = new FloatCounter(0);
        timerIteration = new FloatCounter(0);

        iterationPerStep = new IntCounter();

        profilerValues = new Array<>(false, 9);

        bodyMove = new ProfilerValue();
        bodyContactCheck = new ProfilerValue();
        fixtureContactCheck = new ProfilerValue();
        profilerValues.add(bodyMove, bodyContactCheck, fixtureContactCheck);

        beginContact = new ProfilerValue();
        endContact = new ProfilerValue();
        profilerValues.add(beginContact, endContact);

        testContact = new ProfilerValue();
        replaceContact = new ProfilerValue();
        collisionData = new ProfilerValue();
        computeContact = new ProfilerValue();
        profilerValues.add(testContact, replaceContact, collisionData, computeContact);
    }

    public void startStep() {
        iterationPerStep.current = 0;
        timeStartStep = System.currentTimeMillis();

        for (int i = 0, n = profilerValues.size; i < n; i++)
            profilerValues.get(i).startStep();
    }

    public void endStep() {
        iterationPerStep.addCurrent();

        for (int i = 0, n = profilerValues.size; i < n; i++)
            profilerValues.get(i).endStep();

        long endTime = System.currentTimeMillis();
        timerStep.put(endTime - timeStartStep);
    }

    public void startIteration() {
        timeStartIteration = System.currentTimeMillis();
        iterationPerStep.current++;

        for (int i = 0, n = profilerValues.size; i < n; i++)
            profilerValues.get(i).startIteration();
    }

    public void endIteration() {
        for (int i = 0, n = profilerValues.size; i < n; i++)
            profilerValues.get(i).endIteration();
        long endTime = System.currentTimeMillis();
        timerIteration.put(endTime - timeStartIteration);
    }


}
