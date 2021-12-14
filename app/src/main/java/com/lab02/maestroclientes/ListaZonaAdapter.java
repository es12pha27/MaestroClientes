package com.lab02.maestroclientes;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.lab02.maestroclientes.entidades.Zona;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListaZonaAdapter extends RecyclerView.Adapter<ListaZonaAdapter.ZonaViewHolder>  {
    ArrayList<Zona> listaZonas;
    ArrayList<Zona> listaOriginal=new ArrayList<Zona>();
    private SelectListener listener;
    public ListaZonaAdapter(ArrayList<Zona> listaZonas,SelectListener listener){
        this.listaZonas=listaZonas;
        this.listener=listener;
        listaOriginal.addAll(listaZonas);

    }
    @NonNull
    @Override
    public ZonaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_element,null,false);
        return new ZonaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ZonaViewHolder holder, int  position) {
        int po=position;
        holder.codigo.setText(listaZonas.get(position).getCodigo()+"");
        holder.nombre.setText(listaZonas.get(position).getNombre());
        holder.estado.setText(listaZonas.get(position).getEstado());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClicked(listaZonas.get(po));
            }
        });
    }
    public void filtrado(final String txtBuscar) {
       int longitud = txtBuscar.length();
        if (longitud == 0) {
            listaZonas.clear();
            listaZonas.addAll(listaOriginal);
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Zona> collecion = listaZonas.stream()
                        .filter(i -> i.getNombre().toLowerCase().contains(txtBuscar.toLowerCase()))
                        .collect(Collectors.toList());
                listaZonas.clear();
                listaZonas.addAll(collecion);
            } else {
                for (Zona c : listaOriginal) {
                    if (c.getNombre().toLowerCase().contains(txtBuscar.toLowerCase())) {
                        listaZonas.add(c);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return listaZonas.size();
    }



    public class ZonaViewHolder extends RecyclerView.ViewHolder {
        TextView codigo, nombre, estado;
        CardView cardView;

        public ZonaViewHolder(@NonNull View itemView) {
            super(itemView);
            codigo = itemView.findViewById(R.id.codigo);
            nombre = itemView.findViewById(R.id.nombre);
            estado = itemView.findViewById(R.id.estadoregistro);
            cardView=itemView.findViewById(R.id.card);

        }
        }

}
