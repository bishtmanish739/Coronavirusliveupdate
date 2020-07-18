package in.coronaupdate.coronavirusliveupdate;
import com.google.gson.Gson;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.ArrayMap;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SplashScreen extends AppCompatActivity {
    List<CoronaVirusData> dataset= new ArrayList<CoronaVirusData>();
    List<CoronaVirusData> datasetdiff= new ArrayList<CoronaVirusData>();
    List<CoronaVirusData> datasetdiff2= new ArrayList<CoronaVirusData>();
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private  void setup(){

        Map<String, String> mHeader = new ArrayMap<>();
        Map<String, String> mParm = new ArrayMap<>();
        // mUserController.getRequest(mHeader,mParm,url,responslistener);

        String Listurl = "https://api.covid19api.com/summary";

        UserController ListUserController=new UserController(SplashScreen.this);
        ListUserController.getRequest(mHeader,mParm,Listurl,allresponslistener);

    }

    public <T> void setList(String key, List<T> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        set(key, json);
    }
    public void set(String key, String value) {

        SharedPreferences.Editor editor = getSharedPreferences("MY_PREF", MODE_PRIVATE).edit();
            editor.putString(key, value);
            editor.commit();

    }

    private final ResponseListener allresponslistener = new ResponseListener() {

        @Override
        public void onRequestStart() {

        }

        @Override
        public void onSuccess(String response) {
            try {




                Log.d("CoronA",response);
                JSONObject allJsonObject=new JSONObject(response);

                JSONArray jsonArray=allJsonObject.getJSONArray("Countries");// new JSONArray(response);


                //dataset.clear();
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject=jsonArray.getJSONObject(i);
                    Log.d("thisarray",jsonObject.toString());

                    String country=jsonObject.getString("Country");
                    int totalcases=jsonObject.getInt("TotalConfirmed");
                    String Totalcase=Integer.toString(totalcases);
                    int today=jsonObject.getInt("NewConfirmed");
                    String todayCases=Integer.toString(today);
                    int Tdead=jsonObject.getInt("TotalDeaths");
                    String totaldeads=Integer.toString(Tdead);


                    int todayDeaths=jsonObject.getInt("NewDeaths");
                    String todaysDeaths=Integer.toString(todayDeaths);
                    int recovered=jsonObject.getInt("TotalRecovered");
                    String totalrecovered=Integer.toString(recovered);

                    CoronaVirusData coronaVirusData=new CoronaVirusData();
                    coronaVirusData.setCountryName(country);
                    coronaVirusData.setTotalCases(Totalcase);
                    coronaVirusData.setTotalRecovered(totalrecovered);
                    coronaVirusData.setTotalDeath(totaldeads);
                    coronaVirusData.setTodaycases(todayCases);
                    coronaVirusData.setTodaydeads(todaysDeaths);
                    dataset.add(coronaVirusData);
                    datasetdiff.add(coronaVirusData);



                }
                Log.d("sizeofdataset",Integer.toString(dataset.size()));
                setList("dataList",dataset);
                setList("dataList2",datasetdiff);
                for(int i=0;i<datasetdiff.size();i++){
                    CoronaVirusData thisdata=datasetdiff.get(i);
                    Log.d("namecountry",thisdata.getCountryName()+'\n');
                }








            } catch (JSONException e) {
                Log.d("allresponse",e.toString());
                e.printStackTrace();
            }
        }

        @Override
        public void onError(VolleyError error) {
            error.printStackTrace();
            Toast.makeText(SplashScreen.this, "Network connection Issue Refresh Again ", Toast.LENGTH_SHORT).show();

        }
    };

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        setContentView(R.layout.activity_splash_screen);
        String url = "https://api.covid19api.com/summary";
        Map<String, String> mHeader = new ArrayMap<>();
        Map<String, String> mParm = new ArrayMap<>();
        String Listurl = "https://api.covid19api.com/summary";

        UserController ListUserController=new UserController(SplashScreen.this);
        ListUserController.getRequest(mHeader,mParm,Listurl,allresponslistener);



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
                    public void onResponse(JSONObject global) {
                       // JSONObject jsonObject = new JSONObject(response);
                        JSONObject jsonObject= null;
                        try {
                            jsonObject = global.getJSONObject("Global");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        int totalcase= 0;
                        try {
                            totalcase = jsonObject.getInt("TotalConfirmed");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        int totaldeath= 0;
                        try {
                            totaldeath = jsonObject.getInt("TotalDeaths");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        int totalrecovered= 0;
                        try {
                            totalrecovered = jsonObject.getInt("TotalRecovered");
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
        },3500);
    }
}
