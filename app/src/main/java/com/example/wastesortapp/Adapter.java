package com.example.wastesortapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.viewHolder>{
ArrayList<GarbageInfo> list;
  public Adapter(ArrayList<GarbageInfo> list) {
    this.list = list;
  }

  @NonNull
  @Override
  public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view,parent, false);
    return new viewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull viewHolder holder, int position) {
    holder.nameView.setText(list.get(position).getName());
    holder.colorView.setText(list.get(position).getColor());
  }

  @Override
  public int getItemCount() {
    return list.size();
  }

  class viewHolder extends RecyclerView.ViewHolder{
TextView nameView, colorView;
    public viewHolder(@NonNull View itemView) {
      super(itemView);
      nameView = itemView.findViewById(R.id.itemName);
      colorView = itemView.findViewById(R.id.itemDescripton);
    }
  }
}


