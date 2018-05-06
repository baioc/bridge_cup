package br.ufsc.bridge.android.bridgecup.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.ufsc.bridge.android.bridgecup.R;
import br.ufsc.bridge.android.bridgecup.model.entity.Grupo;

/** Descreve e aplica a forma como os dados sobre os Grupos sao utilizados no layout */
public class GruposAdapter extends RecyclerView.Adapter<GruposAdapter.GrupoViewHolder> {

    private List<Grupo> grupos;
    private int rowLayout;
    private Context context;


    /** Construtor do Grupos adapter
     *
     *  @param grupos Lista de grupos utilizados para preencher o layout
     *  @param rowLayout Modelo de layout para referenciar a cada grupo da lista
     */
    public GruposAdapter(List<Grupo> grupos, int rowLayout, Context context) {
        this.grupos = grupos;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    /** Cria as referencias as Views do layout a serem utilizadas */
    public static class GrupoViewHolder extends RecyclerView.ViewHolder {
        TextView letra;
        ImageView[] bandeira = new ImageView[4];

        public GrupoViewHolder(View v) {
            super(v);
            letra = v.findViewById(R.id.tv_letra);
            bandeira[0] = v.findViewById(R.id.imageView0);
            bandeira[1] = v.findViewById(R.id.imageView1);
            bandeira[2] = v.findViewById(R.id.imageView2);
            bandeira[3] = v.findViewById(R.id.imageView3);
        }

    }


    @Override
    public GruposAdapter.GrupoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new GrupoViewHolder(view);
    }


    /** Preenche o layout criado para cada grupo */
    @Override
    public void onBindViewHolder(GrupoViewHolder holder, final int position) {
        //instancia esse grupo especifico
        Grupo grupo = grupos.get(position);

        //preenche a TextView referente a letra do grupo
        holder.letra.setText(grupos.get(position).getLetra());

        //preenche o icone das bandeiras
        for (int i = 0; i < holder.bandeira.length; i++) {
            Picasso.get()
                    .load(grupo.getSelecoes().get(i).getBandeiraURL())
                    .placeholder(android.R.drawable.star_on)
                    .error(android.R.drawable.sym_def_app_icon)
                    .into(holder.bandeira[i]);
        }
    }

    @Override
    public int getItemCount() {
        return grupos != null ? grupos.size() : 0;
    }

}