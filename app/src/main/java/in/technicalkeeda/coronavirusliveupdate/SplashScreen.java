package in.technicalkeeda.coronavirusliveupdate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        String url = "https://corona.lmao.ninja/all";



        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("first_name", "S");
            jsonObject.put("last_name", "K");
        }

        catch(Exception e)
        {}

        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                       // JSONObject jsonObject = new JSONObject(response);

                        int totalcase= 0;
                        try {
                            totalcase = jsonObject.getInt("cases");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        int totaldeath= 0;
                        try {
                            totaldeath = jsonObject.getInt("deaths");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        int totalrecovered= 0;
                        try {
                            totalrecovered = jsonObject.getInt("recovered");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        SharedPreferences.Editor editor = getSharedPreferences("MY_PREF", MODE_PRIVATE).edit();
                        editor.putString("totalcase", Integer.toString(totalcase));
                        editor.putString("totaldeads", Integer.toString(totaldeath));
                        editor.putString("recovered", Integer.toString(totalrecovered));
                        editor.apply();
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", error.toString());
                    }
                }
        );

// add it to the RequestQueue
        queue.add(getRequest);
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent =new Intent(SplashScreen.this,MainActivity.class);
                startActivity(intent);
                finish();

            }
        },3000);
    }
}
