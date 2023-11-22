package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView totalquestionsTextView;
    TextView questionTextView;
    TextView Answeredquestions;
    Button ansA,ansB,ansC,ansD;
    Button Submitbtn;

    int score =0;
    int totalquestions = QuestionAnswer.question.length;
    int currentquestionIndex =0;
    String selectedAns = " ";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        totalquestionsTextView = findViewById(R.id.total_question);
        questionTextView = findViewById(R.id.question);
        Answeredquestions = findViewById(R.id.answeredquestions);
        ansA = findViewById(R.id.ansA);
        ansB = findViewById(R.id.ansB);
        ansC = findViewById(R.id.ansC);
        ansD = findViewById(R.id.ansD);
        Submitbtn = findViewById(R.id.submitbtn);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        Submitbtn.setOnClickListener(this);

        totalquestionsTextView.setText("QuizApp");

        loadNewQuestion();
    }

    @Override
    public void onClick(View v) {

        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);

        Button ClickedButton = (Button) v;

        if(ClickedButton.getId() == R.id.submitbtn){
            if(selectedAns != " "){

                if (selectedAns.equals(QuestionAnswer.correctAnswers[currentquestionIndex])){
                    score++;
                }
                currentquestionIndex++;
                selectedAns = " ";
                loadNewQuestion();
            }
        }
        else{
            selectedAns = ClickedButton.getText().toString();
            ClickedButton.setBackgroundColor(Color.GREEN);


        }

    }
    private void loadNewQuestion() {
        int totalansquestions = currentquestionIndex;
        if(currentquestionIndex == totalquestions){
//            totalansquestions = currentquestionIndex+1;
            Answeredquestions.setText("Answered "+totalansquestions+"/"+totalquestions);
            finishQuiz();
            return;
        }
        questionTextView.setText(QuestionAnswer.question[currentquestionIndex]);
        Answeredquestions.setText("Answered "+totalansquestions+"/"+totalquestions);
        ansA.setText(QuestionAnswer.choices[currentquestionIndex][0]);
        ansB.setText(QuestionAnswer.choices[currentquestionIndex][1]);
        ansC.setText(QuestionAnswer.choices[currentquestionIndex][2]);
        ansD.setText(QuestionAnswer.choices[currentquestionIndex][3]);

    }
    void finishQuiz(){
        String passstatus = " ";

        if(score > totalquestions*0.6){
            passstatus = "Passed the Test";
        }
        else{
            passstatus = "Failed the Test";
        }
        new AlertDialog.Builder(this)
                .setTitle(passstatus)
                .setMessage("Your Score is "+score+" out of "+totalquestions)
                .setPositiveButton("Restart",(dialogInterface, i) -> restartQuiz())
                .setCancelable(false)
                .show();
    }
    void restartQuiz(){
        score =0;
        currentquestionIndex = 0;
        loadNewQuestion();
    }

}