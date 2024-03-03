package com.example.loginproject_sharman6;

import static com.example.loginproject_sharman6.R.color.white;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.AlertDialog;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Arrays;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {
    //Declare global variables
    TextView totalQuestionsTextView;
    TextView questionTextView;
    Button ansA, ansB, ansC, ansD;
    Boolean ansASelect = false, ansBSelect = false, ansCSelect = false, ansDSelect = false ;
    Button submitBtn;
    int score=0;
    int totalQuestion = QuestionBank.questions.length;
    int currentQuestionIndex = 0;
    String selectedAnswer = "";
    String Email = "";
    SharedPreferences SaveResult;
    String savedScores = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        //Get Login email from previous activity to store quiz results
        Email = getIntent().getStringExtra("Email");
        //Get previous score results
        SaveResult = getSharedPreferences(getString(R.string.preference_file_key), this.MODE_PRIVATE);
        savedScores = SaveResult.getString(getString(R.string.savedScores),"NA");

        //Get TextView Ids to set the question and answer text
        totalQuestionsTextView = findViewById(R.id.total_question);
        questionTextView = findViewById(R.id.question);
        ansA = findViewById(R.id.ans_A);
        ansB = findViewById(R.id.ans_B);
        ansC = findViewById(R.id.ans_C);
        ansD = findViewById(R.id.ans_D);
        submitBtn = findViewById(R.id.submit_btn);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submitBtn.setOnClickListener(this);

        totalQuestionsTextView.setText("Total questions : "+totalQuestion);
        //Display previous quiz results
        new AlertDialog.Builder(this)
                .setTitle("SCORES")
                .setMessage("\n" + savedScores)
                .setPositiveButton("OK", (dialog, which) -> dialog.cancel() )
                .setCancelable(true)
                .show();

        new AlertDialog.Builder(this)
                .setTitle("RULES")
                .setMessage("\n This quiz relates to set of questions with single choice and multi choice questions. " +
                        "For multi choice questions please select all possible answers." +
                        "\n\n At the end of quiz the results will be presented on the screen. If you wish to attempt the quiz" +
                        " again then please restart the quiz. All scores will be recorded." +
                        "\n\n Best of Luck!")
                .setPositiveButton("OK", (dialog, which) -> dialog.cancel() )
                .setCancelable(true)
                .show();
        //Load first Question
        loadNewQuestion();
    }

    @Override
    //function when an answer or submit button is clicked
    public void onClick(View view) {
        //Reset choices for single answer question
        if (QuestionBank.questions[currentQuestionIndex][1] == "SCQ"){
            ansA.setBackgroundColor(Color.WHITE);
            ansB.setBackgroundColor(Color.WHITE);
            ansC.setBackgroundColor(Color.WHITE);
            ansD.setBackgroundColor(Color.WHITE);
        }

        //For Submit button call appropriate function to save answers for SCQ or MCQ
        Button clickedButton = (Button) view;
        if(clickedButton.getId()==R.id.submit_btn){
            if (QuestionBank.questions[currentQuestionIndex][1] == "SCQ"){
                new AlertDialog.Builder(this)
                        .setTitle("Confirmation")
                        .setMessage("Please confirm if you want to submit the answer")
                        .setPositiveButton("Confirm",(dialogInterface, i) -> saveSCQAnswer() )
                        .setCancelable(true)
                        .show();
            } else{
                new AlertDialog.Builder(this)
                        .setTitle("Confirmation")
                        .setMessage("Please confirm if you want to submit the answer")
                        .setPositiveButton("Confirm",(dialogInterface, i) -> saveMCQAnswer() )
                        .setCancelable(true)
                        .show();
            }
            loadNewQuestion();
        //If a choice is clicked, and if it is a MCQ, allow multiple answers to be selected
        }else{
            selectedAnswer  = clickedButton.getText().toString();
            if (QuestionBank.questions[currentQuestionIndex][1] == "MCQ"){
                if (clickedButton.getId() == R.id.ans_A && ansASelect == false){
                    ansASelect = true;
                    clickedButton.setBackgroundColor(Color.YELLOW);
                } else if (clickedButton.getId() == R.id.ans_A){
                    //Reset Answer if user clicks the already selected answer
                    ansASelect = false;
                    clickedButton.setBackgroundColor(Color.WHITE);
                }
                if (clickedButton.getId() == R.id.ans_B && ansBSelect == false){
                    ansBSelect = true;
                    clickedButton.setBackgroundColor(Color.YELLOW);
                } else if (clickedButton.getId() == R.id.ans_B){
                    //Reset Answer if user clicks the already selected answer
                    ansBSelect = false;
                    clickedButton.setBackgroundColor(Color.WHITE);
                }
                if (clickedButton.getId() == R.id.ans_C && ansCSelect == false){
                    ansCSelect = true;
                    clickedButton.setBackgroundColor(Color.YELLOW);
                } else if (clickedButton.getId() == R.id.ans_C){
                    //Reset Answer if user clicks the already selected answer
                    ansCSelect = false;
                    clickedButton.setBackgroundColor(Color.WHITE);
                }
                if (clickedButton.getId() == R.id.ans_D && ansDSelect == false){
                    ansDSelect = true;
                    clickedButton.setBackgroundColor(Color.YELLOW);
                } else if (clickedButton.getId() == R.id.ans_D){
                    //Reset Answer if user clicks the already selected answer
                    ansDSelect = false;
                    clickedButton.setBackgroundColor(Color.WHITE);
                }
            } else{
                clickedButton.setBackgroundColor(Color.YELLOW);
            }
        }

    }
    void saveSCQAnswer(){
        //Check Answer for a SCQ question
        if(selectedAnswer.equals(QuestionBank.correctAnswers[currentQuestionIndex])){
            score++;
        }
        currentQuestionIndex++;
        loadNewQuestion();
    }
    void saveMCQAnswer(){
        //Check Answers for a MCQ question. All choices will be verified.
        Boolean matchAllAns = false;
        Boolean checkOtherAns = false;
        String [] ansArr = {"NA", "NA", "NA", "NA"};

        String correctAns = Arrays.asList(QuestionBank.correctAnswers[currentQuestionIndex]).toString();
        //If a choice is selected then store the value in the array
        if (ansASelect){
            if (correctAns.contains(ansA.getText().toString())){
                ansArr[0] = "true";
            } else {
                ansArr[0] = "false";
            }
        }
        if (ansBSelect ){
            if (correctAns.contains(ansB.getText().toString())){
                ansArr[1] = "true";
            } else {
                ansArr[1] = "false";
            }
        }
        if (ansCSelect){
            if (correctAns.contains(ansC.getText().toString())){
                ansArr[2] = "true";
            } else {
                ansArr[2] = "false";
            }
        }
        if (ansDSelect) {
            if (correctAns.contains(ansD.getText().toString())){
                ansArr[3] = "true";
            } else {
                ansArr[3] = "false";
            }
        }

        String selAns = Arrays.asList(ansArr).toString();
        int ansCount = 0;
        //Count the number of choices selected
        for (int i = 0; i < ansArr.length; i++){
            if (ansArr[i] == "true" || ansArr[i] == "false"){
                ansCount++;
            }
        }
        //Check if any of the choices were wrong and if 2 choices were selected
        if(!selAns.contains("false") &&  ansCount == 2){
            score++;
        }
        currentQuestionIndex++;
        //Reset variables
        ansASelect = false;
        ansBSelect = false;
        ansCSelect = false;
        ansDSelect = false;
        //Reset choices background color
        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);

        loadNewQuestion();
    }
    void loadNewQuestion(){
        //Load question and answer texts
        if(currentQuestionIndex == totalQuestion ){
            //for last question calculate the score
            finishQuiz();
            return;
        }

        questionTextView.setText(QuestionBank.questions[currentQuestionIndex][0]);
        ansA.setText(QuestionBank.choices[currentQuestionIndex][0]);
        ansB.setText(QuestionBank.choices[currentQuestionIndex][1]);
        ansC.setText(QuestionBank.choices[currentQuestionIndex][2]);
        ansD.setText(QuestionBank.choices[currentQuestionIndex][3]);

    }

    void finishQuiz(){
        //Save the quiz results in saved Preferences
        String saveContent = "";
        if (savedScores == "" || savedScores == "NA"){
            saveContent = Email + "      " + score;
        } else {
            saveContent = savedScores + "\n" + Email + "      " + score;
        }
        SaveResult.edit().putString(getString(R.string.savedScores), saveContent).commit();
        //Display the latest scores
        new AlertDialog.Builder(this)
                .setTitle("Result")
                .setMessage("Score is "+ score+" out of "+ totalQuestion)
                .setPositiveButton("Restart",(dialogInterface, i) -> restartQuiz() )
                .setCancelable(false)
                .show();


    }

    void restartQuiz(){
        //Start new quiz
        savedScores = SaveResult.getString(getString(R.string.savedScores),"NA");
        new AlertDialog.Builder(this)
                .setTitle("SCORES")
                .setMessage("\n" + savedScores)
                .setPositiveButton("OK", (dialog, which) -> dialog.cancel() )
                .setCancelable(true)
                .show();

        score = 0;
        currentQuestionIndex =0;
        loadNewQuestion();
    }

}