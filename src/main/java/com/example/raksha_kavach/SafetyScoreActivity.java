package com.example.raksha_kavach;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

/**
 * SafetyScoreActivity - Displays the safety score for completed checklist
 * Shows percentage, rating, and recommendations
 */
public class SafetyScoreActivity extends AppCompatActivity {

    // Declare variables
    private TextView textViewScore;
    private TextView textViewRating;
    private TextView textViewMessage;
    private TextView textViewRecommendations;
    private Button buttonBackToChecklist;
    private String taskType = "";
    private int scorePercentage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safety_score);

        // Initialize views
        textViewScore = findViewById(R.id.textViewScore);
        textViewRating = findViewById(R.id.textViewRating);
        textViewMessage = findViewById(R.id.textViewMessage);
        textViewRecommendations = findViewById(R.id.textViewRecommendations);
        buttonBackToChecklist = findViewById(R.id.buttonBackToChecklist);

        // Get data from intent
        Intent intent = getIntent();
        taskType = intent.getStringExtra("TASK_TYPE");
        scorePercentage = intent.getIntExtra("SCORE", 0);

        // Set default values if not provided
        if (taskType == null || taskType.isEmpty()) {
            taskType = "WELDING";
        }

        // Display score and recommendations
        displayScore(scorePercentage);

        // Back button listener
        buttonBackToChecklist.setOnClickListener(v -> finish());
    }

    /**
     * Display safety score with rating and recommendations
     */
    private void displayScore(int score) {
        // Set the score display
        textViewScore.setText(score + "%");

        // Determine rating and color based on score
        String rating;
        String message;
        String recommendations;
        int backgroundColor;

        if (score >= 90) {
            rating = "⭐⭐⭐⭐⭐ EXCELLENT";
            message = "Outstanding! All safety measures verified.";
            recommendations = "✓ Continue following these safety practices\n✓ Share your safety practices with team members\n✓ Train new workers on these procedures";
            backgroundColor = R.color.safe_green;
        } else if (score >= 75) {
            rating = "⭐⭐⭐⭐ GOOD";
            message = "Good job! Most safety items are checked.";
            recommendations = "→ Review unchecked items carefully\n→ Verify all remaining safety measures\n→ Ensure proper equipment is available";
            backgroundColor = R.color.safe_green;
        } else if (score >= 50) {
            rating = "⭐⭐⭐ FAIR";
            message = "Fair. Additional checks are needed.";
            recommendations = "⚠ Complete all remaining safety checks\n⚠ Do not proceed until all items verified\n⚠ Report any missing safety equipment";
            backgroundColor = R.color.warning_orange;
        } else {
            rating = "⭐ POOR";
            message = "Safety inspection incomplete. Do not proceed.";
            recommendations = "🔴 STOP - Do not begin work\n🔴 Complete all required safety checks\n🔴 Report missing or faulty equipment immediately\n🔴 Contact your supervisor";
            backgroundColor = R.color.danger_red;
        }

        // Update UI
        textViewRating.setText(rating);
        textViewMessage.setText(message);
        textViewRecommendations.setText(recommendations);

        // Change background color based on score (optional)
        // You can modify the card background if needed
    }

    /**
     * Get safety rating text color based on score
     */
    private int getRatingColor(int score) {
        if (score >= 90) {
            return getResources().getColor(R.color.safe_green);
        } else if (score >= 75) {
            return getResources().getColor(R.color.safe_green);
        } else if (score >= 50) {
            return getResources().getColor(R.color.warning_orange);
        } else {
            return getResources().getColor(R.color.danger_red);
        }
    }
}