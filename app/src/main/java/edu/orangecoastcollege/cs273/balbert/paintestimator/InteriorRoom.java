package edu.orangecoastcollege.cs273.balbert.paintestimator;

/**
 * Created by balbert on 9/19/2017.
 */

public class InteriorRoom {
    private int mDoors;
    private int mWindows;
    private float mHeight;
    private float mLength;
    private float mWidth;
    private static final int DOOR_AREA = 21;
    private static final int WINDOW_AREA = 16;
    private static final int SQ_FEET_PER_GALLON = 275;

    public InteriorRoom()
    {
        mDoors = 0;
        mWindows = 0;
        mHeight = 0.0f;
        mLength = 0.0f;
        mWidth = 0.0f;
    }

    public int doorAndWindowArea() { return (DOOR_AREA * mDoors) + (WINDOW_AREA * mWindows); }

    public float gallonsOfPaintRequired() { return 0; }

    public int getDoors() { return mDoors; }

    public int getWindows() { return mWindows; }

    public float getHeight() { return mHeight; }

    public float getLength() { return mLength; }

    public float getWidth() { return mWidth; }

    public void setDoors(int mDoors) {
        this.mDoors = mDoors;
    }

    public void setWindows(int windows) {
        this.mWindows = windows;
    }

    public void setHeight(float height) {
        this.mHeight = height;
    }

    public void setLength(float length) {
        this.mLength = length;
    }

    public void setWidth(float width) {
        this.mWidth = width;
    }

    public float totalSurfaceArea()
    {
        return 0.0f;
    }

    public float wallSurfaceArea()
    {
        return 0.0f;
    }
}
