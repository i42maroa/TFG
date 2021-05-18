package com.example.tfg.Sistems.SistemaPlatos.adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tfg.R;
import com.example.tfg.Sistems.SistemaAlmacenamiento.Models.PlatoAlmacenado;
import com.example.tfg.Sistems.SistemaPlatos.models.Category;
import com.example.tfg.Sistems.SistemaPlatos.models.Plato;
import com.example.tfg.app.Const;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import io.realm.Realm;

public class AdaptadorPlatosAlmacenados extends BaseAdapter {
    private Context context;
    private List<PlatoAlmacenado> list;
    private int layout;
    private Realm realm;
    int raciones;

    public AdaptadorPlatosAlmacenados(Context context, List<PlatoAlmacenado> plato, int layout) {
        this.context = context;
        this.list = plato;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(layout, null);
            vh = new ViewHolder();
            vh.nombre = (TextView) convertView.findViewById(R.id.listViewItemPlatoAlmacenadoTextViewNombre);
            vh.racion = (TextView) convertView.findViewById(R.id.listViewItemPlatoAlmacenadoTextViewRacion);
            vh.fecha = (TextView) convertView.findViewById(R.id.listViewItemPlatoAlmacenadoTextViewFecha);
            vh.imagePlato = (ImageView) convertView.findViewById(R.id.listViewItemPlatoAlmacenadoImage);
            vh.buttonMas = (Button) convertView.findViewById(R.id.listViewItemPlatoAlmacenadoButtonMasRacion);
            vh.buttonMenos = (Button) convertView.findViewById(R.id.listViewItemPlatoAlmacenadoButtonMenosRacion);

            convertView.setTag(vh);
        }else{
            vh = (ViewHolder) convertView.getTag();
        }

        realm = Realm.getDefaultInstance();

        final PlatoAlmacenado platoAlmacenado = list.get(position);
        Plato plato = realm.where(Plato.class).equalTo("id", platoAlmacenado.getIdPlato()).findFirst();

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String createdAt = df.format(platoAlmacenado.getFechaAlmacen());
        raciones = platoAlmacenado.getRaciones();

        vh.nombre.setText(plato.getNombre().toUpperCase());
        vh.racion.setText( raciones + " rac.");
        vh.fecha.setText(createdAt);
        vh.imagePlato.setImageResource(plato.getImage());

        vh.buttonMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                raciones++;
                actualizarPlatoAlmacenado(raciones, platoAlmacenado);
            }
        });

        vh.buttonMenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(raciones == 0)
                    eliminarPlatoAlmacenado(platoAlmacenado);
                else{
                    raciones--;
                    actualizarPlatoAlmacenado(raciones, platoAlmacenado);
                }
            }
        });

        return convertView;
    }

    public class ViewHolder{
        TextView nombre;
        TextView racion;
        TextView fecha;
        ImageView imagePlato;
        Button buttonMas;
        Button buttonMenos;
    }

    public void actualizarPlatoAlmacenado(int raciones, PlatoAlmacenado platoAlmacenado){
        realm.beginTransaction();
        platoAlmacenado.setRaciones(raciones);
        realm.copyToRealmOrUpdate(platoAlmacenado);
        realm.commitTransaction();

    }

    public void eliminarPlatoAlmacenado(PlatoAlmacenado platoAlmacenado){
        realm.beginTransaction();
        Category cat = realm.where(Category.class).equalTo("name", Const.CATEGORIA_STORE).findFirst();

        for(int i=0;i<cat.getListPlatos().size(); i++){
            if(cat.getListPlatos().get(i).getId() == platoAlmacenado.getIdPlato())
                cat.getListPlatos().remove(i);
        }

        platoAlmacenado.deleteFromRealm();
        realm.commitTransaction();
    }
}

