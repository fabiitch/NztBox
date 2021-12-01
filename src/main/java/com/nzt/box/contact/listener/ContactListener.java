package com.nzt.box.contact.listener;

import com.nzt.box.contact.data.ContactFixture;

public interface ContactListener {
    void preSolve(ContactFixture contactFixture);

    void beginContact(ContactFixture contactFixture);

    void endContact(ContactFixture contactFixture);

    void continueContact(ContactFixture contactFixture);

}
