package cmru.kallayajairak.cmrurun;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    //explicit
    private static final String urllogo = "http://swiftcodingthai.com/cmru/cmru_logo.png";
    private static final String urlJSON = "http://swiftcodingthai.com/cmru/get_user_master.php";
    private ImageView imageView;
    private EditText userEdittext, passwordEditText;
    private String userString, passwordString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //bind widget
        imageView = (ImageView) findViewById(R.id.imageView6);
        userEdittext = (EditText) findViewById(R.id.editText5);
        passwordEditText = (EditText) findViewById(R.id.editText5);
    // load logo
        Picasso.with(this).load(urllogo).resize(150,180).into(imageView);
    }  // Main Method

    //create Inner Class
    private class SynUser extends AsyncTask<Void, Void, String> {
        // Explicit
        private Context context;
        private String strURL;

        public SynUser(Context context, String strURL) {
            this.context = context;
            this.strURL = strURL;
        }

        @Override
        protected String doInBackground(Void... params) {

            try {
                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url(strURL).build();
                Response response = okHttpClient.newCall(request).execute();
                return response.body().string();

            } catch (Exception e) {
                Log.d("29June", "e doInBack == > " + e.toString());
                return null;
            }


        }//doInback


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.d("29June", "JSON ==> "+s);
        }//onpost
    }//SynUser Class
public void clickSignIn(View view) {
    userString = userEdittext.getText().toString().trim();
    passwordString = passwordEditText.getText().toString().trim();

    // check space
    if (userString.equals("") || passwordString.equals("")) {
        MyAlert myAlert = new MyAlert();
        myAlert.myDialog(this, "Have Space", "please fill all ever blank");
    } else {
        checkuserpassword();
    }
}

    private void checkuserpassword() {
        SynUser synUser = new SynUser(this, urlJSON);
        synUser.execute();
    }

    public void clickSignUpMain(View view) {
        startActivity(new Intent(MainActivity.this, SignUpActivity.class));
    }
}   //Main Class

