package com.example.tfg.UI.PerfilUI;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tfg.R;
import com.example.tfg.UI.PerfilUI.StorageFragment.AlmacenamientoGenericoFragment;
import com.example.tfg.Sistems.SistemaPerfil.Fragments.PerfilFragment;
import com.example.tfg.UI.PlatosUI.FragmentsPlatos.SecondaryFragment.ListPlatosFragment;
import com.example.tfg.UI.PerfilUI.AdptPerfil.PageTabPerfilAdapter;
import com.google.android.material.tabs.TabLayout;


public class PerfilMainTabFragment extends Fragment {


    FragmentTransaction fragmentTransaction;
    Fragment fragPerfil, fragAlmacen, fragReceta;

    private ViewPager viewPager;
    private PageTabPerfilAdapter pagerAdapter;
    private TabLayout tabLayout;

    public static final int FRAGMENT_PERFIL_USUARIO = 0;
    public static final int FRAGMENT_ESTADISTICAS = 1;
    public static final int FRAGMENT_ALMACENAMIENTO = 2;


    public PerfilMainTabFragment() {
        // Required empty public constructor
    }

    public static PerfilMainTabFragment newInstance() {
        PerfilMainTabFragment fragment = new PerfilMainTabFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fragPerfil = new PerfilFragment();
        fragAlmacen = new AlmacenamientoGenericoFragment();
        fragReceta = new ListPlatosFragment();
    }

    //Es como el onCreate del activity
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_perfil_generico, container, false);

        setToolbar();
        setTabLayout(view);
        setViewPager(view);
        setListenerLayout(viewPager);
        setListenerTab(tabLayout);

        return view;
    }

    //Operaciones rellenar variables
    //-----------------------------
    private void setToolbar(){
        //Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
    }

    private void setTabLayout(View view){
        tabLayout = (TabLayout) view.findViewById(R.id.fragmentPerfilTabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("PERFIL"));
        tabLayout.addTab(tabLayout.newTab().setText("ESTADÍSTICAS"));
        tabLayout.addTab(tabLayout.newTab().setText("ALMACÉN"));
        tabLayout.setTabGravity(tabLayout.GRAVITY_FILL);

    }
    private void setViewPager(View view){
        viewPager = (ViewPager) view.findViewById(R.id.fragmentPerfilViewPagerConten);
        pagerAdapter = new PageTabPerfilAdapter(getActivity().getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(pagerAdapter.getCount() - 1);
        //tabLayout.setupWithViewPager(viewPager);
    }

    private void setListenerLayout(final ViewPager viewPager){
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    private void setListenerTab(final TabLayout tabLayout){
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }
}