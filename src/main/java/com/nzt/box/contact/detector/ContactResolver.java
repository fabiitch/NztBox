package com.nzt.box.contact.detector;

import com.nzt.box.contact.detector.impl.ContactPolygon;
import com.nzt.box.contact.detector.impl.ContactRectangle;
import com.nzt.box.shape.CircleShape;
import com.nzt.box.contact.detector.impl.ContactCircle;
import com.nzt.box.shape.PolygonShape;
import com.nzt.box.shape.RectangleShape;

public class ContactResolver {
    public static ContactCircle contactCircle = new ContactCircle();
    public static ContactRectangle contactRectangle = new ContactRectangle();
    public static ContactPolygon contactPolygon = new ContactPolygon();


    public static ShapeContact get(CircleShape circleShape) {
        contactCircle.myCircle = circleShape.shape;
        return contactCircle;
    }

    public static ShapeContact get(RectangleShape rectangleShape) {
        contactRectangle.myRectangle = rectangleShape.shape;
        return contactRectangle;
    }

    public static ShapeContact get(PolygonShape polygonShape) {
        contactPolygon.myPolygon = polygonShape.shape;
        return contactPolygon;
    }
}
