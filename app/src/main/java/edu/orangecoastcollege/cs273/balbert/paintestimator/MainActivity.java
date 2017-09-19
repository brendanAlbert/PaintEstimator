package edu.orangecoastcollege.cs273.balbert.paintestimator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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
        //mLengthEditText = (EditText) findViewById(R.id.lengthEditText);

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
            mHeightEditText.setText(String.valueOf(mRoom.getHeight()));

            mRoom.setWidth(mPrefs.getFloat("width", 0.0f));
            mWidthEditText.setText(String.valueOf(mRoom.getWidth()));

            mRoom.setLength(mPrefs.getFloat("length", 0.0f));
            mLengthEditText.setText(String.valueOf(mRoom.getLength()));
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
        editor.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        loadSharedPreferences();
    }

    protected void computeGallons( View view )
    {
        // Update model first, then calculate
        mRoom.setLength(Float.parseFloat(mLengthEditText.getText().toString()));
        mRoom.setWidth(Float.parseFloat(mWidthEditText.getText().toString()));
        mRoom.setHeight(Float.parseFloat(mHeightEditText.getText().toString()));

        mGallonsTextView.setText(getString(R.string.interior_surface_area_text) + mRoom.totalSurfaceArea()
        + "\n" + getString(R.string.gallons_needed_text) + mRoom.gallonsOfPaintRequired());

        saveSharedPreferences();
    }

    protected void goToHelp( View view )
    {
        // Construct an Explicit Intent to go to HelpActivity
        // Intent: specify where to start (context) and where we're going (next Activity)
        Intent helpIntent = new Intent(this, HelpActivity.class);
        helpIntent.putExtra("gallons", mRoom.gallonsOfPaintRequired());
        startActivity(helpIntent);
    }
}
