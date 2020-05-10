package com.example.gettingordeleatingfromserver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.parse.ParseInstallation;

public class MainActivity extends AppCompatActivity {       //Back4App email is 88 and dont forget to go to MySaving class to see the objects in the database
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ParseInstallation.getCurrentInstallation().saveInBackground();
    }

    public void openSaveToServer(View view){
        Intent intent = new Intent(this, SavingToServer.class);
        startActivity(intent);
    }
    public void openRetrieveFromServer(View view){
        Intent intent = new Intent(this, RetreivingFromServer.class);
        startActivity(intent);
    }
    public void openUpdateInServer(View view){
        Intent intent = new Intent(this, UpdateTheData.class);
        startActivity(intent);
    }
    public void openDeleteFromServer(View view){
        Intent intent = new Intent(this, DeletingFromServer.class);
        startActivity(intent);
    }

}


