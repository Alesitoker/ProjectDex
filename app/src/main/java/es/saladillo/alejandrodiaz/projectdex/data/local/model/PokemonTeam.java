package es.saladillo.alejandrodiaz.projectdex.data.local.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import es.saladillo.alejandrodiaz.projectdex.data.remote.dto.pokemon.HeldItem;
import es.saladillo.alejandrodiaz.projectdex.data.remote.dto.pokemon.Type;

public class PokemonTeam implements Parcelable {

    private int id;
    private int teamPosition;
    private String name;
    private String nickName;
    private String category;
    private List<Type> types = null;
    private String imgUrl;
    private String[] moveSet = new String[4];
    private byte level = 1;
    private String nature;
    private HeldItem heldItem;

    public PokemonTeam(int id, int teamPosition,String name, List<Type> types, String imgUrl) {
        this.id = id;
        this.teamPosition = teamPosition;
        this.name = name;
        this.nickName = name;
        this.types = types;
        this.imgUrl = imgUrl;
    }

    public int getId() {
        return id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String[] getMoveSet() {
        return moveSet;
    }

    public void setMoveSet(String[] moveSet) {
        this.moveSet = moveSet;
    }

    public byte getLevel() {
        return level;
    }

    public void setLevel(byte level) {
        this.level = level;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public HeldItem getHeldItem() {
        return heldItem;
    }

    public void setHeldItem(HeldItem heldItem) {
        this.heldItem = heldItem;
    }

    public int getTeamPosition() {
        return teamPosition;
    }

    public void setTeamPosition(int teamPosition) {
        this.teamPosition = teamPosition;
    }

    public List<Type> getTypes() {
        return types;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PokemonTeam)) return false;
        PokemonTeam that = (PokemonTeam) o;
        return id == that.id &&
                teamPosition == that.teamPosition &&
                level == that.level &&
                Objects.equals(name, that.name) &&
                Objects.equals(nickName, that.nickName) &&
                Objects.equals(category, that.category) &&
                Objects.equals(types, that.types) &&
                Objects.equals(imgUrl, that.imgUrl) &&
                Arrays.equals(moveSet, that.moveSet) &&
                Objects.equals(nature, that.nature) &&
                Objects.equals(heldItem, that.heldItem);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, teamPosition, name, nickName, category, types, imgUrl, level, nature, heldItem);
        result = 31 * result + Arrays.hashCode(moveSet);
        return result;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.teamPosition);
        dest.writeString(this.name);
        dest.writeString(this.nickName);
        dest.writeString(this.category);
        dest.writeList(this.types);
        dest.writeString(this.imgUrl);
        dest.writeStringArray(this.moveSet);
        dest.writeByte(this.level);
        dest.writeString(this.nature);
        dest.writeParcelable(this.heldItem, flags);
    }

    protected PokemonTeam(Parcel in) {
        this.id = in.readInt();
        this.teamPosition = in.readInt();
        this.name = in.readString();
        this.nickName = in.readString();
        this.category = in.readString();
        this.types = new ArrayList<Type>();
        in.readList(this.types, Type.class.getClassLoader());
        this.imgUrl = in.readString();
        this.moveSet = in.createStringArray();
        this.level = in.readByte();
        this.nature = in.readString();
        this.heldItem = in.readParcelable(HeldItem.class.getClassLoader());
    }

    public static final Parcelable.Creator<PokemonTeam> CREATOR = new Parcelable.Creator<PokemonTeam>() {
        @Override
        public PokemonTeam createFromParcel(Parcel source) {
            return new PokemonTeam(source);
        }

        @Override
        public PokemonTeam[] newArray(int size) {
            return new PokemonTeam[size];
        }
    };
}
