package com.nzt.box.contact.detector;

import com.nzt.box.contact.detector.impl.PolygonContact;
import com.nzt.box.contact.detector.impl.RectangleContact;
import com.nzt.box.shape.CircleShape;
import com.nzt.box.contact.detector.impl.CircleContact;
import com.nzt.box.shape.PolygonShape;
import com.nzt.box.shape.RectangleShape;

public class ContactResolver {
    public static CircleContact circleContact = new CircleContact();
    public static RectangleContact rectangleContact = new RectangleContact();
    public static PolygonContact polygonContact = new PolygonContact();


    public static ShapeContact get(CircleShape circleShape) {
        circleContact.myCircle = circleShape.shape;
        return circleContact;
    }

    public static ShapeContact get(RectangleShape rectangleShape) {
        rectangleContact.myRectangle = rectangleShape.shape;
        return rectangleContact;
    }

    public static ShapeContact get(PolygonShape polygonShape) {
        polygonContact.myPolygon = polygonShape.shape;
        return polygonContact;
    }
}
