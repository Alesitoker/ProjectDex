
package com.pokedex;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EffectEntry {

    @SerializedName("effect")
    @Expose
    private String effect;
    @SerializedName("short_effect")
    @Expose
    private String shortEffect;
    @SerializedName("language")
    @Expose
    private Language_ language;

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public String getShortEffect() {
        return shortEffect;
    }

    public void setShortEffect(String shortEffect) {
        this.shortEffect = shortEffect;
    }

    public Language_ getLanguage() {
        return language;
    }

    public void setLanguage(Language_ language) {
        this.language = language;
    }

}
