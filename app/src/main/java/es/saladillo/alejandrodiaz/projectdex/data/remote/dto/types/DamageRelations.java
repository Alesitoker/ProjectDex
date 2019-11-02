
package com.types;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DamageRelations {

    @SerializedName("no_damage_to")
    @Expose
    private List<NoDamageTo> noDamageTo = null;
    @SerializedName("half_damage_to")
    @Expose
    private List<HalfDamageTo> halfDamageTo = null;
    @SerializedName("double_damage_to")
    @Expose
    private List<DoubleDamageTo> doubleDamageTo = null;
    @SerializedName("no_damage_from")
    @Expose
    private List<NoDamageFrom> noDamageFrom = null;
    @SerializedName("half_damage_from")
    @Expose
    private List<HalfDamageFrom> halfDamageFrom = null;
    @SerializedName("double_damage_from")
    @Expose
    private List<DoubleDamageFrom> doubleDamageFrom = null;

    public List<NoDamageTo> getNoDamageTo() {
        return noDamageTo;
    }

    public void setNoDamageTo(List<NoDamageTo> noDamageTo) {
        this.noDamageTo = noDamageTo;
    }

    public List<HalfDamageTo> getHalfDamageTo() {
        return halfDamageTo;
    }

    public void setHalfDamageTo(List<HalfDamageTo> halfDamageTo) {
        this.halfDamageTo = halfDamageTo;
    }

    public List<DoubleDamageTo> getDoubleDamageTo() {
        return doubleDamageTo;
    }

    public void setDoubleDamageTo(List<DoubleDamageTo> doubleDamageTo) {
        this.doubleDamageTo = doubleDamageTo;
    }

    public List<NoDamageFrom> getNoDamageFrom() {
        return noDamageFrom;
    }

    public void setNoDamageFrom(List<NoDamageFrom> noDamageFrom) {
        this.noDamageFrom = noDamageFrom;
    }

    public List<HalfDamageFrom> getHalfDamageFrom() {
        return halfDamageFrom;
    }

    public void setHalfDamageFrom(List<HalfDamageFrom> halfDamageFrom) {
        this.halfDamageFrom = halfDamageFrom;
    }

    public List<DoubleDamageFrom> getDoubleDamageFrom() {
        return doubleDamageFrom;
    }

    public void setDoubleDamageFrom(List<DoubleDamageFrom> doubleDamageFrom) {
        this.doubleDamageFrom = doubleDamageFrom;
    }

}
