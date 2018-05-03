package br.ufsc.bridge.android.bridgecup.model.client;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WorldCupApiUtil {

    private static final String BASE_API_URL = "https://bridge-worldcup.herokuapp.com/api/v1/";

    private static WorldCupApi worldCupApiClient;

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
