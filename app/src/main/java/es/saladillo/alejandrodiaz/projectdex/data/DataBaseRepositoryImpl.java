package es.saladillo.alejandrodiaz.projectdex.data;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import es.saladillo.alejandrodiaz.projectdex.base.Resource;
import es.saladillo.alejandrodiaz.projectdex.data.local.TeamDao;
import es.saladillo.alejandrodiaz.projectdex.data.local.model.TeamDataB;

public class DataBaseRepositoryImpl implements DataBaseRepository {

    private static DataBaseRepositoryImpl instance;
    private final TeamDao teamDao;
    private MutableLiveData<Resource<Long>> insertResult = new MutableLiveData<>();
    private MutableLiveData<Resource<Integer>> result = new MutableLiveData<>();

    private DataBaseRepositoryImpl(TeamDao teamDao) {
        this.teamDao = teamDao;
    }

    public static DataBaseRepositoryImpl getInstance(TeamDao teamDao) {
        if (instance == null) {
            synchronized (DataBaseRepositoryImpl.class) {
                if (instance == null) {
                    instance = new DataBaseRepositoryImpl(teamDao);
                }
            }
        }
        return instance;
    }

    @Override
    public LiveData<List<TeamDataB>> queryTeams() {
        return teamDao.queryTeams();
    }

    @Override
    public LiveData<TeamDataB> queryTeam(long teamId) {
        return teamDao.queryTeam(teamId);
    }

    @Override
    public LiveData<Resource<Long>> insertTeam(TeamDataB team) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(() -> {
            try {
                long id = teamDao.insert(team);
                insertResult.postValue(Resource.success(id));
            } catch (Exception e) {
                insertResult.postValue(Resource.error(e));
            }
        });
        return insertResult;
    }

    @Override
    public LiveData<Resource<Integer>> updateTeam(TeamDataB team) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(() -> {
            try {
                int update = teamDao.update(team);
                result.postValue(Resource.success(update));
            } catch (Exception e) {
                result.postValue(Resource.error(e));
            }
        });
        return result;
    }

    @Override
    public LiveData<Resource<Integer>> deleteTeam(TeamDataB team) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(() -> {
            try {
                int delete = teamDao.delete(team);
                result.postValue(Resource.success(delete));
            } catch (Exception e) {
                result.postValue(Resource.error(e));
            }
        });
        return result;
    }
}
