package com.example.admin.projectandr;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class TutorialActivity extends AppCompatActivity {
    //Buttons used
    private Button BinDem;
    private Button BinHex;
    private Button HexBin;
    private Button HexDec;
    private Button DecBin;
    private Button DecHex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        //Buttons found by id in activity_tutorial
        Button BinDem = findViewById(R.id.binarydecimal);
        Button BinHex = findViewById(R.id.binhex);
        Button HexBin = findViewById(R.id.hexbin);
        Button HexDec = findViewById(R.id.hexdec);
        Button DecBin = findViewById(R.id.decbin);
        Button DecHex = findViewById(R.id.dechex);

        //new Intent used to pass an Intent and start activity
        //this will open the Youtube app and go to the uri in function
        BinDem.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.youtube.com/watch?v=VLflTjd3lWA")));
                Log.i("Video", "Video Playing....");
            }
        });

        BinHex.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.youtube.com/watch?v=QJW6qnfhC70")));
                Log.i("Video", "Video Playing....");
            }
        });

        HexBin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.youtube.com/watch?v=D_YC6DSPpQE")));
                Log.i("Video", "Video Playing....");
            }
        });

        HexDec.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.youtube.com/watch?v=pg-HEGBpCQk&t=1s")));
                Log.i("Video", "Video Playing....");
            }
        });

        DecBin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.youtube.com/watch?v=rsxT4FfRBaM&t=11s")));
                Log.i("Video", "Video Playing....");
            }
        });

        DecHex.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.youtube.com/watch?v=QJW6qnfhC70&t=1s")));
                Log.i("Video", "Video Playing....");
            }
        });

    }
}
