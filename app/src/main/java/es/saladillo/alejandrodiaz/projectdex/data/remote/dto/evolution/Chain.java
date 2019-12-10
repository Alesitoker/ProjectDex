
package es.saladillo.alejandrodiaz.projectdex.data.remote.dto.evolution;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Chain {

    @SerializedName("evolution_details")
    @Expose
    private List<EvolutionDetail> evolutionDetails = null;
    @SerializedName("evolves_to")
    @Expose
    private List<EvolvesTo> evolvesTo = null;
    @SerializedName("is_baby")
    @Expose
    private Boolean isBaby;
    @SerializedName("species")
    @Expose
    private Species_ species;

    public List<EvolutionDetail> getEvolutionDetails() {
        return evolutionDetails;
    }

    public void setEvolutionDetails(List<EvolutionDetail> evolutionDetails) {
        this.evolutionDetails = evolutionDetails;
    }

    public List<EvolvesTo> getEvolvesTo() {
        return evolvesTo;
    }

    public void setEvolvesTo(List<EvolvesTo> evolvesTo) {
        this.evolvesTo = evolvesTo;
    }

    public Boolean getIsBaby() {
        return isBaby;
    }

    public void setIsBaby(Boolean isBaby) {
        this.isBaby = isBaby;
    }

    public Species_ getSpecies() {
        return species;
    }

    public void setSpecies(Species_ species) {
        this.species = species;
    }

}
