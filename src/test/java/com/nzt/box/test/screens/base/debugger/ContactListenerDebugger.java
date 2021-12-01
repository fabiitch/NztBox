package com.nzt.box.test.screens.base.debugger;

import com.nzt.box.contact.data.ContactFixture;
import com.nzt.box.contact.listener.ContactListener;

public class ContactListenerDebugger implements ContactListener {

    public ContactListener contactListener;

    @Override
    public void preSolve(ContactFixture contactFixture) {
        if (contactListener != null)
            contactListener.preSolve(contactFixture);
    }

    @Override
    public void beginContact(ContactFixture contactFixture) {
        if (contactListener != null)
            contactListener.beginContact(contactFixture);
    }

    @Override
    public void endContact(ContactFixture contactFixture) {
        if (contactListener != null)
            contactListener.endContact(contactFixture);
    }

    @Override
    public void continueContact(ContactFixture contactFixture) {
        if (contactListener != null)
            contactListener.continueContact(contactFixture);
    }
}
