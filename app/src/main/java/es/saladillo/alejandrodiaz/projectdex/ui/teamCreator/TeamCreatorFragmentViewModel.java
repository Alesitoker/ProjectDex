package es.saladillo.alejandrodiaz.projectdex.ui.teamCreator;

import android.util.Log;

import androidx.lifecycle.ViewModel;


import es.saladillo.alejandrodiaz.projectdex.data.local.model.PokemonTeam;
import es.saladillo.alejandrodiaz.projectdex.data.local.model.Team;

public class TeamCreatorFragmentViewModel extends ViewModel {

    private Team team;
    private PokemonTeam[] pokemonsTeam = new PokemonTeam[6];
    private int deletePosition;
    private boolean openDeleteBtn = false;
    private long idTeam = 0;

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public PokemonTeam[] getPokemonsTeam() {
        return pokemonsTeam;
    }

    public void setPokemonsTeam(PokemonTeam[] pokemonsTeam) {
        this.pokemonsTeam = pokemonsTeam;
    }

    public void addPokemon(PokemonTeam pokemonTeam) {
        if (pokemonsTeam[pokemonTeam.getTeamPosition()] != null)
            Log.d("agua", pokemonsTeam[pokemonTeam.getTeamPosition()].getNickName());
        this.pokemonsTeam[pokemonTeam.getTeamPosition()] = pokemonTeam;
        Log.d("agua", pokemonsTeam[pokemonTeam.getTeamPosition()].getNickName());
    }

    public PokemonTeam getPokemon(int position) {

        return pokemonsTeam[position];
    }

    public void deletePokemon(int position) {
        this.pokemonsTeam[position] = null;
    }

    public long getIdTeam() {
        return idTeam++;
    }

    public int getDeletePosition() {
        return deletePosition;
    }

    public void setDeletePosition(int deletePosition) {
        this.deletePosition = deletePosition;
    }

    public boolean isOpenDeleteBtn() {
        return openDeleteBtn;
    }

    public void setOpenDeleteBtn(boolean openDeleteBtn) {
        this.openDeleteBtn = openDeleteBtn;
    }


}
