package es.saladillo.alejandrodiaz.projectdex.data.local.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.TypeConverter;

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
    private List<Type> types;
    private String imgUrl;
    private byte level = 1;
    private String nature;

    public PokemonTeam(int id, int teamPosition, String name, List<Type> types, String imgUrl) {
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

    public int getTeamPosition() {
        return teamPosition;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
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
        dest.writeByte(this.level);
        dest.writeString(this.nature);
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
        this.level = in.readByte();
        this.nature = in.readString();
    }

    public static final Creator<PokemonTeam> CREATOR = new Creator<PokemonTeam>() {
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
