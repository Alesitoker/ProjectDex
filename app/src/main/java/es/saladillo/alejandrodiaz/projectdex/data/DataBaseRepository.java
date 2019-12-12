package es.saladillo.alejandrodiaz.projectdex.data;

import androidx.lifecycle.LiveData;

import java.util.List;

import es.saladillo.alejandrodiaz.projectdex.base.Resource;
import es.saladillo.alejandrodiaz.projectdex.data.local.model.TeamDataB;

public interface DataBaseRepository {


    LiveData<List<TeamDataB>> queryTeams();
    LiveData<TeamDataB> queryTeam(long teamId);
    LiveData<Resource<Long>> insertTeam(TeamDataB team);
    LiveData<Resource<Integer>> updateTeam(TeamDataB team);
    LiveData<Resource<Integer>> deleteTeam(TeamDataB team);
}
