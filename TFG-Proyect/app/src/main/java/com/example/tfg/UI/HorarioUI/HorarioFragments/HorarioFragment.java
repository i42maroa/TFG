package com.example.tfg.UI.HorarioUI.HorarioFragments;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tfg.MainActivity;
import com.example.tfg.R;
import com.example.tfg.Sistems.SistemaAlmacenamiento.Fragments.CalendarioFragment;
import com.example.tfg.Sistems.SistemaHorario.Fragments.ListaCompraFragment;
import com.example.tfg.Sistems.SistemaHorario.adaptors.AdaptadorReciclerHorario;
import com.example.tfg.Sistems.SistemaHorario.models.Dia;
import com.example.tfg.Sistems.SistemaHorario.models.FranjaHorario;
import com.example.tfg.Sistems.SistemaHorario.models.Mes;
import com.example.tfg.Sistems.SistemaHorario.models.Semana;
import com.example.tfg.Sistems.SistemaPerfil.models.Usuario;
import com.example.tfg.Sistems.SistemaPlatos.adaptors.AdaptadorListPlatosIntroducirFranjaHorario;
import com.example.tfg.Sistems.SistemaPlatos.models.Category;
import com.example.tfg.Sistems.SistemaPlatos.models.Plato;
import com.example.tfg.fragments.adaptors.iDataListener;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.RealmResults;


