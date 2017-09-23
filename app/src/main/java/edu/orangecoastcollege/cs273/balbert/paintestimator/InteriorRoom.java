package edu.orangecoastcollege.cs273.balbert.paintestimator;

/**
 * Created by balbert on 9/19/2017.
 *
 * InteriorRoom.java is the Model for Paint Estimator.
 * This class performs the calculations to determine how much paint the user might need
 * for their next room painting project, based on the numbers they provide.
 *
 * There are member variables for the room's length, width, height, number of doors and windows.
 * There are constants to represent a door's area and windows' areas, and the
 * number of square feet in a gallon.
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

    /**
     * InteriorRoom is the default constructor where the member variables are all instantiated
     * to zero.
     */
    public InteriorRoom()
    {
        mDoors = 0;
        mWindows = 0;
        mHeight = 0.0f;
        mLength = 0.0f;
        mWidth = 0.0f;
    }

    /**
     *
     * @return number of doors.  You don't say ...
     */
    public int getDoors() { return mDoors; }

    /**
     *
     * @return number of windows.
     */
    public int getWindows() { return mWindows; }

    /**
     *
     * @return return the room's height.
     */
    public float getHeight() { return mHeight; }

    /**
     *
     * @return return the room's length.
     */
    public float getLength() { return mLength; }

    /**
     *
     * @return return the room's width.
     */
    public float getWidth() { return mWidth; }

    /**
     *
     * @param mDoors sets the Model's number of doors to the number the user provided.
     */
    public void setDoors(int mDoors) { this.mDoors = mDoors; }

    /**
     *
     * @param windows sets the Model's number of windows to the number the user provided.
     */
    public void setWindows(int windows) { this.mWindows = windows; }

    /**
     *
     * @param height sets the room/Model's height to what the user provided.
     */
    public void setHeight(float height) { this.mHeight = height; }

    /**
     *
     * @param length sets the room/Model's length to what the user provided.
     */
    public void setLength(float length) { this.mLength = length; }

    /**
     *
     * @param width sets the room/Model's width to what the user provided.
     */
    public void setWidth(float width) { this.mWidth = width; }

    /**
     *
     * @return the sum of the surface area the doors and windows comprise.
     */
    public float doorAndWindowArea() { return ( DOOR_AREA * mDoors ) + ( WINDOW_AREA * mWindows ); }

    /**
     *
     * @return the surface area of all the walls and ceiling.
     */
    public float wallSurfaceArea() { return 2 * mLength * mHeight + 2 * mWidth * mHeight + mLength * mWidth; }

    /**
     *
     * @return the difference of the walls' surface area and the area taken by the doors and windows.
     */
    public float totalSurfaceArea() { return wallSurfaceArea() - doorAndWindowArea(); }

    /**
     *
     * @return the quotient of the totalSurfaceArea and how many square feet a gallon of paint covers.
     */
    public float gallonsOfPaintRequired() { return totalSurfaceArea() / SQ_FEET_PER_GALLON; }
}
