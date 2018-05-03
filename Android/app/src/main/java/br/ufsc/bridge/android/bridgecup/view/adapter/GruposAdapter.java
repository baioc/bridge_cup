package br.ufsc.bridge.android.bridgecup.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import br.ufsc.bridge.android.bridgecup.R;
import br.ufsc.bridge.android.bridgecup.databinding.ItemGrupoBinding;
import br.ufsc.bridge.android.bridgecup.model.entity.Grupo;

public class GruposAdapter extends RecyclerView.Adapter<BindingViewHolder<ItemGrupoBinding>> {

    private List<Grupo> mGrupos;

    public void setGrupos(List<Grupo> grupos) {
        mGrupos = new ArrayList<>(grupos);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BindingViewHolder<ItemGrupoBinding> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        ItemGrupoBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.item_grupo, parent, false);

        return new BindingViewHolder<>(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BindingViewHolder<ItemGrupoBinding> holder, int position) {
        Grupo grupo = mGrupos.get(position);
        ItemGrupoBinding binding = holder.getBinding();

        binding.setGrupo(grupo);

        Picasso.get()
                .load(grupo.getSelecoes().get(0).getIconeURL())
                .into(binding.icSelecao0);
    }

    @Override
    public int getItemCount() {
        return mGrupos != null ? mGrupos.size() : 0;
    }
}
