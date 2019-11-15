package es.saladillo.alejandrodiaz.projectdex.data;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import es.saladillo.alejandrodiaz.projectdex.base.Resource;
import es.saladillo.alejandrodiaz.projectdex.data.local.model.Pokemon;
import es.saladillo.alejandrodiaz.projectdex.data.mapper.PokemonMapper;
import es.saladillo.alejandrodiaz.projectdex.data.remote.PokeApi;
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
    private final int LIMIT = 20;
    private MutableLiveData<Resource<PokemonResponse>> liveData = new MutableLiveData<>();
    private MutableLiveData<Resource<List<Pokemon>>> liveDataPokemons = new MutableLiveData<>();
    private Pokemon pokemon;

    private RepositoryImpl(PokeApiImpl pokeApi) {
        POKEAPI = pokeApi;
    }

    public static RepositoryImpl getInstance(PokeApiImpl pokeApi) {
        if (instance == null) {
            synchronized (RepositoryImpl.class) {
                if (instance == null) {
                    instance = new RepositoryImpl(pokeApi);
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
                    Log.d("agua", response.body().getName());
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
        Call<PokeAll> call = POKEAPI.getPOKEAPI().queryPokemon(LIMIT, offset);
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

        //TODO: Si dejos los tags cambiarlo por una variable
        for(Result result : results) {
            Log.d("agua", result.getName());
            obtainPokemon(result.getName());

//            if (pokemon.hasSuccess()) {
//                pokemons.add(pokemon.getData());
//            } else if (pokemon.hasError()) {
//                cancel("Asd");
//            }
        }

        return pokemons;
    }
    // TODO: borrar si no lo utilizas
    private void obtainPokemon(String name) {
        Call<PokemonResponse> call = POKEAPI.getPOKEAPI().obtainPokemon(name);
        call.enqueue(new Callback<PokemonResponse>() {
            @Override
            public void onResponse(@NonNull Call<PokemonResponse> call, @NonNull Response<PokemonResponse> response) {
                if (response.body() != null && response.isSuccessful()) {
                    Log.d("agua", "no se sabe");
                    pokemon = PokemonMapper.map(response.body());
                } else {
                    Log.d("agua", "no pi");
                    Resource.error(new Exception(response.message()));
                    cancel("ASD");
                }
            }

            @Override
            public void onFailure(Call<PokemonResponse> call, @NonNull Throwable t) {
                Log.d("agua", "erroror");
                Resource.error(new Exception(t.getMessage()));
                cancel("asd");
            }
        });
    }

    @Override
    public void cancel(String tag) {

    }
}
