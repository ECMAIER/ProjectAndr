package com.example.admin.projectandr;

//Game activity ... blank text view, update as needed
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;



public class QuizActivity extends AppCompatActivity {

    SharedPreferences sharedPref = MainActivity.sharedPref;
    SharedPreferences.Editor editor = sharedPref.edit();
    float start = 0;
    float end = 0;

    //This function takes in a string representing a number in any base,
    //it converts the number to a (base 10) integer
    public static int fromDigits(String S, int base){

        int digit = 0;

        for (int i = 0; i < S.length(); i++){

            int temp;

            //convert character to an integer
            char c = S.charAt(i);
            if (Character.isDigit(c))
                temp = ((int) (c)) - 48;
            else
                temp = ((int) (c)) - 87;

            //
            digit += (int) (temp *  Math.pow(base,S.length()-i-1));
        }
        return digit;
    }

    //this function takes in a base 10 integer, and returns a string representing
    //the number in the passed base
    //
    //for example if inNumber == 6 and base == 2, the return value would be "110", which is 6 in binary (base 2)
    //need to screen base for numbers greater than zero
    public static String BaseToDigit(int inNumber, int base){

        int tempNumber = inNumber;

        String baseRepresentation = "";
        char newChar;
        int newDigit;

        while(tempNumber > 0){

            newDigit = tempNumber % base;

            //for digits zero through nine, add ascii 48 (zero)
            if (newDigit< 10)
                newChar = (char) ((newDigit) + 48);
                //for digits >= 10, add  87, so that 10 +87 = 97, ascii lowercase a
            else
                newChar = (char) ((newDigit) + 87);

            baseRepresentation =  newChar + baseRepresentation;

            tempNumber = tempNumber / base;
        }

        return baseRepresentation;
    }

    //this function generates a value to "offset" wrong answers from the correct answer
    public static int generateOffset(int n){

        int offset;
        //handle particularly small numbers
        if (n < 10) {

            offset = ThreadLocalRandom.current().nextInt(-5, 5);
            if (n+offset < 1)
                offset *= -1;

            if (offset == 0)
                offset++;
            return offset;
        }

        offset = ThreadLocalRandom.current().nextInt(-(n/4), n/4);
        if (offset == 0)
            offset++;
        return offset;
    }

    //this function sets up a new question and populates the buttons
    public void generateQuestion(ArrayList<Integer> from_bases, ArrayList<Integer> to_bases){

        correctButtonNum = ThreadLocalRandom.current().nextInt(1,4);
        int correctAnswer = ThreadLocalRandom.current().nextInt(2, 16);

        String str1 = "";
        String str2 = "";

        int rand;

        rand = ThreadLocalRandom.current().nextInt(0, from_bases.size() -1 );
        from_base= from_bases.get(rand);

        rand = ThreadLocalRandom.current().nextInt(0, to_bases.size() - 1);
        to_base = to_bases.get(rand);

        //brute force make sure baseFrom and baseTo not equal, this is not a good implementation, but it works
        while(from_base == to_base){
            rand = ThreadLocalRandom.current().nextInt(0, to_bases.size());
            to_base = to_bases.get(rand);
        }

        if (from_base == 2)
            str1 = "Binary";
        if (from_base == 10)
            str1 = "Decimal";
        if (from_base == 16)
            str1 = "Hexadecimal";
        if (to_base == 2)
            str2 = "Binary";
        if (to_base == 10)
            str2 = "Decimal";
        if (to_base == 16)
            str2 = "Hexadecimal";

        String generateQuestionString = str1 + "->" + str2;
        QuizActivity.this.genQuestionButton.setText(generateQuestionString);


        correctAnswerString = "Convert: " + BaseToDigit(correctAnswer, from_base);
        QuizActivity.this.inNumberTextView.setText(correctAnswerString);

        while (wrongAnswerTwo == wrongAnswerOne || wrongAnswerTwo == correctAnswer)
            wrongAnswerTwo = correctAnswer + generateOffset(correctAnswer);
        while (wrongAnswerThree == wrongAnswerOne || wrongAnswerThree == wrongAnswerTwo || wrongAnswerThree == correctAnswer)
            wrongAnswerThree = correctAnswer + generateOffset(correctAnswer);


        //make some strings representing the answers in the appropriate base
        correctAnswerInBaseString = BaseToDigit(correctAnswer, to_base);
        wrongAnswerOneString = BaseToDigit(wrongAnswerOne, to_base);
        wrongAnswerTwoString = BaseToDigit(wrongAnswerTwo,to_base);
        wrongAnswerThreeString = BaseToDigit(wrongAnswerThree, to_base);

        if(correctButtonNum == 1){
            QuizActivity.this.answerOne.setText(correctAnswerInBaseString);
            QuizActivity.this.answerTwo.setText(wrongAnswerOneString);
            QuizActivity.this.answerThree.setText(wrongAnswerTwoString);
            QuizActivity.this.answerFour.setText(wrongAnswerThreeString);
        } else if (correctButtonNum == 2){
            QuizActivity.this.answerOne.setText(wrongAnswerOneString);
            QuizActivity.this.answerTwo.setText(correctAnswerInBaseString);
            QuizActivity.this.answerThree.setText(wrongAnswerTwoString);
            QuizActivity.this.answerFour.setText(wrongAnswerThreeString);
        } else if (correctButtonNum == 3){
            QuizActivity.this.answerOne.setText(wrongAnswerOneString);
            QuizActivity.this.answerTwo.setText(wrongAnswerTwoString);
            QuizActivity.this.answerThree.setText(correctAnswerInBaseString);
            QuizActivity.this.answerFour.setText(wrongAnswerThreeString);
        } else if (correctButtonNum == 4){
            QuizActivity.this.answerOne.setText(wrongAnswerOneString);
            QuizActivity.this.answerTwo.setText(wrongAnswerTwoString);
            QuizActivity.this.answerThree.setText(wrongAnswerThreeString);
            QuizActivity.this.answerFour.setText(correctAnswerInBaseString);
        }
    }


