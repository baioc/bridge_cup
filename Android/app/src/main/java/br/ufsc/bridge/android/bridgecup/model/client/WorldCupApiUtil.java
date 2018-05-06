package br.ufsc.bridge.android.bridgecup.model.client;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/** Classe para utilizar a API REST */
public class WorldCupApiUtil {

    // CONSTANTES
    private static final String BASE_API_URL = "https://bridge-worldcup.herokuapp.com/api/v1/";


    // CLASS VARIABLES
    private static WorldCupApi worldCupApiClient;


    // CLASS FUNCTIONS
    /** Cria e retorna um objeto que segue a interface WorldCupApi */
    public static WorldCupApi getClient() {
        if (worldCupApiClient == null) {
            worldCupApiClient = createClient();
        }
        return worldCupApiClient;
    }

    private static WorldCupApi createClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_API_URL)
                .build();

        return retrofit.create(WorldCupApi.class);
    }
}
