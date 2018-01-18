package com.foodback.foodback.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.foodback.foodback.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Restaurant extends Fragment {

    /*protected String name;
    protected String zone;
    protected String city;
    protected Integer avg_price;

    public Restaurant(String name, String zone, String city, Integer avg_price) {
        this.name = name;
        this.zone = zone;
        this.city = city;
        this.avg_price = avg_price
    }*/

    public Restaurant() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab_restaurant, container, false);

        /*ArrayList<Restaurant> restaurants = new ArrayList<>();

        restaurants.add(
                "Pizzaria do Chiado",
                "Baixa",
                "Porto",
                15,
        );*/

        ArrayList<String> restaurants = new ArrayList<>();

        restaurants.add("Pizzaria Torino");
        restaurants.add("Restaurante Alves");
        restaurants.add("Marisqueira de Grijó");
        restaurants.add("Tasca do André");
        restaurants.add("Pizzaria Torino");
        restaurants.add("Restaurante Alves");
        restaurants.add("Marisqueira de Grijó");
        restaurants.add("Tasca do André");
        restaurants.add("Pizzaria Torino");
        restaurants.add("Restaurante Alves");
        restaurants.add("Marisqueira de Grijó");
        restaurants.add("Tasca do André");
        restaurants.add("Pizzaria Torino");
        restaurants.add("Restaurante Alves");
        restaurants.add("Marisqueira de Grijó");
        restaurants.add("Tasca do André");
        restaurants.add("Pizzaria Torino");
        restaurants.add("Restaurante Alves");
        restaurants.add("Marisqueira de Grijó");
        restaurants.add("Tasca do André");
        restaurants.add("Pizzaria Torino");
        restaurants.add("Restaurante Alves");
        restaurants.add("Marisqueira de Grijó");
        restaurants.add("Tasca do André");
        restaurants.add("Pizzaria Torino");
        restaurants.add("Restaurante Alves");
        restaurants.add("Marisqueira de Grijó");
        restaurants.add("Tasca do André");
        restaurants.add("Pizzaria Torino");
        restaurants.add("Restaurante Alves");
        restaurants.add("Marisqueira de Grijó");
        restaurants.add("Tasca do André");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getContext(),
                android.R.layout.simple_list_item_1,
                restaurants
        );

        ListView listRestaurants = view.findViewById(R.id.list_restaurants);
        listRestaurants.setAdapter(adapter);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

}
