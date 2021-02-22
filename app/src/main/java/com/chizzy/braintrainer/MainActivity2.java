package com.chizzy.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity2 extends AppCompatActivity {
    Button button0;
    Button button1;
    Button button2;
    Button button3;
   TextView correct;
    TextView score;
    TextView sumTextView;
    TextView timer;
    Button playAgain;
    int scores;
    int numberOfQuestions;

      public  void play (View view){
          playAgain.setVisibility(View.INVISIBLE);
          scores = 0;
          numberOfQuestions = 0;
          timer.setText("30s");
          score.setText(Integer.toString(scores)+"/"+Integer.toString(numberOfQuestions));

          newQuestion();
          new  CountDownTimer(30100,1000){

              @Override
              public void onTick(long millisUntilFinished) {
                  timer.setText( String .valueOf(millisUntilFinished / 1000)+ "s");
              }

              @Override
              public void onFinish() {
                  correct.setText(" Done! Total score is = "+Integer.toString(scores)+" /"+Integer.toString(numberOfQuestions));
                  playAgain.setVisibility(View.VISIBLE);
              }
          }.start();

      }

    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
     public  void tap (View view){
        if ( Integer.toString( locationOfCorrectAnswer).equals(view.getTag().toString())){
            correct.setText("Correct!");
            scores++;
        }else {
            correct.setText("Wrong :(");
        }
        numberOfQuestions++;
        score.setText(Integer.toString(scores)+"/"+Integer.toString(numberOfQuestions));
        newQuestion();
     }

     public void newQuestion(){

         Random  rand = new Random();

         int a = rand.nextInt(21);
         int b = rand.nextInt(21);

         sumTextView.setText(Integer.toString(a)+ " + " + Integer.toString(b));

         locationOfCorrectAnswer = rand.nextInt(4);
         answers.clear();

         for (int i = 0; i < 4;i++){
             if (i == locationOfCorrectAnswer){
                 answers.add(a + b);
             }else {
                 int wrongAnswer = rand.nextInt(rand.nextInt(41));
                 while (wrongAnswer == a+b){
                     wrongAnswer = rand.nextInt(41);
                 }
                 answers.add(wrongAnswer);

             }

         }
         button0.setText(Integer.toString(answers.get(0)));
         button1.setText(Integer.toString(answers.get(1)));
         button2.setText(Integer.toString(answers.get(2)));
         button3.setText(Integer.toString(answers.get(3)));
     }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        sumTextView = findViewById(R.id.sumTextView);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        correct = findViewById(R.id.correct);
        score   = findViewById(R.id.score);
        timer  = findViewById(R.id.timer);
        playAgain = findViewById(R.id.playAgain);

         play(timer  = findViewById(R.id.timer));
     }
}