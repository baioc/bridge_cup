package br.ufsc.bridge.android.bridgecup.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
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
import br.ufsc.bridge.android.bridgecup.view.activity.GrupoActivity;

/** Descreve e aplica a forma como os dados sobre os Grupos sao utilizados no layout */
public class GruposAdapter extends RecyclerView.Adapter<GruposAdapter.GrupoViewHolder> {

    private static List<Grupo> grupos;
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
    public static class GrupoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView card;
        TextView letra;
        ImageView[] bandeira = new ImageView[4];

        public GrupoViewHolder(View v) {
            super(v);
            itemView.setOnClickListener(this);
            card = v.findViewById(R.id.cv_grupo);
            letra = v.findViewById(R.id.tv_letra);
            bandeira[0] = v.findViewById(R.id.imageView0);
            bandeira[1] = v.findViewById(R.id.imageView1);
            bandeira[2] = v.findViewById(R.id.imageView2);
            bandeira[3] = v.findViewById(R.id.imageView3);
        }

        @Override
        //@todo mudar a cor do card ao clickar nela
        public void onClick(View v) {
            //vai para a tela do Grupo escolhido, enviando seu id
            String idGrupo = grupos.get(getLayoutPosition()).getIdGrupo();
            Intent intent = new Intent(v.getContext(), GrupoActivity.class);
            intent.putExtra("GRUPO", idGrupo);
            v.getContext().startActivity(intent);//@fixme o app trava e fecha se o celular estiver virado na horizontal
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
        for (int sel = 0; sel < holder.bandeira.length; sel++) {
            Picasso.get()
                    .load(grupo.getSelecoes().get(sel).getBandeiraURL())
                    .placeholder(android.R.drawable.ic_menu_gallery)
                    .error(android.R.drawable.stat_notify_error)
                    .into(holder.bandeira[sel]);
        }
    }

    @Override
    public int getItemCount() {
        return grupos != null ? grupos.size() : 0;
    }

}