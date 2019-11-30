package es.saladillo.alejandrodiaz.projectdex.data;

import androidx.lifecycle.LiveData;

import java.util.List;

import es.saladillo.alejandrodiaz.projectdex.base.Resource;
import es.saladillo.alejandrodiaz.projectdex.data.local.model.Pokemon;
import es.saladillo.alejandrodiaz.projectdex.data.remote.dto.pokemon.PokemonResponse;

public interface Repository {

    LiveData<Resource<PokemonResponse>> getPokemon(int id, String tag);
    LiveData<Resource<PokemonResponse>> getPokemon(String name, String tag);
    LiveData<Resource<List<Pokemon>>> queryPokemons(int offset);

    void cancel(String tag);
}
