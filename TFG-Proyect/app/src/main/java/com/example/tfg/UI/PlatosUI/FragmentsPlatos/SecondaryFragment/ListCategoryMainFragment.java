package com.example.tfg.UI.PlatosUI.FragmentsPlatos.SecondaryFragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.tfg.R;
import com.example.tfg.Sistems.SistemaPlatos.adaptors.AdaptadorReciclerCategoria;
import com.example.tfg.Sistems.SistemaPlatos.models.Category;
import com.example.tfg.app.Const;
import com.example.tfg.fragments.adaptors.iDataListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import io.realm.Realm;
import io.realm.RealmResults;

public class ListCategoryMainFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private Realm realm;
    private RealmResults<Category> categories;

    private iDataListener callback;

    private FloatingActionButton fab;


    public ListCategoryMainFragment() {
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

        View view = inflater.inflate(R.layout.fragment_categoria, container, false);

        setRealm(); setmRecyclerView(view); setmAdapter(view);
        setFloatingButtonListener(fab);

        return view;
    }

    private void setmAdapter(View view) {
        mAdapter = new AdaptadorReciclerCategoria(categories,  R.layout.lista_categorias_item, new AdaptadorReciclerCategoria.OnItemClickListener() {
            @Override
            public void onItemClick(Category category, int position) {
                callback.enviarIdCategoria(category.getId());
            }

            @Override
            public void onItemLongClick(Category category, int position) {
                Toast.makeText(getContext(), category.getDescription(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setmRecyclerView(View view) {
        fab = (FloatingActionButton) view.findViewById(R.id.fragmentListPlatosAddPlat);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.fragmentCategoriaRecyclerView);
        mLayoutManager = new GridLayoutManager(view.getContext(), 2);
    }

    private void setRealm() {
        realm = Realm.getDefaultInstance();
        categories = realm.where(Category.class).notEqualTo("name", Const.CATEGORIA_STORE).findAll();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void setFloatingButtonListener(final FloatingActionButton floatingButtonListener){
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreacionPlato creacionPlato = new CreacionPlato();

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentPlatoGenericoContenedor, creacionPlato)
                        .addToBackStack(null);
                transaction.commit();
            }
        });
    }
}