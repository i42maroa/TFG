package com.example.tfg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tfg.Sistems.SistemaPerfil.models.Usuario;
import com.example.tfg.UI.HorarioUI.HorarioFragments.DatePickerFragment;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

import io.realm.Realm;
import io.realm.RealmResults;

public class FormularioInicio extends AppCompatActivity {

    private Realm realm;


    private TextInputLayout  tiNombre, tiEdad, tiAltura, tiPeso, tiCorreo;
    private TextInputEditText etNombre, etEdad, etAltura, etPeso, etCorreo;
    private Button buttonReg;
    private int diaSelec, mesSelec, anioSelec;
    private ImageView sexo;

    public static String CAMPO_INCOMPLETO ="Campo incompleto";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_inicio);

        realm = Realm.getDefaultInstance();

        tiNombre = (TextInputLayout) findViewById(R.id.textFieldNombre);
        tiEdad = (TextInputLayout) findViewById(R.id.textFieldFecha);
        tiAltura = (TextInputLayout) findViewById(R.id.textFieldAltura);
        tiPeso = (TextInputLayout) findViewById(R.id.textFieldPeso);
        tiCorreo = (TextInputLayout) findViewById(R.id.textFieldCorreo);
        buttonReg = (Button) findViewById(R.id.formRegistroBottonEnviar);
        etEdad = (TextInputEditText) findViewById(R.id.textFieldFechaEntrada);
        etNombre = (TextInputEditText) findViewById(R.id.textFieldETNombre);
        etAltura = (TextInputEditText) findViewById(R.id.textFieldETAltura);
        etPeso = (TextInputEditText) findViewById(R.id.textFieldETPeso);
        etCorreo = (TextInputEditText) findViewById(R.id.textFieldETCorreo);
        sexo    = (ImageView)  findViewById(R.id.formImageViewHombre);

        etEdad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogCalendar("Seleccionar fecha Nacimiento", "");
            }
        });

        sexo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realm.beginTransaction();
                Usuario usu = realm.where(Usuario.class).findFirst();
                if(usu.getGenero() == Usuario.USUARIO_HOMBRE){
                    sexo.setImageResource(R.drawable.female);
                    usu.setGenero(Usuario.USUARIO_MUJER);
                }
                else{
                    sexo.setImageResource(R.drawable.male);
                    usu.setGenero(Usuario.USUARIO_HOMBRE);
                }
                realm.commitTransaction();
            }
        });

        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(verificarCampos()){
                    almacenarRealm();
                    goToMain();
                }
                //Toast.makeText(FormularioInicio.this, etNombre.getText().toString() + etEdad.getText().toString() + etAltura.getText().toString() + etAltura.getText().toString() + etPeso.getText().toString() + etCorreo.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });



    }

    private void goToMain() {
        startActivity(new Intent(this, MainActivity.class));
    }

    private void showDialogCalendar(String title, String message){
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because January is zero
                final String selectedDate = day + " / " + (month+1) + " / " + year;
                etEdad.setText(selectedDate);
            }
        });

        newFragment.show(getSupportFragmentManager(), "datePicker");

    }

    public static class DatePickerFragment extends DialogFragment {

        private DatePickerDialog.OnDateSetListener listener;

        public static DatePickerFragment newInstance(DatePickerDialog.OnDateSetListener listener) {
            DatePickerFragment fragment = new DatePickerFragment();
            fragment.setListener(listener);
            return fragment;
        }

        public void setListener(DatePickerDialog.OnDateSetListener listener) {
            this.listener = listener;
        }

        @Override
        @NonNull
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(), listener, year, month, day);
        }
    }

    private boolean verificarCampos() {
        boolean comp =  true;
        double aux;

        if(etNombre.getText().toString().trim().isEmpty()){
            tiNombre.setError(CAMPO_INCOMPLETO);
            comp =false;
        }
        else
            tiNombre.setError(null);



        if(etEdad.getText().toString().trim().isEmpty()){
            tiEdad.setError(CAMPO_INCOMPLETO);
            comp =false;
        }

        else
            tiEdad.setError(null);



        if(isDouble(etAltura.getText().toString().trim())){
            aux = Double.parseDouble(etAltura.getText().toString().trim());

            if(aux <0.5  || aux > 2.5){
                tiAltura.setError("Introduce un valor válido");
                comp =false;
            }
            else
                tiAltura.setError(null);
        }
        else{
            tiAltura.setError("Introduce un valor válido");
            comp =false;
        }



        if(isDouble(etPeso.getText().toString().trim())){
            aux = Double.parseDouble(etPeso.getText().toString().trim());

            if(aux < 10  || aux > 400){
                tiPeso.setError("Introduce un valor válido");
                comp =false;
            }

            else
                tiPeso.setError(null);
        }
        else{
            tiPeso.setError("Introduce un valor válido");
            comp =false;
        }


        if(etCorreo.getText().toString().trim().isEmpty()){
            tiCorreo.setError(CAMPO_INCOMPLETO);
            comp =false;
        }

        else
            tiCorreo.setError(null);

        return comp;
    }

    private void almacenarRealm(){

        Usuario us;

        realm.beginTransaction();
        us = realm.where(Usuario.class).equalTo("id", 1).findFirst();
        us.setNombre(etNombre.getText().toString().trim().toUpperCase());
        us.setCorreo(etCorreo.getText().toString().trim());
        us.setFecha_nacimiento(etEdad.getText().toString());
        us.setAltura(Double.parseDouble(etAltura.getText().toString().trim()));
        us.setPeso( Double.parseDouble(etPeso.getText().toString().trim()));

        realm.copyToRealmOrUpdate(us);
        realm.commitTransaction();
    }

    public boolean isDouble(String numero){
        try{
            Double.parseDouble(numero);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }




}