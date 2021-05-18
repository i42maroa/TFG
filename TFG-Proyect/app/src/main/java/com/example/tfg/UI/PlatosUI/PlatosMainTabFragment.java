package com.example.tfg.UI.PlatosUI;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.tfg.R;
import com.example.tfg.UI.PlatosUI.AdptPlatos.PagerTabPlatoAdapter;
import com.example.tfg.fragments.adaptors.iToTabPlato;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;


public class PlatosMainTabFragment extends Fragment implements iToTabPlato {


    private TabLayout tabLayout;
    private ViewPager viewPager;
    private PagerTabPlatoAdapter pagerAdapter;


    public static final int FRAGMENT_LIST_CATEGORIES = 0;
    public static final int FRAGMENT_SEARCH_PLATOS = 1;

    private int antPos;
    private int nuevaPos;

    public PlatosMainTabFragment() {
        // Required empty public constructor
    }


    public static PlatosMainTabFragment newInstance(int i) {
        PlatosMainTabFragment fragment = new PlatosMainTabFragment();
        Bundle bundleEnvio = new Bundle(); bundleEnvio.putInt("viewpager", i);
        fragment.setArguments(bundleEnvio);
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

       View view = inflater.inflate(R.layout.fragment_plato_generico, container, false);

        int pos;
        Bundle bundle = this.getArguments();



        setToolbar(view);
        setTabLayout(view);
        setViewPager(view);
        setListenerLayout(viewPager);


        return view;
    }

    private void setToolbar(View view){
        //Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
    }

    private void setTabLayout(View view){
        tabLayout = (TabLayout) view.findViewById(R.id.fragmentPlatosTabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("PLATOS"));
        tabLayout.addTab(tabLayout.newTab().setText("BÚSQUEDA"));
        tabLayout.setTabGravity(tabLayout.GRAVITY_FILL);
    }

    private void setViewPager(final View view){
        viewPager = (ViewPager) view.findViewById(R.id.fragmentPlatosViewPagerConten);
        pagerAdapter = new PagerTabPlatoAdapter(getActivity().getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    private void setListenerLayout(final ViewPager viewPager){
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos = tab.getPosition();
                viewPager.setCurrentItem(pos);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                antPos = tab.getPosition();
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Toast.makeText(getContext(), tab.getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void enviarListIdPlatos(ArrayList<Integer> idPlatos) {
        viewPager.setCurrentItem(FRAGMENT_LIST_CATEGORIES);
    }
}