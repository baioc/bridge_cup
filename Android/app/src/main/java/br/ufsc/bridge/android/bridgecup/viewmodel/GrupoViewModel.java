package br.ufsc.bridge.android.bridgecup.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import br.ufsc.bridge.android.bridgecup.model.client.WorldCupApiException;
import br.ufsc.bridge.android.bridgecup.model.client.WorldCupApiUtil;
import br.ufsc.bridge.android.bridgecup.model.entity.Grupo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GrupoViewModel extends ViewModel {
    private MutableLiveData<List<Grupo>> grupos;
    private MutableLiveData<Throwable> error;

    /** Getter da lista dinamica de Grupos */
    public LiveData<List<Grupo>> getGrupos(int ano) {
        if (grupos == null) {
            grupos = new MutableLiveData<>();
            loadGrupos(ano);
        }
        return grupos;
    }

    public LiveData<Throwable> getError() {
        if (error == null) {
            error = new MutableLiveData<>();
        }
        return error;
    }

    /** Preenche a lista 'grupos' desse ViewModel com aqueles retornados pela API para um ano dado */
    private void loadGrupos(int ano) {
        WorldCupApiUtil.getClient().getGrupos(ano).enqueue(new Callback<List<Grupo>>() {
            @Override
            public void onResponse(@NonNull Call<List<Grupo>> call, @NonNull Response<List<Grupo>> response) {
                if (response.isSuccessful()) {
                    grupos.postValue(response.body());
                    error.postValue(null);
                } else {
                    grupos.postValue(new ArrayList<>());
                    error.postValue(new WorldCupApiException());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Grupo>> call, @NonNull Throwable t) {
                grupos.postValue(new ArrayList<>());
                error.postValue(t);
            }
        });
    }
}
