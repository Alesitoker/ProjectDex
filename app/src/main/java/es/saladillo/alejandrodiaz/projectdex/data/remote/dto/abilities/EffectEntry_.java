
package com.pokedex;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EffectEntry_ {

    @SerializedName("effect")
    @Expose
    private String effect;
    @SerializedName("language")
    @Expose
    private Language__ language;

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public Language__ getLanguage() {
        return language;
    }

    public void setLanguage(Language__ language) {
        this.language = language;
    }

}
