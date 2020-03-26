package in.technicalkeeda.coronavirusliveupdate;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.ArrayMap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import in.technicalkeeda.coronavirusliveupdate.R;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public HomeFragment() {
        // Required empty public constructor
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }
    TextView totalcases,totalDeaths,ToatalRecoverds;
    ImageView SearchButton;
    EditText Search;
    RecyclerView recyclerView;
    List<CoronaVirusData> dataset= new ArrayList<CoronaVirusData>();
    List<CoronaVirusData> datasetdiff= new ArrayList<CoronaVirusData>();
    List<CoronaVirusData> datasetdiff2= new ArrayList<CoronaVirusData>();

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {





        View view=inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView=view.findViewById(R.id.RecyclerView);
        totalcases=view.findViewById(R.id.TotalCases);
        totalDeaths=view.findViewById(R.id.TotalDeath);
        ToatalRecoverds=view.findViewById(R.id.TotalRecovered);
        Search=view.findViewById(R.id.SearchCountry);
        SearchButton=view.findViewById(R.id.searchButton);
        SearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s=" ";
               s=Search.getText().toString();
               datasetdiff2.addAll(datasetdiff);
                datasetdiff.addAll(datasetdiff2);
                CoronaVirusAdapter coronaVirusAdapter=new CoronaVirusAdapter(datasetdiff);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(coronaVirusAdapter);
                coronaVirusAdapter.getFilter().filter(s);
                datasetdiff.addAll(datasetdiff2);
                //Toast.makeText(getContext(), Integer.toString(datasetdiff2.size()), Toast.LENGTH_SHORT).show();
               // Toast.makeText(getContext(), dataset.toString(), Toast.LENGTH_SHORT).show();

            }
        });



        String url = "https://corona.lmao.ninja/all";

       // UserController mUserController=new UserController(getContext());
        SharedPreferences prefs = getContext().getSharedPreferences("MY_PREF", MODE_PRIVATE);
        String totalcase = prefs.getString("totalcase", "");//"No name defined" is the default value.
        String totaldeads = prefs.getString("totaldeads", "");
        String totalRecoverded=prefs.getString("recovered","");

        totalcases.setText(totalcase);
        totalDeaths.setText(totaldeads);
        ToatalRecoverds.setText(totalRecoverded);//0 is the default value.



        Map<String, String> mHeader = new ArrayMap<>();
        Map<String, String> mParm = new ArrayMap<>();
      // mUserController.getRequest(mHeader,mParm,url,responslistener);

                String Listurl = "https://corona.lmao.ninja/countries";

                UserController ListUserController=new UserController(getContext());
                ListUserController.getRequest(mHeader,mParm,Listurl,allresponslistener);




        return view;
    }

    private final ResponseListener allresponslistener = new ResponseListener() {

        @Override
        public void onRequestStart() {

        }

        @Override
        public void onSuccess(String response) {
            try {




                JSONArray jsonArray=new JSONArray(response);

                //dataset.clear();
                for(int i=0;i<jsonArray.length();i++){
                    String flag="";
                    JSONObject jsonObject=jsonArray.getJSONObject(i);
                    String country=jsonObject.getString("country");
                     int totalcases=jsonObject.getInt("cases");
                     String Totalcase=Integer.toString(totalcases);
                    int today=jsonObject.getInt("todayCases");
                    String todayCases=Integer.toString(today);
                    int Tdead=jsonObject.getInt("deaths");
                    String totaldeads=Integer.toString(Tdead);


                    int todayDeaths=jsonObject.getInt("todayDeaths");
                    String todaysDeaths=Integer.toString(todayDeaths);
                    int recovered=jsonObject.getInt("recovered");
                    String totalrecovered=Integer.toString(recovered);
                    JSONObject countryobject=jsonObject.getJSONObject("countryInfo");
                   flag= countryobject.getString("flag");
                   CoronaVirusData coronaVirusData=new CoronaVirusData();
                   coronaVirusData.setCountryName(country);
                   coronaVirusData.setFlagUrl(flag);
                   coronaVirusData.setTotalCases(Totalcase);
                   coronaVirusData.setTotalRecovered(totalrecovered);
                   coronaVirusData.setTotalDeath(totaldeads);
                   coronaVirusData.setTodaycases(todayCases);
                   coronaVirusData.setTodaydeads(todaysDeaths);
                   dataset.add(coronaVirusData);
                   datasetdiff.add(coronaVirusData);


                }
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                CoronaVirusAdapter coronaVirusAdapter=new CoronaVirusAdapter();


                coronaVirusAdapter.setProduct(dataset);
                CoronaVirusAdapter coronaVirusAdapter1=new CoronaVirusAdapter(dataset);
                recyclerView.setAdapter(coronaVirusAdapter1);
               // coronaVirusAdapter.getFilter().filter("india");
               /// Toast.makeText(getContext(), Integer.toString(dataset.size()), Toast.LENGTH_SHORT).show();
               // coronaVirusAdapter.getFilter().filter("india");
               // JSONObject jsonObject = new JSONObject(response);

                //JSONArray jsonArray = jsonObject.getJSONArray("data");
                Log.d("allresponse",response.toString());


                //Toast.makeText(CustomerListActivity.this, response, Toast.LENGTH_SHORT).show();



            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onError(VolleyError error) {
            error.printStackTrace();
             Toast.makeText(getContext(), "Network connection Issue Refresh Again ", Toast.LENGTH_SHORT).show();

        }
    };

    private final ResponseListener responslistener = new ResponseListener() {

        @Override
        public void onRequestStart() {

        }

        @Override
        public void onSuccess(String response) {
            try {





                JSONObject jsonObject = new JSONObject(response);

                //JSONArray jsonArray = jsonObject.getJSONArray("data");
                Log.d("gggggkkkk",jsonObject.toString());
                int totalcase=jsonObject.getInt("cases");
                int totaldeath=jsonObject.getInt("deaths");
                int totalrecovered=jsonObject.getInt("recovered");
                totalcases.setText(Integer.toString(totalcase));
                totalDeaths.setText(Integer.toString(totaldeath));
                ToatalRecoverds.setText(Integer.toString(totalrecovered));
                //Toast.makeText(CustomerListActivity.this, response, Toast.LENGTH_SHORT).show();



            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onError(VolleyError error) {
            error.printStackTrace();
            // Toast.makeText(CustomerListActivity.this, "error", Toast.LENGTH_SHORT).show();

        }
    };
}