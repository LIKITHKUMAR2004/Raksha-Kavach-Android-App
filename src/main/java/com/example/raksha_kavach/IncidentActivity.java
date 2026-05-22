package com.example.raksha_kavach;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

/**
 * IncidentActivity - Screen for logging incident details
 * This activity allows users to enter incident information and save it
 */
public class IncidentActivity extends AppCompatActivity {

    // Declare variables for UI elements
    private EditText editTextIncidentDetails;
    private Button buttonSaveIncident;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Link this activity to the XML layout file
        setContentView(R.layout.activity_incident);

        // Initialize (find) the UI elements from the XML layout
        editTextIncidentDetails = findViewById(R.id.editTextIncidentDetails);
        buttonSaveIncident = findViewById(R.id.buttonSaveIncident);

        // Set click listener on the Save Incident button
        buttonSaveIncident.setOnClickListener(v -> {
            // This code runs when the button is clicked
            saveIncident();
        });
    }

    /**
     * Method to save incident details
     * Currently just shows a Toast message
     * Later, you can add database/cloud storage functionality here
     */
    private void saveIncident() {
        // Get the text entered in the EditText
        String incidentDetails = editTextIncidentDetails.getText().toString().trim();

        // Check if EditText is empty
        if (incidentDetails.isEmpty()) {
            // Show error message if no details entered
            Toast.makeText(IncidentActivity.this,
                    "Please enter incident details",
                    Toast.LENGTH_SHORT).show();
        } else {
            // Show success message
            Toast.makeText(IncidentActivity.this,
                    "Incident Saved Successfully",
                    Toast.LENGTH_SHORT).show();

            // Clear the EditText after saving (optional)
            editTextIncidentDetails.setText("");
        }
    }
}