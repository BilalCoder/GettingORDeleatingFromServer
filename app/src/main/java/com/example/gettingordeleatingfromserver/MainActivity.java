package com.example.gettingordeleatingfromserver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class MainActivity extends AppCompatActivity {       //Back4App email is 88 and dont forget to go to MySaving class to see the objects in the database
    private Button btn1, btn2, btn3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ParseInstallation.getCurrentInstallation().saveInBackground();
        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
    }

    public void openSaveToServer(View view){
        Intent intent = new Intent(this, SavingToServer.class);
        startActivity(intent);
    }
    public void openDeleteFromServer(View view){
        Intent intent = new Intent(this, DeletingFromServer.class);
        startActivity(intent);
    }
    public void openRetrieveFromServer(View view){
        Intent intent = new Intent(this, RetreivingFromServer.class);
        startActivity(intent);
    }

}


