package edu.orangecoastcollege.cs273.balbert.paintestimator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


/**
 * HelpActivity.java is the second controller of Paint Estimator.
 * This Activity helps describe how the app works and why someone might want or need help
 * calculating how much paint would be needed to paint a room.
 *
 * The calculated amount is displayed at the top for convenience.
 *
 * An Input Room Dimensions button is provided if the user wishes to return to
 * the main activity to tweak the numbers.
 */
public class HelpActivity extends AppCompatActivity
{

    /**
     *
     * onCreate(Bundle savedInstanceState)
     * - sets the View up by instantiating the View widgets and the "Back" button.
     * - The Intent, the number of gallons on paint, is received from MainActivity.
     * - The long help text describes the purpose of this app and provides some helpful
     *   know-how for the user's next painting project.
     *
     * @param savedInstanceState remembers the state of the app when it was "minimized".
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        TextView estimatedPaintRequiredTextView = (TextView) findViewById(R.id.estimatedPaintRequiredTextView);

        Intent intentFromMain = getIntent();

        // Populate the text view with data
        String helpText = intentFromMain.getStringExtra("gallons");

        // Fill your TextView with data from the report
        estimatedPaintRequiredTextView.setText(helpText);

    }

    /**
     *
     * @param v is the View, in this case the Input Room Dimension button, which is tapped to
     *          call this method.
     *
     *          finish() is called; it sends the user back to the previous Activity and cleans up
     *          the memory being used by HelpActivity.
     */
    public void returnToInputRoomDimensions(View v)
    {
        finish();
    }
}
