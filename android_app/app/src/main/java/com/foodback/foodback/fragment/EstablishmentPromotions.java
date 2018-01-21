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
public class EstablishmentPromotions extends Fragment {


    public EstablishmentPromotions() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_establishment_promotions, container, false);
    }

}
