package es.saladillo.alejandrodiaz.projectdex.ui.teamCreator.listPokemon;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import java.util.Collections;
import java.util.List;

import es.saladillo.alejandrodiaz.projectdex.base.Event;
import es.saladillo.alejandrodiaz.projectdex.base.Resource;
import es.saladillo.alejandrodiaz.projectdex.data.Repository;
import es.saladillo.alejandrodiaz.projectdex.data.local.model.Pokemon;

public class SelectPokemonTeamFragmentViewModel extends ViewModel {

    private final MutableLiveData<Integer> queryPokemonsTrigger = new MutableLiveData<>();
    private final LiveData<Resource<List<Pokemon>>> queryPokemonsReply;
    private final MediatorLiveData<List<Pokemon>> pokemons = new MediatorLiveData<>();
    private final MediatorLiveData<Event<String>> message = new MediatorLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();
    private Repository repository;
    private final int SUMOFFSET = 20;
    private int offset = 0;

    public SelectPokemonTeamFragmentViewModel(Repository repository) {
        this.repository = repository;

        queryPokemonsReply = Transformations.switchMap(queryPokemonsTrigger,
                repository::queryPokemons);

        setupReplyQuery();
        setupMessage();
    }

    private void setupReplyQuery() {

        pokemons.addSource(queryPokemonsReply, resource -> {
            if (resource.hasSuccess()) {
                List<Pokemon> listPokemon = resource.getData();
                Collections.sort(listPokemon, (o1, o2) -> Integer.compare(o1.getId(), o2.getId()));
                pokemons.postValue(listPokemon);
                //Comprobar que se han obtenido todos los pokemons.
                if (resource.getData().size() == offset + SUMOFFSET)
                    offset += SUMOFFSET;
            }
        });
    }

    private void setupMessage() {
        message.addSource(queryPokemonsReply, resource -> {
            if (resource.hasError()) {
                message.setValue(new Event<>(resource.getException().getMessage()));
            }
        });
    }

    public void orderName() {
        if (pokemons.getValue() != null) {
            List<Pokemon> listPokemon = pokemons.getValue();
            Collections.sort(listPokemon, (o1, o2) -> o1.getName().compareTo(o2.getName()));
            pokemons.postValue(listPokemon);
        }
    }

    public void queryPokemons() {
            queryPokemonsTrigger.setValue(offset);
    }

    public LiveData<List<Pokemon>> getPokemons() {
        return pokemons;
    }

    public LiveData<Event<String>> getMessage() {
        return message;
    }

    public LiveData<Boolean> getLoading() {
        return loading;
    }
}
