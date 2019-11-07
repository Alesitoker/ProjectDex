
package es.saladillo.alejandrodiaz.projectdex.data.remote.dto.pokemon;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Type {

    @SerializedName("slot")
    @Expose
    private Integer slot;
    @SerializedName("type")
    @Expose
    private Type_ type;

    public Integer getSlot() {
        return slot;
    }

    public void setSlot(Integer slot) {
        this.slot = slot;
    }

    public Type_ getType() {
        return type;
    }

    public void setType(Type_ type) {
        this.type = type;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return obj instanceof Type && slot.equals(((Type) obj).slot) &&
                type.equals(((Type) obj).type);
    }
}
