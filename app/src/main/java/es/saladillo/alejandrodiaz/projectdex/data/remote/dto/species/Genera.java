
package es.saladillo.alejandrodiaz.projectdex.data.remote.dto.species;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Genera {

    @SerializedName("genus")
    @Expose
    private String genus;
    @SerializedName("language")
    @Expose
    private Language_ language;

    public String getGenus() {
        return genus;
    }

    public void setGenus(String genus) {
        this.genus = genus;
    }

    public Language_ getLanguage() {
        return language;
    }

    public void setLanguage(Language_ language) {
        this.language = language;
    }

}