    private Button genQuestionButton;
    private TextView scoreView;
    private TextView inNumberTextView;
    private Button answerOne;
    private Button answerTwo;
    private Button answerThree;
    private Button answerFour;


    int correctButtonNum = ThreadLocalRandom.current().nextInt(1,4);
    int correctAnswer = ThreadLocalRandom.current().nextInt(1, 50);

    int wrongAnswerOne = correctAnswer + generateOffset(correctAnswer);
    int wrongAnswerTwo = correctAnswer + generateOffset(correctAnswer);
    int wrongAnswerThree = correctAnswer + generateOffset(correctAnswer);

    public String correctAnswerString;
    public String correctAnswerInBaseString;
    public String wrongAnswerOneString;
    public String wrongAnswerTwoString;
    public String wrongAnswerThreeString;

    int from_base;
    int to_base;

    int score = 0;
    String scoreString = "Score: " + Integer.toString(score);

    boolean answerSelected = false;

    ArrayList<Integer> from_bases = new ArrayList<Integer>(0);
    ArrayList<Integer> to_bases = new ArrayList<Integer>(0);

    @Override
    protected void onStart(){
        super.onStart();
        start = System.currentTimeMillis();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //hard coded conversion values, later these will be brought in by an intent from
        //the mode select view.  They may later be arrays so that users can practice different
        //conversions in the same game
        from_base = 0;
        to_base = 0;

        //setup the conversion values
        final Intent intent = getIntent();
        from_bases = intent.getIntegerArrayListExtra("left_values");
        to_bases = intent.getIntegerArrayListExtra("right_values");

        genQuestionButton = findViewById(R.id.generate_question_button);
        scoreView = findViewById(R.id.score_text_view);
        inNumberTextView = findViewById(R.id.toConvert);
        answerOne = findViewById(R.id.answerOne);
        answerTwo = findViewById(R.id.answerTwo);
        answerThree = findViewById(R.id.answerThree);
        answerFour = findViewById(R.id.answerFour);




//        String str1;
//        String str2;
//        if (from_base == 10)
//            str1 = "Decimal";
//        else
//            str1 = Integer.toString(from_base);
//
//        if (to_base == 2)
//            str2 = "Binary";
//        else
//            str2 = Integer.toString(to_base);
//
//
//        String generateQuestionString = str1 + "->" + str2;
//
//        QuizActivity.this.genQuestionButton.setText(generateQuestionString);

        generateQuestion(from_bases, to_bases);

//        //setup the number that the user is asked to convert
//        correctAnswerString = "Convert: " + Integer.toString(correctAnswer);
//        QuizActivity.this.inNumberTextView.setText(correctAnswerString);

        QuizActivity.this.scoreView.setText(scoreString);

//        while (wrongAnswerTwo == wrongAnswerOne || wrongAnswerTwo == correctAnswer)
//            wrongAnswerTwo = correctAnswer + generateOffset(correctAnswer);
//        while (wrongAnswerThree == wrongAnswerOne || wrongAnswerThree == wrongAnswerTwo || wrongAnswerThree == correctAnswer)
//            wrongAnswerThree = correctAnswer + generateOffset(correctAnswer);
//
//
//        //make some strings representing the answers in the appropriate base
//        correctAnswerInBaseString = BaseToDigit(correctAnswer, to_base);
//        wrongAnswerOneString = BaseToDigit(wrongAnswerOne, to_base);
//        wrongAnswerTwoString = BaseToDigit(wrongAnswerTwo,to_base);
//        wrongAnswerThreeString = BaseToDigit(wrongAnswerThree, to_base);
//
//        if(correctButtonNum == 1){
//            QuizActivity.this.answerOne.setText(correctAnswerInBaseString);
//            QuizActivity.this.answerTwo.setText(wrongAnswerOneString);
//            QuizActivity.this.answerThree.setText(wrongAnswerTwoString);
//            QuizActivity.this.answerFour.setText(wrongAnswerThreeString);
//        } else if (correctButtonNum == 2){
//            QuizActivity.this.answerOne.setText(wrongAnswerOneString);
//            QuizActivity.this.answerTwo.setText(correctAnswerInBaseString);
//            QuizActivity.this.answerThree.setText(wrongAnswerTwoString);
//            QuizActivity.this.answerFour.setText(wrongAnswerThreeString);
//        } else if (correctButtonNum == 3){
//            QuizActivity.this.answerOne.setText(wrongAnswerOneString);
//            QuizActivity.this.answerTwo.setText(wrongAnswerTwoString);
//            QuizActivity.this.answerThree.setText(correctAnswerInBaseString);
//            QuizActivity.this.answerFour.setText(wrongAnswerThreeString);
//        } else if (correctButtonNum == 4){
//            QuizActivity.this.answerOne.setText(wrongAnswerOneString);
//            QuizActivity.this.answerTwo.setText(wrongAnswerTwoString);
//            QuizActivity.this.answerThree.setText(wrongAnswerThreeString);
//            QuizActivity.this.answerFour.setText(correctAnswerInBaseString);
//        }


        answerOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!answerSelected) {
                    answerSelected= true;
                    if (correctButtonNum == 1) {
                        //not sure how to use the resource color?
                        QuizActivity.this.answerOne.setBackgroundColor(Color.GREEN);
                        score++;
                        scoreString = "Score: " + Integer.toString(score);
                        QuizActivity.this.scoreView.setText(scoreString);
                    } else {
                        QuizActivity.this.answerOne.setBackgroundColor(Color.RED);
                        if (correctButtonNum == 2)
                            QuizActivity.this.answerTwo.setBackgroundColor(Color.GREEN);
                        else if (correctButtonNum == 3)
                            QuizActivity.this.answerThree.setBackgroundColor(Color.GREEN);
                        else if (correctButtonNum == 4)
                            QuizActivity.this.answerFour.setBackgroundColor(Color.GREEN);
                    }
                }
            }

        });

        answerTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!answerSelected) {
                    answerSelected = true;
                    if (correctButtonNum == 2) {
                        //not sure how to use the resource color?
                        QuizActivity.this.answerTwo.setBackgroundColor(Color.GREEN);
                        score++;
                        scoreString = "Score: " + Integer.toString(score);
                        QuizActivity.this.scoreView.setText(scoreString);
                    } else {
                        QuizActivity.this.answerTwo.setBackgroundColor(Color.RED);
                        if (correctButtonNum == 1)
                            QuizActivity.this.answerOne.setBackgroundColor(Color.GREEN);
                        else if (correctButtonNum == 3)
                            QuizActivity.this.answerThree.setBackgroundColor(Color.GREEN);
                        else if (correctButtonNum == 4)
                            QuizActivity.this.answerFour.setBackgroundColor(Color.GREEN);
                    }
                }
            }
        });


        answerThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!answerSelected) {
                    answerSelected = true;
                    if (correctButtonNum == 3) {
                        //not sure how to use the resource color?
                        QuizActivity.this.answerThree.setBackgroundColor(Color.GREEN);
                        score++;
                        scoreString = "Score: " + Integer.toString(score);
                        QuizActivity.this.scoreView.setText(scoreString);
                    } else {
                        QuizActivity.this.answerThree.setBackgroundColor(Color.RED);
                        if (correctButtonNum == 1)
                            QuizActivity.this.answerOne.setBackgroundColor(Color.GREEN);
                        else if (correctButtonNum == 2)
                            QuizActivity.this.answerTwo.setBackgroundColor(Color.GREEN);
                        else if (correctButtonNum == 4)
                            QuizActivity.this.answerFour.setBackgroundColor(Color.GREEN);
                    }
                }
            }

        });

        answerFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!answerSelected) {
                    answerSelected = true;
                    if (correctButtonNum == 4) {
                        //not sure how to use the resource color?
                        QuizActivity.this.answerFour.setBackgroundColor(Color.GREEN);
                        score++;
                        scoreString = "Score: " + Integer.toString(score);
                        QuizActivity.this.scoreView.setText(scoreString);
                    } else {
                        QuizActivity.this.answerFour.setBackgroundColor(Color.RED);
                        if (correctButtonNum == 1)
                            QuizActivity.this.answerOne.setBackgroundColor(Color.GREEN);
                        else if (correctButtonNum == 2)
                            QuizActivity.this.answerTwo.setBackgroundColor(Color.GREEN);
                        else if (correctButtonNum == 3)
                            QuizActivity.this.answerThree.setBackgroundColor(Color.GREEN);
                    }
                }
            }

        });

        genQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answerSelected = false;
                //correctAnswer = ThreadLocalRandom.current().nextInt(2, 50);

                generateQuestion(from_bases, to_bases);

