package com.example.precarga.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.precarga.R;
import com.example.precarga.data.Materia;
import com.example.precarga.databinding.MateriaItemBinding;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class MateriaAdapter extends ListAdapter<Materia, MateriaAdapter.MateriaHolder> {

    private static final DiffUtil.ItemCallback<Materia> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Materia>() {
                @Override
                public boolean areItemsTheSame(@NonNull Materia oldItem, @NonNull Materia newItem) {
                    return oldItem.id == newItem.id;
                }

                @Override
                public boolean areContentsTheSame(@NonNull Materia oldItem, @NonNull Materia newItem) {
                    return oldItem.nombre.equals(newItem.nombre);
                }
            };

    public MateriaAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public MateriaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MateriaItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.materia_item, parent, false);

        return new MateriaHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MateriaHolder holder, int position) {
        holder.bindConnection(getItem(position));
        holder.cardItem.setOnClickListener(mOnClickListener);
    }

    private final View.OnClickListener mOnClickListener = view -> ((MaterialCardView)view).toggle();

    class MateriaHolder extends RecyclerView.ViewHolder {
        public CardView cardItem;
        private MateriaItemBinding binding;

        public MateriaHolder(@NonNull MateriaItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            this.cardItem = binding.cardItem;
        }

        public void bindConnection(Materia materia) {
            binding.setMateria(materia);
            binding.executePendingBindings();
        }

    }
}
