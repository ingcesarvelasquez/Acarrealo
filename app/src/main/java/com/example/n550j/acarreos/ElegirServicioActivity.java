package com.example.n550j.acarreos;

import android.os.Bundle;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.n550j.acarreos.Objetos.Vehiculo;

import java.util.ArrayList;
import java.util.List;

public class ElegirServicioActivity extends AppCompatActivity implements MyAdapter.OnItemClickListener{
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elegir_servicio);
        getSupportActionBar().setTitle("Pedir servicio");
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
// use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this ,LinearLayoutManager.HORIZONTAL, false);

        mRecyclerView.setLayoutManager(mLayoutManager);


        List vehiculos=new ArrayList();
        vehiculos.add(new Vehiculo("1","Chevrolet","9 metros", "4 metros","2", R.drawable.camion));
        vehiculos.add(new Vehiculo("2","Chevrolet","8 metros", "2 metros","1", R.drawable.camion));
        vehiculos.add(new Vehiculo("3","Chevrolet","8 metros", "2 metros","1", R.drawable.camion));
        vehiculos.add(new Vehiculo("4","Chevrolet","8 metros", "2 metros","1", R.drawable.camion));

        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(vehiculos);
        mRecyclerView.setAdapter(mAdapter);

        llenarSpinner();

    }

/*Metodo sobreescrito para infla el menu de la barra
* en este caso tenemos unicamente el exit session*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_pedir_servicio, menu);
        return super.onCreateOptionsMenu(menu);
    }

/*Metodo para elegir la opcion seleccionada en el menu de la
* actividad @ElegirSercivicionActivity por ahora solo esta
* por implementar el sali de session */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            /*case R.id.action_search:

                return true;*/
            case R.id.action_exit:
                /*Metodo para aalir de la sesion de la app :P*/
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

  /*Metodo para llenar el Spinner (Menu desplegable con las horas de reseva
  )*/
    public  void llenarSpinner(){
        Spinner spinner = (Spinner) findViewById(R.id.spinnerHorario);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
        R.array.horario, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

    }


    @Override
    public void onClick(MyAdapter.ViewHolder holder, String idVehiculo) {

    }
}
class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<Vehiculo> mDataset;
    private OnItemClickListener escucha;
    public interface OnItemClickListener {
        public void onClick(ViewHolder holder, String idVehiculo);
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;
        public ImageView miImagen;
        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.nombre);
            miImagen= (ImageView) v.findViewById(R.id.imagen);
        }
    }
 /*   @Override
    public void onClick(View view) {
        escucha.onClick(this, obtenerIdAlquiler(getAdapterPosition()));
    }*/

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<Vehiculo> myDataset) {
        this.mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder( v);
        return vh;
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView.setText(mDataset.get(position).getAncho());
        holder.miImagen.setImageResource(mDataset.get(position).getImagen());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e( "ID*****", ""+ mDataset.get(position).getId());
              //  Toast.makeText(ElegirServicioActivity , ""+ mDataset.get(position).getId() ,Toast.LENGTH_SHORT).show();
                Snackbar.make(v, ""+ mDataset.get(position).getAncho(), Snackbar.LENGTH_LONG).show();

            }
        });
    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }



}
