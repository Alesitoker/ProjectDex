package es.saladillo.alejandrodiaz.projectdex.data.remote;

import es.saladillo.alejandrodiaz.projectdex.data.remote.dto.pokedex.PokeAll;
import es.saladillo.alejandrodiaz.projectdex.data.remote.dto.pokemon.PokemonResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PokeApi {

    @GET("Pokemon")
    Call<PokemonResponse> obtainPokemon(@Query("id") int id);
    @GET("Pokemon")
    Call<PokemonResponse> obtainPokemon(@Query("name") String name);
    @GET("Pokemon?limit=20")
    Call<PokeAll> queryPokemon(@Query("offset") int position);
}
