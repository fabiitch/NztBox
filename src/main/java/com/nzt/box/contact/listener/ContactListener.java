package com.nzt.box.contact.listener;

import com.nzt.box.contact.ContactBody;

public abstract class ContactListener {
    public abstract void beginContact(ContactBody contactBody);

    public abstract void endContact(ContactBody contactBody);

    public abstract void continusContact(ContactBody contactBody);

    public abstract void preSolve(ContactBody contactBody);

    public abstract void postSolve(ContactBody contactBody);
}
