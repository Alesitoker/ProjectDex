package es.saladillo.alejandrodiaz.projectdex.ui.teamCreator.listTeam;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import es.saladillo.alejandrodiaz.projectdex.data.local.model.Team;

public class ListTeamsFragmentViewModel extends ViewModel {

    List<Team> teams = new ArrayList<>();
    private String searchQuery;

    public String getSearchQuery() {
        return searchQuery;
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }
}
