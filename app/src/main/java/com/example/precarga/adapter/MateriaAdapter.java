package com.example.precarga.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.precarga.R;
import com.example.precarga.data.Materia;
import com.example.precarga.databinding.MateriaItemBinding;

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
    }

    class MateriaHolder extends RecyclerView.ViewHolder {
        private MateriaItemBinding binding;

        public MateriaHolder(@NonNull MateriaItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindConnection(Materia materia) {
            binding.setMateria(materia);
            binding.executePendingBindings();
        }
    }
}
