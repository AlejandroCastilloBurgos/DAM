package com.example.todosejercicios.ut05;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
//2do
import androidx.recyclerview.widget.RecyclerView;

import com.example.todosejercicios.R;

import java.util.ArrayList;
import java.util.Arrays;

public class EstadiosAdapter extends RecyclerView.Adapter<EstadiosAdapter.ViewHolder>{

    private ArrayList<EstadiosFutbol> datos;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView nombre;
        private final TextView estadio;
        private final TextView aforo;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            nombre = (TextView) view.findViewById(R.id.NombreEquipo);
            estadio = (TextView) view.findViewById(R.id.Estadio);
            aforo = (TextView) view.findViewById(R.id.Aforo);
        }

        public TextView getTextNombre() {
            return nombre;
        }

        public TextView getTextEstadio() {
            return estadio;
        }
        public TextView getTextAforo() {
            return aforo;
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used
     *                by RecyclerView.
     */
    public EstadiosAdapter(EstadiosFutbol[] dataSet) {
        datos = new ArrayList<EstadiosFutbol>();
        add(dataSet);
    }

    // Create new views (invoked by the layout manager)
    @Override
    public EstadiosAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_estadios, viewGroup, false);

        return new EstadiosAdapter.ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(EstadiosAdapter.ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getTextNombre().setText(datos.get(position).nombre);
        viewHolder.getTextEstadio().setText(datos.get(position).estadio);
        viewHolder.getTextAforo().setText(datos.get(position).aforo);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return datos.size();
    }

    public void add(EstadiosFutbol[] dataSet) {
        datos.addAll(Arrays.asList(dataSet));
        notifyDataSetChanged();
    }
}