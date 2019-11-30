package es.saladillo.alejandrodiaz.projectdex.ui.listdex;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;

import es.saladillo.alejandrodiaz.projectdex.R;
import es.saladillo.alejandrodiaz.projectdex.base.EventObserver;
import es.saladillo.alejandrodiaz.projectdex.databinding.FragmentListPokemonBinding;
import es.saladillo.alejandrodiaz.projectdex.di.Injector;
import es.saladillo.alejandrodiaz.projectdex.utils.SnackbarUtils;

public class ListPokemonFragment extends Fragment {

    private FragmentListPokemonBinding b;
    private ListPokemonFragmentViewModel viewModel;
    private ListPokemonFragmentAdapter listAdapter;
    private NavController navController;

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
        setupViews();
        observe();
    }

    private void observe() {
        viewModel.getPokemons().observe(this, pokemons -> listAdapter.submitList(pokemons));
        viewModel.getMessage().observe(this, new EventObserver<>(this::showMessage));
    }

    private void showMessage(String message) {
        SnackbarUtils.snackbar(requireView(), message);
    }

    private void setupViews() {
        setupRecyclerView();
        viewModel.queryPokemons();
    }

    private void setupRecyclerView() {
        listAdapter = new ListPokemonFragmentAdapter();

        b.lstPokemon.setHasFixedSize(true);
        b.lstPokemon.setLayoutManager(new GridLayoutManager(requireContext(), getResources().getInteger(R.integer.lstPokemon_columns)));
        b.lstPokemon.setAdapter(listAdapter);
    }


}
