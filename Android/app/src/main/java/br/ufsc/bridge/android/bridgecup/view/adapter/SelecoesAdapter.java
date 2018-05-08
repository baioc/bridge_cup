package br.ufsc.bridge.android.bridgecup.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.ufsc.bridge.android.bridgecup.R;
import br.ufsc.bridge.android.bridgecup.model.entity.Selecao;

/** Descreve e aplica a forma como os dados sobre as Selecoes sao utilizados no layout */
public class SelecoesAdapter extends RecyclerView.Adapter<SelecoesAdapter.SelecaoViewHolder> {

    private static List<Selecao> selecoes;
    private int rowLayout;
    private Context context;


    /** Construtor do SelecoesAdapter
     *
     *  @param selecoes Lista de selecoes utilizados para preencher o layout
     *  @param rowLayout Modelo de layout para referenciar a cada item da lista
     */
    public SelecoesAdapter(List<Selecao> selecoes, int rowLayout, Context context) {
        this.selecoes = selecoes;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    /** Cria as referencias as Views do layout a serem utilizadas */
    public static class SelecaoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView bandeira;
        TextView nome;
        TextView sigla;

        public SelecaoViewHolder(View v) {
            super(v);
            itemView.setOnClickListener(this);
            nome = v.findViewById(R.id.tv_nome);
            sigla = v.findViewById(R.id.tv_sigla);
            bandeira = v.findViewById(R.id.iv_bandeira_casa);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(), selecoes.get(getLayoutPosition()).getNome(), Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public SelecoesAdapter.SelecaoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new SelecaoViewHolder(view);
    }

    /** Preenche o layout criado, "bind" dos dados */
    @Override
    public void onBindViewHolder(SelecaoViewHolder holder, final int position) {
        Selecao selecao = selecoes.get(position);

        holder.nome.setText(selecao.getNome());
        holder.sigla.setText(selecao.getSigla());
        Picasso.get()
                .load(selecoes.get(position).getIconeURL())
                .placeholder(android.R.drawable.ic_menu_gallery)
                .error(android.R.drawable.stat_notify_error)
                .into(holder.bandeira);
    }

    @Override
    public int getItemCount() {
        return selecoes != null ? selecoes.size() : 0;
    }

}