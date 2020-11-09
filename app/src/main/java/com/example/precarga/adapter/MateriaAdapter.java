package com.example.precarga.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.precarga.R;
import com.example.precarga.data.Materia;
import com.example.precarga.databinding.MateriaItemBinding;

import java.util.List;

public class MateriaAdapter extends RecyclerView.Adapter<MateriaAdapter.MateriaViewHolder> {

    private List<Materia> materiaList;

    public MateriaAdapter(List<Materia> materiaList) {
        this.materiaList = materiaList;
    }

    @NonNull
    @Override
    public MateriaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MateriaItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.materia_item, parent, false);
        return new MateriaViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MateriaViewHolder holder, int position) {
        Materia materia = materiaList.get(position);
        holder.bind(materia);
    }

    @Override
    public int getItemCount() {
        return materiaList != null ? materiaList.size() : 0 ;
    }

    public static class MateriaViewHolder extends RecyclerView.ViewHolder{
        private MateriaItemBinding binding;

        public MateriaViewHolder(@NonNull MateriaItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Materia materia){
            binding.setMateria(materia);
            binding.executePendingBindings();
        }
    }
}
