package es.saladillo.alejandrodiaz.projectdex.ui.teamCreator.listPokemon;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import es.saladillo.alejandrodiaz.projectdex.data.Repository;

public class SelectPokemonTeamFragmentViewModelFactory implements ViewModelProvider.Factory {

    private Repository repository;

    public SelectPokemonTeamFragmentViewModelFactory(Repository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new SelectPokemonTeamFragmentViewModel(repository);
    }
}
