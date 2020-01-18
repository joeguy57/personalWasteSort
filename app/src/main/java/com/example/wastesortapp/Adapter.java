package com.example.wastesortapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.viewHolder> {

  private ArrayList<GarbageInfo> list;

  Adapter(ArrayList<GarbageInfo> list) {
    this.list = list;
  }

  @NonNull
  @Override
  public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
    return new viewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull viewHolder holder, int position) {
    holder.nameView.setText(list.get(position).getName());
    holder.colorView.setText(list.get(position).getBinType());
    holder.descriptionView.setText(list.get(position).getDescription());
    switch (list.get(position).getBinType()) {
      case "Others":
        //holder.imageView.setImageDrawable();
        break;
      case "Trash":
        break;
      case "Recyclables":
        break;
      case "Beverages":
        break;
      case "Organics":
        break;
      default:
        break;
    }
  }

  @Override
  public int getItemCount() {
    return (list.size());
  }

  class viewHolder extends RecyclerView.ViewHolder {

    TextView nameView, colorView, descriptionView;
    ImageView imageView;

    public viewHolder(@NonNull View itemView) {
      super(itemView);
      nameView = itemView.findViewById(R.id.itemName);
      colorView = itemView.findViewById(R.id.itemBinType);
      descriptionView = itemView.findViewById(R.id.itemDescription);
      imageView = itemView.findViewById(R.id.binImage);
    }
  }
}


