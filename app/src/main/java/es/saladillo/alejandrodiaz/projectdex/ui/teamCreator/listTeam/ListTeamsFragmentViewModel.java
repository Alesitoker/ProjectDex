package es.saladillo.alejandrodiaz.projectdex.ui.teamCreator.listTeam;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import es.saladillo.alejandrodiaz.projectdex.data.local.model.Team;

public class ListTeamsFragmentViewModel extends ViewModel {

    private List<Team> teams = new ArrayList<>();
    private LiveData<List<Team>> temasLiveData;

    public boolean isTeamsEmpty() {
        return teams.isEmpty();
    }
}
