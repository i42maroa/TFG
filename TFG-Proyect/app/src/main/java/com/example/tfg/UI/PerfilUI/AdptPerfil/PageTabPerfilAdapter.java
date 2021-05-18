package com.example.tfg.UI.PerfilUI.AdptPerfil;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.tfg.UI.PerfilUI.StorageFragment.AlmacenamientoGenericoFragment;
import com.example.tfg.Sistems.SistemaPerfil.Fragments.EstadisticasFragment;
import com.example.tfg.Sistems.SistemaPerfil.Fragments.PerfilFragment;
import com.example.tfg.UI.PerfilUI.PerfilMainTabFragment;


public class PageTabPerfilAdapter extends FragmentStatePagerAdapter {

    private int numberOfTabs;

    public PageTabPerfilAdapter(@NonNull FragmentManager fm, int tabs) {
        super(fm, tabs);
        this.numberOfTabs = tabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case PerfilMainTabFragment.FRAGMENT_PERFIL_USUARIO:
                return new PerfilFragment();
            case PerfilMainTabFragment.FRAGMENT_ESTADISTICAS:
                return new EstadisticasFragment();
            case PerfilMainTabFragment.FRAGMENT_ALMACENAMIENTO:
                return new AlmacenamientoGenericoFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
