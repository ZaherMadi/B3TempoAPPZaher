package com.example.b3tempoappzaher;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.b3tempoappzaher.TempoDaysColor;
import com.example.b3tempoappzaher.databinding.TempoDateItemBinding;

import androidx.annotation.NonNull;
import androidx.constraintlayout.helper.widget.Layer;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TempoDateAdapter extends RecyclerView.Adapter<TempoDateAdapter.TempoDateViewHolder> {

    private List<TempoDate> tempoDates;
    private Context context;

    /*
     * CTor
     */
    public TempoDateAdapter(List<TempoDate> tempoDates, Context context) {
        this.tempoDates = tempoDates;
        this.context = context;
    }

    @NonNull
    @Override
    public TempoDateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TempoDateViewHolder(TempoDateItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
        //View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.tempo_date_item, parent, false);
        //return new TempoDateViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TempoDateViewHolder holder, int position) {
        holder.binding.textView.setText(tempoDates.get(position).getDate());
        holder.binding.frameLayout.setBackgroundColor(ContextCompat.getColor(context, tempoDates.get(position).getCouleur().getColorResId()));
    }

    @Override
    public int getItemCount() {
        return tempoDates.size();
    }

    public class TempoDateViewHolder extends RecyclerView.ViewHolder {
        TempoDateItemBinding binding;

        public TempoDateViewHolder(TempoDateItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
