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

import com.foodback.foodback.R;

public class Homepage extends Fragment {

    AppBarLayout appBar;
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_homepage, container, false);

        View menu = (View)container.getParent();
        appBar = menu.findViewById(R.id.appBar);
        tabLayout = new TabLayout(getActivity());
        tabLayout.setTabTextColors(Color.parseColor("#FFFFFF"), Color.parseColor("#FFFFFF"));
        appBar.addView(tabLayout);

        viewPager = myView.findViewById(R.id.viewPager);
        Menu_Wrapper menu_wrapper = new Menu_Wrapper(getFragmentManager());
        viewPager.setAdapter(menu_wrapper);
        tabLayout.setupWithViewPager(viewPager);

        return myView;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        appBar.removeView(tabLayout);
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
