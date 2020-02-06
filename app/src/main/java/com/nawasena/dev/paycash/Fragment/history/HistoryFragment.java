package com.nawasena.dev.paycash.Fragment.history;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.nawasena.dev.paycash.Fragment.history.Adapter.RiwayatPagerAdapter;
import com.nawasena.dev.paycash.R;

import java.util.ArrayList;

public class HistoryFragment extends Fragment {

    ViewPager viewPager;
    AppBarLayout appBarLayout;
    public Fragment fragment;
    TabLayout tabLayout;
    private ArrayList fragmentList = new ArrayList<>();
    View v;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_history,container,false);



        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewPager = (ViewPager) v.findViewById(R.id.nampungFragmentHistory);
        tabLayout = (TabLayout) v.findViewById(R.id.tabLayoutpager);
        appBarLayout = (AppBarLayout) v.findViewById(R.id.appBar_main);

        RiwayatPagerAdapter adapter = new RiwayatPagerAdapter(getFragmentManager());
        adapter.addFragment(new PendingFragment(),"Pending");
        adapter.addFragment(new SuccessFragment(),"Success");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }


//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//
//        final FragmentManager fragmentManager = getFragmentManager();
//        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//        for( fragment : fragmentList){
//            fragmentTransaction.remove(fragment);
//        }
//        fragmentTransaction.commit();
//    }
}
