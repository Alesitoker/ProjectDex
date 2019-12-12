package es.saladillo.alejandrodiaz.projectdex.ui.teamCreator.listTeam;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import es.saladillo.alejandrodiaz.projectdex.data.local.model.TeamDataB;
import es.saladillo.alejandrodiaz.projectdex.databinding.FragmentTeamsItemBinding;
import es.saladillo.alejandrodiaz.projectdex.ui.OnSelectItemClickListener;
import es.saladillo.alejandrodiaz.projectdex.utils.PicassoUtils;

public class ListTeamsFragmentAdapter extends ListAdapter<TeamDataB, ListTeamsFragmentAdapter.ViewHolder> implements Filterable {

    private OnSelectItemClickListener onSelectItemClickListener;
    private List<TeamDataB> dataList;
    private List<TeamDataB> filteredList;

    protected ListTeamsFragmentAdapter() {
        super(new DiffUtil.ItemCallback<TeamDataB>() {
            @Override
            public boolean areItemsTheSame(@NonNull TeamDataB oldItem, @NonNull TeamDataB newItem) {
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull TeamDataB oldItem, @NonNull TeamDataB newItem) {
                return TextUtils.equals(oldItem.getTeamName(), newItem.getTeamName());
            }
        });
    }

    @Override
    public void submitList(List<TeamDataB> list) {
        dataList = list;
        filteredList = list;
        super.submitList(list);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FragmentTeamsItemBinding b = FragmentTeamsItemBinding.inflate(LayoutInflater.from(
                parent.getContext()), parent, false);
        return new ViewHolder(b);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    @Override
    protected TeamDataB getItem(int position) {
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
                    List<TeamDataB> list = new ArrayList<>();
                    for (TeamDataB teamDataB : dataList) {
                        if (includeInFilter(teamDataB, filterString)) {
                            list.add(teamDataB);
                        }

                    }
                    filteredList = list;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                //noinspection unchecked
                filteredList = (List<TeamDataB>) results.values;
                updateList(filteredList);
            }
        };
    }

    private void updateList(List<TeamDataB> list) {
        super.submitList(list);
    }

    public boolean includeInFilter(TeamDataB item, String filterString) {
        return item.getTeamName().toLowerCase().contains(filterString.toLowerCase());
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final FragmentTeamsItemBinding b;

        public ViewHolder(@NonNull FragmentTeamsItemBinding b) {
            super(b.getRoot());
            this.b = b;

            b.CLTeamsItem.setOnClickListener(v -> onSelectItemClickListener.onItemClick(getAdapterPosition()));
        }

        public void bind(TeamDataB teamDataB) {
            b.lblTeamName.setText(teamDataB.getTeamName());

            if (teamDataB.getPokemon1() != null) {
                b.Poke1.lblName.setText(teamDataB.getPokemon1().getNickName());
                PicassoUtils.loadUrl(b.Poke1.imgPokemon, teamDataB.getPokemon1().getImgUrl());
            }
            if (teamDataB.getPokemon2() != null) {
                b.Poke2.lblName.setText(teamDataB.getPokemon2().getNickName());
                PicassoUtils.loadUrl(b.Poke2.imgPokemon, teamDataB.getPokemon2().getImgUrl());
            }
            if (teamDataB.getPokemon3() != null) {
                b.Poke3.lblName.setText(teamDataB.getPokemon3().getNickName());
                PicassoUtils.loadUrl(b.Poke3.imgPokemon, teamDataB.getPokemon3().getImgUrl());
            }
            if (teamDataB.getPokemon4() != null) {
                b.Poke4.lblName.setText(teamDataB.getPokemon4().getNickName());
                PicassoUtils.loadUrl(b.Poke4.imgPokemon, teamDataB.getPokemon4().getImgUrl());
            }
            if (teamDataB.getPokemon5() != null) {
                b.Poke5.lblName.setText(teamDataB.getPokemon5().getNickName());
                PicassoUtils.loadUrl(b.Poke5.imgPokemon, teamDataB.getPokemon5().getImgUrl());
            }
            if (teamDataB.getPokemon6() != null) {
                b.Poke6.lblName.setText(teamDataB.getPokemon6().getNickName());
                PicassoUtils.loadUrl(b.Poke6.imgPokemon, teamDataB.getPokemon6().getImgUrl());
            }
        }
    }
}
