package es.saladillo.alejandrodiaz.projectdex.ui.detaildex;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;

import java.util.List;
import java.util.Objects;

import es.saladillo.alejandrodiaz.projectdex.R;
import es.saladillo.alejandrodiaz.projectdex.base.EventObserver;
import es.saladillo.alejandrodiaz.projectdex.data.remote.dto.pokemon.Ability;
import es.saladillo.alejandrodiaz.projectdex.data.remote.dto.pokemon.PokemonResponse;
import es.saladillo.alejandrodiaz.projectdex.data.remote.dto.pokemon.Stat;
import es.saladillo.alejandrodiaz.projectdex.data.remote.dto.pokemon.Type;
import es.saladillo.alejandrodiaz.projectdex.data.remote.dto.species.FlavorTextEntry;
import es.saladillo.alejandrodiaz.projectdex.data.remote.dto.species.Genera;
import es.saladillo.alejandrodiaz.projectdex.data.remote.dto.species.PokemonSpecies;
import es.saladillo.alejandrodiaz.projectdex.databinding.FragmentPokemonDetailBinding;
import es.saladillo.alejandrodiaz.projectdex.di.Injector;
import es.saladillo.alejandrodiaz.projectdex.utils.PicassoUtils;
import es.saladillo.alejandrodiaz.projectdex.utils.SnackbarUtils;
import es.saladillo.alejandrodiaz.projectdex.utils.StringUtils;

import static es.saladillo.alejandrodiaz.projectdex.utils.ColorTypeUtils.obtainColor;

public class PokemonDetailFragment extends Fragment {

