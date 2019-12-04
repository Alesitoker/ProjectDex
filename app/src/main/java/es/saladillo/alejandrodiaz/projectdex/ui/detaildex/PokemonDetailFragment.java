package es.saladillo.alejandrodiaz.projectdex.ui.detaildex;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import java.util.List;
import java.util.Objects;

import es.saladillo.alejandrodiaz.projectdex.R;
import es.saladillo.alejandrodiaz.projectdex.base.EventObserver;
import es.saladillo.alejandrodiaz.projectdex.data.remote.dto.pokemon.PokemonResponse;
import es.saladillo.alejandrodiaz.projectdex.data.remote.dto.pokemon.Type;
import es.saladillo.alejandrodiaz.projectdex.databinding.FragmentPokemonDetailBinding;
import es.saladillo.alejandrodiaz.projectdex.di.Injector;
import es.saladillo.alejandrodiaz.projectdex.utils.PicassoUtils;
import es.saladillo.alejandrodiaz.projectdex.utils.SnackbarUtils;

public class PokemonDetailFragment extends Fragment {

    private FragmentPokemonDetailBinding b;
    private PokemonDetailFragmentViewModel viewModel;
    private NavController navController;
    private String name;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getArguments());
        PokemonDetailFragmentArgs args = PokemonDetailFragmentArgs.fromBundle(getArguments());
        name = args.getName();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        b = FragmentPokemonDetailBinding.inflate(inflater, container, false);
        return b.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this, new PokemonDetailFragmentViewModelFactory(
                Injector.provideRepository())).get(PokemonDetailFragmentViewModel.class);
        navController = NavHostFragment.findNavController(this);
        if(savedInstanceState == null)
            viewModel.queryPokemon(name);
        observe();
    }

    private void observe() {
        viewModel.getPokemon().observe(this, this::setupDetail);
        viewModel.getMessage().observe(this, new EventObserver<>(this::showMessage));
    }

    private void showMessage(String message) {
        SnackbarUtils.snackbar(requireView(), message);
    }

    private void setupDetail(PokemonResponse pokemon) {
        PicassoUtils.loadUrl(b.imgPkm, pokemon.getSprites().getFrontDefault());
        b.lblNumber.setText(String.format("#%03d", pokemon.getId()));
        b.lblName.setText(pokemon.getName());
        showType(pokemon.getTypes());
    }

    private void showType(List<Type> types) {
        for (Type type : types) {
            if (type.getSlot() == 1) {
                b.lblType1.setText(type.getType().getName());
                DrawableCompat.setTint(
                        DrawableCompat.wrap(b.lblType1.getBackground()),
                        ContextCompat.getColor(requireContext(), viewModel.obtainColor(type))
                );
                if (types.size() == 1) {
                    b.lblType2.setVisibility(View.GONE);
                }
            } else {
                b.lblType2.setVisibility(View.VISIBLE);
                b.lblType2.setText(type.getType().getName());
                DrawableCompat.setTint(
                        DrawableCompat.wrap(b.lblType2.getBackground()),
                        ContextCompat.getColor(requireContext(), viewModel.obtainColor(type))
                );
            }
        }
    }
}
