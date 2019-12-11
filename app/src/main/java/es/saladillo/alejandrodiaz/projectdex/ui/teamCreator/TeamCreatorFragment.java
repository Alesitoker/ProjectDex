package es.saladillo.alejandrodiaz.projectdex.ui.teamCreator;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.palette.graphics.Palette;

import com.google.android.gms.common.util.ArrayUtils;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import es.saladillo.alejandrodiaz.projectdex.R;
import es.saladillo.alejandrodiaz.projectdex.base.EventObserver;
import es.saladillo.alejandrodiaz.projectdex.data.local.model.PokemonTeam;
import es.saladillo.alejandrodiaz.projectdex.data.local.model.Team;
import es.saladillo.alejandrodiaz.projectdex.databinding.FragmentTeamCreatorBinding;
import es.saladillo.alejandrodiaz.projectdex.databinding.FragmentTeamCreatorNoPokemonBinding;
import es.saladillo.alejandrodiaz.projectdex.databinding.FragmentTeamCreatorPokemonBinding;
import es.saladillo.alejandrodiaz.projectdex.ui.main.MainActivityViewModel;
import es.saladillo.alejandrodiaz.projectdex.utils.KeyboardUtils;
import es.saladillo.alejandrodiaz.projectdex.utils.PicassoUtils;
import es.saladillo.alejandrodiaz.projectdex.utils.SnackbarUtils;
import es.saladillo.alejandrodiaz.projectdex.utils.StringUtils;
import es.saladillo.alejandrodiaz.projectdex.utils.TypesUtils;

import static es.saladillo.alejandrodiaz.projectdex.utils.ColorTypeUtils.obtainColor;

public class TeamCreatorFragment<TEAMPOSITION1> extends Fragment {

