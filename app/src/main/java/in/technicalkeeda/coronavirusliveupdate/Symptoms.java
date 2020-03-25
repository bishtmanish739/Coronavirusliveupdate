package in.technicalkeeda.coronavirusliveupdate;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Symptoms#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Symptoms extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Symptoms() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Symptoms.
     */
    // TODO: Rename and change types and number of parameters
    public static Symptoms newInstance(String param1, String param2) {
        Symptoms fragment = new Symptoms();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String symptoms="The COVID-19 virus affects different people in different ways.  COVID-19 is a respiratory disease and most infected people will develop mild to moderate symptoms and recover without requiring special treatment.  People who have underlying medical conditions and those over 60 years old have a higher risk of developing severe disease and death.";
        String symptoms1="Common symptoms include:\n\n";
        String symptoms2="1-fever"+'\n'+"2-tiredness"+'\n'+"3-dry cough."+'\n'+"\nOther symptoms include\n";

        String symptoms3="\n\n1-shortness of breath \n 2-aches and pains \n 3- sore throat \n 4-and very few people will report diarrhoea, nausea or a runny nose";
        String symptoms4="\n\nPeople with mild symptoms who are otherwise healthy should self-isolate and contact their medical provider or a COVID-19 information line for advice on testing and referral.\nPeople with fever, cough or difficulty breathing should call their doctor and seek medical attention";
        String symptoms5="";
        TextView textView;
        View view=inflater.inflate(R.layout.fragment_symptoms, container, false);
        textView=view.findViewById(R.id.contentSymprom);
        textView.setText(symptoms+symptoms1+symptoms2+symptoms3+symptoms4);
        // Inflate the layout for this fragment
        return view;
        // Inflate the layout for this fragment

    }
}
