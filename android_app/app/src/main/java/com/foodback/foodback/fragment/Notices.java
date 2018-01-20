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
public class Notices extends Fragment {


    public Notices() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab_notices, container, false);



        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

}