    private FragmentPokemonDetailBinding b;
    private PokemonDetailFragmentViewModel viewModel;
    private NavController navController;
    private PokemonEvolutionChainAdapter listAdapter;
    private int id;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getArguments());
        PokemonDetailFragmentArgs args = PokemonDetailFragmentArgs.fromBundle(getArguments());
        id = args.getId();

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
        setupRecyclerView();
        if(savedInstanceState == null) {
            viewModel.queryPokemon(id);
            viewModel.querySpecie(id);
        }
        setupViews();
        observe();
    }

    private void observe() {
        viewModel.getPokemon().observe(this, this::setupDetail);
        viewModel.getSpecies().observe(this, this::setupSpecie);
        viewModel.getEvoChains().observe(this, evoChains -> listAdapter.submitList(evoChains));
        viewModel.getMessage().observe(this, new EventObserver<>(this::showMessage));
    }

    private void showMessage(String message) {
        if(!message.isEmpty())
            SnackbarUtils.snackbar(requireView(), message);
    }

    private void setupViews() {

    }

    private void setupDetail(PokemonResponse pokemon) {
        String name = pokemon.getName();
        String url = "http://www.pokestadium.com/assets/img/sprites/official-art/%s.png";

        name = StringUtils.pokemonNameSprite(name);

        PicassoUtils.loadUrl(b.dHeader.imgPkm, String.format(url, name));
        b.dHeader.lblNumber.setText(getString(R.string.id_pokemon, pokemon.getId()));
        b.dHeader.lblName.setText(StringUtils.CapitalizeFirstLetter(pokemon.getName()));
        showType(pokemon.getTypes());

        b.dData.lblHeight.setText(getString(R.string.data_pokemon_detail_lblHeight,
                String.valueOf(pokemon.getHeight()/10.0)));
        b.dData.lblWeight.setText(getString(R.string.data_pokemon_detail_lblWeight,
                String.valueOf(pokemon.getWeight()/10.0)));
        configAbilities(pokemon);
        configStats(pokemon);
    }

    private void configStats(PokemonResponse pokemon) {
        int total = 0;
        for(Stat stat : pokemon.getStats()) {
            switch (stat.getStat().getName()) {
                case "speed":
                    b.dData.lblSpeedData.setText(String.valueOf(stat.getBaseStat()));
                    break;
                case "special-defense":
                    b.dData.lblSpDefenseData.setText(String.valueOf(stat.getBaseStat()));
                    break;
                case "special-attack":
                    b.dData.lblSpAttackData.setText(String.valueOf(stat.getBaseStat()));
                    break;
                case "defense":
                    b.dData.lblDefenseData.setText(String.valueOf(stat.getBaseStat()));
                    break;
                case "attack":
                    b.dData.lblAttackData.setText(String.valueOf(stat.getBaseStat()));
                    break;
                case "hp":
                    b.dData.lblHPData.setText(String.valueOf(stat.getBaseStat()));
                    break;
            }
            total += stat.getBaseStat();
        }
        b.dData.lblTotalStatsNumber.setText(String.valueOf(total));
    }

    private void configAbilities(PokemonResponse pokemon) {
        b.dData.lblHidden.setVisibility(View.GONE);
        b.dData.lblAbilityHidden.setVisibility(View.GONE);
        b.dData.lblAbilitiesSeparator.setVisibility(View.GONE);
        b.dData.lblAbility2.setVisibility(View.GONE);
        for(Ability ability : pokemon.getAbilities()) {
            if(ability.getIsHidden()) {
                b.dData.lblAbilityHidden.setText(ability.getAbility().getName());
                b.dData.lblHidden.setVisibility(View.VISIBLE);
                b.dData.lblAbilityHidden.setVisibility(View.VISIBLE);
            }else if(ability.getSlot() == 1) {
                b.dData.lblAbility1.setText(ability.getAbility().getName());
            } else if(ability.getSlot() == 2) {
                b.dData.lblAbility2.setText(ability.getAbility().getName());
                b.dData.lblAbilitiesSeparator.setVisibility(View.VISIBLE);
                b.dData.lblAbility2.setVisibility(View.VISIBLE);
            }
        }
    }

    private void showType(List<Type> types) {
        int color;
        for (Type type : types) {
            if (type.getSlot() == 1) {
                b.dHeader.lblType1.setText(type.getType().getName());
                color = obtainColor(type);
                DrawableCompat.setTint(
                        DrawableCompat.wrap(b.dHeader.lblType1.getBackground()),
                        ContextCompat.getColor(requireContext(), color)
                );
                b.dData.lblTotalStatsNumber.setTextColor(getResources().getColor(color));
                if (types.size() == 1) {
                    b.dHeader.lblType2.setVisibility(View.GONE);
                }
            } else {
                b.dHeader.lblType2.setVisibility(View.VISIBLE);
                b.dHeader.lblType2.setText(type.getType().getName());
                DrawableCompat.setTint(
                        DrawableCompat.wrap(b.dHeader.lblType2.getBackground()),
                        ContextCompat.getColor(requireContext(), obtainColor(type))
                );
            }
        }
    }

    private void setupSpecie(PokemonSpecies species) {
        List<FlavorTextEntry> entries = species.getFlavorTextEntries();
        int i;
        boolean end = false;
        for(i = 0; i < entries.size() && !end; i++) {
            if(!entries.get(i).getFlavorText().isEmpty()
                    && entries.get(i).getLanguage().getName().equals(viewModel.getLanguage())) {
                b.dData.lblDescription.setText(entries.get(i).getFlavorText()
                        .replaceAll("\\s+"," "));
                end = true;
            }
        }
        for(Genera g : species.getGenera())
            if(g.getLanguage().getName().equals(viewModel.getLanguage()))
                b.dHeader.lblCategory.setText(g.getGenus());

    }

    private void setupRecyclerView() {
        listAdapter = new PokemonEvolutionChainAdapter();

        b.dData.lstEvolutionChain.setHasFixedSize(false);
        b.dData.lstEvolutionChain.setNestedScrollingEnabled(false);
        b.dData.lstEvolutionChain.setLayoutManager(new GridLayoutManager(requireContext(), getResources().getInteger(R.integer.lstEvoChain_columns)));
        b.dData.lstEvolutionChain.setAdapter(listAdapter);
    }
}
