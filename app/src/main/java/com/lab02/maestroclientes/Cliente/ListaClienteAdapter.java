package com.lab02.maestroclientes.Cliente;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lab02.maestroclientes.ListaZonaAdapter;
import com.lab02.maestroclientes.R;
import com.lab02.maestroclientes.SelectListener;
import com.lab02.maestroclientes.db.DbTipoCliente;
import com.lab02.maestroclientes.db.DbZonas;
import com.lab02.maestroclientes.entidades.Cliente;
import com.lab02.maestroclientes.entidades.TipoCliente;
import com.lab02.maestroclientes.entidades.Zona;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListaClienteAdapter extends RecyclerView.Adapter<ListaClienteAdapter.ClienteViewHolder>  {
    ArrayList<Cliente> listaClientes;
    ArrayList<Cliente> listaOriginal=new ArrayList<Cliente>();
    DbTipoCliente dbTclientes;
    Zona zona;
    TipoCliente tcliente;
    Context context;
    DbZonas dbzonas;
    private SelectListenerCliente listener;
    public ListaClienteAdapter(ArrayList<Cliente> listaClientes, SelectListenerCliente listener,Context context){
        this.listaClientes=listaClientes;
        this.listener=listener;
        this.context=context;
        listaOriginal.addAll(listaClientes);

    }
    @NonNull
    @Override
    public ListaClienteAdapter.ClienteViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_element_cliente,null,false);
        return new ListaClienteAdapter.ClienteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClienteViewHolder holder, int  position) {
        int po=position;
        dbzonas = new DbZonas(context);
        dbTclientes = new DbTipoCliente(context);
        holder.codigo.setText(listaClientes.get(position).getCodigo()+"");
        holder.nombre.setText(listaClientes.get(position).getNombre());
        holder.ruc.setText(listaClientes.get(position).getRuc()+"");
        zona=dbzonas.verZona(listaClientes.get(position).getCodigozona());
        holder.zonaC.setText(zona.getNombre()+"");
        tcliente=dbTclientes.verTipoCliente(listaClientes.get(position).getCodigotipocliente());
        holder.tipoCC.setText(tcliente.getNombre()+"");
        holder.estado.setText(listaClientes.get(position).getEstado());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClicked(listaClientes.get(po));
            }
        });
    }
    public void filtrado(final String txtBuscar) {
        int longitud = txtBuscar.length();
        if (longitud == 0) {
            listaClientes.clear();
            listaClientes.addAll(listaOriginal);
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Cliente> collecion = listaClientes.stream()
                        .filter(i -> i.getNombre().toLowerCase().contains(txtBuscar.toLowerCase()))
                        .collect(Collectors.toList());
                listaClientes.clear();
                listaClientes.addAll(collecion);
            } else {
                for (Cliente c : listaOriginal) {
                    if (c.getNombre().toLowerCase().contains(txtBuscar.toLowerCase())) {
                        listaClientes.add(c);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return listaClientes.size();
    }



    public class ClienteViewHolder extends RecyclerView.ViewHolder {
        TextView codigo, nombre, estado,ruc,zonaC,tipoCC;
        CardView cardView;

        public ClienteViewHolder(@NonNull View itemView) {
            super(itemView);
            codigo = itemView.findViewById(R.id.codigoCliente);
            nombre = itemView.findViewById(R.id.nombreCliente);
            ruc = itemView.findViewById(R.id.rucCliente);
            zonaC = itemView.findViewById(R.id.zonaCliente);
            tipoCC = itemView.findViewById(R.id.tipoCC);
            estado = itemView.findViewById(R.id.estadoregistroCliente);
            cardView=itemView.findViewById(R.id.cardCliente);

        }
    }

}
