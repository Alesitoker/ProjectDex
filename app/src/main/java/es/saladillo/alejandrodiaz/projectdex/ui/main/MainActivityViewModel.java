package es.saladillo.alejandrodiaz.projectdex.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import es.saladillo.alejandrodiaz.projectdex.base.Event;
import es.saladillo.alejandrodiaz.projectdex.data.local.model.PokemonTeam;
import es.saladillo.alejandrodiaz.projectdex.data.local.model.Team;

public class MainActivityViewModel extends ViewModel {

    private final MutableLiveData<Event<PokemonTeam>> transferedPokemon = new MutableLiveData<>();
    private final MutableLiveData<Event<Team>> transferedTeam = new MutableLiveData<>();

    public void setTransferedPokemon(PokemonTeam pokemonTeam) {
        transferedPokemon.setValue(new Event<>(pokemonTeam));
    }

    public LiveData<Event<PokemonTeam>> getTransferedPokemon() {
        return transferedPokemon;
    }

    public void setTransferedTeam(Team team) {
        transferedTeam.setValue(new Event<>(team));
    }

    public LiveData<Event<Team>> getTransferedTeam() {
        return transferedTeam;
    }
}
