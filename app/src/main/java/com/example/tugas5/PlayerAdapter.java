package com.example.tugas5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugas5.databinding.ItemPlayerBinding;
import com.example.tugas5.entity.Player;

import java.util.List;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.ViewHolder> {
    private static final String TAG = PlayerAdapter.class.getSimpleName();

    private Context context;
    private List<Player> list;
    private PlayerAdapterCallback playerAdapterCallback;
    private ItemPlayerBinding binding;

    public PlayerAdapter(Context context, List<Player> list, PlayerAdapterCallback adapterCallback) {
        this.context = context;
        this.list = list;
        this.playerAdapterCallback = adapterCallback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        binding = ItemPlayerBinding.inflate(LayoutInflater
                .from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Player player = list.get(position);
        holder.bindData(player);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void clear() {
        int size = this.list.size();
        this.list.clear();
        notifyItemRangeRemoved(0, size);
    }

    public void addData(List<Player> players){
        this.list = players;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ViewHolder(@NonNull ItemPlayerBinding itemView) {
            super(itemView.getRoot());

            binding.ivEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Player player = list.get(getAdapterPosition());
                    playerAdapterCallback.onEdit(player);
                }
            });


            binding.ivDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Player player = list.get(getAdapterPosition());
                    playerAdapterCallback.onDelete(player);
                }
            });

        }

        void bindData(Player item) {
            Integer number = item.number;
            binding.tvNumber.setText(number.toString());

            String name = item.name;
            binding.tvName.setText(name);

            String pos = item.position;
            binding.tvPosition.setText(pos);

            Integer age = item.age;
            binding.tvAge.setText(age.toString());
        }
    }

    public interface PlayerAdapterCallback {
        void onEdit(Player player);
        void onDelete(Player player);
    }
}
