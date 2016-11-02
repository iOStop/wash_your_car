package com.iostop.wash_your_car;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;


public class SettingsFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

//    private OnFragmentInteractionListener mListener;

    public SettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
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
        final View rootView = inflater.inflate(R.layout.fragment_settings, container, false);
        final AutoCompleteTextView textView =
                (AutoCompleteTextView) rootView.findViewById(R.id.city_pick_autocompletetextview);
        final String[] cities = new String[]{"Moscow", "Mordovia", "Mordor"};
        final ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, cities);
        textView.setAdapter(arrayAdapter);

        //check city and put it to sharedPreferences
        rootView.findViewById(R.id.city_pick_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city = textView.getText().toString();
                if (arrayAdapter.getPosition(city) != -1) {
                    SharedPreferences sharedPrefs = getActivity()
                            .getSharedPreferences(getResources()
                                    .getString(R.string.shared_prefs_name), Context.MODE_PRIVATE);
                    SharedPreferences.Editor sharedPrefsEditor = sharedPrefs.edit();
                    sharedPrefsEditor.putString("city", city);
                    sharedPrefsEditor.apply();
                    getActivity()
                            .getFragmentManager()
                            .beginTransaction()
                            .setCustomAnimations(android.R.animator.fade_in, R.animator.slide_down)
                            .remove(SettingsFragment.this)
                            .commit();
                }
                else {
                    Toast.makeText(getActivity(),
                            "City not found. Please, check city name.",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        return rootView;
    }

//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }

//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}
