package es.saladillo.alejandrodiaz.projectdex.ui.teamCreator.listTeam;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import es.saladillo.alejandrodiaz.projectdex.data.DataBaseRepository;

public class ListTeamsFragmentViewModelFactory implements ViewModelProvider.Factory {

    private DataBaseRepository repository;

    public ListTeamsFragmentViewModelFactory(DataBaseRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ListTeamsFragmentViewModel(repository);
    }
}
