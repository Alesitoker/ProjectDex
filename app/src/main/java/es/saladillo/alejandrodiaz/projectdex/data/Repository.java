package es.saladillo.alejandrodiaz.projectdex.data;

import androidx.lifecycle.LiveData;

import java.util.List;

import es.saladillo.alejandrodiaz.projectdex.base.Resource;
import es.saladillo.alejandrodiaz.projectdex.data.local.model.EvoChain;
import es.saladillo.alejandrodiaz.projectdex.data.local.model.Pokemon;
import es.saladillo.alejandrodiaz.projectdex.data.local.model.TeamDataB;
import es.saladillo.alejandrodiaz.projectdex.data.remote.dto.evolution.EvolutionChain;
import es.saladillo.alejandrodiaz.projectdex.data.remote.dto.pokemon.PokemonResponse;
import es.saladillo.alejandrodiaz.projectdex.data.remote.dto.species.PokemonSpecies;

public interface Repository {

    LiveData<Resource<PokemonResponse>> getPokemon(int id, String tag);
    LiveData<Resource<PokemonResponse>> getPokemon(String name, String tag);
    LiveData<Resource<List<Pokemon>>> queryPokemons(int offset);
    LiveData<Resource<PokemonSpecies>> querySpecies(int id);
    LiveData<Resource<List<EvoChain>>> queryEvolutions(int evoId);

    void cancel(String tag);
}
