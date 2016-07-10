package thailand.cns.pheng.phengebookshop;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class ServiceActivity extends AppCompatActivity {

    // Explicit
    private TextView textView;
    private ListView listView;
    private String nameString, surnameString, urlJSON;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        // Setup Constant
        MyConstant myConstant = new MyConstant();
        urlJSON = myConstant.getUrlJSONproduct();

        // Initial Widget
        textView = (TextView) findViewById(R.id.textView7);
        listView = (ListView) findViewById(R.id.listView);

        // Show View
        nameString = getIntent().getStringExtra("Name");
        surnameString = getIntent().getStringExtra("Surname");
        textView.setText("Welcome " + nameString + " " + surnameString);

        // Syn and Create ListView
        SynProduct synProduct = new SynProduct(this, urlJSON, listView);
        synProduct.execute();

    }   // Main Method

    private class SynProduct extends AsyncTask<Void, Void, String> {

        // Explicit
        private Context context;
        private String myURL;
        private ListView myListView;

        public SynProduct(Context context, String myURL, ListView myListView) { // Constructor
            this.context = context;
            this.myURL = myURL;
            this.myListView = myListView;
        }

        @Override
        protected String doInBackground(Void... params) {

            try {

                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url(myURL).build();
                Response response = okHttpClient.newCall(request).execute();
                return response.body().string();

            } catch (Exception e) {
                Log.d("ShopV2", "e doInBack ==> " + e.toString());
                return null;
            }

        }   // doInBack

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("ShopV2", "JSON ==> " + s);
        }   // onPost

    }   // SynProduct Class

}   // Main Class
