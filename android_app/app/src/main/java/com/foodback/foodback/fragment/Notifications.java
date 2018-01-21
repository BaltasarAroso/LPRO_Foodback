package com.foodback.foodback.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.foodback.foodback.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Notifications extends Fragment {

    private AppBarLayout appBar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        appBar.removeView(tabLayout);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View myView = inflater.inflate(R.layout.fragment_notifications, container, false);

        View admin_menu = (View)container.getParent();
        appBar = (AppBarLayout) admin_menu.findViewById(R.id.appBar);
        tabLayout = new TabLayout(getActivity());
        tabLayout.setTabTextColors(Color.parseColor("#FFFFFF"), Color.parseColor("#FFFFFF"));
        appBar.addView(tabLayout);

        viewPager = (ViewPager) myView.findViewById(R.id.viewPager);
        Notification_Tabs notificationTabs = new Notification_Tabs(getFragmentManager());
        viewPager.setAdapter(notificationTabs);
        tabLayout.setupWithViewPager(viewPager);

        return myView;
    }

    public class Notification_Tabs extends FragmentStatePagerAdapter {

        private String[] titles = new String[] {"Estabelecimentos", "Denuncias"};

        public Notification_Tabs(FragmentManager fm) {
            super(fm);
        }

        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public Fragment getItem(int position) {
            switch(position) {
                case 0:
                    return new Notices();
                case 1:
                    return new Reports();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

}
