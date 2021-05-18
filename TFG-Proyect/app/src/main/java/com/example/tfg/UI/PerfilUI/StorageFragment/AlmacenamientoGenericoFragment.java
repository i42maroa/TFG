package com.example.tfg.UI.PerfilUI.StorageFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tfg.R;


public class AlmacenamientoGenericoFragment extends Fragment {

    public AlmacenamientoGenericoFragment() {
        // Required empty public constructor
    }

    public static AlmacenamientoGenericoFragment newInstance(String param1, String param2) {
        AlmacenamientoGenericoFragment fragment = new AlmacenamientoGenericoFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_almacenamiento_generico, container, false);

        Fragment almacenamientoFragment = new AlmacenamientoFragment();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.fragmentAlmacenaniemtoGenericoContain, almacenamientoFragment);
        transaction.addToBackStack(null);
        transaction.commit();
        return view;
    }
}