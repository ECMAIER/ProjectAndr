package com.example.admin.projectandr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CalculatorActivity extends AppCompatActivity{

    //this function takes in a String which represents a number in any base (letters MUST be in lower case)
    //and an integer representing the base of the passed string.
    //
    //It returns an integer representing the conversion of the passed string into a base 10 number
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

            digit += (int) (temp *  Math.pow(base,S.length()-i-1));

        }
        return digit;
    }

    private Button calculateButton;
    private TextView resultText;
    private EditText numberInBase;
    private EditText base;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        resultText = findViewById(R.id.result);
        calculateButton = findViewById(R.id.calculate_button);
        numberInBase = findViewById(R.id.inNumber);
        base = findViewById(R.id.inBase);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //collect input from text views
                String inNumber = numberInBase.getText().toString();
                int inBase = Integer.parseInt(base.getText().toString());

                //convert to base 10
                int output = fromDigits(inNumber, inBase);

                //display result
                CalculatorActivity.this.resultText.setText(Integer.toString(output));
            }
        });

    }
}
