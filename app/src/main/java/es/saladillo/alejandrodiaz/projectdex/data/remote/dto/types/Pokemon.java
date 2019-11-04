
package es.saladillo.alejandrodiaz.projectdex.data.remote.dto.types;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pokemon {

    @SerializedName("slot")
    @Expose
    private Integer slot;
    @SerializedName("pokemon")
    @Expose
    private Pokemon_ pokemon;

    public Integer getSlot() {
        return slot;
    }

    public void setSlot(Integer slot) {
        this.slot = slot;
    }

    public Pokemon_ getPokemon() {
        return pokemon;
    }

    public void setPokemon(Pokemon_ pokemon) {
        this.pokemon = pokemon;
    }

}
