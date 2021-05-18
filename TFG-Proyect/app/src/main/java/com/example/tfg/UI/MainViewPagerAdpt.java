package com.example.tfg.UI;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.tfg.UI.HorarioUI.HorarioMainFragment;
import com.example.tfg.UI.PerfilUI.PerfilMainTabFragment;
import com.example.tfg.UI.PlatosUI.PlatosMainTabFragment;

import static com.example.tfg.MainActivity.FRAGMENT_HORARIO;
import static com.example.tfg.MainActivity.FRAGMENT_PERFIL;
import static com.example.tfg.MainActivity.FRAGMENT_PLATOS;

public class MainViewPagerAdpt extends FragmentStatePagerAdapter {

    private int numElem;

    public MainViewPagerAdpt(FragmentManager manager, int elem) {
        super(manager, elem);
        numElem = elem;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case FRAGMENT_PERFIL:
                return new PerfilMainTabFragment();
            case FRAGMENT_HORARIO:
                return new HorarioMainFragment();
            case FRAGMENT_PLATOS:
                return new PlatosMainTabFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numElem;
    }
}
