
package es.saladillo.alejandrodiaz.projectdex.data.remote.dto.abilities;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EffectChange {

    @SerializedName("version_group")
    @Expose
    private VersionGroup versionGroup;
    @SerializedName("effect_entries")
    @Expose
    private List<EffectEntry_> effectEntries = null;

    public VersionGroup getVersionGroup() {
        return versionGroup;
    }

    public void setVersionGroup(VersionGroup versionGroup) {
        this.versionGroup = versionGroup;
    }

    public List<EffectEntry_> getEffectEntries() {
        return effectEntries;
    }

    public void setEffectEntries(List<EffectEntry_> effectEntries) {
        this.effectEntries = effectEntries;
    }

}
