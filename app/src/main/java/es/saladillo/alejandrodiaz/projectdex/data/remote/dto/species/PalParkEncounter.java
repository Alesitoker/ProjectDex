
package es.saladillo.alejandrodiaz.projectdex.data.remote.dto.species;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PalParkEncounter {

    @SerializedName("area")
    @Expose
    private Area area;
    @SerializedName("base_score")
    @Expose
    private Integer baseScore;
    @SerializedName("rate")
    @Expose
    private Integer rate;

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Integer getBaseScore() {
        return baseScore;
    }

    public void setBaseScore(Integer baseScore) {
        this.baseScore = baseScore;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

}
