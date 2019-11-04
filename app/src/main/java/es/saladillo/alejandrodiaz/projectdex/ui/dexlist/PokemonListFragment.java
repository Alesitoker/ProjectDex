package es.saladillo.alejandrodiaz.projectdex.ui.dexlist;

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

import es.saladillo.alejandrodiaz.projectdex.databinding.FragmentPokemonListBinding;

public class PokemonListFragment extends Fragment {

    private FragmentPokemonListBinding b;
    private PokemonListFragmentViewModel viewModel;
    private NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        b = FragmentPokemonListBinding.inflate(inflater, container, false);
        return b.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this, new PokemonListFragmentViewModelFactory()).get(PokemonListFragmentViewModel.class);
        navController = NavHostFragment.findNavController(this);
    }
}
