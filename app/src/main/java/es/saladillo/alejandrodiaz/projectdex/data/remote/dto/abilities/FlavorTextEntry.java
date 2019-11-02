
package com.pokedex;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FlavorTextEntry {

    @SerializedName("flavor_text")
    @Expose
    private String flavorText;
    @SerializedName("language")
    @Expose
    private Language___ language;
    @SerializedName("version_group")
    @Expose
    private VersionGroup_ versionGroup;

    public String getFlavorText() {
        return flavorText;
    }

    public void setFlavorText(String flavorText) {
        this.flavorText = flavorText;
    }

    public Language___ getLanguage() {
        return language;
    }

    public void setLanguage(Language___ language) {
        this.language = language;
    }

    public VersionGroup_ getVersionGroup() {
        return versionGroup;
    }

    public void setVersionGroup(VersionGroup_ versionGroup) {
        this.versionGroup = versionGroup;
    }

}
