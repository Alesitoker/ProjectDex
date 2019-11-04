package es.saladillo.alejandrodiaz.projectdex.data;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import es.saladillo.alejandrodiaz.projectdex.base.Resource;
import es.saladillo.alejandrodiaz.projectdex.data.local.model.Pokemon;
import es.saladillo.alejandrodiaz.projectdex.data.mapper.PokemonMapper;
import es.saladillo.alejandrodiaz.projectdex.data.remote.PokeApiImpl;
import es.saladillo.alejandrodiaz.projectdex.data.remote.dto.pokedex.PokeAll;
import es.saladillo.alejandrodiaz.projectdex.data.remote.dto.pokedex.Result;
import es.saladillo.alejandrodiaz.projectdex.data.remote.dto.pokemon.PokemonResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepositoryImpl implements Repository {

    private static RepositoryImpl instance;
    private final PokeApiImpl POKEAPI;
    private final PokemonMapper POKEMONMAPPER;
    private MutableLiveData<Resource<PokemonResponse>> liveData = new MutableLiveData<>();
    private MutableLiveData<Resource<List<Pokemon>>> liveDataPokemons = new MutableLiveData<>();
    private Resource<Pokemon> pokemon;

    private RepositoryImpl(PokeApiImpl pokeApi, PokemonMapper pokemonMapper) {
        POKEAPI = pokeApi;
        this.POKEMONMAPPER = pokemonMapper;
    }

    public static RepositoryImpl getInstance(PokeApiImpl pokeApi, PokemonMapper pokemonMapper) {
        if (instance == null) {
            synchronized (RepositoryImpl.class) {
                if (instance == null) {
                    instance = new RepositoryImpl(pokeApi, pokemonMapper);
                }
            }
        }
        return instance;
    }
    // TODO: quitar los tags si no los utilizo.
    @Override
    public LiveData<Resource<PokemonResponse>> getPokemon(int id, String tag) {
        Call<PokemonResponse> call = POKEAPI.getPOKEAPI().obtainPokemon(id);
        call.enqueue(new Callback<PokemonResponse>() {
            @Override
            public void onResponse(@NonNull Call<PokemonResponse> call, @NonNull Response<PokemonResponse> response) {
                if (response.body() != null && response.isSuccessful()) {
                    liveData.postValue(Resource.success(response.body()));
                } else {
                    liveData.postValue(Resource.error(new Exception(response.message())));
                }
            }

            @Override
            public void onFailure(Call<PokemonResponse> call, @NonNull Throwable t) {
                liveData.postValue(Resource.error(new Exception(t.getMessage())));
            }
        });
        return liveData;
    }

    @Override
    public LiveData<Resource<PokemonResponse>> getPokemon(String name, String tag) {
        Call<PokemonResponse> call = POKEAPI.getPOKEAPI().obtainPokemon(name);
        call.enqueue(new Callback<PokemonResponse>() {
            @Override
            public void onResponse(@NonNull Call<PokemonResponse> call, @NonNull Response<PokemonResponse> response) {
                if (response.body() != null && response.isSuccessful()) {
                    liveData.postValue(Resource.success(response.body()));
                } else {
                    liveData.postValue(Resource.error(new Exception(response.message())));
                }
            }

            @Override
            public void onFailure(Call<PokemonResponse> call, @NonNull Throwable t) {
                liveData.postValue(Resource.error(new Exception(t.getMessage())));
            }
        });
        return liveData;
    }

    @Override
    public LiveData<Resource<List<Pokemon>>> queryPokemons(int offset) {
        Call<PokeAll> call = POKEAPI.getPOKEAPI().queryPokemon(offset);
        call.enqueue(new Callback<PokeAll>() {
            @Override
            public void onResponse(@NonNull Call<PokeAll> call, @NonNull Response<PokeAll> response) {
                if (response.body() != null && response.isSuccessful()) {
                    liveDataPokemons.postValue(Resource.success(
                            convertResultPokemon(response.body().getResults())));
                } else {
                    liveDataPokemons.postValue(Resource.error(new Exception(response.message())));
                }
            }

            @Override
            public void onFailure(Call<PokeAll> call, @NonNull Throwable t) {
                liveDataPokemons.postValue(Resource.error(new Exception(t.getMessage())));
            }
        });
        return liveDataPokemons;
    }

    private List<Pokemon> convertResultPokemon(List<Result> results) {
        List<Pokemon> pokemons = new ArrayList<>();
        Resource<PokemonResponse> respond;
        //TODO: Si dejos los tags cambiarlo por una variable
        for(Result result : results) {
            respond = getPokemon(result.getName(), "sadas").getValue();
            if (respond.hasSuccess()) {
                pokemons.add(POKEMONMAPPER.map(respond.getData()));
            } else if (respond.hasError()) {
                cancel("Asd");
            }
        }

        return pokemons;
    }
    // TODO: borrar si no lo utilizas
//    private Resource<Pokemon> obtainPokemon(String name) {
//        Call<PokemonResponse> call = POKEAPI.getPOKEAPI().obtainPokemon(name);
//        call.enqueue(new Callback<PokemonResponse>() {
//            @Override
//            public void onResponse(@NonNull Call<PokemonResponse> call, @NonNull Response<PokemonResponse> response) {
//                if (response.body() != null && response.isSuccessful()) {
//                    pokemon = Resource.success(POKEMONMAPPER.map(response.body()));
//                } else {
//                    Resource.error(new Exception(response.message()));
//                }
//            }
//
//            @Override
//            public void onFailure(Call<PokemonResponse> call, @NonNull Throwable t) {
//                Resource.error(new Exception(t.getMessage()));
//            }
//        });
//        return pokemon;
//    }

    @Override
    public void cancel(String tag) {

    }
}