public class HorarioFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private Realm realm;
    private RealmResults<FranjaHorario> franjaHorario;

    private ArrayList<Integer> calPred;

    private Button buttonLista;
    private TextView textFecha;

    private iDataListener callback;

    private Mes mes;
    private Semana semana;

    private int diaSelec, mesSelec, anioSelec, pDiaSemana;
    private int diaSelecFin, mesSelecFin;

    private int DIA_SEL = 2;
    private int MES_SEL = 1;


    public HorarioFragment() {
        // Required empty public constructor
    }


    public static HorarioFragment newInstance() {
        HorarioFragment fragment = new HorarioFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_horario, container, false);


        if(diaSelec == 0){
            diaSelec = DIA_SEL;
            diaSelecFin = DIA_SEL + 5;
            mesSelec = MES_SEL;
        }

        setRealm();
        setmRecyclerView(view);
        setmAdapter(view);
        setButtonListaListener(buttonLista);

        setButtonCalendarioListener(textFecha);


        semana.addChangeListener(new RealmChangeListener<RealmModel>() {
            @Override
            public void onChange(RealmModel realmModel) {
                mAdapter.notifyDataSetChanged();
                textFecha.setText(nomMes(semana) + ", " + "2020");
            }
        });

        textFecha.setText( nomMes(semana) + ", " + "2020");
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
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

    private void setRealm(){
        realm = Realm.getDefaultInstance(); //Instanciacion de la base de datos
        semana = realm.where(Semana.class).equalTo("id", 1).findFirst();
        mes = realm.where(Mes.class).equalTo("mes", semana.getMesInicio()).findFirst();
        franjaHorario = realm.where(FranjaHorario.class).equalTo("mes", 1).findAll(); //Recoger todos los objetos almacenados en la tabla del plato

        actualizarCal();
    }

    private void actualizarCal(){
        calPred = new ArrayList<Integer>();
        Usuario us = realm.where(Usuario.class).findFirst();
        calPred.add(us.getCalDes());
        calPred.add(us.getCalAl());
        calPred.add(us.getCalCena());
        calPred.add(us.getCalComp());
    }

    private void setmRecyclerView(View view){
        mRecyclerView = (RecyclerView) view.findViewById(R.id.fragmentHorarioRecyclerViewHorario);
        buttonLista = (Button) view.findViewById(R.id.fragmentHorarioButtonListaCompra);
        textFecha = (TextView) view.findViewById(R.id.fragmentHorarioTextViewFecha);
        mLayoutManager = new GridLayoutManager(view.getContext(), 1); //Como si fuera un GridView
    }

    private void setmAdapter(View view){
        mAdapter = new AdaptadorReciclerHorario(semana.getDias(), R.layout.grid_item_horario, calPred, new AdaptadorReciclerHorario.OnItemClickListener() {
            @Override
            public void onItemClick(Dia dia, int position) {
                callback.sendDayMonth(dia.getDia(), dia.getMes()); //Llama a la funcion dentro del main para pasarle el id de la franja clickeada
            }
        });
    }

    private void setButtonListaListener(final Button buttonLista){
        buttonLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListaCompraFragment listaCompraFragment = new ListaCompraFragment();

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contenedorFragmentHorario, listaCompraFragment)
                        .addToBackStack(null);
                transaction.commit();
            }
        });
    }

    private void setButtonCalendarioListener(final TextView textFecha){
        textFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupRangePickerDialog();
            }
        });
    }


    private void showDialogCalendar(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        View viewInflater = LayoutInflater.from(getContext()).inflate(R.layout.dialog_calendario, null);
        builder.setView(viewInflater);

        CalendarView calendarView = (CalendarView) viewInflater.findViewById(R.id.dialogCalendarioCalendarView);


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                diaSelec = dayOfMonth;
                mesSelec = month;
                anioSelec = year;
            }
        });

        builder.setPositiveButton("Seleccionar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("Atras", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                //showAlertForCategory("Seleccione categoria", "Selecciona la categoria de platos", franjaHorario);
            }
        });

        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    private void setupRangePickerDialog() {
        MaterialDatePicker.Builder builderRange = MaterialDatePicker.Builder.dateRangePicker();

        builderRange.setCalendarConstraints(limitRange().build());
        MaterialDatePicker pickerRange = builderRange.build();
        pickerRange.show(getActivity().getSupportFragmentManager(), pickerRange.toString());

        pickerRange.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Pair<Long, Long>>() {
            @Override public void onPositiveButtonClick(Pair<Long,Long> selection) {
                Long startDate = selection.first;
                Long endDate = selection.second;

                diaSelec = Integer.valueOf(DateFormat.format("dd", new Date(startDate)).toString());
                mesSelec = Integer.valueOf(DateFormat.format("MM", new Date(startDate)).toString());
                diaSelecFin = Integer.valueOf(DateFormat.format("dd", new Date(endDate)).toString());
                mesSelecFin = Integer.valueOf(DateFormat.format("MM", new Date(endDate)).toString());

                realm.beginTransaction();
                Semana semana = realm.where(Semana.class).equalTo("id", 1).findFirst();
                semana.setDias(diaSelec,mesSelec,diaSelecFin,mesSelecFin);
                realm.commitTransaction();
            }
        });
    }

    private CalendarConstraints.Builder limitRange()  {

        CalendarConstraints.Builder constraintsBuilderRange = new CalendarConstraints.Builder();

        Calendar calendarStart  = Calendar.getInstance();
        Calendar calendarEnd  = Calendar.getInstance();

        int year = 2022, startMonth = 1, startDate = 2;
        int endMonth = 12 , endDate = 31;

        calendarStart.set(2020, startMonth - 1, startDate - 1);
        calendarEnd.set(2022, endMonth - 1, endDate);

        long minDate = calendarStart.getTimeInMillis();
        long  maxDate = calendarEnd.getTimeInMillis();

        constraintsBuilderRange.setStart(minDate)
                .setEnd(maxDate)
                .setValidator(new RangeValidator(minDate, maxDate));

        return constraintsBuilderRange;
    }

    public static String nomMes(final Semana semana){
        if(semana.getMesInicio() == semana.getMesFin())
            return Mes.getNombMes(semana.getMesInicio());
        else
            return Mes.getNombMes(semana.getMesInicio()) + " - " + Mes.getNombMes(semana.getMesFin());
    }
}

