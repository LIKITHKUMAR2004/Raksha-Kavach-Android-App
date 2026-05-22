package com.example.raksha_kavach;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

/**
 * QuizActivity - Daily safety quiz based on task type
 * Tests user knowledge on safety procedures
 */
public class QuizActivity extends AppCompatActivity {

    // Declare variables
    private String taskType = "";
    private int currentQuestion = 0;
    private int correctAnswers = 0;
    private TextView textViewQuestion;
    private TextView textViewQuestionNo;
    private RadioGroup radioGroupAnswers;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;
    private Button buttonNext;
    private Button buttonSubmit;

    // Quiz questions and answers
    private String[][] quizQuestions;
    private String[][] quizAnswers;
    private int[] correctAnswerIndices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Initialize views
        textViewQuestion = findViewById(R.id.textViewQuestion);
        textViewQuestionNo = findViewById(R.id.textViewQuestionNo);
        radioGroupAnswers = findViewById(R.id.radioGroupAnswers);
        radioButton1 = findViewById(R.id.radioButton1);
        radioButton2 = findViewById(R.id.radioButton2);
        radioButton3 = findViewById(R.id.radioButton3);
        buttonNext = findViewById(R.id.buttonNext);
        buttonSubmit = findViewById(R.id.buttonSubmit);

        // Get task type from intent
        Intent intent = getIntent();
        taskType = intent.getStringExtra("TASK_TYPE");

        if (taskType == null || taskType.isEmpty()) {
            taskType = "WELDING";
        }

        // Load quiz questions based on task type
        loadQuizQuestions(taskType);

        // Set up button listeners
        buttonNext.setOnClickListener(v -> nextQuestion());
        buttonSubmit.setOnClickListener(v -> submitQuiz());

