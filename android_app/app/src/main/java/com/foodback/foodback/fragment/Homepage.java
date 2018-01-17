package com.foodback.foodback.fragment;

import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.foodback.foodback.R;

import java.util.ArrayList;
import java.util.Arrays;

public class Homepage extends Fragment {

    AppBarLayout appBar;
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_homepage, container, false);

        View admin_menu = (View)container.getParent();
        appBar = (AppBarLayout) admin_menu.findViewById(R.id.appBar);
        tabLayout = new TabLayout(getActivity());
        tabLayout.setTabTextColors(Color.parseColor("#FFFFFF"), Color.parseColor("#FFFFFF"));
        appBar.addView(tabLayout);

        viewPager = (ViewPager) myView.findViewById(R.id.viewPager);
        Menu_Wrapper menu_wrapper = new Menu_Wrapper(getFragmentManager());
        viewPager.setAdapter(menu_wrapper);
        tabLayout.setupWithViewPager(viewPager);

        return myView;

        /*
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
        */
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    public class Menu_Wrapper extends FragmentStatePagerAdapter {

        private String[] titles = new String[] {"Restaurante", "Café", "Bar", "Sobremesa"};

        public Menu_Wrapper(FragmentManager fm) {
            super(fm);
        }

        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public Fragment getItem(int position) {
            switch(position) {
                case 0:
                    return new Restaurant();
                case 1:
                    return new Coffee();
                case 2:
                    return new Bar();
                case 3:
                    return new Dessert();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    }
}