    private FragmentTeamCreatorBinding b;
    private TeamCreatorFragmentViewModel viewModel;
    private MainActivityViewModel activityViewModel;
    private NavController navController;
    private final int TEAMPOSITION1 = 0;
    private final int TEAMPOSITION2 = 1;
    private final int TEAMPOSITION3 = 2;
    private final int TEAMPOSITION4 = 3;
    private final int TEAMPOSITION5 = 4;
    private final int TEAMPOSITION6 = 5;
    private Team team;
    private final int maxSizetxt = 30;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        TeamCreatorFragmentArgs args = TeamCreatorFragmentArgs.fromBundle(getArguments());
        team = args.getTeam();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_team_creator, menu);
        if(team == null) {
            menu.getItem(1).setVisible(false);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.mnuSave) {
            save();
            return true;
        } else if(item.getItemId() == R.id.mnuDelete) {
            deleteTeam();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        b = FragmentTeamCreatorBinding.inflate(inflater, container, false);
        return b.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(TeamCreatorFragmentViewModel.class);
        activityViewModel = ViewModelProviders.of(requireActivity()).get(MainActivityViewModel.class);
        navController = NavHostFragment.findNavController(this);
        if(savedInstanceState == null) {
            viewModel.setTeam(team);
            if(viewModel.getTeam() != null) {
                viewModel.setPokemonsTeam(team.getPokemons());
            }
        }
        setupViews();
        observe();
        for(int i = 0; i < viewModel.getPokemonsTeam().length; i++) {
            if (viewModel.getPokemon(i) != null) {
                configTeam(viewModel.getPokemon(i));
            }
        }
    }

    private void observe() {
        activityViewModel.getTransferedPokemon().observe(this, new EventObserver<>(pokemonTeam -> {
            viewModel.addPokemon(pokemonTeam);
            configTeam(pokemonTeam);
        }));
    }

    private void configTeam(PokemonTeam pokemonTeam) {
        switch (pokemonTeam.getTeamPosition()) {
            case TEAMPOSITION1:
                configPokemon(b.pokeParty1, b.addPoke1, TEAMPOSITION1);
                break;
            case TEAMPOSITION2:
                configPokemon(b.pokeParty2, b.addPoke2, TEAMPOSITION2);
                break;
            case TEAMPOSITION3:
                configPokemon(b.pokeParty3, b.addPoke3, TEAMPOSITION3);
                break;
            case TEAMPOSITION4:
                configPokemon(b.pokeParty4, b.addPoke4, TEAMPOSITION4);
                break;
            case TEAMPOSITION5:
                configPokemon(b.pokeParty5, b.addPoke5, TEAMPOSITION5);
                break;
            case TEAMPOSITION6:
                configPokemon(b.pokeParty6, b.addPoke6, TEAMPOSITION6);
                break;
        }
    }

    private void addPokeVisible(int position) {
        switch (position) {
            case TEAMPOSITION1:
                b.addPoke1.ClAddPokemon.setVisibility(View.VISIBLE);
                break;
            case TEAMPOSITION2:
                b.addPoke2.ClAddPokemon.setVisibility(View.VISIBLE);
                break;
            case TEAMPOSITION3:
                b.addPoke3.ClAddPokemon.setVisibility(View.VISIBLE);
                break;
            case TEAMPOSITION4:
                b.addPoke4.ClAddPokemon.setVisibility(View.VISIBLE);
                break;
            case TEAMPOSITION5:
                b.addPoke5.ClAddPokemon.setVisibility(View.VISIBLE);
                break;
            case TEAMPOSITION6:
                b.addPoke6.ClAddPokemon.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void configPokemon(FragmentTeamCreatorPokemonBinding pokeParty,
                               FragmentTeamCreatorNoPokemonBinding addPoke, int position) {
        PokemonTeam pokemonTeam = viewModel.getPokemon(position);

        addPoke.ClAddPokemon.setVisibility(View.INVISIBLE);
        pokeParty.CLPokemonTeam.setVisibility(View.VISIBLE);

        PicassoUtils.loadUrl(pokeParty.imgPokemon, pokemonTeam.getImgUrl());
        pokeParty.lblPokemonNumber.setText(getString(R.string.id_pokemon, pokemonTeam.getId()));
        pokeParty.lblPokemonName.setText(StringUtils.CapitalizeFirstLetter(pokemonTeam.getNickName()));
        TypesUtils.showType(requireContext(), pokemonTeam.getTypes(), pokeParty.lblType1, pokeParty.lblType2);
    }

    private void setupViews() {
        setupToolbar();

        viewModel.setOpenDeleteBtn(false);

        b.addPoke1.btnAddPokemon.setOnClickListener(v -> navigateToselectPokemonTeam(TEAMPOSITION1));
        b.addPoke2.btnAddPokemon.setOnClickListener(v -> navigateToselectPokemonTeam(TEAMPOSITION2));
        b.addPoke3.btnAddPokemon.setOnClickListener(v -> navigateToselectPokemonTeam(TEAMPOSITION3));
        b.addPoke4.btnAddPokemon.setOnClickListener(v -> navigateToselectPokemonTeam(TEAMPOSITION4));
        b.addPoke5.btnAddPokemon.setOnClickListener(v -> navigateToselectPokemonTeam(TEAMPOSITION5));
        b.addPoke6.btnAddPokemon.setOnClickListener(v -> navigateToselectPokemonTeam(TEAMPOSITION6));

        b.pokeParty1.CLPokemonTeam.setOnClickListener(v -> showBtndeletePokemon(TEAMPOSITION1));
        b.pokeParty2.CLPokemonTeam.setOnClickListener(v -> showBtndeletePokemon(TEAMPOSITION2));
        b.pokeParty3.CLPokemonTeam.setOnClickListener(v -> showBtndeletePokemon(TEAMPOSITION3));
        b.pokeParty4.CLPokemonTeam.setOnClickListener(v -> showBtndeletePokemon(TEAMPOSITION4));
        b.pokeParty5.CLPokemonTeam.setOnClickListener(v -> showBtndeletePokemon(TEAMPOSITION5));
        b.pokeParty6.CLPokemonTeam.setOnClickListener(v -> showBtndeletePokemon(TEAMPOSITION6));

        b.btnDeletePokemon.setOnClickListener(v -> deletePokemon());
    }

    private void deletePokemon() {
        int position = viewModel.getDeletePosition();
        addPokeVisible(position);
        b.btnDeletePokemon.setVisibility(View.GONE);
        viewModel.setOpenDeleteBtn(false);
        viewModel.deletePokemon(position);
    }

    private void showBtndeletePokemon(int deletePosition) {

        if (viewModel.isOpenDeleteBtn() && viewModel.getDeletePosition() == deletePosition) {
            viewModel.setOpenDeleteBtn(false);
            b.btnDeletePokemon.setVisibility(View.GONE);
        } else if (!viewModel.isOpenDeleteBtn()) {
            viewModel.setOpenDeleteBtn(true);
            b.btnDeletePokemon.setVisibility(View.VISIBLE);
        }

        extractColor(deletePosition);
        viewModel.setDeletePosition(deletePosition);
    }

    public void extractColor(int position) {
        PokemonTeam pokemon = viewModel.getPokemon(position);
        Picasso.with(requireContext())
                .load(pokemon.getImgUrl())
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                            @Override
                            public void onGenerated(@Nullable Palette palette) {
                                Palette.Swatch textSwatch = palette.getVibrantSwatch();
                                if(textSwatch == null) {
                                    return;
                                }
                                DrawableCompat.setTint(
                                        DrawableCompat.wrap(b.btnDeletePokemon.getBackground()),
                                        textSwatch.getRgb()
                                );
                            }
                        });
                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {

                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });
    }

    private void navigateToselectPokemonTeam(int teamPosition) {
        TeamCreatorFragmentDirections.ActionTeamCreatorToSelectPokemonTeam action =
                TeamCreatorFragmentDirections.actionTeamCreatorToSelectPokemonTeam(teamPosition);
        navController.navigate(action);
    }

    private void setupToolbar() {
        Toolbar toolbar = b.toolbar;

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration);
        ((AppCompatActivity) requireActivity()).setSupportActionBar(toolbar);
    }

    private void save() {
        KeyboardUtils.hideSoftKeyboard(requireActivity());
        if (b.txtTeamName.getText().toString().length() < maxSizetxt) {
            activityViewModel.setTransferedTeam(createTeam());
        } else {
            SnackbarUtils.snackbar(b.txtTeamName, "Very long team name");
        }
    }

    private Team createTeam() {
        long id;
        String teamName;

        if (viewModel.getTeam() != null) {
            id = viewModel.getTeam().getId();
        } else {
            id = viewModel.getIdTeam();
        }

        if(b.txtTeamName.getText().toString().isEmpty()) {
            teamName = getString(R.string.default_team_name);
        } else {
            teamName = b.txtTeamName.getText().toString();
        }

        return new Team(id, teamName, viewModel.getPokemonsTeam());

    }

    private void deleteTeam() {

    }
}
