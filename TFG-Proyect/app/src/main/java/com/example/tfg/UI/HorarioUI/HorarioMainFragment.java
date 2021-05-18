package com.example.tfg.UI.HorarioUI;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tfg.R;
import com.example.tfg.UI.HorarioUI.HorarioFragments.HorarioFragment;


public class HorarioMainFragment extends Fragment {

    public HorarioMainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.contenedorFragmentHorario, new HorarioFragment()).commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_horario_generico, container, false);
        return view;
    }
}