package es.saladillo.alejandrodiaz.projectdex.ui.listdex;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

import es.saladillo.alejandrodiaz.projectdex.R;
import es.saladillo.alejandrodiaz.projectdex.data.local.model.Pokemon;
import es.saladillo.alejandrodiaz.projectdex.data.remote.dto.pokemon.Type;
import es.saladillo.alejandrodiaz.projectdex.databinding.FragmentItemPokemonBinding;
import es.saladillo.alejandrodiaz.projectdex.ui.OnSelectItemClickListener;
import es.saladillo.alejandrodiaz.projectdex.utils.PicassoUtils;
import es.saladillo.alejandrodiaz.projectdex.utils.StringUtils;

import static es.saladillo.alejandrodiaz.projectdex.utils.ColorTypeUtils.obtainColor;

public class ListPokemonFragmentAdapter extends ListAdapter<Pokemon, ListPokemonFragmentAdapter.ViewHolder> {

    private OnSelectItemClickListener onSelectItemClickListener;

    protected ListPokemonFragmentAdapter() {
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

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FragmentItemPokemonBinding b = FragmentItemPokemonBinding.inflate(LayoutInflater.from(
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

    class ViewHolder extends RecyclerView.ViewHolder {

        private FragmentItemPokemonBinding b;

        public ViewHolder(@NonNull FragmentItemPokemonBinding b) {
            super(b.getRoot());
            this.b = b;

            b.clItemRoot.setOnClickListener(v -> onSelectItemClickListener.onItemClick(getAdapterPosition()));
        }

        public void bind(Pokemon pokemon) {
            String name = pokemon.getName();
            String url;

            b.lblPkmNumber.setText(b.clItemRoot.getContext().getString(R.string.id_pokemon,
                    pokemon.getId()));
            b.lblPkmName.setText(StringUtils.CapitalizeFirstLetter(name));
            name = StringUtils.pokemonNameSprite(name);
            pokemon.setImgUrl(name);
            url = String.format("http://www.pokestadium.com/assets/img/sprites/official-art/%s.png", pokemon.getImgUrl());
            PicassoUtils.loadUrl(b.imgPkm, url);
            showTypes(pokemon.getTypes());
        }

        private void showTypes(List<Type> types) {
            for (Type type : types) {
                if (type.getSlot() == 1) {
                    b.lblType1.setText(type.getType().getName());
                    DrawableCompat.setTint(
                            DrawableCompat.wrap(b.lblType1.getBackground()),
                            ContextCompat.getColor(b.imgPkm.getContext(), obtainColor(type)));
                    if (types.size() == 1) {
                        b.lblType2.setVisibility(View.GONE);
                    }
                } else {
                    b.lblType2.setVisibility(View.VISIBLE);
                    b.lblType2.setText(type.getType().getName());
                    DrawableCompat.setTint(
                            DrawableCompat.wrap(b.lblType2.getBackground()),
                            ContextCompat.getColor(b.imgPkm.getContext(), obtainColor(type)));
                }
            }
        }
    }
    

}
