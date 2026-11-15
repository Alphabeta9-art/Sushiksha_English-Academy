package com.cloudinary.android.uploadwidget.model;

import android.graphics.Point;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes11.dex */
public class CropPoints implements Parcelable {
    public static final Parcelable.Creator<CropPoints> CREATOR = new Parcelable.Creator<CropPoints>() { // from class: com.cloudinary.android.uploadwidget.model.CropPoints.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CropPoints createFromParcel(Parcel in) {
            return new CropPoints(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CropPoints[] newArray(int size) {
            return new CropPoints[size];
        }
    };
    private Point p1;
    private Point p2;

    public CropPoints(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public void setPoint1(Point p1) {
        this.p1 = p1;
    }

    public Point getPoint1() {
        return this.p1;
    }

    public void setPoint2(Point p2) {
        this.p2 = p2;
    }

    public Point getPoint2() {
        return this.p2;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.p1, flags);
        dest.writeParcelable(this.p2, flags);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected CropPoints(Parcel in) {
        this.p1 = (Point) in.readParcelable(Point.class.getClassLoader());
        this.p2 = (Point) in.readParcelable(Point.class.getClassLoader());
    }
}
