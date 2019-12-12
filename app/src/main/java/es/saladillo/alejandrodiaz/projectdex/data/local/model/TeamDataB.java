package es.saladillo.alejandrodiaz.projectdex.data.local.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "team")
public class TeamDataB implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;
    @ColumnInfo(name = "teamName")
    private String teamName;
    @Embedded(prefix = "poke1_")
    private PokemonTeam pokemon1;
    @Embedded(prefix = "poke2_")
    private PokemonTeam pokemon2;
    @Embedded(prefix = "poke3_")
    private PokemonTeam pokemon3;
    @Embedded(prefix = "poke4_")
    private PokemonTeam pokemon4;
    @Embedded(prefix = "poke5_")
    private PokemonTeam pokemon5;
    @Embedded(prefix = "poke6_")
    private PokemonTeam pokemon6;

    public TeamDataB(long id, String teamName) {
        this.id = id;
        this.teamName = teamName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public PokemonTeam getPokemon1() {
        return pokemon1;
    }

    public void setPokemon1(PokemonTeam pokemon1) {
        this.pokemon1 = pokemon1;
    }

    public PokemonTeam getPokemon2() {
        return pokemon2;
    }

    public void setPokemon2(PokemonTeam pokemon2) {
        this.pokemon2 = pokemon2;
    }

    public PokemonTeam getPokemon3() {
        return pokemon3;
    }

    public void setPokemon3(PokemonTeam pokemon3) {
        this.pokemon3 = pokemon3;
    }

    public PokemonTeam getPokemon4() {
        return pokemon4;
    }

    public void setPokemon4(PokemonTeam pokemon4) {
        this.pokemon4 = pokemon4;
    }

    public PokemonTeam getPokemon5() {
        return pokemon5;
    }

    public void setPokemon5(PokemonTeam pokemon5) {
        this.pokemon5 = pokemon5;
    }

    public PokemonTeam getPokemon6() {
        return pokemon6;
    }

    public void setPokemon6(PokemonTeam pokemon6) {
        this.pokemon6 = pokemon6;
    }

    @Ignore
    @Override
    public int describeContents() {
        return 0;
    }

    @Ignore
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.teamName);
        dest.writeParcelable(this.pokemon1, flags);
        dest.writeParcelable(this.pokemon2, flags);
        dest.writeParcelable(this.pokemon3, flags);
        dest.writeParcelable(this.pokemon4, flags);
        dest.writeParcelable(this.pokemon5, flags);
        dest.writeParcelable(this.pokemon6, flags);
    }

    @Ignore
    protected TeamDataB(Parcel in) {
        this.id = in.readLong();
        this.teamName = in.readString();
        this.pokemon1 = in.readParcelable(PokemonTeam.class.getClassLoader());
        this.pokemon2 = in.readParcelable(PokemonTeam.class.getClassLoader());
        this.pokemon3 = in.readParcelable(PokemonTeam.class.getClassLoader());
        this.pokemon4 = in.readParcelable(PokemonTeam.class.getClassLoader());
        this.pokemon5 = in.readParcelable(PokemonTeam.class.getClassLoader());
        this.pokemon6 = in.readParcelable(PokemonTeam.class.getClassLoader());
    }

    @Ignore
    public static final Creator<TeamDataB> CREATOR = new Creator<TeamDataB>() {
        @Override
        public TeamDataB createFromParcel(Parcel source) {
            return new TeamDataB(source);
        }

        @Override
        public TeamDataB[] newArray(int size) {
            return new TeamDataB[size];
        }
    };
}
