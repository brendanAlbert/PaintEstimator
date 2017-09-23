package edu.orangecoastcollege.cs273.balbert.paintestimator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

/**
 * MainActivity.java is the Controller for the main View of Paint Estimator.
 * This controller creates and establishes connections to the various TextView and EditText widgets
 * in the View (activity_main.xml).
 *
 * When the user enters or changes a number, this class will inform the Model (InteriorRoom.java).
 * The model does some calculations and sends back to this class the new data, and the new data is
 * then sent to the View.
 *
 * The saveSharedPreferences and loadSharedPreferences methods are how data is persisted
 * between app uses.
 *
 * When the user taps the Compute Gallons button, computeGallons is called.
 * This method receives the requisite data from the View, sends any new data to the Model
 * so it can be computed, the computed data is returned and we update the View.
 * And the data is persisted using saveSharedPreferences.
 *
 * The other button provided is Help.  When tapped, the goToHelp method is called.
 * This method instantiates an Intent which is provided with the amount of paint required.
 * We then call startActivity to send the data to the HelpActivity.java controller.
 */
public class MainActivity extends AppCompatActivity {

    // Member variables for the Views
    private EditText mLengthEditText;
    private EditText mWidthEditText;
    private EditText mHeightEditText;

    private EditText mWindowsEditText;
    private EditText mDoorsEditText;

    private TextView mGallonsTextView;

    // Member variable for the Model
    private InteriorRoom mRoom = new InteriorRoom();

    // Member variable for SharedPreferences
    private SharedPreferences mPrefs;

    private void initializeViews()
    {
        mLengthEditText = (EditText) findViewById(R.id.lengthEditText);
        mWidthEditText = (EditText) findViewById(R.id.widthEditText);
        mHeightEditText = (EditText) findViewById(R.id.heightEditText);

        mWindowsEditText = (EditText) findViewById(R.id.windowsEditText);
        mDoorsEditText = (EditText) findViewById(R.id.doorsEditText);

        mGallonsTextView = (TextView) findViewById(R.id.gallonsTextView);
    }

    private void loadSharedPreferences()
    {
        mPrefs = getSharedPreferences("edu.orangecoastcollege.cs273.balbert.PaintEstimator", MODE_PRIVATE);
        if( mPrefs != null )
        {
            // Load all the room information
            mRoom.setDoors(mPrefs.getInt("doors", 0));
            mDoorsEditText.setText(String.valueOf(mRoom.getDoors()));

            mRoom.setWindows(mPrefs.getInt("windows", 0));
            mWindowsEditText.setText(String.valueOf(mRoom.getWindows()));

            mRoom.setHeight(mPrefs.getFloat("height", 0.0f));
            mHeightEditText.setText(String.valueOf((int) mRoom.getHeight()));

            mRoom.setWidth(mPrefs.getFloat("width", 0.0f));
            mWidthEditText.setText(String.valueOf((int) mRoom.getWidth()));

            mRoom.setLength(mPrefs.getFloat("length", 0.0f));
            mLengthEditText.setText(String.valueOf((int) mRoom.getLength()));
        }
    }

    private void saveSharedPreferences()
    {
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.clear();
        editor.putInt("doors", mRoom.getDoors());
        editor.putInt("windows", mRoom.getWindows());
        editor.putFloat("height", mRoom.getHeight());
        editor.putFloat("width", mRoom.getWidth());
        editor.putFloat("length", mRoom.getLength());
        editor.apply(); // also works with editor.commit();
    }

    /**
     *  onCreate() sets up the View for MainActivity.
     *  Inside initializeViews wires up all the TextView and EditText widgets.
     *  And then any previous saved data is loaded.
     *
     * @param savedInstanceState contains the state of the app since it was last "minimized"
     *                           but not closed.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        loadSharedPreferences();
    }

    /**
     *
     * @param view is the View that called this method.
     *             In this case, it is the Compute Gallons button.
     *
     *             Data from the View is collected, passed to the Model, computed,
     *             and sent back to this Controller which then updates the appropriate
     *             TextView widgets.
     *
     *             Then we make a call to saveSharedPreferences to persist the data.
     */
    protected void computeGallons( View view )
    {
        // Update model first, then calculate
        mRoom.setLength(Float.parseFloat(mLengthEditText.getText().toString()));
        mRoom.setWidth(Float.parseFloat(mWidthEditText.getText().toString()));
        mRoom.setHeight(Float.parseFloat(mHeightEditText.getText().toString()));
        mRoom.setDoors(Integer.parseInt(mDoorsEditText.getText().toString()));
        mRoom.setWindows(Integer.parseInt(mWindowsEditText.getText().toString()));

        mGallonsTextView.setText(String.format(Locale.getDefault(),"%s %s feet\n%s %.1f",
                getString(R.string.interior_surface_area_text),
                mRoom.totalSurfaceArea(),
                getString(R.string.gallons_needed_text),
                mRoom.gallonsOfPaintRequired()));

        saveSharedPreferences();
    }

    /**
     *
     * @param view is the View that called this method.
     *             In this case it happens to be the Help button.
     *             Tapping Help will initiate the Intent creation code so that we can send
     *             a payload of data to the HelpActivity.java Controller which send it to its View,
     *             activity_help.xml.
     */
    protected void goToHelp( View view )
    {
        // Construct an Explicit Intent to go to HelpActivity
        // Intent: specify where to start (context) and where we're going (next Activity)
        Intent helpIntent = new Intent(this, HelpActivity.class);
        helpIntent.putExtra("gallons", String.format(Locale.getDefault(),"%s %.1f gallons", getString(R.string.gallons),mRoom.gallonsOfPaintRequired()));
        startActivity(helpIntent);
    }
}
