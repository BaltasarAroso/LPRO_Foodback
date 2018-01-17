package com.foodback.foodback.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.foodback.foodback.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Dessert extends Fragment {


    public Dessert() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.tab_dessert, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

}
