package com.example.raksha_kavach;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * MainActivity - Home screen of Raksha-Kavach Safety App
 */
public class MainActivity extends AppCompatActivity {

    // Existing buttons
    private Button buttonWelding;



    // AI Smart Generator
    private EditText editTextTask;
    private Button buttonGenerateChecklist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Existing buttons
        buttonWelding = findViewById(R.id.buttonWelding);


        // AI Generator Views
        editTextTask = findViewById(R.id.editTextTask);
        buttonGenerateChecklist = findViewById(R.id.buttonGenerateChecklist);

        // Existing buttons
        buttonWelding.setOnClickListener(v -> openChecklist("WELDING"));


        // AI Smart Generator Button
        buttonGenerateChecklist.setOnClickListener(v -> generateSmartChecklist());
    }

    /**
     * AI Smart Checklist Generator
     */
    private void generateSmartChecklist() {

        String task = editTextTask.getText().toString().toLowerCase();

        if (task.isEmpty()) {
            Toast.makeText(this,
                    "Please enter a task",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        // Welding related tasks
        if (task.contains("weld") ||
                task.contains("metal") ||
                task.contains("fabrication")) {

            Toast.makeText(this,
                    "AI detected Welding Task 🔥",
                    Toast.LENGTH_SHORT).show();

            openChecklist("WELDING");
        }

        // Construction related tasks
        else if (task.contains("construction") ||
                task.contains("building") ||
                task.contains("cement") ||
                task.contains("brick")) {

            Toast.makeText(this,
                    "AI detected Construction Task 🏗️",
                    Toast.LENGTH_SHORT).show();

            openChecklist("CONSTRUCTION");
        }

        // Electrical related tasks
        else if (task.contains("electric") ||
                task.contains("wire") ||
                task.contains("circuit") ||
                task.contains("repair")) {

            Toast.makeText(this,
                    "AI detected Electrical Task ⚡",
                    Toast.LENGTH_SHORT).show();

            openChecklist("ELECTRICAL");
        }

        // Sports related tasks
        else if (task.contains("cricket") ||
                task.contains("football") ||
                task.contains("sports")) {

            Toast.makeText(this,
                    "Sports Safety Checklist Suggested 🏏",
                    Toast.LENGTH_LONG).show();
        }

        // Cooking related tasks
        else if (task.contains("cooking") ||
                task.contains("kitchen") ||
                task.contains("chef")) {

            Toast.makeText(this,
                    "Kitchen Safety Checklist Suggested 🍳",
                    Toast.LENGTH_LONG).show();
        }

        // Unknown task
        else {

            Toast.makeText(this,
                    "AI could not identify task. Showing general safety guidelines.",
                    Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Open ChecklistActivity
     */
    private void openChecklist(String taskType) {

        Intent intent = new Intent(MainActivity.this,
                ChecklistActivity.class);

        intent.putExtra("TASK_TYPE", taskType);

        startActivity(intent);
    }
}