package com.example.technicalquizgame;

import static com.example.technicalquizgame.Quesanswer.correctanswers;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener{


    TextView totalquestextview ;
    TextView questextview;
    Button ansA,ansB,ansC,ansD;
    Button submitbtn;

    int score = 0;
    int totalques = Quesanswer.question.length;
    int currentquesindex = 0;
    String selectedanswer = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        totalquestextview= findViewById(R.id.total_question);
        questextview = findViewById(R.id.question);
        ansA = findViewById(R.id.ans_A);
        ansB = findViewById(R.id.ans_B);
        ansC = findViewById(R.id.ans_C);
        ansD = findViewById(R.id.ans_D);
        submitbtn = findViewById(R.id.submit_btn);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submitbtn.setOnClickListener(this);

        totalquestextview.setText("Total question : "+totalques);

        loadNewQues();


    }

    @Override
    public void onClick(View v) {

        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);

       Button clickedbutton = (Button) v;
        if (clickedbutton.getId()==R.id.submit_btn){
            if (selectedanswer.equals(Quesanswer.correctanswers[currentquesindex])){
                score++;
                v.setBackgroundColor(Color.RED);
            }
            currentquesindex++;
            loadNewQues();

        }
        else{
//            selectedanswer=clickedbutton.getText().toString();


            v.setBackgroundColor(Color.GREEN);
        }




    }



    void loadNewQues(){
        if(currentquesindex==totalques){
            finishQuiz();
            return;
        }
        questextview.setText(Quesanswer.question[currentquesindex]);
        ansA.setText(Quesanswer.choices[currentquesindex][0]);
        ansB.setText(Quesanswer.choices[currentquesindex][1]);
        ansC.setText(Quesanswer.choices[currentquesindex][2]);
        ansD.setText(Quesanswer.choices[currentquesindex][3]);
    }


    void finishQuiz(){
        String passStatus = "";
        if(score>totalques*0.60){
            passStatus="Passed";
        }
        else{
            passStatus="Failed";
        }
        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("score is "+score+"out of"+totalques)
                .setPositiveButton("Restart",(dialogInterface,i)-> restartQuiz())
                .setCancelable(false)
                .show();

    }
    void restartQuiz(){
        score = 0;
        currentquesindex = 0;
        loadNewQues();
    }
}