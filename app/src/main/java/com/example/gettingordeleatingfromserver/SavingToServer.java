package com.example.gettingordeleatingfromserver;

import androidx.appcompat.app.AppCompatActivity;            //Go to my4app and open dashboard and see in the class name
//and not forget to refresh every time
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SavingToServer extends AppCompatActivity {
    private Button btn;
    private EditText editTextName, editTextPhone, editTextAge, editTextMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saving_to_server);

        btn = findViewById(R.id.button);
        editTextName = findViewById(R.id.editTextName);
        editTextAge = findViewById(R.id.editTextAge);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextMessage = findViewById(R.id.editTextMessage);
    }
    public void saveMethodOnClick(View view) {
        try {                                                                               //used try to catch the invalid entry in the text fields
            final ParseObject myFirstClass = new ParseObject("MySaving");
            myFirstClass.put("Message", editTextMessage.getText().toString());
            myFirstClass.put("Age", editTextAge.getText().toString());
            myFirstClass.put("Phone", editTextPhone.getText().toString());
            myFirstClass.put("Name", editTextName.getText().toString());
            if (!(TextUtils.isEmpty(editTextName.getText().toString()) || TextUtils.isEmpty(editTextPhone.getText().toString()))) {
                myFirstClass.saveInBackground(new SaveCallback() {              // we can directly save the content by without writing new Savecallback()
                    @Override
                    //and dont inmplement the exception part.
                    //But it is imp because of connection to show user weather success or not
                    public void done(ParseException e) {                        //Also i implemented the designer toast search fancy toast on google
                        if (e == null) {
                            FancyToast.makeText(SavingToServer.this,
                                    "info for " + editTextName.getText().toString() + " is saved to server",
                                    FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                            myFirstClass.saveEventually();                      // if the user currently doesn’t have a network connection, saveEventually will store the update on the device until a network connection is re-established.
                            //but saveEventually wont work here because of the exception handling.
                            editTextMessage.setText("");                        // to clear text after the saving.
                            editTextName.setText("");
                            editTextPhone.setText("");
                            editTextAge.setText("");
                        } else {
                            FancyToast.makeText(SavingToServer.this, e.getMessage(),
                                    FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                        }
                    }
                });
            } else{
                editTextName.setError("This field is required!");
                editTextPhone.setError("This field is required!");
            }
        }
        catch (Exception e){
            FancyToast.makeText(SavingToServer.this, e.getMessage(),
                    FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
        }
    }
}
