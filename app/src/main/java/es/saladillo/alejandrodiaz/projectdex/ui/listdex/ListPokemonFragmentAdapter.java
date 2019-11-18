package es.saladillo.alejandrodiaz.projectdex.ui.listdex;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import es.saladillo.alejandrodiaz.projectdex.data.local.model.Pokemon;
import es.saladillo.alejandrodiaz.projectdex.data.remote.dto.pokemon.Type;
import es.saladillo.alejandrodiaz.projectdex.databinding.FragmentItemPokemonBinding;

public class ListPokemonFragmentAdapter extends ListAdapter<Pokemon, ListPokemonFragmentAdapter.ViewHolder> {

    protected ListPokemonFragmentAdapter() {
        super(new DiffUtil.ItemCallback<Pokemon>() {
            @Override
            public boolean areItemsTheSame(@NonNull Pokemon oldItem, @NonNull Pokemon newItem) {
                return oldItem.getId().equals(newItem.getId());
            }

            @Override
            public boolean areContentsTheSame(@NonNull Pokemon oldItem, @NonNull Pokemon newItem) {
                return TextUtils.equals(oldItem.getName(), newItem.getName()) &&
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

    class ViewHolder extends RecyclerView.ViewHolder {

        private FragmentItemPokemonBinding b;

        public ViewHolder(@NonNull FragmentItemPokemonBinding b) {
            super(b.getRoot());
            this.b = b;
        }

        public void bind(Pokemon pokemon) {
            b.lblPkmNumber.setText(String.valueOf(pokemon.getId()));
            b.lblPkmName.setText(pokemon.getName());
            Picasso.with(b.getRoot().getContext()).load(pokemon.getSprites().
                    getFrontDefault()).into(b.imgPkm);
            for (Type type : pokemon.getTypes()) {
                if (type.getSlot() == 1) {
                    b.lblType1.setText(type.getType().getName());
                } else {
                    b.lblType2.setVisibility(View.VISIBLE);
                    b.lblType2.setText(type.getType().getName());
                }
            }
        }
    }
}
