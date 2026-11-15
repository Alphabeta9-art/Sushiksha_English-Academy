package com.cloudinary.utils;

import java.io.Serializable;

/* JADX INFO: loaded from: classes11.dex */
public class Rectangle implements Serializable {
    public int height;
    public int width;
    public int x;
    public int y;

    public Rectangle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}
