package es.saladillo.alejandrodiaz.projectdex.ui.teamCreator;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;


import es.saladillo.alejandrodiaz.projectdex.base.Event;
import es.saladillo.alejandrodiaz.projectdex.base.Resource;
import es.saladillo.alejandrodiaz.projectdex.data.DataBaseRepository;
import es.saladillo.alejandrodiaz.projectdex.data.local.model.PokemonTeam;
import es.saladillo.alejandrodiaz.projectdex.data.local.model.TeamDataB;

public class TeamCreatorFragmentViewModel extends ViewModel {

    private final MutableLiveData<TeamDataB> insertTrigger = new MutableLiveData<>();
    private final MutableLiveData<TeamDataB> updateTrigger = new MutableLiveData<>();
    private final MutableLiveData<TeamDataB> deleteTrigger = new MutableLiveData<>();
    private final MediatorLiveData<Event<String>> successMessage = new MediatorLiveData<>();
    private final MediatorLiveData<Event<String>> errorMessage = new MediatorLiveData<>();
    private final LiveData<Resource<Long>> insertResult;
    private final LiveData<Resource<Integer>> updateResult;
    private final LiveData<Resource<Integer>> deleteResult;
    private TeamDataB teamDataB;
    private PokemonTeam[] pokemonsTeam = new PokemonTeam[6];
    private int deletePosition;
    private boolean openDeleteBtn = false;
    private boolean first = true;
    private long idTeam = 0;
    private final DataBaseRepository repository;

    public TeamCreatorFragmentViewModel(DataBaseRepository repository) {
        this.repository = repository;

        insertResult = Transformations.switchMap(insertTrigger, repository::insertTeam);
        updateResult = Transformations.switchMap(updateTrigger, repository::updateTeam);
        deleteResult = Transformations.switchMap(deleteTrigger, repository::deleteTeam);

        setupSuccesMessage();
        setupErrorMessage();
    }

    public TeamDataB getTeamDataB() {
        return teamDataB;
    }

    public void setTeamDataB(TeamDataB teamDataB) {
        this.teamDataB = teamDataB;
    }

    public PokemonTeam[] getPokemonsTeam() {
        return pokemonsTeam;
    }

    public void obtainPokemonsTeam() {
        pokemonsTeam[0] = teamDataB.getPokemon1();
        pokemonsTeam[1] = teamDataB.getPokemon2();
        pokemonsTeam[2] = teamDataB.getPokemon3();
        pokemonsTeam[3] = teamDataB.getPokemon4();
        pokemonsTeam[4] = teamDataB.getPokemon5();
        pokemonsTeam[5] = teamDataB.getPokemon6();
    }

    public void addPokemon(PokemonTeam pokemonTeam) {
        if (pokemonsTeam[pokemonTeam.getTeamPosition()] != null)
            Log.d("agua", pokemonsTeam[pokemonTeam.getTeamPosition()].getNickName());
        this.pokemonsTeam[pokemonTeam.getTeamPosition()] = pokemonTeam;
        Log.d("agua", pokemonsTeam[pokemonTeam.getTeamPosition()].getNickName());
    }

    public PokemonTeam getPokemon(int position) {
        return pokemonsTeam[position];
    }

    public void deletePokemon(int position) {
        this.pokemonsTeam[position] = null;
    }

    public long getIdTeam() {
        return idTeam++;
    }

    public int getDeletePosition() {
        return deletePosition;
    }

    public void setDeletePosition(int deletePosition) {
        this.deletePosition = deletePosition;
    }

    public boolean isOpenDeleteBtn() {
        return openDeleteBtn;
    }

    public void setOpenDeleteBtn(boolean openDeleteBtn) {
        this.openDeleteBtn = openDeleteBtn;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    private void setupSuccesMessage() {
        successMessage.addSource(insertResult, resource -> {
            if (resource.hasSuccess() && resource.getData() > 0) {
                successMessage.setValue(new Event<>("The team has been inserted successfully"));
            }
        });
        successMessage.addSource(updateResult, resource -> {
            if (resource.hasSuccess() && resource.getData() > 0) {
                successMessage.setValue(new Event<>("The team has been updated successfully"));
            }
        });
        successMessage.addSource(deleteResult, resource -> {
            if (resource.hasSuccess() && resource.getData() > 0) {
                successMessage.setValue(new Event<>("The team has been successfully removed"));
            }
        });
    }

    private void setupErrorMessage() {
        errorMessage.addSource(insertResult, resource -> {
            if (resource.hasError()) {
                errorMessage.setValue(new Event<>(resource.getException().getMessage()));
            } else if (resource.hasSuccess() && resource.getData() <= 0) {
                errorMessage.setValue(new Event<>("Error inserting the team"));
            }
        });
        errorMessage.addSource(updateResult, resource -> {
            if (resource.hasError()) {
                errorMessage.setValue(new Event<>(resource.getException().getMessage()));
            } else if (resource.hasSuccess() && resource.getData() <= 0) {
                errorMessage.setValue(new Event<>("Error updating the team"));
            }
        });
        errorMessage.addSource(deleteResult, resource -> {
            if (resource.hasError()) {
                errorMessage.setValue(new Event<>(resource.getException().getMessage()));
            } else if (resource.hasSuccess() && resource.getData() <= 0) {
                errorMessage.setValue(new Event<>("Error deleting the team"));
            }
        });
    }

    public LiveData<Event<String>> getSuccessMessage() {
        return successMessage;
    }

    public LiveData<Event<String>> getErrorMessage() {
        return errorMessage;
    }

    public void insertTeam(TeamDataB team) {
        insertTrigger.setValue(team);
    }

    public void updateTeam(TeamDataB team) {
        updateTrigger.setValue(team);
    }

    public void deleteTeam(TeamDataB team) {
        deleteTrigger.setValue(team);
    }


}
