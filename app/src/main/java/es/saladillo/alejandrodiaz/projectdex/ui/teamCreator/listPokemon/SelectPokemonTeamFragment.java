package es.saladillo.alejandrodiaz.projectdex.ui.teamCreator.listPokemon;

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
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;

import es.saladillo.alejandrodiaz.projectdex.R;
import es.saladillo.alejandrodiaz.projectdex.base.EventObserver;
import es.saladillo.alejandrodiaz.projectdex.data.local.model.Pokemon;
import es.saladillo.alejandrodiaz.projectdex.data.local.model.PokemonTeam;
import es.saladillo.alejandrodiaz.projectdex.databinding.FragmentTeamPokemonSelectBinding;
import es.saladillo.alejandrodiaz.projectdex.di.Injector;
import es.saladillo.alejandrodiaz.projectdex.ui.main.MainActivityViewModel;
import es.saladillo.alejandrodiaz.projectdex.utils.SnackbarUtils;
import es.saladillo.alejandrodiaz.projectdex.utils.StringUtils;

public class SelectPokemonTeamFragment extends Fragment {

    private FragmentTeamPokemonSelectBinding b;
    private SelectPokemonTeamFragmentViewModel viewModel;
    private MainActivityViewModel activityViewModel;
    private SelectPokemonTeamFragmentAdapter listAdapter;
    private NavController navController;
    private MenuItem mnuSearch;
    private SearchView searchView;
    private int teamPosition;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        SelectPokemonTeamFragmentArgs args = SelectPokemonTeamFragmentArgs.fromBundle(getArguments());
        teamPosition = args.getTeamPosition();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_list_pokemon, menu);
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
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        b = FragmentTeamPokemonSelectBinding.inflate(inflater, container, false);
        return b.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this, new SelectPokemonTeamFragmentViewModelFactory(
                Injector.provideRepository())).get(SelectPokemonTeamFragmentViewModel.class);
        activityViewModel = ViewModelProviders.of(requireActivity()).get(MainActivityViewModel.class);
        navController = NavHostFragment.findNavController(this);
        setupViews();
        observe();
        if (savedInstanceState == null)
            viewModel.queryPokemons();
    }

    private void observe() {
        viewModel.getPokemons().observe(this, pokemons -> {
            viewModel.queryPokemons();
            listAdapter.submitList(pokemons);
        });
        viewModel.getMessage().observe(this, new EventObserver<>(this::showMessage));
        viewModel.getLoading().observe(this, loading -> b.pbListPokemon.setVisibility(loading ? View.VISIBLE : View.INVISIBLE));
    }

    private void showMessage(String message) {
        SnackbarUtils.snackbar(requireView(), message);
        viewModel.queryPokemons();
    }

    private void setupViews() {
        setupToolbar();
        setupRecyclerView();
    }

    private void setupToolbar() {
        Toolbar toolbar = b.toolbar;

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration);
        ((AppCompatActivity) requireActivity()).setSupportActionBar(toolbar);
    }

    private void setupRecyclerView() {
        listAdapter = new SelectPokemonTeamFragmentAdapter();
        listAdapter.setOnSelectItemClickListener(position -> NavigateToTeamCreator(listAdapter.getItem(position)));

        b.lstPokemon.setHasFixedSize(true);
        b.lstPokemon.setLayoutManager(new GridLayoutManager(requireContext(), getResources().getInteger(R.integer.lstPokemon_columns)));
        b.lstPokemon.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
        b.lstPokemon.setAdapter(listAdapter);
    }

    private void NavigateToTeamCreator(Pokemon pokemon) {
        String name = StringUtils.pokemonNameSprite(pokemon.getName());
        String url = "http://www.pokestadium.com/assets/img/sprites/official-art/%s.png";

        PokemonTeam pokemonTeam = new PokemonTeam(pokemon.getId(), teamPosition,
                pokemon.getName(), pokemon.getTypes(), String.format(url, name));
        activityViewModel.setTransferedPokemon(pokemonTeam);
        navController.popBackStack();
    }


}
