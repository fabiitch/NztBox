package com.nzt.box.test.api.contact.listener;

import com.nzt.box.test.api.contact.Base2BodyContactTest;
import com.nzt.box.test.api.mock.ContactListenerMock;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ContactListener3BodyTest extends Base2BodyContactTest {

    @Test
    public void shouldCallMethod() {
        ContactListenerMock mock = Mockito.mock(ContactListenerMock.class);
        world.contactListener = mock;
        world.step(0.01f);
        verify(mock, times(1)).preSolve(any()); //le replace block
        verify(mock, times(1)).beginContact(any());


        bodyA.setPosition(100, 100);
        world.step(0.01f);
        verify(mock, times(1)).endContact(any());
    }
}
