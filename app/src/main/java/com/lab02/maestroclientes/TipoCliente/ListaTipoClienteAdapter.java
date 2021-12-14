package com.lab02.maestroclientes.TipoCliente;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.lab02.maestroclientes.R;
import com.lab02.maestroclientes.entidades.TipoCliente;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListaTipoClienteAdapter extends RecyclerView.Adapter<ListaTipoClienteAdapter.TipoClienteViewHolder>  {
    ArrayList<TipoCliente> listaTipoClientes;
    ArrayList<TipoCliente> listaOriginal=new ArrayList<TipoCliente>();
    private SelectListenerTipoCliente listener;
    public  ListaTipoClienteAdapter(ArrayList<TipoCliente> listaTipoClientes,SelectListenerTipoCliente listener){
        this.listaTipoClientes=listaTipoClientes;
        this.listener=listener;
        listaOriginal.addAll(listaTipoClientes);

    }
    @NonNull
    @Override
    public ListaTipoClienteAdapter.TipoClienteViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_element_tipo_cliente,null,false);
        return new ListaTipoClienteAdapter.TipoClienteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TipoClienteViewHolder holder, int  position) {
        int po=position;
        holder.codigo.setText(listaTipoClientes.get(position).getCodigo()+"");
        holder.nombre.setText(listaTipoClientes.get(position).getNombre());
        holder.estado.setText(listaTipoClientes.get(position).getEstado());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClicked(listaTipoClientes.get(po));
            }
        });
    }
    public void filtrado(final String txtBuscar) {
        int longitud = txtBuscar.length();
        if (longitud == 0) {
            listaTipoClientes.clear();
            listaTipoClientes.addAll(listaOriginal);
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<TipoCliente> collecion = listaTipoClientes.stream()
                        .filter(i -> i.getNombre().toLowerCase().contains(txtBuscar.toLowerCase()))
                        .collect(Collectors.toList());
                listaTipoClientes.clear();
                listaTipoClientes.addAll(collecion);
            } else {
                for (TipoCliente c : listaOriginal) {
                    if (c.getNombre().toLowerCase().contains(txtBuscar.toLowerCase())) {
                        listaTipoClientes.add(c);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return listaTipoClientes.size();
    }
    public class TipoClienteViewHolder extends RecyclerView.ViewHolder {
        TextView codigo, nombre, estado;
        CardView cardView;

        public TipoClienteViewHolder(@NonNull View itemView) {
            super(itemView);
            codigo = itemView.findViewById(R.id.codigo);
            nombre = itemView.findViewById(R.id.nombre);
            estado = itemView.findViewById(R.id.estadoregistro);
            cardView=itemView.findViewById(R.id.card);

        }
    }

}