        // Display first question
        displayQuestion();
    }

    /**
     * Load quiz questions based on task type
     */
    private void loadQuizQuestions(String type) {
        switch (type) {
            case "WELDING":
                loadWeldingQuiz();
                break;
            case "CONSTRUCTION":
                loadConstructionQuiz();
                break;
            case "ELECTRICAL":
                loadElectricalQuiz();
                break;
            default:
                loadWeldingQuiz();
        }
    }

    /**
     * Load welding safety quiz questions
     */
    private void loadWeldingQuiz() {
        quizQuestions = new String[][] {
                {
                        "What is the primary purpose of a welding helmet?",
                        "To keep you cool",
                        "To protect eyes and face from UV radiation and sparks",
                        "To communicate with other workers"
                },
                {
                        "When should you perform equipment inspection?",
                        "Only when there's a problem",
                        "Before each work session",
                        "Once a month"
                },
                {
                        "What is the proper ventilation for welding?",
                        "Open window is enough",
                        "Proper exhaust system or forced ventilation",
                        "No ventilation needed"
                },
                {
                        "Which fire extinguisher type is suitable for welding fires?",
                        "Water only",
                        "Type ABC or CO2",
                        "No extinguisher needed"
                },
                {
                        "What PPE is essential for welders?",
                        "Only helmet",
                        "Helmet, gloves, apron, boots, and clothing",
                        "No specific PPE required"
                }
        };

        quizAnswers = new String[][] {
                { "To keep you cool", "To protect eyes and face from UV radiation and sparks", "To communicate with other workers" },
                { "Only when there's a problem", "Before each work session", "Once a month" },
                { "Open window is enough", "Proper exhaust system or forced ventilation", "No ventilation needed" },
                { "Water only", "Type ABC or CO2", "No extinguisher needed" },
                { "Only helmet", "Helmet, gloves, apron, boots, and clothing", "No specific PPE required" }
        };

        correctAnswerIndices = new int[] { 1, 1, 1, 1, 1 }; // Index 1 is correct for all
    }

    /**
     * Load construction safety quiz questions
     */
    private void loadConstructionQuiz() {
        quizQuestions = new String[][] {
                {
                        "What is the minimum height for guardrails?",
                        "2 feet",
                        "42 inches (3.5 feet)",
                        "5 feet"
                },
                {
                        "How often should ladders be inspected?",
                        "Never",
                        "Before each use",
                        "Once a year"
                },
                {
                        "What does PPE stand for?",
                        "Personal Equipment Enhancement",
                        "Personal Protective Equipment",
                        "Protective Professional Equipment"
                },
                {
                        "What is the maximum slope for a safe ladder?",
                        "80 degrees",
                        "75 degrees",
                        "45 degrees"
                },
                {
                        "Who is responsible for site safety?",
                        "Only supervisors",
                        "Everyone on site",
                        "Only managers"
                }
        };

        quizAnswers = new String[][] {
                { "2 feet", "42 inches (3.5 feet)", "5 feet" },
                { "Never", "Before each use", "Once a year" },
                { "Personal Equipment Enhancement", "Personal Protective Equipment", "Protective Professional Equipment" },
                { "80 degrees", "75 degrees", "45 degrees" },
                { "Only supervisors", "Everyone on site", "Only managers" }
        };

        correctAnswerIndices = new int[] { 1, 1, 1, 2, 1 };
    }

    /**
     * Load electrical safety quiz questions
     */
    private void loadElectricalQuiz() {
        quizQuestions = new String[][] {
                {
                        "What is the safe voltage for working on electrical equipment?",
                        "Below 24 volts",
                        "Below 50 volts",
                        "No limit with proper PPE"
                },
                {
                        "What should you do before working on electrical circuits?",
                        "Turn off power and verify it's OFF",
                        "Just wear gloves",
                        "Ask someone to hold the switch"
                },
                {
                        "What is earthing/grounding?",
                        "Connecting to earth to provide safe path for electricity",
                        "Painting equipment",
                        "Not important"
                },
                {
                        "How often should electrical cords be inspected?",
                        "Never",
                        "Every month",
                        "Before each use and regularly"
                },
                {
                        "What is the hazard of wet environments with electricity?",
                        "Water conducts electricity, increasing electrocution risk",
                        "No hazard",
                        "Only a problem in rain"
                }
        };

        quizAnswers = new String[][] {
                { "Below 24 volts", "Below 50 volts", "No limit with proper PPE" },
                { "Turn off power and verify it's OFF", "Just wear gloves", "Ask someone to hold the switch" },
                { "Connecting to earth to provide safe path for electricity", "Painting equipment", "Not important" },
                { "Never", "Every month", "Before each use and regularly" },
                { "Water conducts electricity, increasing electrocution risk", "No hazard", "Only a problem in rain" }
        };

        correctAnswerIndices = new int[] { 0, 0, 0, 2, 0 };
    }

    /**
     * Display current question
     */
    private void displayQuestion() {
        // Clear radio group
        radioGroupAnswers.clearCheck();

        // Set question number
        textViewQuestionNo.setText("Question " + (currentQuestion + 1) + " of " + quizQuestions.length);

        // Set question text
        textViewQuestion.setText(quizQuestions[currentQuestion][0]);

        // Set answer options
        radioButton1.setText(quizAnswers[currentQuestion][0]);
        radioButton2.setText(quizAnswers[currentQuestion][1]);
        radioButton3.setText(quizAnswers[currentQuestion][2]);

        // Show/hide submit button
        if (currentQuestion == quizQuestions.length - 1) {
            buttonNext.setVisibility(Button.GONE);
            buttonSubmit.setVisibility(Button.VISIBLE);
        } else {
            buttonNext.setVisibility(Button.VISIBLE);
            buttonSubmit.setVisibility(Button.GONE);
        }
    }

    /**
     * Check if answer is correct and move to next question
     */
    private void nextQuestion() {
        // Get selected answer
        int selectedId = radioGroupAnswers.getCheckedRadioButtonId();

        if (selectedId == -1) {
            Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check if answer is correct
        RadioButton selectedRadio = findViewById(selectedId);
        int selectedIndex = -1;

        if (selectedRadio == radioButton1) selectedIndex = 0;
        else if (selectedRadio == radioButton2) selectedIndex = 1;
        else if (selectedRadio == radioButton3) selectedIndex = 2;

        // Compare with correct answer
        if (selectedIndex == correctAnswerIndices[currentQuestion]) {
            correctAnswers++;
            Toast.makeText(this, "✓ Correct!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "✗ Incorrect. Correct answer: " + quizAnswers[currentQuestion][correctAnswerIndices[currentQuestion]], Toast.LENGTH_LONG).show();
        }

        // Move to next question
        currentQuestion++;

        if (currentQuestion < quizQuestions.length) {
            displayQuestion();
        }
    }

    /**
     * Submit quiz and show results
     */
    private void submitQuiz() {
        // Get selected answer for last question
        int selectedId = radioGroupAnswers.getCheckedRadioButtonId();

        if (selectedId == -1) {
            Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check if last answer is correct
        RadioButton selectedRadio = findViewById(selectedId);
        int selectedIndex = -1;

        if (selectedRadio == radioButton1) selectedIndex = 0;
        else if (selectedRadio == radioButton2) selectedIndex = 1;
        else if (selectedRadio == radioButton3) selectedIndex = 2;

        if (selectedIndex == correctAnswerIndices[currentQuestion]) {
            correctAnswers++;
        }

        // Calculate percentage
        int percentage = (correctAnswers * 100) / quizQuestions.length;

        // Show results
        String resultMessage = "Quiz Complete!\n\n" +
                "Your Score: " + correctAnswers + "/" + quizQuestions.length + "\n" +
                "Percentage: " + percentage + "%\n\n";

        if (percentage >= 80) {
            resultMessage += "✓ Excellent! You understand the safety procedures.";
        } else if (percentage >= 60) {
            resultMessage += "Good! Review the missed questions for better understanding.";
        } else {
            resultMessage += "⚠ Please review all safety procedures and retake the quiz.";
        }

        Toast.makeText(this, resultMessage, Toast.LENGTH_LONG).show();

        // Close activity after delay
        new android.os.Handler().postDelayed(() -> finish(), 2000);
    }
}