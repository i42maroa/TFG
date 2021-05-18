package com.example.tfg.UI.PlatosUI.FragmentsPlatos.MainFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tfg.R;
import com.example.tfg.UI.PlatosUI.FragmentsPlatos.SecondaryFragment.ListCategoryMainFragment;

public class ContainerPlatoMainFragment extends Fragment {

    public ContainerPlatoMainFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.fragmentPlatoGenericoContenedor, new ListCategoryMainFragment()).commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_plato_generico2, container, false);
    }
}