package es.saladillo.alejandrodiaz.projectdex.ui.teamCreator;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import es.saladillo.alejandrodiaz.projectdex.data.DataBaseRepository;
import es.saladillo.alejandrodiaz.projectdex.ui.teamCreator.listTeam.ListTeamsFragmentViewModel;

public class TeamCreatorFragmentViewModelFactory implements ViewModelProvider.Factory {

    private DataBaseRepository repository;

    public TeamCreatorFragmentViewModelFactory(DataBaseRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new TeamCreatorFragmentViewModel(repository);
    }
}
