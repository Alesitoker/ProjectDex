
package es.saladillo.alejandrodiaz.projectdex.data.remote.dto.species;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EvolutionChain {

    @SerializedName("url")
    @Expose
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
