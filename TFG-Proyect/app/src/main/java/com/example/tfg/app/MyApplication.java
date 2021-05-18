package com.example.tfg.app;

import android.app.Application;

import com.example.tfg.Sistems.SistemaAlmacenamiento.Models.Almacenamiento;
import com.example.tfg.Sistems.SistemaAlmacenamiento.Models.PlatoAlmacenado;
import com.example.tfg.Sistems.SistemaHorario.models.Anio;
import com.example.tfg.Sistems.SistemaHorario.models.Dia;
import com.example.tfg.Sistems.SistemaHorario.models.FranjaHorario;
import com.example.tfg.Sistems.SistemaHorario.models.Semana;
import com.example.tfg.Sistems.SistemaPerfil.models.Usuario;
import com.example.tfg.Sistems.SistemaPlatos.models.Category;
import com.example.tfg.Sistems.SistemaPlatos.models.CategoryIngredient;
import com.example.tfg.Sistems.SistemaPlatos.models.Ingrediente;
import com.example.tfg.Sistems.SistemaPlatos.models.IngredienteLista;
import com.example.tfg.Sistems.SistemaPlatos.models.PasoPlato;
import com.example.tfg.Sistems.SistemaPlatos.models.Plato;

import java.util.concurrent.atomic.AtomicInteger;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class MyApplication extends Application {

    public static AtomicInteger PlatoID = new AtomicInteger();
    public static AtomicInteger IngredienteID = new AtomicInteger();
    public static AtomicInteger AnioID = new AtomicInteger();
    public static AtomicInteger MesID = new AtomicInteger();
    public static AtomicInteger DiaID = new AtomicInteger();
    public static AtomicInteger FranjaID = new AtomicInteger();
    public static AtomicInteger AlmacenID = new AtomicInteger();
    public static AtomicInteger PlatoAlmacenID = new AtomicInteger();
    public static AtomicInteger CantidadIngredienteID = new AtomicInteger();
    public static AtomicInteger IngredienteListaID = new AtomicInteger();
    public static AtomicInteger CategoriaID = new AtomicInteger();
    public static AtomicInteger CategoriaIDIngrediente = new AtomicInteger();
    public static AtomicInteger PasoID = new AtomicInteger();
    public static AtomicInteger SemanaID = new AtomicInteger();
    public static AtomicInteger UsuarioID = new AtomicInteger();

    public static int inicio;

    public static int PRIMER_INICIO = 0;
    public static int USO_NORMAL = 1;

    @Override
    public void onCreate() {
        setUpRealmConfig();

        Realm realm = Realm.getDefaultInstance();

        //eliminarBD(realm);

        PlatoID = getIdByTable(realm, Plato.class);
        IngredienteID = getIdByTable(realm, Ingrediente.class);
        AnioID = getIdByTable(realm, Anio.class);
        DiaID = getIdByTable(realm, Dia.class);
        FranjaID = getIdByTable(realm, FranjaHorario.class);
        AlmacenID = getIdByTable(realm, Almacenamiento.class);
        PlatoAlmacenID = getIdByTable(realm, PlatoAlmacenado.class);
        CantidadIngredienteID = getIdByTable(realm, Ingrediente.class);
        IngredienteListaID = getIdByTable(realm, IngredienteLista.class);
        CategoriaID = getIdByTable(realm, Category.class);
        CategoriaIDIngrediente = getIdByTable(realm, CategoryIngredient.class);
        PasoID = getIdByTable(realm, PasoPlato.class);
        SemanaID = getIdByTable(realm, Semana.class);
        UsuarioID = getIdByTable(realm, Usuario.class);

        if (PlatoID.get() == 0 && IngredienteID.get() == 0  && AnioID.get() == 0 && DiaID.get() == 0 && FranjaID.get() == 0 && AlmacenID.get() == 0 && PlatoAlmacenID.get() == 0 && AlmacenID.get() == 0 && CategoriaID.get() == 0 && UsuarioID.get() == 0)
            rellenarBD(realm); //Rellenar BD




        asignarSemana(realm);

        realm.close();
        super.onCreate();
    }

    private void setUpRealmConfig(){
        Realm.init(getApplicationContext());
        RealmConfiguration config = new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }

    private <T extends RealmObject> AtomicInteger getIdByTable(Realm realm, Class<T> anyClass){
        RealmResults<T> results = realm.where(anyClass).findAll();

        //Si ha encontrado resultados le pasa el valor del ultimo id, si no le pasa el valor 0
        return (results.size() > 0) ? new AtomicInteger(results.max("id").intValue()) : new AtomicInteger(); //  si solo se pone AtomicInteger() == 0
    }



    public void eliminarBD(Realm realm){
        realm.beginTransaction();
        realm.deleteAll();
        realm.commitTransaction();
    }

    public void rellenarBD(Realm realm){

        realm.beginTransaction();

        for (Ingrediente ingrediente: RellenarBD.rellenarListaIngredientes())
            realm.copyToRealm(ingrediente);

        for(Almacenamiento alm : RellenarBD.rellenarListaAlmacen())
            realm.copyToRealm(alm);

        RellenarBD.crearPlatos(realm);

        RellenarBD.introducirPlatosCategorias(realm);

        RellenarBD.introducirIngredienteCategorias(realm);

        realm.copyToRealm(RellenarBD.crearUsuario());

        RellenarBD.crearHorario(realm);

        realm.commitTransaction();

    }

    public void asignarSemana(Realm realm){
        realm.beginTransaction();
        RellenarBD.asignarSemana(realm);
        realm.commitTransaction();
    }
}
