package es.saladillo.alejandrodiaz.projectdex.data.local.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Team implements Parcelable {

    long id;
    String teamName;
    List<PokemonTeam> pokemons;

    public Team(long id, String teamName, List<PokemonTeam> pokemons) {
        this.id = id;
        this.teamName = teamName;
        this.pokemons = pokemons;
    }

    public Team() {
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

    public List<PokemonTeam> getPokemons() {
        return pokemons;
    }

    public void setPokemons(List<PokemonTeam> pokemons) {
        this.pokemons = pokemons;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.teamName);
        dest.writeList(this.pokemons);
    }

    protected Team(Parcel in) {
        this.id = in.readLong();
        this.teamName = in.readString();
        this.pokemons = new ArrayList<PokemonTeam>();
        in.readList(this.pokemons, PokemonTeam.class.getClassLoader());
    }

    public static final Creator<Team> CREATOR = new Creator<Team>() {
        @Override
        public Team createFromParcel(Parcel source) {
            return new Team(source);
        }

        @Override
        public Team[] newArray(int size) {
            return new Team[size];
        }
    };
}
