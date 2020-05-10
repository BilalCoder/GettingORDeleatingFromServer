package com.example.gettingordeleatingfromserver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.shashank.sony.fancytoastlib.FancyToast;

public class RetreivingFromServer extends AppCompatActivity {
    private EditText editTextRetrieveName, editTextRetrievePhone, editTextRetrieveAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retreiving_from_server);
        editTextRetrieveName = findViewById(R.id.editTextRetrieveName);
        editTextRetrieveAge = findViewById(R.id.editTextRetrieveAge);
        editTextRetrievePhone = findViewById(R.id.editTextRetrievePhone);
    }

    public void retrieveButtonClicked(View view) {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("MySaving");
        if( ! (TextUtils.isEmpty(editTextRetrieveName.getText().toString())))
            query.whereEqualTo("Name", editTextRetrieveName.getText().toString());
        if( ! (TextUtils.isEmpty(editTextRetrievePhone.getText().toString())))
            query.whereEqualTo("Phone", editTextRetrievePhone.getText().toString());
        if( ! (TextUtils.isEmpty(editTextRetrieveAge.getText().toString())))
            query.whereEqualTo("Age", editTextRetrieveAge.getText().toString());
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            public void done(ParseObject data, ParseException e) {
                if (e == null) {
                    String objectId = data.getObjectId().toString();
                    String createdAt = data.getString("createdAt");
                    String name = data.getString("Name");
                    int age = Integer.parseInt(data.getString("Age"));
                    String phone = data.getString("Phone");
                    String message = data.getString("Message");
//                    FancyToast.makeText(RetreivingFromServer.this,
//                            "\nName - " + name + "\n" + "Age - " + age + "\nPhone - "+ phone + "\nMessage - " + message,
//                            FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();

                    // inflate the layout of the popup window
                    LayoutInflater inflater = (LayoutInflater)
                            getSystemService(LAYOUT_INFLATER_SERVICE);
                    View popupView = inflater.inflate(R.layout.popup_window, null);

                    // create the popup window
                    int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                    int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                    boolean focusable = true; // lets taps outside the popup also dismiss it
                    final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
                    popupWindow.setElevation(50);
                    // show the popup window
                    // which view you pass in doesn't matter, it is only used for the window tolken
                    ((TextView)popupWindow.getContentView().findViewById(R.id.popUpText)).setText("ObjectId - " + objectId+ "\nName    - " + name + "\n" + "Age     - " + age + "\nPhone   - "+ phone + "\nMessage - " + message);
                    popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);

                    // dismiss the popup window when touched
                    popupView.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            popupWindow.dismiss();
                            return true;
                        }
                    });

                } else {
                    FancyToast.makeText(RetreivingFromServer.this, e.getMessage(),
                            FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                }
            }
        });
    }
}
