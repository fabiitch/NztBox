package com.nzt.box.profiler;

import com.badlogic.gdx.math.FloatCounter;
import com.nzt.gdx.counter.IntCounter;

public class WorldProfiler {

    private long timeStartStep, timeStartIteration;
    public FloatCounter timerStep, timerIteration;
    public IntCounter iterationPerStep;

    public ProfilerValue bodyMove, bodyContactCheck, fixtureContactCheck;

    public ProfilerValue beginContact, endContact;

    public ProfilerValue testContact, replaceContact, collisionData, computeContact;


    public WorldProfiler() {
        timerStep = new FloatCounter(0);
        timerIteration = new FloatCounter(0);

        iterationPerStep = new IntCounter();

        bodyMove = new ProfilerValue();
        bodyContactCheck = new ProfilerValue();
        fixtureContactCheck = new ProfilerValue();

        beginContact = new ProfilerValue();
        endContact = new ProfilerValue();

        testContact = new ProfilerValue();
        replaceContact = new ProfilerValue();
        collisionData = new ProfilerValue();
        computeContact = new ProfilerValue();
    }

    public void startStep() {
        timeStartStep = System.currentTimeMillis();
    }

    public void endStep() {
        iterationPerStep.addCurrent();

        bodyMove.endStep();
        bodyContactCheck.endStep();
        fixtureContactCheck.endStep();

        beginContact.endStep();
        endContact.endStep();

        testContact.endStep();
        replaceContact.endStep();
        collisionData.endStep();
        computeContact.endStep();

        long endTime = System.currentTimeMillis();
        timerStep.put(endTime - timeStartStep);
    }

    public void startIteration() {
        timeStartIteration = System.currentTimeMillis();
    }

    public void endIteration() {
        iterationPerStep.current++;

        bodyMove.endIteration();
        bodyContactCheck.endIteration();
        fixtureContactCheck.endIteration();

        beginContact.endIteration();
        endContact.endIteration();

        testContact.endIteration();
        replaceContact.endIteration();
        collisionData.endIteration();
        computeContact.endIteration();
        long endTime = System.currentTimeMillis();
        timerIteration.put(endTime - timeStartIteration);
    }


}
