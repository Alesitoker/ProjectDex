package es.saladillo.alejandrodiaz.projectdex.ui.teamCreator.listTeam;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import es.saladillo.alejandrodiaz.projectdex.data.local.model.Pokemon;
import es.saladillo.alejandrodiaz.projectdex.data.local.model.Team;
import es.saladillo.alejandrodiaz.projectdex.databinding.FragmentTeamsItemBinding;
import es.saladillo.alejandrodiaz.projectdex.ui.OnSelectItemClickListener;

public class ListTeamsFragmentAdapter extends ListAdapter<Team, ListTeamsFragmentAdapter.ViewHolder> implements Filterable {

    private OnSelectItemClickListener onSelectItemClickListener;
    private List<Team> dataList;
    private List<Team> filteredList;

    protected ListTeamsFragmentAdapter() {
        super(new DiffUtil.ItemCallback<Team>() {
            @Override
            public boolean areItemsTheSame(@NonNull Team oldItem, @NonNull Team newItem) {
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull Team oldItem, @NonNull Team newItem) {
                return TextUtils.equals(oldItem.getTeamName(), newItem.getTeamName());
            }
        });
    }

    @Override
    public void submitList(List<Team> list) {
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
    protected Team getItem(int position) {
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
                    List<Team> list = new ArrayList<>();
                    for (Team team : dataList) {
                        if (includeInFilter(team, filterString)) {
                            list.add(team);
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
                filteredList = (List<Team>) results.values;
                updateList(filteredList);
            }
        };
    }

    private void updateList(List<Team> list) {
        super.submitList(list);
    }

    public boolean includeInFilter(Team item, String filterString) {
        return item.getTeamName().toLowerCase().contains(filterString.toLowerCase());
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final FragmentTeamsItemBinding b;

        public ViewHolder(@NonNull FragmentTeamsItemBinding b) {
            super(b.getRoot());
            this.b = b;
        }

        public void bind(Team item) {
            b.lblTeamName.setText(item.getTeamName());
        }
    }
}
