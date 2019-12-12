package es.saladillo.alejandrodiaz.projectdex.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import es.saladillo.alejandrodiaz.projectdex.base.BaseDao;
import es.saladillo.alejandrodiaz.projectdex.data.local.model.TeamDataB;

@Dao
public interface TeamDao extends BaseDao<TeamDataB> {

    @Query("SELECT * FROM team")
    LiveData<List<TeamDataB>> queryTeams();

    @Query("SELECT * FROM team WHERE id = :teamId")
    LiveData<TeamDataB> queryTeam(long teamId);
}