//                String correctAnswerString = "Convert: " + Integer.toString(correctAnswer);
//                QuizActivity.this.inNumberTextView.setText(correctAnswerString);
//
//                correctButtonNum = ThreadLocalRandom.current().nextInt(1,4);
//
//                wrongAnswerOne = correctAnswer + generateOffset(correctAnswer);
//                wrongAnswerTwo = correctAnswer + generateOffset(correctAnswer);
//                wrongAnswerThree = correctAnswer + generateOffset(correctAnswer);
//
//                //handle identical answers
//                while (wrongAnswerTwo == wrongAnswerOne || wrongAnswerTwo == correctAnswer)
//                    wrongAnswerTwo = correctAnswer + generateOffset(correctAnswer);
//                while (wrongAnswerThree == wrongAnswerOne || wrongAnswerThree == wrongAnswerTwo || wrongAnswerThree == correctAnswer)
//                    wrongAnswerThree = correctAnswer + generateOffset(correctAnswer);
//
//
//                correctAnswerInBaseString = BaseToDigit(correctAnswer, to_base);
//                wrongAnswerOneString = BaseToDigit(wrongAnswerOne, to_base);
//                wrongAnswerTwoString = BaseToDigit(wrongAnswerTwo,to_base);
//                wrongAnswerThreeString = BaseToDigit(wrongAnswerThree, to_base);
//
//                if(correctButtonNum == 1){
//                    QuizActivity.this.answerOne.setText(correctAnswerInBaseString);
//                    QuizActivity.this.answerTwo.setText(wrongAnswerOneString);
//                    QuizActivity.this.answerThree.setText(wrongAnswerTwoString);
//                    QuizActivity.this.answerFour.setText(wrongAnswerThreeString);
//                } else if (correctButtonNum == 2){
//                    QuizActivity.this.answerOne.setText(wrongAnswerOneString);
//                    QuizActivity.this.answerTwo.setText(correctAnswerInBaseString);
//                    QuizActivity.this.answerThree.setText(wrongAnswerTwoString);
//                    QuizActivity.this.answerFour.setText(wrongAnswerThreeString);
//                } else if (correctButtonNum == 3){
//                    QuizActivity.this.answerOne.setText(wrongAnswerOneString);
//                    QuizActivity.this.answerTwo.setText(wrongAnswerTwoString);
//                    QuizActivity.this.answerThree.setText(correctAnswerInBaseString);
//                    QuizActivity.this.answerFour.setText(wrongAnswerThreeString);
//                } else if (correctButtonNum == 4){
//                    QuizActivity.this.answerOne.setText(wrongAnswerOneString);
//                    QuizActivity.this.answerTwo.setText(wrongAnswerTwoString);
//                    QuizActivity.this.answerThree.setText(wrongAnswerThreeString);
//                    QuizActivity.this.answerFour.setText(correctAnswerInBaseString);
//                }

                answerOne.setBackgroundColor(Color.LTGRAY);
                answerTwo.setBackgroundColor(Color.LTGRAY);
                answerThree.setBackgroundColor(Color.LTGRAY);
                answerFour.setBackgroundColor(Color.LTGRAY);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        end = System.currentTimeMillis() - start;
        int playtime = (int)(end + sharedPref.getFloat(getString(R.string.total_time_played),0));
        editor.putInt(getString(R.string.total_time_played), playtime );
        editor.apply();
    }
}