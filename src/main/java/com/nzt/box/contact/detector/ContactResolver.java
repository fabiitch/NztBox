package com.nzt.box.contact.detector;

import com.nzt.box.contact.detector.impl.ContactCircle;
import com.nzt.box.contact.detector.impl.ContactPolygon;
import com.nzt.box.contact.detector.impl.ContactRectangle;
import com.nzt.box.shape.CircleShape;
import com.nzt.box.shape.PolygonShape;
import com.nzt.box.shape.RectangleShape;

public class ContactResolver {
    public ContactCircle contactCircle = new ContactCircle();
    public ContactRectangle contactRectangle = new ContactRectangle();
    public ContactPolygon contactPolygon = new ContactPolygon();


    public ShapeContact get(CircleShape circleShape) {
        contactCircle.myCircle = circleShape.shape;
        return contactCircle;
    }

    public ShapeContact get(RectangleShape rectangleShape) {
        contactRectangle.myRectangle = rectangleShape.shape;
        return contactRectangle;
    }

    public ShapeContact get(PolygonShape polygonShape) {
        contactPolygon.myPolygon = polygonShape.shape;
        return contactPolygon;
    }
}
