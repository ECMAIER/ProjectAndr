package com.example.admin.projectandr;

//Game activity ... blank text view, update as needed
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;


public class GameActivity extends AppCompatActivity {

    CheckBox left_binary, left_decimal, left_hex, right_binary, right_decimal, right_hex;
    Button enter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //Get instances of CheckBoxes and the button from activity_game.xml
        left_binary = findViewById(R.id.left_binary);
        left_decimal = findViewById(R.id.left_decimal);
        left_hex =  findViewById(R.id.left_hex);
        right_binary =  findViewById(R.id.right_binary);
        right_decimal =  findViewById(R.id.right_decimal);
        right_hex =  findViewById(R.id.right_hex);

        enter = findViewById(R.id.enter_button);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameActivity.this, QuizActivty.class);
                startActivity(intent);
            }
        });

        left_binary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (left_binary.isChecked()) {
                    Toast.makeText(getApplicationContext(), "checked", Toast.LENGTH_LONG).show();
                }
            }
        });

        left_decimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( left_decimal.isChecked()){
                    Toast.makeText(getApplicationContext(), "checked",Toast.LENGTH_LONG).show();
                }
            }
        });

        left_hex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(left_hex.isChecked()){
                    Toast.makeText(getApplicationContext(), "checked", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    //public void addListenerOnButtonClick(){

        //Get instances of CheckBoxes and the button from activity_game.xml
        //left_binary = (CheckBox) findViewById(R.id.left_binary);
        //left_decimal = (CheckBox) findViewById(R.id.left_decimal);
        //left_hex = (CheckBox) findViewById(R.id.left_hex);
        //right_binary = (CheckBox) findViewById(R.id.right_binary);
        //right_decimal = (CheckBox) findViewById(R.id.right_decimal);
        //right_hex = (CheckBox) findViewById(R.id.right_hex);

        //enter = (Button) findViewById(R.id.enter_button);

        //enter.setOnClickListener(new View.OnClickListener() {
           // @Override
            //public void onClick(View v) {
              //  Intent intent = new Intent(GameActivity.this, QuizActivity.class);
                //startActivity(intent);
          //  }
      //  });
    //}
}
