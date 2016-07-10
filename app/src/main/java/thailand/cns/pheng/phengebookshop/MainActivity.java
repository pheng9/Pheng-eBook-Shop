package thailand.cns.pheng.phengebookshop;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    // Explicit
    private EditText userEditText, passwordEditText;
    private String userString, passwordString;
    //private static final String urlJSON = "http://swiftcodingthai.com/9july/get_user_pheng.php";
    private String urlJSON;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Bind Widget
        userEditText = (EditText) findViewById(R.id.editText5);
        passwordEditText = (EditText) findViewById(R.id.editText6);

        MyConstant myConstant = new MyConstant();
        urlJSON = myConstant.getUrlJSONuser();

    }   // Main Method

    private class SynUserTABLE extends AsyncTask<Void, Void, String> {  // <ก่อนโหลด, กำลังโหลด, หลังโหลด>

        // Explicit
        private Context context;
        private String myURL, myUserString, myPasswordString, truePassword, loginNameString, loginSurnameString;
        private boolean statusABoolean = true;

        public SynUserTABLE(Context context, String myURL, String myUserString, String myPasswordString) {
            this.context = context;
            this.myURL = myURL;
            this.myUserString = myUserString;
            this.myPasswordString = myPasswordString;
        }

        @Override
        protected String doInBackground(Void... voids) {

            try {

                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url(myURL).build();
                Response response = okHttpClient.newCall(request).execute();
                return response.body().string();

            } catch (Exception e) {
                Log.d("ShopV1", "e doInBack ==> " + e.toString());
                return null;
            }
        }   // doInBack

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("ShopV1", "JSON ==> " + s);

            try {

                JSONArray jsonArray = new JSONArray(s);
                for (int i=0; i<jsonArray.length(); i+=1) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    if (myUserString.equals(jsonObject.getString("user"))) {
                        statusABoolean = false;
                        truePassword = jsonObject.getString("password");
                        loginNameString = jsonObject.getString("name");
                        loginSurnameString = jsonObject.getString("surname");
                    }

                }   // for
                if (statusABoolean) {
                    MyAlert myAlert = new MyAlert();
                    myAlert.myDialog(context, "ไม่มี User นี้", "ไม่มี " + myUserString + " ในฐานข้อมูลของเรา");
                } else if (myPasswordString.equals(truePassword)) {
                    Toast.makeText(context, "Welcome " + loginNameString + " " + loginSurnameString, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, ServiceActivity.class);
                    intent.putExtra("Name", loginNameString);
                    intent.putExtra("Surname", loginSurnameString);
                    startActivity(intent);
                    finish();
                } else {
                    MyAlert myAlert = new MyAlert();
                    myAlert.myDialog(context, "Password False", "Please try again password false!");
                }

            } catch (Exception e) {
                Log.d("ShopV1", "e onPost ==> " + e.toString());
            }

        }   // onPost

    }   // SynUser Class


    public void clickSignIn(View view) {

        userString = userEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();

        // Check Space
        if (userString.equals("") || passwordString.equals("")) {
            // Have Space
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this, "Have Space", "Please fill all every blank");

        } else {
            // No Space
            SynUserTABLE synUserTABLE = new SynUserTABLE(this, urlJSON, userString, passwordString);
            synUserTABLE.execute();

        }

    }   // clickSignIn

    public void clickSignUpMain(View view) {
        startActivity(new Intent(MainActivity.this, SignUpActivity.class));
    }

}   // Main Class
