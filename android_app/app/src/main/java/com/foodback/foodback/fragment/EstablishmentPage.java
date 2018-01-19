package com.foodback.foodback.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.foodback.foodback.R;
import com.foodback.foodback.logic.Establishment;

/**
 * A simple {@link Fragment} subclass.
 */
public class EstablishmentPage extends Fragment {

    Establishment estab;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_establishment_page, container, false);

        


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
