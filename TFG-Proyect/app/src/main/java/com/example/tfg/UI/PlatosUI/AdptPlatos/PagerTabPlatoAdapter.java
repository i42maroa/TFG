package com.example.tfg.UI.PlatosUI.AdptPlatos;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.tfg.UI.PlatosUI.FragmentsPlatos.MainFragments.ContainerPlatoMainFragment;
import com.example.tfg.UI.PlatosUI.FragmentsPlatos.MainFragments.SearchMainFragment;

import static com.example.tfg.UI.PlatosUI.PlatosMainTabFragment.FRAGMENT_SEARCH_PLATOS;
import static com.example.tfg.UI.PlatosUI.PlatosMainTabFragment.FRAGMENT_LIST_CATEGORIES;

public class PagerTabPlatoAdapter extends FragmentStatePagerAdapter {

    private int numberOfTabs;

    public PagerTabPlatoAdapter(@NonNull FragmentManager fm, int tabs) {
        super(fm, tabs);
        this.numberOfTabs = tabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case FRAGMENT_LIST_CATEGORIES:
                return new ContainerPlatoMainFragment();
            case FRAGMENT_SEARCH_PLATOS:
                return new SearchMainFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
