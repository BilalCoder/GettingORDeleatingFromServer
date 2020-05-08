package com.example.gettingordeleatingfromserver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DeletingFromServer extends AppCompatActivity {
    private Button btnDelete;
    private EditText editTextDeleteName, editTextDeletePhone, editTextDeleteAge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deleting_from_server);
        editTextDeleteName = findViewById(R.id.editTextDeleteName);
        editTextDeleteAge = findViewById(R.id.editTextDeleteAge);
        editTextDeletePhone = findViewById(R.id.editTextDeletePhone);
    }
    public void deleteButtonClicked(View view){

    }
}
