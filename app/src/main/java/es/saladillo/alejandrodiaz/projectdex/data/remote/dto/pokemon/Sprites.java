
package es.saladillo.alejandrodiaz.projectdex.data.remote.dto.pokemon;

import android.text.TextUtils;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sprites {

    @SerializedName("back_default")
    @Expose
    private String backDefault;
    @SerializedName("back_female")
    @Expose
    private String backFemale;
    @SerializedName("back_shiny")
    @Expose
    private String backShiny;
    @SerializedName("back_shiny_female")
    @Expose
    private String backShinyFemale;
    @SerializedName("front_default")
    @Expose
    private String frontDefault;
    @SerializedName("front_female")
    @Expose
    private String frontFemale;
    @SerializedName("front_shiny")
    @Expose
    private String frontShiny;
    @SerializedName("front_shiny_female")
    @Expose
    private String frontShinyFemale;

    public String getBackDefault() {
        return backDefault;
    }

    public void setBackDefault(String backDefault) {
        this.backDefault = backDefault;
    }

    public String getBackFemale() {
        return backFemale;
    }

    public void setBackFemale(String backFemale) {
        this.backFemale = backFemale;
    }

    public String getBackShiny() {
        return backShiny;
    }

    public void setBackShiny(String backShiny) {
        this.backShiny = backShiny;
    }

    public String getBackShinyFemale() {
        return backShinyFemale;
    }

    public void setBackShinyFemale(String backShinyFemale) {
        this.backShinyFemale = backShinyFemale;
    }

    public String getFrontDefault() {
        return frontDefault;
    }

    public void setFrontDefault(String frontDefault) {
        this.frontDefault = frontDefault;
    }

    public String getFrontFemale() {
        return frontFemale;
    }

    public void setFrontFemale(String frontFemale) {
        this.frontFemale = frontFemale;
    }

    public String getFrontShiny() {
        return frontShiny;
    }

    public void setFrontShiny(String frontShiny) {
        this.frontShiny = frontShiny;
    }

    public String getFrontShinyFemale() {
        return frontShinyFemale;
    }

    public void setFrontShinyFemale(String frontShinyFemale) {
        this.frontShinyFemale = frontShinyFemale;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return obj instanceof Sprites && TextUtils.equals(backDefault, ((Sprites) obj).backDefault) &&
                TextUtils.equals(backFemale, ((Sprites) obj).backFemale) &&
                TextUtils.equals(backShiny, ((Sprites) obj).backShiny) &&
                TextUtils.equals(backShinyFemale, ((Sprites) obj).backShinyFemale) &&
                TextUtils.equals(frontDefault, ((Sprites) obj).frontDefault) &&
                TextUtils.equals(frontFemale, ((Sprites) obj).frontFemale) &&
                TextUtils.equals(frontShiny, ((Sprites) obj).frontShiny) &&
                TextUtils.equals(frontShinyFemale, ((Sprites) obj).frontShinyFemale);
    }
}
