package com.example.tfg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.tfg.Sistems.SistemaPerfil.models.Usuario;
import com.example.tfg.UI.PerfilUI.StorageFragment.ListPlatosAlmacenadosFragment;
import com.example.tfg.UI.HorarioUI.HorarioFragments.FranjaHorarioFragment;
import com.example.tfg.UI.HorarioUI.HorarioFragments.HorarioFragment;
import com.example.tfg.UI.MainViewPagerAdpt;
import com.example.tfg.UI.PlatosUI.FragmentsPlatos.SecondaryFragment.IngredienteUnicoFragment;
import com.example.tfg.Sistems.SistemaPlatos.Fragmentos.ListIngredientesFragment;
import com.example.tfg.UI.PlatosUI.FragmentsPlatos.SecondaryFragment.ListPlatosFragment;
import com.example.tfg.UI.PlatosUI.FragmentsPlatos.SecondaryFragment.PlatoUnicoFragment;
import com.example.tfg.UI.PlatosUI.PlatosMainTabFragment;
import com.example.tfg.fragments.adaptors.iDataListener;
import com.example.tfg.fragments.adaptors.iToTabPlato;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity implements  iDataListener, iToTabPlato {

    private MenuItem prevMenuItem;

    public static final int FRAGMENT_PERFIL = 0;
    public static final int FRAGMENT_HORARIO = 1;
    public static final int FRAGMENT_PLATOS = 2;

    public static final int GO_TO_PLATO_UNICO = 0;
    public static final int GO_TO_LIST_INGREDIENTES_PLATO = 1;

    public static final int CALORIAS_PERSONA = 600;

    private ViewPager viewPager;
    private MainViewPagerAdpt pagerAdapter;
    private BottomNavigationView bottomNavigationView ;
    private Toolbar toolbar;

    Realm realm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        realm = Realm.getDefaultInstance();

        Usuario us = realm.where(Usuario.class).findFirst();
        if(us.getNombre().equals("vacio") && us.getAltura() == -1)
            startActivity(new Intent(this, FormularioInicio.class));



        setToolbar();
        setBottomNavigation();
        setViewPager();
        setListenerLayout(viewPager);
        viewPager.setCurrentItem(FRAGMENT_HORARIO);

        Toolbar toolbar = findViewById(R.id.toolbar);


    }



    //Operaciones rellenar variables
    //-----------------------------
    private void setToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void setBottomNavigation(){
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomActionBar2);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_perfil:
                        viewPager.setCurrentItem(FRAGMENT_PERFIL);
                        return true;

                    case R.id.navigation_horario:
                        viewPager.setCurrentItem(FRAGMENT_HORARIO);
                        return true;

                    case R.id.navigation_platos:
                        viewPager.setCurrentItem(FRAGMENT_PLATOS);
                        return true;
                }
                return false;
            }
        });
    }


    private void setViewPager(){
        viewPager = (ViewPager) findViewById(R.id.viewPager2);
        pagerAdapter = new MainViewPagerAdpt(getSupportFragmentManager(), 3);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(pagerAdapter.getCount() - 1);
    }


    private void setListenerLayout(final ViewPager viewPager){
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case FRAGMENT_PERFIL:
                        bottomNavigationView.getMenu().findItem(R.id.navigation_perfil).setChecked(true);
                        break;
                    case FRAGMENT_HORARIO:
                        bottomNavigationView.getMenu().findItem(R.id.navigation_horario).setChecked(true);
                        break;
                    case FRAGMENT_PLATOS:
                        bottomNavigationView.getMenu().findItem(R.id.navigation_platos).setChecked(true);
                        break;
                }

                if (prevMenuItem != null)
                    prevMenuItem.setChecked(false);
                else
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);

                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNavigationView.getMenu().getItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }




    //OPERATIONS MANIPULATE FRAGMENTS UI
    //--------------------------------

    @Override
    public void sendDayMonth(int dia, int mes) {

        FranjaHorarioFragment franjaHorarioFragment = new FranjaHorarioFragment();

        Bundle bundleEnvio = new Bundle();
        bundleEnvio.putInt("dia", dia);
        bundleEnvio.putInt("mes", mes);
        franjaHorarioFragment.setArguments(bundleEnvio);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction()
                .replace(R.id.contenedorFragmentHorario, franjaHorarioFragment)
                .addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void sendIdPlates(int idPlate, int opc) {

        viewPager.setCurrentItem(FRAGMENT_PLATOS);
        PlatoUnicoFragment platoUnicoFragment = PlatoUnicoFragment.newInstance(idPlate);
        ListIngredientesFragment listIngredientesFragment = new ListIngredientesFragment();
        FragmentTransaction transaction;

        switch (opc){
            case GO_TO_PLATO_UNICO:
                transaction = getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentPlatoGenericoContenedor, platoUnicoFragment)
                        .addToBackStack(null);
                transaction.commit();
                break;

            case GO_TO_LIST_INGREDIENTES_PLATO:
                transaction = getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentPlatoGenericoContenedor, listIngredientesFragment)
                        .addToBackStack(null);
                transaction.commit();
                break;
        }
    }

    @Override
    public void enviarIdPlatos(ArrayList<Integer> idPlatos) {

        ListPlatosFragment listPlatosFragment = new ListPlatosFragment();

        Bundle bundleEnvio = new Bundle();
        bundleEnvio.putIntegerArrayList("idPlato", idPlatos);
        listPlatosFragment.setArguments(bundleEnvio);


        FragmentTransaction transaction;
        transaction = getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentPlatoGenericoContenedor, listPlatosFragment)
                .addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void enviarIdAlmacen(int idAlmacenamiento){
        ListPlatosAlmacenadosFragment listPlatosAlmacenadosFragment = new ListPlatosAlmacenadosFragment();

        Bundle bundleEnvio = new Bundle();
        bundleEnvio.putInt("idAlmacen", idAlmacenamiento);
        listPlatosAlmacenadosFragment.setArguments(bundleEnvio);
        listPlatosAlmacenadosFragment.setArguments(bundleEnvio);
        FragmentTransaction transaction;

        transaction = getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentAlmacenaniemtoGenericoContain, listPlatosAlmacenadosFragment)
                .addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void enviarDiaMesAnio(int dia, int mes, int anio) {
        HorarioFragment horarioFragment = new HorarioFragment();

        Bundle bundleEnvio = new Bundle();
        bundleEnvio.putInt("dia", dia);
        bundleEnvio.putInt("mes", mes);
        bundleEnvio.putInt("anio", anio);
        horarioFragment.setArguments(bundleEnvio);

        FragmentTransaction transaction= getSupportFragmentManager().beginTransaction()
                .replace(R.id.contenedorFragmentHorario, horarioFragment)
                .addToBackStack(null);
        transaction.commit();
    }




    @Override
    public void enviarIdIngrediente(int idIngrediente) {
        IngredienteUnicoFragment ingredienteUnicoFragment = new IngredienteUnicoFragment().newInstance(idIngrediente);

        FragmentTransaction transaction= getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentPlatoGenericoContenedor, ingredienteUnicoFragment)
                .addToBackStack(null);
        transaction.commit();
    }


    @Override
    public void enviarIdCategoria(int idCategory) {
        ListPlatosFragment listPlatosFragment = ListPlatosFragment.newInstance(idCategory);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction().replace(R.id.fragmentPlatoGenericoContenedor, listPlatosFragment).addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void enviarListIdPlatos(ArrayList<Integer> idPlatos) {
        ListPlatosFragment listPlatosFragment = new ListPlatosFragment().newInstance(idPlatos);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentPlatoGenericoContenedor, listPlatosFragment)
                .addToBackStack(null);
        transaction.commit();
    }
}