package es.saladillo.alejandrodiaz.projectdex.ui.listdex;

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
import es.saladillo.alejandrodiaz.projectdex.base.EventObserver;
import es.saladillo.alejandrodiaz.projectdex.data.local.model.Pokemon;
import es.saladillo.alejandrodiaz.projectdex.databinding.FragmentListPokemonBinding;
import es.saladillo.alejandrodiaz.projectdex.di.Injector;
import es.saladillo.alejandrodiaz.projectdex.ui.main.DrawerLocker;
import es.saladillo.alejandrodiaz.projectdex.ui.main.ToolbarConfigurationInterface;
import es.saladillo.alejandrodiaz.projectdex.utils.SnackbarUtils;

public class ListPokemonFragment extends Fragment {

    private FragmentListPokemonBinding b;
    private ToolbarConfigurationInterface toolbarConfiguration;
    private ListPokemonFragmentViewModel viewModel;
    private ListPokemonFragmentAdapter listAdapter;
    private NavController navController;
    private MenuItem mnuSearch;
    private SearchView searchView;
    private DrawerLocker drawerLocker;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            toolbarConfiguration = (ToolbarConfigurationInterface) context;
            drawerLocker = (DrawerLocker) context;
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
                viewModel.setSearchQuery(query);
                return false;
            }
        });
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        b = FragmentListPokemonBinding.inflate(inflater, container, false);
        return b.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this, new ListPokemonFragmentViewModelFactory(
                Injector.provideRepository())).get(ListPokemonFragmentViewModel.class);
        navController = NavHostFragment.findNavController(this);
        drawerLocker.setDrawerEnabled(true);
        setupToolbar();
        setupViews();
        observe();
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
        setupRecyclerView();
    }

    private void setupToolbar() {
        Toolbar toolbar = b.toolbar;
        toolbarConfiguration.configureToolbar(toolbar);
    }

    private void setupRecyclerView() {
        listAdapter = new ListPokemonFragmentAdapter();
        listAdapter.setOnSelectItemClickListener(position -> NavigateToDetailDex(listAdapter.getItem(position)));

        b.lstPokemon.setHasFixedSize(true);
        b.lstPokemon.setLayoutManager(new GridLayoutManager(requireContext(), getResources().getInteger(R.integer.lstPokemon_columns)));
        b.lstPokemon.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
        b.lstPokemon.setAdapter(listAdapter);
    }

    private void NavigateToDetailDex(Pokemon pokemon) {
        ListPokemonFragmentDirections.ActionListPokemonToPokemonDetail action =
                ListPokemonFragmentDirections.actionListPokemonToPokemonDetail()
                        .setId(pokemon.getId());
        navController.navigate(action);
    }


}
