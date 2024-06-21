package com.example.todosejercicios.ut02.Ej2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.todosejercicios.R;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class DestinosAdapter extends RecyclerView.Adapter<DestinosAdapter.ViewHolder> {

    private ArrayList<Destino> datos;

    public interface ItemClickListener {
        void onClick(View view, int position, Destino unDestino);
    }

    private ItemClickListener clickListener;

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView tvNombre;
        private final TextView tvPrecio;
        private final TextView tvPais;
        private final TextView tvCiudad;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            tvNombre = (TextView) view.findViewById(R.id.tvNombre);
            tvPrecio = (TextView) view.findViewById(R.id.tvPrecio);
            tvPais = (TextView) view.findViewById(R.id.tvPais);
            tvCiudad = (TextView) view.findViewById(R.id.tvCiudad);
            view.setOnClickListener(this);
        }

        public void setInfo(String nombre, String precio, String pais, String ciudad ) {
            tvNombre.setText(nombre);
            tvPrecio.setText(precio);
            tvCiudad.setText(ciudad);
            tvPais.setText(pais);
        }

        @Override
        public void onClick(View view) {
            // Si tengo un manejador de evento lo propago con el índice
            if (clickListener != null) clickListener.onClick(view, getAdapterPosition(), datos.get(getAdapterPosition()));
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data_RickAndMorty to populate views to be used
     * by RecyclerView.
     */
    public DestinosAdapter(ArrayList<Destino> dataSet) {
        datos = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_destinos, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        Destino unDestino = datos.get(position);
        viewHolder.setInfo(unDestino.getNombre(), unDestino.getCiudad(), String.valueOf(unDestino.getPrecio()), unDestino.getPais());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return datos.size();
    }

    public void setDestinos(ArrayList<Destino> destinos){
        datos.clear();
        datos.addAll(destinos);
        notifyDataSetChanged();
    }
}