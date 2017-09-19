package edu.orangecoastcollege.cs273.balbert.paintestimator;

/**
 * Created by balbert on 9/19/2017.
 */

public class InteriorRoom {
    private float mHeight;
    private float mLength;
    private float mWidth;
    private int mDoors;
    private int mWindows;
    private static final float DOOR_AREA = 21.0f;
    private static final float WINDOW_AREA = 16.0f;
    private static final float SQ_FEET_PER_GALLON = 275.0f;

    public InteriorRoom()
    {
        mDoors = 0;
        mWindows = 0;
        mHeight = 0.0f;
        mLength = 0.0f;
        mWidth = 0.0f;
    }

    public int getDoors() { return mDoors; }

    public int getWindows() { return mWindows; }

    public float getHeight() { return mHeight; }

    public float getLength() { return mLength; }

    public float getWidth() { return mWidth; }

    public void setDoors(int mDoors) { this.mDoors = mDoors; }

    public void setWindows(int windows) { this.mWindows = windows; }

    public void setHeight(float height) { this.mHeight = height; }

    public void setLength(float length) { this.mLength = length; }

    public void setWidth(float width) { this.mWidth = width; }

    public float doorAndWindowArea() { return ( DOOR_AREA * mDoors ) + ( WINDOW_AREA * mWindows ); }

    public float wallSurfaceArea() { return 2 * mLength * mHeight + 2 * mWidth * mHeight + mLength * mWidth; }

    public float totalSurfaceArea() { return wallSurfaceArea() - doorAndWindowArea(); }

    public float gallonsOfPaintRequired() { return totalSurfaceArea() / SQ_FEET_PER_GALLON; }
}
