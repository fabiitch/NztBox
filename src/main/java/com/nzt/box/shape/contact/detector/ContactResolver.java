package com.nzt.box.shape.contact.detector;

import com.nzt.box.shape.CircleShape;
import com.nzt.box.shape.PolygonShape;
import com.nzt.box.shape.contact.detector.impl.CircleContact;
import com.nzt.box.shape.contact.detector.impl.PolygonContact;

public class ContactResolver {
    public static CircleContact circleContact = new CircleContact();
    public static PolygonContact polygonContact = new PolygonContact();


    public static ShapeContact get(CircleShape circleShape) {
        circleContact.myCircle = circleShape.shape;
        return circleContact;
    }

    public static ShapeContact get(PolygonShape polygonShape) {
        polygonContact.myPolygon = polygonShape.shape;
        return polygonContact;
//        circleContact.
    }
}
