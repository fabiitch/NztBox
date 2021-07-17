package com.nzt.box.shape.contact.listener;

import com.nzt.box.shape.contact.ContactBody;

public abstract class ContactListener {

    public abstract void beginContact(ContactBody contactBody);

    public abstract void endContact(ContactBody contactBody);

    public abstract void continusContact(ContactBody contactBody);
}
