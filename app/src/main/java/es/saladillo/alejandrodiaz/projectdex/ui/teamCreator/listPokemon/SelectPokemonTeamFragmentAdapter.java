package es.saladillo.alejandrodiaz.projectdex.ui.teamCreator.listPokemon;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import es.saladillo.alejandrodiaz.projectdex.R;
import es.saladillo.alejandrodiaz.projectdex.data.local.model.Pokemon;
import es.saladillo.alejandrodiaz.projectdex.data.remote.dto.pokemon.Type;
import es.saladillo.alejandrodiaz.projectdex.databinding.FragmentTeamPokemonSelectItemBinding;
import es.saladillo.alejandrodiaz.projectdex.ui.OnSelectItemClickListener;
import es.saladillo.alejandrodiaz.projectdex.utils.PicassoUtils;
import es.saladillo.alejandrodiaz.projectdex.utils.StringUtils;
import es.saladillo.alejandrodiaz.projectdex.utils.TypesUtils;

import static es.saladillo.alejandrodiaz.projectdex.utils.ColorTypeUtils.obtainColor;

public class SelectPokemonTeamFragmentAdapter extends ListAdapter<Pokemon, SelectPokemonTeamFragmentAdapter.ViewHolder> implements Filterable {

    private OnSelectItemClickListener onSelectItemClickListener;
    private List<Pokemon> dataList;
    private List<Pokemon> filteredList;

    protected SelectPokemonTeamFragmentAdapter() {
        super(new DiffUtil.ItemCallback<Pokemon>() {
            @Override
            public boolean areItemsTheSame(@NonNull Pokemon oldItem, @NonNull Pokemon newItem) {
                return oldItem.getId().equals(newItem.getId());
            }

            @Override
            public boolean areContentsTheSame(@NonNull Pokemon oldItem, @NonNull Pokemon newItem) {
                return oldItem.getOrder().equals(newItem.getOrder()) &&
                        TextUtils.equals(oldItem.getName(), newItem.getName()) &&
                        oldItem.getSprites().equals(newItem.getSprites()) &&
                        oldItem.getTypes().equals(newItem.getTypes());
            }
        });
    }

    @Override
    public void submitList(List<Pokemon> list) {
        dataList = list;
        filteredList = list;
        super.submitList(list);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FragmentTeamPokemonSelectItemBinding b = FragmentTeamPokemonSelectItemBinding.inflate(LayoutInflater.from(
                parent.getContext()), parent, false);
        return new ViewHolder(b);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    @Override
    protected Pokemon getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItem(position).getId();
    }

    public void setOnSelectItemClickListener(OnSelectItemClickListener onSelectItemClickListener) {
        this.onSelectItemClickListener = onSelectItemClickListener;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String filterString = constraint.toString();
                if (filterString.isEmpty()) {
                    filteredList = dataList;
                } else {
                    List<Pokemon> list = new ArrayList<>();
                    for (Pokemon pokemon : dataList) {
                        if (includeInFilter(pokemon, filterString)) {
                            list.add(pokemon);
                        }

                    }
                    filteredList = list;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredList;
                return filterResults;            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                //noinspection unchecked
                filteredList = (List<Pokemon>) results.values;
                updateList(filteredList);
            }
        };
    }

    private void updateList(List<Pokemon> list) {
        super.submitList(list);
    }

    public boolean includeInFilter(Pokemon item, String filterString) {
        return item.getName().toLowerCase().contains(filterString.toLowerCase());
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private FragmentTeamPokemonSelectItemBinding b;

        public ViewHolder(@NonNull FragmentTeamPokemonSelectItemBinding b) {
            super(b.getRoot());
            this.b = b;

            b.clItemRoot.setOnClickListener(v -> onSelectItemClickListener.onItemClick(getAdapterPosition()));
        }

        public void bind(Pokemon pokemon) {
            String name = pokemon.getName();
            String url;
            String id = b.clItemRoot.getContext().getString(R.string.id_pokemon,
                    pokemon.getId());

            pokemon.setFilterId(id);
            b.lblPkmNumber.setText(id);
            b.lblPkmName.setText(StringUtils.CapitalizeFirstLetter(name));
            name = StringUtils.pokemonNameSprite(name);
            pokemon.setImgUrl(name);
            url = String.format("http://www.pokestadium.com/assets/img/sprites/official-art/%s.png", pokemon.getImgUrl());
            PicassoUtils.loadUrl(b.imgPkm, url);
            TypesUtils.showType(b.imgPkm.getContext(), pokemon.getTypes(), b.lblType1, b.lblType2);
        }
    }
    

}
