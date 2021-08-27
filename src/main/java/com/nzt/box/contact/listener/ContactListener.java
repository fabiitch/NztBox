package com.nzt.box.contact.listener;

import com.nzt.box.contact.data.ContactFixture;

public abstract class ContactListener {
    public abstract void beginContact(ContactFixture contactFixture);

    public abstract void endContact(ContactFixture contactFixture);

    public abstract void continueContact(ContactFixture contactFixture);

    public abstract void preSolve(ContactFixture contactFixture);

    public abstract void postSolve(ContactFixture contactFixture);
}
