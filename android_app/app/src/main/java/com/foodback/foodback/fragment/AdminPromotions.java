package com.foodback.foodback.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.foodback.foodback.R;

import static com.foodback.foodback.utils.ErrorDisplay.isException;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdminPromotions extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_admin_promotions, container, false);

        sendPromotion();

        return view;
    }

    private void sendPromotion() {

        try {
            //todo
        } catch (Exception e) {
            isException(getContext(), e);
        }
    }

}
