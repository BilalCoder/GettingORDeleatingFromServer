package com.example.gettingordeleatingfromserver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.shashank.sony.fancytoastlib.FancyToast;

public class UpdateTheData extends AppCompatActivity {

    private EditText name, updateName, id, updateAge, updatePhone, updateMessage;
    private TextView textViewId;
    private Button btnGetData, btnUpdateData;
    private String objectId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_the_data);

        name = findViewById(R.id.editTextName);
        updateName = findViewById(R.id.editTextUpdateName);
        id = findViewById(R.id.editTextId);
        updateAge = findViewById(R.id.editTextUpdateAge);
        updatePhone = findViewById(R.id.editTextUpdatePhone);
        updateMessage = findViewById(R.id.editTextUpdateMessage);
        textViewId = findViewById(R.id.textViewId);
    }

    public void getData(View view) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("MySaving");
        if (!(TextUtils.isEmpty(name.getText().toString())))
            query.whereEqualTo("Name", name.getText().toString());
        if (!(TextUtils.isEmpty(id.getText().toString())))
            query.whereEqualTo("objectId", id.getText().toString());
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            public void done(ParseObject info, ParseException e) {
                if (e == null) {
                    objectId = info.getObjectId().toString();
                    String mName = info.getString("Name");
                    String mAge =info.getString("Age");
                    String mPhone = info.getString("Phone");
                    String mMessage = info.getString("Message");
                    textViewId.setText("Id - " + objectId);
                    updateName.setText(mName);
                    updateAge.setText(mAge);
                    updatePhone.setText(mPhone);
                    updateMessage.setText(mMessage);
                } else {
                    FancyToast.makeText(UpdateTheData.this, e.getMessage(),
                            FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                }
            }
        }
        );
    }

    public void updateData(View view){

        ParseQuery<ParseObject> query = ParseQuery.getQuery("MySaving");           //MySaving is the class name
        // Retrieve the object by id
        query.getInBackground(objectId.toString(), new GetCallback<ParseObject>() {
            public void done(ParseObject myDataUpdate, ParseException e) {
                if (e == null) {
                    // Now let's update it with some new data. In this case, everything other than Id
                    // will get sent to your Parse Server.
                    myDataUpdate.put("Name", updateName.getText().toString());
                    myDataUpdate.put("Age", updateAge.getText().toString());
                    myDataUpdate.put("Phone", updatePhone.getText().toString());
                    myDataUpdate.put("Message", updateMessage.getText().toString());
                    myDataUpdate.saveInBackground();
                    FancyToast.makeText(UpdateTheData.this,
                            "Your data is updated. Please retrieve the data to check!",
                            FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();

                }
                else {
                    // Failed
                    FancyToast.makeText(UpdateTheData.this, e.getMessage(),
                            FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                }
            }
        });
    }
}
