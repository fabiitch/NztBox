package com.nzt.box.shape.contact;

import com.nzt.box.shape.CircleShape;
import com.nzt.box.shape.PolygonShape;
import com.nzt.box.shape.contact.impl.CircleContact;
import com.nzt.box.shape.contact.impl.PolygonContact;

public class ContactResolver {
    public static CircleContact circleContact = new CircleContact();
    public static PolygonContact polygonContact = new PolygonContact();


    public static ShapeContactVisitor get(CircleShape circleShape) {
        circleContact.myCircle = circleShape.shape;
        return circleContact;
    }

    public static ShapeContactVisitor get(PolygonShape polygonShape) {
        polygonContact.myPolygon = polygonShape.shape;
        return polygonContact;
//        circleContact.
    }
}
