package com.example.raksha_kavach;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

/**
 * ChecklistActivity - Shows safety checklist for selected task
 * Supports: WELDING, CONSTRUCTION, ELECTRICAL tasks
 * Each task has different safety items to check
 */
public class ChecklistActivity extends AppCompatActivity {

    // Declare variables
    private String taskType = "";
    private int checkedCount = 0;
    private int totalCount = 0;
    private LinearLayout checklistContainer;
    private TextView textViewTitle;
    private TextView textViewTaskDesc;
    private Button buttonReportIncident;
    private Button buttonViewSafety;
    private Button buttonStartQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklist);

        // Initialize views from XML
        checklistContainer = findViewById(R.id.checklistContainer);
        textViewTitle = findViewById(R.id.textViewTitle);
        textViewTaskDesc = findViewById(R.id.textViewTaskDesc);
        buttonReportIncident = findViewById(R.id.buttonReportIncident);
        buttonViewSafety = findViewById(R.id.buttonViewSafety);
        buttonStartQuiz = findViewById(R.id.buttonStartQuiz);

        // Get task type from intent
        Intent intent = getIntent();
        taskType = intent.getStringExtra("TASK_TYPE");

        // Set default task type if not provided
        if (taskType == null || taskType.isEmpty()) {
            taskType = "WELDING";
        }

        // Load checklist based on task type
        loadChecklist(taskType);

        // Set button click listeners
        buttonReportIncident.setOnClickListener(v -> openIncidentActivity());
        buttonViewSafety.setOnClickListener(v -> openSafetyScore());
        buttonStartQuiz.setOnClickListener(v -> openQuiz());
    }

    /**
     * Load checklist items based on task type
     */
    private void loadChecklist(String type) {
        // Clear previous items
        checklistContainer.removeAllViews();
        checkedCount = 0;

        // Set title and description based on task type
        switch (type) {
            case "WELDING":
                textViewTitle.setText("🔥 WELDING SAFETY CHECKLIST");
                textViewTaskDesc.setText("Ensure all welding precautions are followed");
                loadWeldingChecklist();
                break;

            case "CONSTRUCTION":
                textViewTitle.setText("🏗️ CONSTRUCTION SAFETY CHECKLIST");
                textViewTaskDesc.setText("Verify all construction site safety measures");
                loadConstructionChecklist();
                break;

            case "ELECTRICAL":
                textViewTitle.setText("⚡ ELECTRICAL SAFETY CHECKLIST");
                textViewTaskDesc.setText("Follow all electrical safety protocols");
                loadElectricalChecklist();
                break;

            default:
                textViewTitle.setText("SAFETY CHECKLIST");
                textViewTaskDesc.setText("Safety Check Required");
        }
    }

    /**
     * Load welding specific checklist items
     */
    private void loadWeldingChecklist() {
        String[] weldingItems = {
                "✓ Wear welding helmet and face shield",
                "✓ Wear heat-resistant gloves",
                "✓ Wear leather apron",
                "✓ Check welding machine is properly grounded",
                "✓ Ensure proper ventilation in work area",
                "✓ Check cables for damage",
                "✓ Keep fire extinguisher nearby",
                "✓ Clear flammable materials from work area",
                "✓ Wear steel-toed boots",
                "✓ Inspect welding electrodes"
        };

        totalCount = weldingItems.length;
        addCheckboxItems(weldingItems);
    }

    /**
     * Load construction specific checklist items
     */
    private void loadConstructionChecklist() {
        String[] constructionItems = {
                "✓ Wear hard hat (yellow/orange)",
                "✓ Wear safety vest with reflective strips",
                "✓ Wear steel-toed safety boots",
                "✓ Check all scaffolding is secure",
                "✓ Inspect ladders for damage",
                "✓ Ensure guardrails are installed at heights",
                "✓ Check safety net installation",
                "✓ Verify fall protection equipment available",
                "✓ Inspect power tools for damage",
                "✓ Check first aid kit is accessible",
                "✓ Verify all workers have helmets",
                "✓ Ensure site is clean and organized"
        };

        totalCount = constructionItems.length;
        addCheckboxItems(constructionItems);
    }

    /**
     * Load electrical specific checklist items
     */
    private void loadElectricalChecklist() {
        String[] electricalItems = {
                "✓ Check if power supply is OFF",
                "✓ Wear insulated gloves",
                "✓ Use insulated tools only",
                "✓ Verify no live wires are exposed",
                "✓ Check grounding is proper",
                "✓ Inspect circuit breakers",
                "✓ Verify cable insulation intact",
                "✓ Wear safety goggles",
                "✓ Check earthing is correct",
                "✓ Verify emergency shutdown is accessible",
                "✓ Wear rubber-soled shoes",
                "✓ Check for water/moisture near equipment"
        };

        totalCount = electricalItems.length;
        addCheckboxItems(electricalItems);
    }

    /**
     * Add checkbox items to the container
     */
    private void addCheckboxItems(String[] items) {
        for (String item : items) {
            CheckBox checkBox = new CheckBox(this);
            checkBox.setText(item);
            checkBox.setTextSize(14);
            checkBox.setPadding(16, 12, 16, 12);
            checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    checkedCount++;
                } else {
                    checkedCount--;
                }
                updateProgress();
            });

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(0, 8, 0, 8);
            checkBox.setLayoutParams(params);

            checklistContainer.addView(checkBox);
        }
    }

    /**
     * Update progress display
     */
    private void updateProgress() {
        int percentage = (totalCount > 0) ? (checkedCount * 100) / totalCount : 0;

        if (percentage == 100) {
            Toast.makeText(this, "✓ All items checked! You're safe to proceed!", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Open IncidentActivity to report incident
     */
    private void openIncidentActivity() {
        Intent intent = new Intent(ChecklistActivity.this, IncidentActivity.class);
        intent.putExtra("TASK_TYPE", taskType);
        startActivity(intent);
    }

    /**
     * Open SafetyScoreActivity
     */
    private void openSafetyScore() {
        int percentage = (totalCount > 0) ? (checkedCount * 100) / totalCount : 0;

        Intent intent = new Intent(ChecklistActivity.this, SafetyScoreActivity.class);
        intent.putExtra("TASK_TYPE", taskType);
        intent.putExtra("SCORE", percentage);
        startActivity(intent);
    }

    /**
     * Open QuizActivity for daily safety quiz
     */
    private void openQuiz() {
        Intent intent = new Intent(ChecklistActivity.this, QuizActivity.class);
        intent.putExtra("TASK_TYPE", taskType);
        startActivity(intent);
    }
}