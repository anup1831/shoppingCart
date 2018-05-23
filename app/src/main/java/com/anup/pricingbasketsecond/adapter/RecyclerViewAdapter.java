package com.anup.pricingbasketsecond.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.anup.pricingbasketsecond.R;
import com.anup.pricingbasketsecond.models.ItemGridViewObject;

import java.util.List;

/**
 * Created by Anup on 5/20/2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {
    private List<ItemGridViewObject> itemList;
    private Context context;
    private OnRecyclerViewClickListener onItemClickListener;

    public RecyclerViewAdapter( Context context, List<ItemGridViewObject> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid_view, null);
        RecyclerViewHolder rcv = new RecyclerViewHolder(layoutView);

        return rcv;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.imageView.setImageResource(itemList.get(position).getImageView());
        holder.textView.setText(itemList.get(position).getName());
        holder.textviewPrice.setText(""+itemList.get(position).getPrice());
    }

  /*  @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

    }*/

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageView;
        TextView textView;
        TextView textviewPrice;
        //com.anup.pricingbasketsecond.RecyclerViewHolder.OnRecyclerViewClickListener onItemClickListener;
        public RecyclerViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            imageView = (ImageView) itemView.findViewById(R.id.iv_item_image);
            textView = (TextView) itemView.findViewById(R.id.tv_item_name);
            textviewPrice = (TextView) itemView.findViewById(R.id.tv_item_price);
        }



        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(v.getContext(), itemList.get(getAdapterPosition()));
            // Toast.makeText(v.getContext(), "ItemClickedATPosition -" + getPosition(), Toast.LENGTH_LONG).show();
        }


    }

    public void setOnItemClickListener(OnRecyclerViewClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    interface OnRecyclerViewClickListener{

        void onItemClick(Context context, ItemGridViewObject itemAtPosition);
    }
}
