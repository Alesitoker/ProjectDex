package es.saladillo.alejandrodiaz.projectdex.data.mapper;


import es.saladillo.alejandrodiaz.projectdex.data.local.model.Pokemon;
import es.saladillo.alejandrodiaz.projectdex.data.remote.dto.pokemon.PokemonResponse;

public class PokemonMapper {

    public Pokemon map(PokemonResponse pokemon) {
        return new Pokemon(pokemon.getId(), pokemon.getName(), pokemon.getSprites(),
                pokemon.getTypes());
    }
}