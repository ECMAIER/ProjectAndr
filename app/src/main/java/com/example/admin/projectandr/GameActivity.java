package com.example.admin.projectandr;

//Game activity ... blank text view, update as needed
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.concurrent.ThreadLocalRandom;



public class GameActivity extends AppCompatActivity {


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

        String baseRepresentation = "";
        char newChar;
        int newDigit;

        while(inNumber > 0){

            newDigit = inNumber % base;

            //for digits zero through nine, add ascii 48 (zero)
            if (newDigit< 10)
                newChar = (char) ((newDigit) + 48);
                //for digits >= 10, add  87, so that 10 +87 = 97, ascii lowercase a
            else
                newChar = (char) ((newDigit) + 87);

            baseRepresentation =  newChar + baseRepresentation;

            inNumber = inNumber / base;
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


    private Button genQuestionButton;
    private TextView scoreView;
    private TextView inNumberTextView;
    private Button answerOne;
    private Button answerTwo;
    private Button answerThree;
    private Button answerFour;


    int correctButtonNum = ThreadLocalRandom.current().nextInt(1,4);
    int correctAnswer = ThreadLocalRandom.current().nextInt(1, 10);

    int wrongAnswerOne = correctAnswer + generateOffset(correctAnswer);
    int wrongAnswerTwo = correctAnswer + generateOffset(correctAnswer);
    int wrongAnswerThree = correctAnswer + generateOffset(correctAnswer);

    int from_base;
    int to_base;

    int score = 0;
    String scoreString = "Score: " + Integer.toString(score);

    boolean answerSelected = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //hard coded conversion values, later these will be brought in by an intent from
        //the mode select view.  They may later be arrays so that users can practice different
        //conversions in the same game
        from_base = 10;
        to_base = 2;

        genQuestionButton = findViewById(R.id.generate_question_button);
        scoreView = findViewById(R.id.score_text_view);
        inNumberTextView = findViewById(R.id.toConvert);
        answerOne = findViewById(R.id.answerOne);
        answerTwo = findViewById(R.id.answerTwo);
        answerThree = findViewById(R.id.answerThree);
        answerFour = findViewById(R.id.answerFour);


        String str1;
        String str2;
        if (from_base == 10)
            str1 = "Decimal";
        else
            str1 = Integer.toString(from_base);

        if (to_base == 2)
            str2 = "Binary";
        else
            str2 = Integer.toString(to_base);

        String generateQuestionString = str1 + "->" + str2;

        GameActivity.this.genQuestionButton.setText(generateQuestionString);

        //setup the number that the user is asked to convert
       String correctAnswerString = "Convert: " + Integer.toString(correctAnswer);
       GameActivity.this.inNumberTextView.setText(correctAnswerString);

       GameActivity.this.scoreView.setText(scoreString);

        while (wrongAnswerTwo == wrongAnswerOne || wrongAnswerTwo == correctAnswer)
            wrongAnswerTwo = correctAnswer + generateOffset(correctAnswer);
        while (wrongAnswerThree == wrongAnswerOne || wrongAnswerThree == wrongAnswerTwo || wrongAnswerThree == correctAnswer)
            wrongAnswerThree = correctAnswer + generateOffset(correctAnswer);


        //make some strings representing the answers in the appropriate base
        String correctAnswerInBaseString = BaseToDigit(correctAnswer, to_base);
        String wrongAnswerOneString = BaseToDigit(wrongAnswerOne, to_base);
        String wrongAnswerTwoString = BaseToDigit(wrongAnswerTwo,to_base);
        String wrongAnswerThreeString = BaseToDigit(wrongAnswerThree, to_base);

       if(correctButtonNum == 1){
           GameActivity.this.answerOne.setText(correctAnswerInBaseString);
           GameActivity.this.answerTwo.setText(wrongAnswerOneString);
           GameActivity.this.answerThree.setText(wrongAnswerTwoString);
           GameActivity.this.answerFour.setText(wrongAnswerThreeString);
       } else if (correctButtonNum == 2){
           GameActivity.this.answerOne.setText(wrongAnswerOneString);
           GameActivity.this.answerTwo.setText(correctAnswerInBaseString);
           GameActivity.this.answerThree.setText(wrongAnswerTwoString);
           GameActivity.this.answerFour.setText(wrongAnswerThreeString);
       } else if (correctButtonNum == 3){
           GameActivity.this.answerOne.setText(wrongAnswerOneString);
           GameActivity.this.answerTwo.setText(wrongAnswerTwoString);
           GameActivity.this.answerThree.setText(correctAnswerInBaseString);
           GameActivity.this.answerFour.setText(wrongAnswerThreeString);
       } else if (correctButtonNum == 4){
           GameActivity.this.answerOne.setText(wrongAnswerOneString);
           GameActivity.this.answerTwo.setText(wrongAnswerTwoString);
           GameActivity.this.answerThree.setText(wrongAnswerThreeString);
           GameActivity.this.answerFour.setText(correctAnswerInBaseString);
       }


       answerOne.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if (!answerSelected) {
                   answerSelected= true;
                   if (correctButtonNum == 1) {
                       //not sure how to use the resource color?
                       GameActivity.this.answerOne.setBackgroundColor(Color.GREEN);
                       score++;
                       scoreString = "Score: " + Integer.toString(score);
                       GameActivity.this.scoreView.setText(scoreString);
                   } else {
                       GameActivity.this.answerOne.setBackgroundColor(Color.RED);
                       if (correctButtonNum == 2)
                           GameActivity.this.answerTwo.setBackgroundColor(Color.GREEN);
                       else if (correctButtonNum == 3)
                           GameActivity.this.answerThree.setBackgroundColor(Color.GREEN);
                       else if (correctButtonNum == 4)
                           GameActivity.this.answerFour.setBackgroundColor(Color.GREEN);
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
                        GameActivity.this.answerTwo.setBackgroundColor(Color.GREEN);
                        score++;
                        scoreString = "Score: " + Integer.toString(score);
                        GameActivity.this.scoreView.setText(scoreString);
                    } else {
                        GameActivity.this.answerTwo.setBackgroundColor(Color.RED);
                        if (correctButtonNum == 1)
                            GameActivity.this.answerOne.setBackgroundColor(Color.GREEN);
                        else if (correctButtonNum == 3)
                            GameActivity.this.answerThree.setBackgroundColor(Color.GREEN);
                        else if (correctButtonNum == 4)
                            GameActivity.this.answerFour.setBackgroundColor(Color.GREEN);
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
                        GameActivity.this.answerThree.setBackgroundColor(Color.GREEN);
                        score++;
                        scoreString = "Score: " + Integer.toString(score);
                        GameActivity.this.scoreView.setText(scoreString);
                    } else {
                        GameActivity.this.answerThree.setBackgroundColor(Color.RED);
                        if (correctButtonNum == 1)
                            GameActivity.this.answerOne.setBackgroundColor(Color.GREEN);
                        else if (correctButtonNum == 2)
                            GameActivity.this.answerTwo.setBackgroundColor(Color.GREEN);
                        else if (correctButtonNum == 4)
                            GameActivity.this.answerFour.setBackgroundColor(Color.GREEN);
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
                        GameActivity.this.answerFour.setBackgroundColor(Color.GREEN);
                        score++;
                        scoreString = "Score: " + Integer.toString(score);
                        GameActivity.this.scoreView.setText(scoreString);
                    } else {
                        GameActivity.this.answerFour.setBackgroundColor(Color.RED);
                        if (correctButtonNum == 1)
                            GameActivity.this.answerOne.setBackgroundColor(Color.GREEN);
                        else if (correctButtonNum == 2)
                            GameActivity.this.answerTwo.setBackgroundColor(Color.GREEN);
                        else if (correctButtonNum == 3)
                            GameActivity.this.answerThree.setBackgroundColor(Color.GREEN);
                    }
                }
            }

        });

        genQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answerSelected = false;
                correctAnswer = ThreadLocalRandom.current().nextInt(2, 10);
                String correctAnswerString = "Convert: " + Integer.toString(correctAnswer);
                GameActivity.this.inNumberTextView.setText(correctAnswerString);

                correctButtonNum = ThreadLocalRandom.current().nextInt(1,4);

                wrongAnswerOne = correctAnswer + generateOffset(correctAnswer);
                wrongAnswerTwo = correctAnswer + generateOffset(correctAnswer);
                wrongAnswerThree = correctAnswer + generateOffset(correctAnswer);

                //handle identical answers
                while (wrongAnswerTwo == wrongAnswerOne || wrongAnswerTwo == correctAnswer)
                    wrongAnswerTwo = correctAnswer + generateOffset(correctAnswer);
                while (wrongAnswerThree == wrongAnswerOne || wrongAnswerThree == wrongAnswerTwo || wrongAnswerThree == correctAnswer)
                    wrongAnswerThree = correctAnswer + generateOffset(correctAnswer);


                String correctAnswerInBaseString = BaseToDigit(correctAnswer, to_base);
                String wrongAnswerOneString = BaseToDigit(wrongAnswerOne, to_base);
                String wrongAnswerTwoString = BaseToDigit(wrongAnswerTwo,to_base);
                String wrongAnswerThreeString = BaseToDigit(wrongAnswerThree, to_base);

                if(correctButtonNum == 1){
                    GameActivity.this.answerOne.setText(correctAnswerInBaseString);
                    GameActivity.this.answerTwo.setText(wrongAnswerOneString);
                    GameActivity.this.answerThree.setText(wrongAnswerTwoString);
                    GameActivity.this.answerFour.setText(wrongAnswerThreeString);
                } else if (correctButtonNum == 2){
                    GameActivity.this.answerOne.setText(wrongAnswerOneString);
                    GameActivity.this.answerTwo.setText(correctAnswerInBaseString);
                    GameActivity.this.answerThree.setText(wrongAnswerTwoString);
                    GameActivity.this.answerFour.setText(wrongAnswerThreeString);
                } else if (correctButtonNum == 3){
                    GameActivity.this.answerOne.setText(wrongAnswerOneString);
                    GameActivity.this.answerTwo.setText(wrongAnswerTwoString);
                    GameActivity.this.answerThree.setText(correctAnswerInBaseString);
                    GameActivity.this.answerFour.setText(wrongAnswerThreeString);
                } else if (correctButtonNum == 4){
                    GameActivity.this.answerOne.setText(wrongAnswerOneString);
                    GameActivity.this.answerTwo.setText(wrongAnswerTwoString);
                    GameActivity.this.answerThree.setText(wrongAnswerThreeString);
                    GameActivity.this.answerFour.setText(correctAnswerInBaseString);
                }

                answerOne.setBackgroundColor(Color.LTGRAY);
                answerTwo.setBackgroundColor(Color.LTGRAY);
                answerThree.setBackgroundColor(Color.LTGRAY);
                answerFour.setBackgroundColor(Color.LTGRAY);
            }
        });
    }
}
