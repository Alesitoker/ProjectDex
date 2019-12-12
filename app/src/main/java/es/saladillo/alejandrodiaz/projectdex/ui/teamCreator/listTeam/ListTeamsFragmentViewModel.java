package es.saladillo.alejandrodiaz.projectdex.ui.teamCreator.listTeam;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import es.saladillo.alejandrodiaz.projectdex.data.DataBaseRepository;
import es.saladillo.alejandrodiaz.projectdex.data.local.model.TeamDataB;

public class ListTeamsFragmentViewModel extends ViewModel {

    private LiveData<List<TeamDataB>> teams;
    private DataBaseRepository repository;

    public ListTeamsFragmentViewModel(DataBaseRepository repository) {
        this.repository = repository;
    }

    public LiveData<List<TeamDataB>> queryTeams() {
        return teams = repository.queryTeams();
    }
}
