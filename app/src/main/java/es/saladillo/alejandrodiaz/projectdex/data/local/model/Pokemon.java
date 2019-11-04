package es.saladillo.alejandrodiaz.projectdex.data.local.model;

import java.util.List;

import es.saladillo.alejandrodiaz.projectdex.data.remote.dto.pokemon.Sprites;
import es.saladillo.alejandrodiaz.projectdex.data.remote.dto.pokemon.Type;

public class Pokemon {

    private Integer id;
    private String name;
    private Sprites sprites;
    private List<Type> types = null;

    public Pokemon(Integer id, String name, Sprites sprites, List<Type> types) {
        this.id = id;
        this.name = name;
        this.sprites = sprites;
        this.types = types;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Sprites getSprites() {
        return sprites;
    }

    public List<Type> getTypes() {
        return types;
    }
}
