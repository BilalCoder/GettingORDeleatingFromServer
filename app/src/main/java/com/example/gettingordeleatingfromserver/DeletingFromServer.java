package com.example.gettingordeleatingfromserver;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.parse.DeleteCallback;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialog;
import com.shashank.sony.fancydialoglib.FancyAlertDialogListener;
import com.shashank.sony.fancydialoglib.Icon;
import com.shashank.sony.fancytoastlib.FancyToast;
import java.util.List;

public class DeletingFromServer extends AppCompatActivity {
    private EditText id;
    private String mId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deleting_from_server);
        id = findViewById(R.id.editText);
            }

    public void goButtonClicked(View view) {
        mId = id.getText().toString();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("MySaving");
        if (!(TextUtils.isEmpty(id.getText().toString()))) {
            query.whereEqualTo("objectId", id.getText().toString());
            query.getFirstInBackground(new GetCallback<ParseObject>() {
                public void done(ParseObject data, ParseException e) {
                    if (e == null) {
                        String name = data.getString("Name");
                        String age = data.getString("Age");
                        String phone = data.getString("Phone");
                        String message = data.getString("Message");
                        //Fancy alert dialog starts from here
                        new FancyAlertDialog.Builder(DeletingFromServer.this)
                                .setTitle("Your information found")
                                .setBackgroundColor(Color.parseColor("#303F9F"))  //Don't pass R.color.colorvalue
                                .setMessage("\nName - " + name + "\n" + "Age - " + age + "\nPhone - " + phone + "\nMessage - " + message)
                                .setNegativeBtnText("Not my data")
                                .setPositiveBtnBackground(Color.parseColor("#FF4081"))  //Don't pass R.color.colorvalue
                                .setPositiveBtnText("Ok")
                                .setNegativeBtnBackground(Color.parseColor("#FFA9A7A8"))  //Don't pass R.color.colorvalue
                                .setAnimation(Animation.POP)
                                .isCancellable(true)
                                .setIcon(R.drawable.ic_info_outline_black_24dp, Icon.Visible)
                                .OnPositiveClicked(new FancyAlertDialogListener() {
                                    @Override
                                    public void OnClick() {
                                        FancyToast.makeText(DeletingFromServer.this, "You may now click the Delete button to delete your data",
                                                FancyToast.LENGTH_LONG, FancyToast.INFO, true).show();
                                    }
                                })
                                .OnNegativeClicked(new FancyAlertDialogListener() {
                                    @Override
                                    public void OnClick() {
                                        Toast.makeText(getApplicationContext(), "get your Id or email us", Toast.LENGTH_SHORT).show();
                                        finish();
                                    }
                                })
                                .build();
                        //Fancy Alert ends here


                    } else {
                        FancyToast.makeText(DeletingFromServer.this, e.getMessage(),
                                FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                    }
                }
            });
        }
        else
            Toast.makeText(getApplicationContext(),"Enter the id", Toast.LENGTH_SHORT).show();
    }

    public void onDeleteClicked(View view) {

        final ParseQuery<ParseObject> dataEntries = ParseQuery.getQuery("MySaving");      // Query parameters based on the item id
        dataEntries.whereEqualTo("objectId", mId);
            dataEntries.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(final List<ParseObject> entry, ParseException e) {
                    if (e == null) {
                        entry.get(0).deleteInBackground(new DeleteCallback() {
                            @Override
                            public void done(ParseException ex) {
                                if (ex == null) {
                                    // Success
                                    FancyToast.makeText(DeletingFromServer.this,
                                            "Your data is deleted. Please retrieve the data again to check!",
                                            FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                                } else {
                                    // Failed
                                    FancyToast.makeText(DeletingFromServer.this, ex.getMessage(),
                                            FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                                                    }
                                                }
                                            });
                                        }
                    else {
                        // Something is wrong
                        FancyToast.makeText(DeletingFromServer.this, e.getMessage(),
                                FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                    }
                }
            });
        }
    }
