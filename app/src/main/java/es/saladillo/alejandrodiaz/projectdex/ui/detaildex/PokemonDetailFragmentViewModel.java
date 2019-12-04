package es.saladillo.alejandrodiaz.projectdex.ui.detaildex;

import android.graphics.Color;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import es.saladillo.alejandrodiaz.projectdex.R;
import es.saladillo.alejandrodiaz.projectdex.base.Event;
import es.saladillo.alejandrodiaz.projectdex.base.Resource;
import es.saladillo.alejandrodiaz.projectdex.data.Repository;
import es.saladillo.alejandrodiaz.projectdex.data.remote.dto.pokemon.PokemonResponse;
import es.saladillo.alejandrodiaz.projectdex.data.remote.dto.pokemon.Type;

public class PokemonDetailFragmentViewModel extends ViewModel {

    private final MutableLiveData<String> queryPokemonTrigger = new MutableLiveData<>();
    private final LiveData<Resource<PokemonResponse>> queryPokemonsReply;
    private final MediatorLiveData<PokemonResponse> pokemon = new MediatorLiveData<>();
    private final MediatorLiveData<Event<String>> message = new MediatorLiveData<>();
    private Repository repository;
    private final String TAG = "cancel";

    public PokemonDetailFragmentViewModel(Repository repository) {
        this.repository = repository;

        queryPokemonsReply = Transformations.switchMap(queryPokemonTrigger, name ->
                repository.getPokemon(name, TAG));
        setupReplyQuery();
        setupMessage();
    }

    private void setupReplyQuery() {
        pokemon.addSource(queryPokemonsReply, resource -> {
            if(resource.hasSuccess()) {
                pokemon.setValue(resource.getData());
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

    public void queryPokemon(String name) {
        queryPokemonTrigger.setValue(name);
    }

    public LiveData<PokemonResponse> getPokemon() {
        return pokemon;
    }

    public LiveData<Event<String>> getMessage() {
        return message;
    }

    public int obtainColor(Type type) {
        int color = Color.BLACK;
        switch (type.getType().getName()) {
            case "normal":
                color = R.color.normal;
                break;
            case "fighting":
                color = R.color.fighting;
                break;
            case "flying":
                color = R.color.fliying;
                break;
            case "poison":
                color = R.color.poison;
                break;
            case "ground":
                color = R.color.ground;
                break;
            case "rock":
                color = R.color.rock;
                break;
            case "bug":
                color = R.color.bug;
                break;
            case "ghost":
                color = R.color.ghost;
                break;
            case "steel":
                color = R.color.steel;
                break;
            case "fire":color = R.color.fire;
                break;
            case "water":
                color = R.color.water;
                break;
            case "grass":
                color = R.color.grass;
                break;
            case "electric":
                color = R.color.electric;
                break;
            case "psychic":
                color = R.color.psychic;
                break;
            case "ice":
                color = R.color.ice;
                break;
            case "dragon":
                color = R.color.dragon;
                break;
            case "dark":
                color = R.color.dark;
                break;
            case "fairy":
                color = R.color.fairy;
                break;
        }
        return color;
    }
}
