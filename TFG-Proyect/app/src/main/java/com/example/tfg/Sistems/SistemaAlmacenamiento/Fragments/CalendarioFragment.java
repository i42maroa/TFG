package com.example.tfg.Sistems.SistemaAlmacenamiento.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import com.example.tfg.R;
import com.example.tfg.fragments.adaptors.iDataListener;

import io.realm.RealmList;


public class CalendarioFragment extends Fragment {

    private CalendarView calendarView;
    private Button buttonCambiar;
    private int diaSelec, mesSelec, anoSelec;

    private iDataListener callback;

    public CalendarioFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try{
            callback = (iDataListener) context;
        }catch (Exception e){
            throw new ClassCastException(context.toString() + "should implement DataListener" );
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calendario, container, false);

        calendarView = (CalendarView) view.findViewById(R.id.fragmentCalendarioCalendarView);
        buttonCambiar = (Button) view.findViewById(R.id.fragmentCalendarioButtonCambiar);




        buttonCambiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.enviarDiaMesAnio(diaSelec, mesSelec, anoSelec);
            }
        });


        return view;
    }
}