package com.example.precarga.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.precarga.R;
import com.example.precarga.data.models.Materia;
import com.example.precarga.databinding.MateriaItemBinding;
import com.google.android.material.card.MaterialCardView;

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
//        this.mTotalCredit = new MutableLiveData<>();
//        this.mTotalCredit.setValue(0);
    }

    @NonNull
    @Override
    public MateriaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MateriaItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.materia_item, parent, false);

        return new MateriaHolder(binding);
    }

    public int totalCredit;
//    private MutableLiveData<Integer> mTotalCredit;

//    public LiveData<Integer> getTotalCredit() {
//        return mTotalCredit;
//    }

    @Override
    public void onBindViewHolder(@NonNull MateriaHolder holder, int position) {
        Materia materia = getItem(position);

        holder.bindConnection(materia);
        holder.cardItem.setOnClickListener(view -> {
            ((MaterialCardView) view).toggle();
            totalCredit += materia.creditos;
//            mTotalCredit.setValue(totalCredit);

            Log.d("HEY HEY!", "onClick: + " + materia.creditos + " creditos");
//            Log.d("HEY HEY!", "total:  " + getTotalCredit() + " creditos");
        });

    }

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
