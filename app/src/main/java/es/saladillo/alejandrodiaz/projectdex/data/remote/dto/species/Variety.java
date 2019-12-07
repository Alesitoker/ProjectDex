
package es.saladillo.alejandrodiaz.projectdex.data.remote.dto.species;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Variety {

    @SerializedName("is_default")
    @Expose
    private Boolean isDefault;
    @SerializedName("pokemon")
    @Expose
    private Pokemon pokemon;

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

}
