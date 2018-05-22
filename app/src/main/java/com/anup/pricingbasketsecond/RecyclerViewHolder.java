/*
package com.anup.pricingbasketsecond;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

*/
/**
 * Created by Anup on 5/20/2018.
 *//*


public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    ImageView imageView;
    TextView textView;
    TextView tvItemPrice;
    OnRecyclerViewClickListener onItemClickListener;
    public RecyclerViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        imageView = (ImageView) itemView.findViewById(R.id.iv_item_image);
        textView = (TextView) itemView.findViewById(R.id.tv_item_name);
        tvItemPrice = (TextView) itemView.findViewById(R.id.tv_item_price);
    }

    public void setOnItemClickListener(OnRecyclerViewClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public void onClick(View v) {
        onItemClickListener.onItemClick(v.getContext(), getAdapterPosition());
       // Toast.makeText(v.getContext(), "ItemClickedATPosition -" + getPosition(), Toast.LENGTH_LONG).show();
    }

        interface OnRecyclerViewClickListener{

            void onItemClick(Context context, int position);
        }
}
*/
