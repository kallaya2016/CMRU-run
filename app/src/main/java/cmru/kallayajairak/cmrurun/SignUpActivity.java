package cmru.kallayajairak.cmrurun;

import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

import cmru.kallayajairak.cmrurun.R;

public class SignUpActivity extends AppCompatActivity {

    //Explicit
    private EditText nameEditText, passwordEditText, userEditText;
    private RadioGroup radioGroup;
    private RadioButton avata0RadioButton, avata1RadioButton,
            avata2RadioButton, avata3RadioButton, avata4RadioButton;
    private String nameString, userString, passwordString, avataString;
    private static final String urlPHP = "http://swiftcodingthai.com/cmru/add_user_master.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Bind Widget
        nameEditText = (EditText) findViewById(R.id.editText);
        userEditText = (EditText) findViewById(R.id.editText2);
        passwordEditText = (EditText) findViewById(R.id.editText3);
        radioGroup = (RadioGroup) findViewById(R.id.avatar);
        avata0RadioButton = (RadioButton) findViewById(R.id.radioButton);
        avata1RadioButton = (RadioButton) findViewById(R.id.radioButton2);
        avata2RadioButton = (RadioButton) findViewById(R.id.radioButton3);
        avata3RadioButton = (RadioButton) findViewById(R.id.radioButton4);
        avata4RadioButton = (RadioButton) findViewById(R.id.radioButton5);

        // Radio Controller
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButton:
                        avataString = "0";
                        break;
                    case R.id.radioButton2:
                        avataString = "1";
                        break;
                    case R.id.radioButton3:
                        avataString = "2";
                        break;
                    case R.id.radioButton4:
                        avataString = "3";
                        break;
                    case R.id.radioButton5:
                        avataString = "4";
                        break;
                }
            }
        });

    }   // Main Method

    public void clickSignUpSign(View view) {
        // Get Value From Edit Text
        nameString = nameEditText.getText().toString().trim();
        userString = userEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();
        // Click Space
        if (nameString.equals("") || userString.equals("") || passwordString.equals("")) {
            // have Space
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this, "มีช่องว่าง", "กรุณากรอกทุกช่องค่ะ");
        } else if (checkChooseAvata()) {
            // checked
            confirmData();
        } else {

            //not Checked
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this, "ยังไม่เลือก Avata", "กรุณาเลือก Avata ด้วยค่ะ");

        }


    } // ClickSign


    private void confirmData() {
        Mydata mydata = new Mydata();
        int[] avataInts = mydata.getAvataInts();
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setIcon(avataInts[Integer.parseInt(avataString)]);
        builder.setTitle(nameString);
        builder.setMessage("User =" + userString + "\n" + "Password = " + passwordString);
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });

        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                uploadUsertoServer();
                dialog.dismiss();

            }
        });
        builder.show();


        // confirmdata

    }

    private void uploadUsertoServer() {
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormEncodingBuilder()
                .add("isAdd", "true")
                .add("Name", nameString)
                .add("User", userString)
                .add("Password", passwordString)
                .add("Avata", avataString)
                .build();
        Request.Builder builder = new Request.Builder();
        Request request = builder.url(urlPHP).post(requestBody).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.d("28june", "e =" + e.toString());
            }

            @Override
            public void onResponse(Response response) throws IOException {
                finish();

            }
        });



    }//upload

    private boolean checkChooseAvata() {

        boolean status = true;
        if (avata0RadioButton.isChecked() ||
                avata1RadioButton.isChecked() ||
                avata2RadioButton.isChecked() ||
                avata3RadioButton.isChecked() ||
                avata4RadioButton.isChecked()) {
            //have Checked
            status = true;
        } else {
            status = false;
        }
        return status;
    }



}   // Main Class

