package es.saladillo.alejandrodiaz.projectdex.data.remote;

import es.saladillo.alejandrodiaz.projectdex.data.remote.dto.pokedex.PokeAll;
import es.saladillo.alejandrodiaz.projectdex.data.remote.dto.pokemon.PokemonResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PokeApi {

    @GET("pokemon/{id}")
    Call<PokemonResponse> obtainPokemon(@Path("id") int id);
    @GET("pokemon/{name}")
    Call<PokemonResponse> obtainPokemon(@Path("name") String name);
    @GET("pokemon")
    Call<PokeAll> queryPokemon(@Query("limit") int limit, @Query("offset") int offset);
}
