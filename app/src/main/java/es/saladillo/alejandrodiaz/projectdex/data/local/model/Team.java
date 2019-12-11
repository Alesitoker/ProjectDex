package es.saladillo.alejandrodiaz.projectdex.data.local.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Team implements Parcelable {

    private long id;
    private String teamName;
    private PokemonTeam[] pokemons = new PokemonTeam[6];

    public Team(long id, String teamName, PokemonTeam[] pokemons) {
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

    public PokemonTeam[] getPokemons() {
        return pokemons;
    }

    public void setPokemons(PokemonTeam[] pokemons) {
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
        dest.writeTypedArray(this.pokemons, flags);
    }

    protected Team(Parcel in) {
        this.id = in.readLong();
        this.teamName = in.readString();
        this.pokemons = in.createTypedArray(PokemonTeam.CREATOR);
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
