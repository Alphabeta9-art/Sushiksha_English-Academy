package com.cloudinary;

import com.cloudinary.utils.Rectangle;
import com.cloudinary.utils.StringUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes11.dex */
public class Coordinates implements Serializable {
    Collection<Rectangle> coordinates;

    public Coordinates() {
        this.coordinates = new ArrayList();
    }

    public Coordinates(Collection<Rectangle> coordinates) {
        this.coordinates = new ArrayList();
        this.coordinates = coordinates;
    }

    public Coordinates(int[] rect) {
        this.coordinates = new ArrayList();
        Collection<Rectangle> coordinates = new ArrayList<>();
        if (rect.length != 4) {
            throw new IllegalArgumentException("Must supply exactly 4 values for coordinates (x,y,width,height)");
        }
        coordinates.add(new Rectangle(rect[0], rect[1], rect[2], rect[3]));
        this.coordinates = coordinates;
    }

    public Coordinates(Rectangle rect) {
        this.coordinates = new ArrayList();
        Collection<Rectangle> coordinates = new ArrayList<>();
        coordinates.add(rect);
        this.coordinates = coordinates;
    }

    public Coordinates(String stringCoords) throws IllegalArgumentException {
        this.coordinates = new ArrayList();
        Collection<Rectangle> coordinates = new ArrayList<>();
        for (String stringRect : stringCoords.split("\\|")) {
            if (!StringUtils.isEmpty(stringRect)) {
                String[] elements = stringRect.split(",");
                if (elements.length != 4) {
                    throw new IllegalArgumentException(String.format("Must supply exactly 4 values for coordinates (x,y,width,height) %d supplied: %s", Integer.valueOf(elements.length), stringRect));
                }
                coordinates.add(new Rectangle(Integer.parseInt(elements[0]), Integer.parseInt(elements[1]), Integer.parseInt(elements[2]), Integer.parseInt(elements[3])));
            }
        }
        this.coordinates = coordinates;
    }

    public static Coordinates parseCoordinates(Object coordinates) throws IllegalArgumentException {
        if (coordinates instanceof Coordinates) {
            return (Coordinates) coordinates;
        }
        if (coordinates instanceof int[]) {
            return new Coordinates((int[]) coordinates);
        }
        if (coordinates instanceof Rectangle) {
            return new Coordinates((Rectangle) coordinates);
        }
        return new Coordinates(coordinates.toString());
    }

    public void addRect(Rectangle rect) {
        this.coordinates.add(rect);
    }

    public Collection<Rectangle> underlaying() {
        return this.coordinates;
    }

    public String toString() {
        ArrayList<String> rects = new ArrayList<>();
        for (Rectangle rect : this.coordinates) {
            rects.add(rect.x + "," + rect.y + "," + rect.width + "," + rect.height);
        }
        return StringUtils.join((List<String>) rects, "|");
    }
}
