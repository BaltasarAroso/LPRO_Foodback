package com.foodback.foodback.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.foodback.foodback.R;

import java.util.ArrayList;
import java.util.Arrays;

public class Homepage extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.activity_homepage, container, false);

        ListView lv  = myView.findViewById(R.id.establishment_list);
        ArrayList<String> arrayEstabs = new ArrayList<>();
        arrayEstabs.addAll(Arrays.asList(getResources().getStringArray(R.array.establishment_list)));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                arrayEstabs
        );
        lv.setAdapter(adapter);

        return myView;
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
