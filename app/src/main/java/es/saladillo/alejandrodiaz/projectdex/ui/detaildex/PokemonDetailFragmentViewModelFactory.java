package es.saladillo.alejandrodiaz.projectdex.ui.detaildex;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import es.saladillo.alejandrodiaz.projectdex.data.Repository;

public class PokemonDetailFragmentViewModelFactory implements ViewModelProvider.Factory {

    private Repository repository;

    public PokemonDetailFragmentViewModelFactory(Repository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new PokemonDetailFragmentViewModel(repository);
    }
}
