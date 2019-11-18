package es.saladillo.alejandrodiaz.projectdex.ui.listdex;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import java.util.List;

import es.saladillo.alejandrodiaz.projectdex.base.Event;
import es.saladillo.alejandrodiaz.projectdex.base.Resource;
import es.saladillo.alejandrodiaz.projectdex.data.Repository;
import es.saladillo.alejandrodiaz.projectdex.data.local.model.Pokemon;

public class ListPokemonFragmentViewModel extends ViewModel {

    private final MutableLiveData<Integer> queryPokemonsTrigger = new MutableLiveData<>();
    private final LiveData<Resource<List<Pokemon>>> queryPokemonsReply;
    private final MediatorLiveData<List<Pokemon>> pokemons = new MediatorLiveData<>();
    private final MediatorLiveData<Event<String>> message = new MediatorLiveData<>();
    private Repository repository;
    private int offset = 0;


    public ListPokemonFragmentViewModel(Repository repository) {
        this.repository = repository;

        queryPokemonsReply = Transformations.switchMap(queryPokemonsTrigger,
                repository::queryPokemons);

        setupReplyQuery();
        setupMessage();
    }

    private void setupReplyQuery() {
        pokemons.addSource(queryPokemonsReply, resource -> {
            if (resource.hasSuccess()) {
                pokemons.postValue(resource.getData());
                offset += 20;
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

    public void queryPokemons() {
            queryPokemonsTrigger.setValue(offset);
    }

    public LiveData<List<Pokemon>> getPokemons() {
        return pokemons;
    }

    public LiveData<Event<String>> getMessage() {
        return message;
    }
}
