package es.saladillo.alejandrodiaz.projectdex.ui.detaildex;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import es.saladillo.alejandrodiaz.projectdex.R;
import es.saladillo.alejandrodiaz.projectdex.data.local.model.EvoChain;
import es.saladillo.alejandrodiaz.projectdex.data.remote.dto.evolution.EvolutionDetail;
import es.saladillo.alejandrodiaz.projectdex.databinding.EvolutionChainItemBinding;
import es.saladillo.alejandrodiaz.projectdex.utils.PicassoUtils;

public class PokemonEvolutionChainAdapter extends ListAdapter<EvoChain, PokemonEvolutionChainAdapter.ViewHolder> {


    protected PokemonEvolutionChainAdapter() {
        super(new DiffUtil.ItemCallback<EvoChain>() {
            @Override
            public boolean areItemsTheSame(@NonNull EvoChain oldItem, @NonNull EvoChain newItem) {
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull EvoChain oldItem, @NonNull EvoChain newItem) {
                return TextUtils.equals(oldItem.getImgUrl(), newItem.getImgUrl()) &&
                        TextUtils.equals(oldItem.getName(), newItem.getName());
            }
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        EvolutionChainItemBinding b = EvolutionChainItemBinding.inflate(LayoutInflater.from(
                parent.getContext()), parent, false);
        return new ViewHolder(b);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    @Override
    protected EvoChain getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItem(position).getId();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private EvolutionChainItemBinding b;

        public ViewHolder(@NonNull EvolutionChainItemBinding b) {
            super(b.getRoot());
            this.b = b;
        }

        public void bind(EvoChain evoChain) {
            String wayEvolve;
            PicassoUtils.loadUrl(b.imgPokeEvo, evoChain.getImgUrl());
            b.lblPokemon.setText(b.ClEvoChainItem.getContext().getString(R.string.pokemonEvoName,
                    evoChain.getId(), evoChain.getName()));
            wayEvolve = configWayEvolve(evoChain.getEvolutionDetail());

            b.lblWayEvolve.setText(wayEvolve);
        }

        private String configWayEvolve(EvolutionDetail evolutionDetail) {
            String wayEvolve = "-";
            String timeOfDay = "";
            String knownMoveType = "";

            if(evolutionDetail.getMinBeauty() != null && evolutionDetail.getMinLevel() != null) {
                wayEvolve = b.ClEvoChainItem.getContext().getString(R.string.evolution_by_beauty);
            } else if(evolutionDetail.getHeldItem() != null) {
                if (evolutionDetail.getTrigger().getName().equals("trade")) {
                    wayEvolve = b.ClEvoChainItem.getContext().getString(
                            R.string.evolution_by_trade_with_heldItem,
                            evolutionDetail.getHeldItem().getName());
                } else if (evolutionDetail.getMinLevel() != null) {
                    if(!evolutionDetail.getTimeOfDay().isEmpty()) {
                        timeOfDay = "(" + evolutionDetail.getTimeOfDay() + ")";
                    }
                    wayEvolve = b.ClEvoChainItem.getContext().getString(R.string.evolution_by_level_with_heldItem, timeOfDay, evolutionDetail.getItem().getName()) ;
                }
            } else if(evolutionDetail.getMinLevel() != null) {
                if(!evolutionDetail.getTimeOfDay().isEmpty()) {
                    timeOfDay = "(" + evolutionDetail.getTimeOfDay() + ")";
                }
                wayEvolve = b.ClEvoChainItem.getContext().getString(R.string.evolution_by_level, evolutionDetail.getMinLevel(), timeOfDay);
            } else if(evolutionDetail.getItem() != null) {
                wayEvolve = evolutionDetail.getItem().getName().replaceAll("-", " ");
            } else if(evolutionDetail.getMinHappiness() != null) {

            } else if(evolutionDetail.getMinAffection() != null) {
                if(evolutionDetail.getKnownMoveType() != null) {
                    knownMoveType = String.format("Know a %s movement + ", evolutionDetail.getKnownMoveType().getName());
                }
                wayEvolve = b.ClEvoChainItem.getContext().getString(R.string.evolution_by_Affection, knownMoveType, evolutionDetail.getMinAffection());
            } else if(evolutionDetail.getKnownMove() != null) {
                wayEvolve = b.ClEvoChainItem.getContext().getString(R.string.evolution_by_knowMove, evolutionDetail.getKnownMove().getName());
            } else if(evolutionDetail.getPartySpecies() != null) {
                wayEvolve = b.ClEvoChainItem.getContext().getString(R.string.evolution_by_partySpecie, evolutionDetail.getPartySpecies().getName());
            } else if(evolutionDetail.getPartyType() != null) {
                wayEvolve = b.ClEvoChainItem.getContext().getString(R.string.evolution_by_partyType, evolutionDetail.getMinLevel(), evolutionDetail.getPartySpecies().getName());
            } else if (evolutionDetail.getTurnUpsideDown()) {
                wayEvolve = b.ClEvoChainItem.getContext().getString(R.string.evolution_by_turn_upside_down, evolutionDetail.getMinLevel());
            }
            return wayEvolve;
        }
    }
}
