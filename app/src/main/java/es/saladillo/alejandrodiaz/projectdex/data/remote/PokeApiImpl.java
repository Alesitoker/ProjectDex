package es.saladillo.alejandrodiaz.projectdex.data.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokeApiImpl {

    private static PokeApiImpl instance;
    private final PokeApi POKEAPI;

    private PokeApiImpl(PokeApi pokeApi) {
        POKEAPI = pokeApi;
    }

    public static PokeApiImpl getInstance() {
        if (instance == null) {
            synchronized (PokeApiImpl.class) {
                if (instance == null) {
                    instance = new PokeApiImpl(buildInstance());
                }
            }
        }
        return instance;
    }

    private static PokeApi buildInstance() {
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl("https://pokeapi.co/api/v2/").
                addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit.create(PokeApi.class);
    }

    public PokeApi getPOKEAPI() {
        return POKEAPI;
    }
}
