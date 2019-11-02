
package com.pokedex;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pokemon {

    @SerializedName("is_hidden")
    @Expose
    private Boolean isHidden;
    @SerializedName("slot")
    @Expose
    private Integer slot;
    @SerializedName("pokemon")
    @Expose
    private Pokemon_ pokemon;

    public Boolean getIsHidden() {
        return isHidden;
    }

    public void setIsHidden(Boolean isHidden) {
        this.isHidden = isHidden;
    }

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
