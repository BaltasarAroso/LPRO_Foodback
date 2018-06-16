package com.foodback.foodback.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.amigold.fundapter.BindDictionary;
import com.amigold.fundapter.FunDapter;
import com.foodback.foodback.R;
import com.foodback.foodback.logic.Establishment;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Notices extends Fragment {

    //TODO

    ArrayList<Establishment> notices = new ArrayList<>();

    public Notices() {
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //fillEstablishmentDictionary(view);

        return inflater.inflate(R.layout.tab_notices, container, false);
    }


    private void declareList(View view, BindDictionary<Establishment> dictionary) {

        FunDapter<Establishment> adapter = new FunDapter<>(getActivity(), notices, R.layout.layout_establishment, dictionary);

        ListView listRestaurants = view.findViewById(R.id.list_notices);
        listRestaurants.setAdapter(adapter);
        listRestaurants.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Establishment selectedEstab = notices.get(position);
                Toast.makeText(getActivity(), selectedEstab.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
    }

}
