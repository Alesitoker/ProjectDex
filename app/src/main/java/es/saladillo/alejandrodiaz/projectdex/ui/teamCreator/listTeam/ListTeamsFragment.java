package es.saladillo.alejandrodiaz.projectdex.ui.teamCreator.listTeam;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;


import es.saladillo.alejandrodiaz.projectdex.R;
import es.saladillo.alejandrodiaz.projectdex.data.local.model.Team;
import es.saladillo.alejandrodiaz.projectdex.databinding.FragmentTeamsListBinding;
import es.saladillo.alejandrodiaz.projectdex.ui.main.MainActivityViewModel;
import es.saladillo.alejandrodiaz.projectdex.ui.main.ToolbarConfigurationInterface;

public class ListTeamsFragment extends Fragment {

    private FragmentTeamsListBinding b;
    private ToolbarConfigurationInterface toolbarConfiguration;
    private ListTeamsFragmentViewModel viewModel;
    private MainActivityViewModel activityViewModel;
    private NavController navController;
    private ListTeamsFragmentAdapter listAdapter;
    private MenuItem mnuSearch;
    private SearchView searchView;
    private boolean noTeams = false;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            toolbarConfiguration = (ToolbarConfigurationInterface) context;
        } catch (ClassCastException e) {
            throw new ClassCastException("Listener must implement ToolbarConfigurationInterface");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_teams_list, menu);

        mnuSearch = menu.findItem(R.id.mnuSearch);
        searchView = (SearchView) mnuSearch.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint(getString(R.string.fragment_list_pokemon_mnuSearch_hint));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter adapter when text is changed
                listAdapter.getFilter().filter(query);
                return false;
            }
        });
        if (viewModel.isTeamsEmpty()) {
            menu.getItem(0).setVisible(false);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        b = FragmentTeamsListBinding.inflate(inflater, container, false);
        return b.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(ListTeamsFragmentViewModel.class);
        activityViewModel = ViewModelProviders.of(requireActivity()).get(MainActivityViewModel.class);
        navController = NavHostFragment.findNavController(this);
        setupToolbar();
        setupsViews();
        observe();
    }

    private void observe() {

    }

    private void setupToolbar() {
        Toolbar toolbar = b.toolbar;
        toolbarConfiguration.configureToolbar(toolbar);
    }

    private void setupsViews() {
        setupRecyclerView();

        b.fabAdd.setOnClickListener(v -> navigateToTeamCreator(null));
    }

    private void setupRecyclerView() {
        listAdapter = new ListTeamsFragmentAdapter();
        listAdapter.setOnSelectItemClickListener(position -> navigateToTeamCreator(listAdapter.getItem(position)));

        b.lstTeamPokemon.setHasFixedSize(true);
        b.lstTeamPokemon.setLayoutManager(new GridLayoutManager(requireContext(), getResources().getInteger(R.integer.lstTeamPokemon)));
        b.lstTeamPokemon.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
        b.lstTeamPokemon.setAdapter(listAdapter);
    }

    private void navigateToTeamCreator(Team team) {
        ListTeamsFragmentDirections.ActionListTeamsToTeamCreator action =
                ListTeamsFragmentDirections.actionListTeamsToTeamCreator(team);
        navController.navigate(action);
    }
}
