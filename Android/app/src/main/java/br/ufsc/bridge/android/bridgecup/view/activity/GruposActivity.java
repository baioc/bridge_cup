package br.ufsc.bridge.android.bridgecup.view.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import br.ufsc.bridge.android.bridgecup.R;
import br.ufsc.bridge.android.bridgecup.databinding.ActivityGruposBinding;
import br.ufsc.bridge.android.bridgecup.view.adapter.GruposAdapter;
import br.ufsc.bridge.android.bridgecup.viewmodel.GrupoViewModel;

public class GruposActivity extends BindingActivity<ActivityGruposBinding> {

    private static final int ANO = 2018;

    private final GruposAdapter mAdapter = new GruposAdapter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RecyclerView gruposRecyclerView = getBinding().rvGrupos;

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        gruposRecyclerView.setLayoutManager(layoutManager);

        gruposRecyclerView.setAdapter(mAdapter);

        GrupoViewModel grupoViewModel = ViewModelProviders.of(this).get(GrupoViewModel.class);

        grupoViewModel.getGrupos(ANO)
                .observe(this, (grupos) -> {
                    if (grupos == null) return;

                    mAdapter.setGrupos(grupos);
                });

        grupoViewModel.getError()
                .observe(this, (throwable) -> {
                    if (throwable == null) return;

                    Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_LONG).show();
                });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_grupos;
    }
}
