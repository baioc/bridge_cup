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
import br.ufsc.bridge.android.bridgecup.model.entity.Grupo;
import br.ufsc.bridge.android.bridgecup.model.entity.Partida;
import br.ufsc.bridge.android.bridgecup.model.entity.Selecao;

/** Descreve e aplica a forma como os dados sobre as Partidas sao utilizados no layout */
public class PartidasAdapter extends RecyclerView.Adapter<PartidasAdapter.PartidaViewHolder> {

    private static List<Partida> partidas;
    private static List<Selecao> selecoes;
    private int rowLayout;
    private Context context;


    /** Construtor do PartidasAdapter
     *
     *  @param grupo Grupo que contem as listas de Partidas e Selecoes utilizadas para preencher o layout
     *  @param rowLayout Modelo de layout para referenciar a cada item da lista
     */
    public PartidasAdapter(Grupo grupo, int rowLayout, Context context) {
        this.partidas = grupo.getPartidas();
        this.selecoes = grupo.getSelecoes();
        this.rowLayout = rowLayout;
        this.context = context;
    }

    /** Cria as referencias as Views do layout a serem utilizadas */
    public static class PartidaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView sigla_casa;
        ImageView bandeira_casa;
        TextView sigla_fora;
        ImageView bandeira_fora;

        public PartidaViewHolder(View v) {
            super(v);
            itemView.setOnClickListener(this);
            sigla_casa = v.findViewById(R.id.tv_sigla_casa);
            bandeira_casa = v.findViewById(R.id.iv_bandeira_casa);
            sigla_fora = v.findViewById(R.id.tv_sigla_fora);
            bandeira_fora = v.findViewById(R.id.iv_bandeira_fora);
        }

        public void onClick(View v) {
            Toast.makeText(v.getContext(), partidas.get(getLayoutPosition()).getIdPartida().toString(), Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public PartidasAdapter.PartidaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new PartidaViewHolder(view);
    }


    /** Preenche o layout criado, "bind" dos dados */
    @Override
    public void onBindViewHolder(PartidaViewHolder holder, final int position) {
        Partida partida = partidas.get(position);
        Selecao casa = getSelecaoBySigla(selecoes, partida.getSelecaoCasa());
        Selecao fora = getSelecaoBySigla(selecoes, partida.getSelecaoFora());

        holder.sigla_casa.setText(partida.getSelecaoCasa());
        Picasso.get()
                .load(casa.getIconeURL())
                .placeholder(android.R.drawable.ic_menu_gallery)
                .error(android.R.drawable.stat_notify_error)
                .into(holder.bandeira_casa);

        holder.sigla_fora.setText(partida.getSelecaoFora());
        Picasso.get()
                .load(fora.getIconeURL())
                .placeholder(android.R.drawable.ic_menu_gallery)
                .error(android.R.drawable.stat_notify_error)
                .into(holder.bandeira_fora);
    }

    /** @returns Selecao com a sigla passada como parametro, se nao encontrar retorna null*/
    private Selecao getSelecaoBySigla(List<Selecao> selecoes, String sigla) {
        for (Selecao selecao : selecoes) {
            if (selecao.getSigla().equalsIgnoreCase(sigla)) {
                return selecao;
            }
        }

        return null;
    }

    @Override
    public int getItemCount() {
        return partidas != null ? partidas.size() : 0;
    }

}