/**
 * This class will take the information from GarbageInfo and gits it into a list. it converts it to
 * a cardView to be displayed in UI.
 *
 *
 * created: 16/01/2019
 * Completed and ready MVP
 */
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

  private final ArrayList<GarbageInfo> list;
  //--------------------------------------------------------------------------------------------
  Adapter(ArrayList<GarbageInfo> list) {
    this.list = list;
  }

  @NonNull
  @Override
  public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    // view in constraint is assigned to cardViews
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.card_view, parent, false);
    return new viewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull viewHolder holder, int position) {
    //Setting the textViews in cardView.xml to it's appropriate value.
    holder.nameView.setText(list.get(position).getName());
    holder.binTypeView.setText("Bin: " + list.get(position).getBinType());
    holder.descriptionView.setText("Description: " + list.get(position).getDescription());
    //Switch cases to assign the bins.
    switch (list.get(position).getBinType()) {
      case "Other":
        holder.imageView.setImageResource(R.drawable.other);
        break;
      case "Landfill":
        holder.imageView.setImageResource(R.drawable.trash);
        break;
      case "Recyclable":
        holder.imageView.setImageResource(R.drawable.recyclables);
        break;
      case "Beverage":
        holder.imageView.setImageResource(R.drawable.beverages);
        break;
      case "Organic":
        holder.imageView.setImageResource(R.drawable.organics);
        break;
      default:
        break;
    }
  }

  @Override
  public int getItemCount() {
    return (list.size());
  }

  /**
   * this class will allow for the information to be used for the cardView.
   */
  class viewHolder extends RecyclerView.ViewHolder {

    final TextView nameView;
    final TextView binTypeView;
    final TextView descriptionView;
    final ImageView imageView;

    /**
     * This function assigns global variables to be ready to get set in the Card View.
     * @param itemView- the CardView to be used in the constraint layout.
     */
    viewHolder(@NonNull View itemView) {
      super(itemView);
      nameView = itemView.findViewById(R.id.itemName);
      binTypeView = itemView.findViewById(R.id.itemBinType);
      descriptionView = itemView.findViewById(R.id.itemDescription);
      imageView = itemView.findViewById(R.id.binImage);
    }
  }
}


