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

    private Button calculateButton;
    private TextView resultText;
    private EditText numberInBase;
    private EditText base;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        resultText = findViewById(R.id.result);
        calculateButton = findViewById(R.id.calculate_button);
        numberInBase = findViewById(R.id.number_in_base);
        base = findViewById(R.id.number_in_base);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inNumber = numberInBase.getText().toString();
                String inBaseStr = base.getText().toString();

                int inBaseNum = Integer.parseInt(inBaseStr);

                int output = fromDigits(inNumber,inBaseNum);



                GameActivity.this.resultText.setText(Integer.toString(output));

            }
        });




    }
}
