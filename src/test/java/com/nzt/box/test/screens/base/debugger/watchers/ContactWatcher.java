package com.nzt.box.test.screens.base.debugger.watchers;

public class ContactWatcher {

    public boolean breakAtBegin = true;
    public boolean breakAtEnd = true;
    public boolean breakAtContinue;

    public ContactWatcher() {
    }

    public ContactWatcher(boolean breakAtBegin, boolean breakAtEnd, boolean breakAtContinue) {
        this.breakAtBegin = breakAtBegin;
        this.breakAtEnd = breakAtEnd;
        this.breakAtContinue = breakAtContinue;
    }
}
