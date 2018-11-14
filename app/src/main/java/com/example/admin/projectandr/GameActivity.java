package com.example.admin.projectandr;

//Game activity ... blank text view, update as needed
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



public class GameActivity extends AppCompatActivity {

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

    private Button genQuestionButton;
    private TextView inNumberTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //hard coded conversion values, later these will be brought in by an intent from
        //the mode select view.  They may later be arrays so that users can practice different
        //conversions in the same game
        int from_base = 10;
        int to_base = 2;

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

    }
}
