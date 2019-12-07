package es.saladillo.alejandrodiaz.projectdex.ui.detaildex;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import es.saladillo.alejandrodiaz.projectdex.base.Event;
import es.saladillo.alejandrodiaz.projectdex.base.Resource;
import es.saladillo.alejandrodiaz.projectdex.data.Repository;
import es.saladillo.alejandrodiaz.projectdex.data.remote.dto.evolution.EvolutionChain;
import es.saladillo.alejandrodiaz.projectdex.data.remote.dto.pokemon.PokemonResponse;
import es.saladillo.alejandrodiaz.projectdex.data.remote.dto.species.PokemonSpecies;

public class PokemonDetailFragmentViewModel extends ViewModel {

    private final MutableLiveData<String> queryPokemonTrigger = new MutableLiveData<>();
    private final LiveData<Resource<PokemonResponse>> queryPokemonsReply;
    private final MutableLiveData<Integer> querySpecieTrigger = new MutableLiveData<>();
    private final LiveData<Resource<PokemonSpecies>> querySpeciesReply;
    private final MutableLiveData<Integer> queryEvolutionTrigger = new MutableLiveData<>();
    private final LiveData<Resource<EvolutionChain>> queryEvolutionReply;
    private final MediatorLiveData<PokemonResponse> pokemon = new MediatorLiveData<>();
    private final MediatorLiveData<PokemonSpecies> species = new MediatorLiveData<>();
    private final MediatorLiveData<EvolutionChain> evolution = new MediatorLiveData<>();
    private final MediatorLiveData<Event<String>> message = new MediatorLiveData<>();
    private Repository repository;
    private final String TAG = "cancel";

    public PokemonDetailFragmentViewModel(Repository repository) {
        this.repository = repository;

        queryPokemonsReply = Transformations.switchMap(queryPokemonTrigger, name ->
                repository.getPokemon(name, TAG));
        querySpeciesReply = Transformations.switchMap(querySpecieTrigger, id ->
                repository.querySpecies(id));
        queryEvolutionReply = Transformations.switchMap(queryEvolutionTrigger, id ->
                repository.queryEvolutions(id));

        setupReplyQuery();
        setupMessage();
    }

    private void setupReplyQuery() {
        pokemon.addSource(queryPokemonsReply, resource -> {
            if(resource.hasSuccess()) {
                pokemon.setValue(resource.getData());
            }
        });
        species.addSource(querySpeciesReply, resource -> {
            if(resource.hasSuccess()) {
                species.setValue(resource.getData());
            }
        });
        evolution.addSource(queryEvolutionReply, resource -> {
            if(resource.hasSuccess()) {
                evolution.setValue(resource.getData());
            }
        });
    }

    private void setupMessage() {
        message.addSource(queryPokemonsReply, resource -> {
            if (resource.hasError()) {
                message.setValue(new Event<>(resource.getException().getMessage()));
            }
        });
        message.addSource(querySpeciesReply, resource -> {
            if (resource.hasError()) {
                message.setValue(new Event<>(resource.getException().getMessage()));
            }
        });
        message.addSource(queryEvolutionReply, resource -> {
            if (resource.hasError()) {
                message.setValue(new Event<>(resource.getException().getMessage()));
            }
        });
    }

    public void queryData() {

    }

    public void queryPokemon(String name) {
        queryPokemonTrigger.setValue(name);
    }

    public void querySpecie(int id) {
        querySpecieTrigger.setValue(id);
    }

    public LiveData<PokemonResponse> getPokemon() {
        return pokemon;
    }

    public LiveData<Event<String>> getMessage() {
        return message;
    }

}
