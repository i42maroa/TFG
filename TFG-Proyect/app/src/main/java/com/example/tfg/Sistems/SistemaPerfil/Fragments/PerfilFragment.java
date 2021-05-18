package com.example.tfg.Sistems.SistemaPerfil.Fragments;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tfg.FormularioInicio;
import com.example.tfg.R;
import com.example.tfg.Sistems.SistemaPerfil.models.Usuario;
import com.example.tfg.app.Const;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.DecimalFormat;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmModel;


public class PerfilFragment extends Fragment {

    private Button buttonModificar, buttonCalorias;
    private TextView textNombre, textEdad, textPeso, textAltura, textCorreo, textIMC;
    private ImageView imgGenero, imgInfo;

    private double imc;

    private Usuario usu, usuMod;

    private Realm realm;

    public PerfilFragment() {
    }

    public static PerfilFragment newInstance(String param1, String param2) {
        PerfilFragment fragment = new PerfilFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        setRealm();
        setReferencias(view);
        settersElementos();

        usu.addChangeListener(new RealmChangeListener<RealmModel>() {
            @Override
            public void onChange(RealmModel realmModel) {
                settersElementos();
            }
        });


        imgInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertForInfo("INFORMACION IMC");
            }
        });

        buttonModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertForModficarAlmacen("MODIFICAR PERFIL");
            }
        });

        buttonCalorias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertForChangeCalorias("");
            }
        });

        return view;
    }

    private void setRealm(){
        realm = Realm.getDefaultInstance();
        usu = realm.where(Usuario.class).equalTo("id", 1).findFirst();

        if(usu == null)
            usu = new Usuario("","",Usuario.USUARIO_HOMBRE,"",0,0);
    }

    private void setReferencias(View view){
        buttonModificar = (Button) view.findViewById(R.id.fragmentPerfilButtonModificar);
        buttonCalorias = (Button) view.findViewById(R.id.fragmentPerfilButtonModificarCalorias);
        textAltura = (TextView) view.findViewById(R.id.fragmentPerfilTextViewAltura);
        textPeso = (TextView) view.findViewById(R.id.fragmentPerfilTextViewPeso);
        textCorreo = (TextView) view.findViewById(R.id.fragmentPerfilTextViewCorreo);
        textNombre = (TextView) view.findViewById(R.id.fragmentPerfilTextViewNombre);
        textEdad = (TextView) view.findViewById(R.id.fragmentPerfilTextViewEdad);
        imgGenero = (ImageView) view.findViewById(R.id.fragmentPerfilImageGenero);
        imgInfo = (ImageView) view.findViewById(R.id.fragmentPerfilImageViewInfo);
        textIMC = (TextView) view.findViewById(R.id.fragmentPerfilTextViewIMC);

        usuMod = new Usuario();
    }

    private void settersElementos(){
        textNombre.setText(usu.getNombre().toUpperCase());
        textCorreo.setText(usu.getCorreo());
        textPeso.setText(Double.toString(usu.getPeso()) + " kg" );
        textAltura.setText(Double.toString(usu.getAltura()) + " m");
        textEdad.setText(usu.getFecha_nacimiento());

        if(usu.getGenero() == Usuario.USUARIO_HOMBRE)
            imgGenero.setImageResource(R.drawable.male);
        else
            imgGenero.setImageResource(R.drawable.female);

        imc = usu.getAltura() * usu.getAltura();
        imc = usu.getPeso() / imc;
        textIMC.setText(new DecimalFormat("0.00").format(imc));
    }

    private void showAlertForModficarAlmacen(String title){

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        if(title != null) builder.setTitle(title);

        View viewInflater = LayoutInflater.from(getContext()).inflate(R.layout.dialog_mod_usuario, null);
        builder.setView(viewInflater);

        final EditText etNombre = (TextInputEditText) viewInflater.findViewById(R.id.dialogModUsuarioEditNombre);
        final EditText etEdad = (TextInputEditText) viewInflater.findViewById(R.id.dialogModUsuarioEdiEdad);
        final EditText etPeso = (TextInputEditText) viewInflater.findViewById(R.id.dialogModUsuarioEditPeso);
        final EditText etAltura = (TextInputEditText) viewInflater.findViewById(R.id.dialogModUsuarioEditAltura);
        final EditText etCorreo = (TextInputEditText) viewInflater.findViewById(R.id.dialogModUsuarioEditCorreo);

        final TextInputLayout tiEdad = (TextInputLayout) viewInflater.findViewById(R.id.dialogModUsuarioEdiEdadTL);
        final TextInputLayout tiNombre = (TextInputLayout) viewInflater.findViewById(R.id.dialogModUsuarioEditNombreTL);
        final TextInputLayout tiAltura = (TextInputLayout) viewInflater.findViewById(R.id.dialogModUsuarioEditAlturaTL);
        final TextInputLayout tiPeso = (TextInputLayout) viewInflater.findViewById(R.id.dialogModUsuarioEditPesoTL);
        final TextInputLayout tiCorreo = (TextInputLayout) viewInflater.findViewById(R.id.dialogModUsuarioEditCorreoTL);

        final ImageView imageView = (ImageView) viewInflater.findViewById(R.id.dialogImageHombre);


        imageView.setImageResource((usu.getGenero() == Usuario.USUARIO_HOMBRE)? R.drawable.male: R.drawable.female);
        etNombre.setText(usu.getNombre());
        etEdad.setText(String.valueOf(usu.getFecha_nacimiento()));
        etAltura.setText(String.valueOf(usu.getAltura()));
        etCorreo.setText(usu.getCorreo());
        etPeso.setText(String.valueOf(usu.getPeso()));



        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                realm.beginTransaction();
                if(usu.getGenero() == Usuario.USUARIO_HOMBRE){
                    imageView.setImageResource(R.drawable.female);
                    usu.setGenero(Usuario.USUARIO_MUJER);
                }
                else{
                    imageView.setImageResource(R.drawable.male);
                    usu.setGenero(Usuario.USUARIO_HOMBRE);
                }
                realm.commitTransaction();
            }
        });

        builder.setPositiveButton("Modificar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                boolean comp =  true;
                double aux;

                if(etNombre.getText().toString().trim().isEmpty()){
                    tiNombre.setError(FormularioInicio.CAMPO_INCOMPLETO);
                    comp =false;
                }
                else
                    tiNombre.setError(null);


                if(etEdad.getText().toString().trim().isEmpty()){
                    tiEdad.setError(FormularioInicio.CAMPO_INCOMPLETO);
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
                    tiCorreo.setError(FormularioInicio.CAMPO_INCOMPLETO);
                    comp =false;
                }
                else
                    tiCorreo.setError(null);


                if(comp){

                    usuMod.setNombre(etNombre.getText().toString().trim());
                    usuMod.setCorreo( etCorreo.getText().toString().trim());
                    usuMod.setFecha_nacimiento(etEdad.getText().toString().trim());
                    usuMod.setAltura(Double.valueOf(etAltura.getText().toString().trim()));
                    usuMod.setPeso(Double.valueOf(etPeso.getText().toString().trim()));

                    modificarUsuario(usuMod);
                }
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void modificarUsuario(Usuario usuarioM){
        Usuario us;

        realm.beginTransaction();
        us = realm.where(Usuario.class).equalTo("id", 1).findFirst();
        us.setNombre(usuarioM.getNombre());
        us.setCorreo(usuarioM.getCorreo());
        us.setGenero(usuarioM.getGenero());
        us.setFecha_nacimiento(usuarioM.getFecha_nacimiento());
        us.setAltura(usuarioM.getAltura());
        us.setPeso(usuarioM.getPeso());

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

    public void showAlertForInfo(String title){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        if(title != null) builder.setTitle("ÍNDICE MASA CORPORAL");

        View viewInflater = LayoutInflater.from(getContext()).inflate(R.layout.dialog_infoimc, null);
        builder.setView(viewInflater);

        final TextView textIMC = (TextView) viewInflater.findViewById(R.id.dialogInfoIMC);
        final TextView textInfoR = (TextView) viewInflater.findViewById(R.id.dialogInfInfoRango);
        final TextView textInfo = (TextView) viewInflater.findViewById(R.id.dialogInfInfo);
        final ImageView pb = (ImageView) viewInflater.findViewById(R.id.dialogInfoBP);
        final ImageView pn = (ImageView) viewInflater.findViewById(R.id.dialogInfoPN);
        final ImageView sp = (ImageView) viewInflater.findViewById(R.id.dialogInfoSP);
        final ImageView ob = (ImageView) viewInflater.findViewById(R.id.dialogInfoOB);

        textIMC.setText(new DecimalFormat("0.00").format(imc));

       switch (obtenerFranjaIMC(imc)){
           case 0:
               pb.setImageResource(R.drawable.flecha_abajo_dos);
               textInfoR.setText("Por debajo de 18.5");
               textInfo.setText(R.string.info_infrapeso);
               break;
           case 1:
               pn.setImageResource(R.drawable.flecha_abajo_dos);
               textInfoR.setText("18.5 a 24.9");
               textInfo.setText(R.string.info_peso_normal);
               break;
           case 2:
               sp.setImageResource(R.drawable.flecha_abajo_dos);
               textInfoR.setText("25 a 29.9");
               textInfo.setText(R.string.info_sobrepeso);
               break;
           case 3:
               ob.setImageResource(R.drawable.flecha_abajo_dos);
               textInfoR.setText("30+");
               textInfo.setText(R.string.info_obesidad);
               break;
           default:
            break;
       }

        builder.setNegativeButton("Atrás", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    public void showAlertForChangeCalorias(String title){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        if(title != null) builder.setTitle("CAMBIO DE CALORÍAS");

        View viewInflater = LayoutInflater.from(getContext()).inflate(R.layout.dialog_cambiar_cal, null);
        builder.setView(viewInflater);

        final TextView textViewTo = (TextView) viewInflater.findViewById(R.id.dialogTextViewCalTo);
        final TextView textViewDes = (TextView) viewInflater.findViewById(R.id.dialogTextViewCalDes);
        final TextView textViewAl = (TextView) viewInflater.findViewById(R.id.dialogTextViewCalAl);
        final TextView textViewCen = (TextView) viewInflater.findViewById(R.id.dialogTextViewCalCena);
        final TextView textViewComp = (TextView) viewInflater.findViewById(R.id.dialogTextViewCalComp);


        final SeekBar seekBarDes = (SeekBar) viewInflater.findViewById(R.id.dialogSeekBarCalDes);
        final SeekBar seekBarAl = (SeekBar) viewInflater.findViewById(R.id.dialogSeekBarCalAlm);
        final SeekBar seekBarCen = (SeekBar) viewInflater.findViewById(R.id.dialogSeekBarCalCena);
        final SeekBar seekBarCom = (SeekBar) viewInflater.findViewById(R.id.dialogSeekBarCalCom);
        Button buttonMod = (Button) viewInflater.findViewById(R.id.dialogCalBotom);
        final Usuario usu = realm.where(Usuario.class).findFirst();

        seekBarDes.setProgress(usu.getCalDes());
        seekBarAl.setProgress(usu.getCalAl());
        seekBarCen.setProgress(usu.getCalCena());
        seekBarCom.setProgress(usu.getCalComp());

        textViewDes.setText(seekBarDes.getProgress() + " cal");
        textViewAl.setText(seekBarAl.getProgress() + " cal");
        textViewCen.setText(seekBarCen.getProgress() + " cal");
        textViewComp.setText(seekBarCom.getProgress() + " cal");
        int total = seekBarDes.getProgress() + seekBarAl.getProgress() + seekBarCen.getProgress() + seekBarCom.getProgress();
        textViewTo.setText(total + " cal");

        buttonMod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seekBarDes.setProgress(Const.CAL_DESAYUNO);
                seekBarAl.setProgress(Const.CAL_ALMUERZO);
                seekBarCen.setProgress(Const.CAL_CENA);
                seekBarCom.setProgress(Const.CAL_COMP);

                textViewDes.setText(seekBarDes.getProgress() + " cal");
                textViewAl.setText(seekBarAl.getProgress() + " cal");
                textViewCen.setText(seekBarCen.getProgress() + " cal");
                textViewComp.setText(seekBarCom.getProgress() + " cal");
                int total = seekBarDes.getProgress() + seekBarAl.getProgress() + seekBarCen.getProgress() + seekBarCom.getProgress();
                textViewTo.setText(total + " cal");
            }
        });

        seekBarDes.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress = progress / 20;
                progress = progress * 20;
                textViewDes.setText(progress + " cal");
                int total = progress + seekBarAl.getProgress() + seekBarCen.getProgress() + seekBarCom.getProgress();
                textViewTo.setText(total + " cal");
                seekBarDes.setProgress(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        seekBarAl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress = progress / 20;
                progress = progress * 20;
                textViewAl.setText(progress + " cal");
                int total = seekBarDes.getProgress() + progress + seekBarCen.getProgress() + seekBarCom.getProgress();
                textViewTo.setText(total + " cal");
                seekBarAl.setProgress(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        seekBarCen.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress = progress / 20;
                progress = progress * 20;
                textViewCen.setText(progress + " cal");
                int total = seekBarDes.getProgress() + seekBarAl.getProgress() + progress + seekBarCom.getProgress();
                textViewTo.setText(total + " cal");
                seekBarCen.setProgress(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        seekBarCom.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress = progress / 20;
                progress = progress * 20;
                textViewComp.setText(progress + " cal");
                int total = seekBarDes.getProgress() + seekBarAl.getProgress() + seekBarCen.getProgress() + progress;
                textViewTo.setText(total + " cal");
                seekBarCom.setProgress(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.setPositiveButton("Modificar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                realm.beginTransaction();
                usu.setCalDes(seekBarDes.getProgress());
                usu.setCalAl(seekBarAl.getProgress());
                usu.setCalCena(seekBarCen.getProgress());
                usu.setCalComp(seekBarCom.getProgress());
                realm.copyToRealmOrUpdate(usu);
                realm.commitTransaction();
                Toast.makeText(getContext(), "Valores modificados", Toast.LENGTH_SHORT).show();
            }
        });


        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    private int obtenerFranjaIMC(double imc) {
        if (imc < 18.5)
            return 0;
        if ( imc < 25 )
            return 1;
        if( imc < 30 )
            return 2;

        return 3;
    }
}