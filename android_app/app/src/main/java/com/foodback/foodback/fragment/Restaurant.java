package com.foodback.foodback.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.amigold.fundapter.BindDictionary;
import com.amigold.fundapter.FunDapter;
import com.amigold.fundapter.extractors.StringExtractor;
import com.foodback.foodback.R;
import com.foodback.foodback.logic.Establishment;

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

    // TODO Andre faz a ligacao ao servidor e verifica que o metodo declareList() esta bem implementado


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

        /*ArrayList<String> restaurants = new ArrayList<>();

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
        listRestaurants.setAdapter(adapter); */

        ArrayList<Establishment> restaurants = new ArrayList<>();

        Establishment e1 = new Establishment(
                "Li Yun",
                1,
                "Rua da Boavista",
                "Boavista",
                "Porto",
                "liyun@restaurant.com",
                "223456789",
                20,
                "Seg. a Sex. - 11h às 15h | 19h às 00h",
                "Sab e Dom - 11h Às 15h",
                "liyun",
                "chinatown",
                true
        );

        Establishment e2 = new Establishment(
                "Hamburgueria O Gordo",
                1,
                "Rua Mouzinho da Silveira",
                "Baixa",
                "Porto",
                "ogordo@restaurant.com",
                "223456789",
                20,
                "Seg. a Sex. - 11h às 15h | 19h às 00h",
                "Sab e Dom - 11h Às 15h",
                "badoxa",
                "controlar",
                true
        );

        Establishment e3 = new Establishment(
                "Li Yun",
                1,
                "Rua da Boavista",
                "Boavista",
                "Porto",
                "liyun@restaurant.com",
                "223456789",
                20,
                "Seg. a Sex. - 11h às 15h | 19h às 00h",
                "Sab e Dom - 11h Às 15h",
                "liyun",
                "chinatown",
                true
        );

        restaurants.add(e1);
        restaurants.add(e2);
        restaurants.add(e3);


        BindDictionary<Establishment> dictionary = new BindDictionary<>();

        dictionary.addStringField(R.id.estab_name, new StringExtractor<Establishment>() {
            @Override
            public String getStringValue(Establishment estab, int position) {
                return estab.getName();
            }
        });
        dictionary.addStringField(R.id.estab_zone, new StringExtractor<Establishment>() {
            @Override
            public String getStringValue(Establishment estab, int position) {
                return estab.getZone();
            }
        });
        dictionary.addStringField(R.id.estab_city, new StringExtractor<Establishment>() {
            @Override
            public String getStringValue(Establishment estab, int position) {
                return estab.getCity();
            }
        });
        dictionary.addStringField(R.id.estab_category, new StringExtractor<Establishment>() {
            @Override
            public String getStringValue(Establishment estab, int position) {
                return "" + estab.getCategory_id();
            }
        });
        dictionary.addStringField(R.id.estab_contact, new StringExtractor<Establishment>() {
            @Override
            public String getStringValue(Establishment estab, int position) {
                return estab.getContact();
            }
        });
        dictionary.addStringField(R.id.estab_avg_price, new StringExtractor<Establishment>() {
            @Override
            public String getStringValue(Establishment estab, int position) {
                return "" + estab.getAvg_price() + "€";
            }
        });

        FunDapter <Establishment> adapter = new FunDapter<Establishment>(Restaurant.this.getActivity(), restaurants, R.layout.layout_establishment, dictionary);

        ListView listRestaurants = view.findViewById(R.id.list_restaurants);
        listRestaurants.setAdapter(adapter);

        //declareList(view);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

}
