package com.anup.pricingbasketsecond;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Anup on 5/22/2018.
 */

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.RecyclerCartViewHolder>{
    private List<CartItemsModel> itemList;
    private Context context;
    private int quantity;
    private CartItemAdapter.OnRecyclerCartViewClickListener onItemClickListener;
    private CartItemAdapter.calculatePriceListener calculatePriceListener;

    public CartItemAdapter( Context context, List<CartItemsModel> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @Override
    public CartItemAdapter.RecyclerCartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_item, null);
        CartItemAdapter.RecyclerCartViewHolder rcv = new CartItemAdapter.RecyclerCartViewHolder(layoutView);

        return rcv;
    }

    @Override
    public void onBindViewHolder(CartItemAdapter.RecyclerCartViewHolder holder, int position) {
        Log.i("Anup", "ChekoutAdapter onBindView "+itemList.get(position).getItemImage());
        holder.imageView.setImageResource(itemList.get(position).getItemImage());
        holder.plistName.setText(itemList.get(position).getItemName());
        holder.plistPrice.setText(""+itemList.get(position).getItemPrice());
        holder.qty.setText(""+itemList.get(position).getQty());
    }

  /*  @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

    }*/

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class RecyclerCartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageView, plus, minus;
        TextView plistName, qty;
        TextView plistPrice;
        //com.anup.pricingbasketsecond.RecyclerViewHolder.OnRecyclerViewClickListener onItemClickListener;
        public RecyclerCartViewHolder(View itemView) {
            super(itemView);
            //itemView.setOnClickListener(this);
            imageView = (ImageView) itemView.findViewById(R.id.list_image);
            plus = (ImageView) itemView.findViewById(R.id.cart_plus_img);
            minus = (ImageView) itemView.findViewById(R.id.cart_minus_img);
            qty = (TextView) itemView.findViewById(R.id.cart_product_quantity_tv);
            plistName = (TextView) itemView.findViewById(R.id.item_name);
            plistPrice = (TextView) itemView.findViewById(R.id.plist_price_text);
            plus.setOnClickListener(this);
            minus.setOnClickListener(this);
        }



        @Override
        public void onClick(View v) {
            //onItemClickListener.onItemClick(v.getContext(), itemList.get(getAdapterPosition()));
            // Toast.makeText(v.getContext(), "ItemClickedATPosition -" + getPosition(), Toast.LENGTH_LONG).show();
            if(v.getId() == R.id.cart_plus_img){
                //onItemClickListener.onPlusClick(v.getContext(), Integer.parseInt(itemList.get(getPosition()).getQty()));
            } else if (v.getId() == R.id.cart_minus_img){
                //onItemClickListener.onMinusClick(v.getContext(), Integer.parseInt(itemList.get(getPosition()).getQty()));
            }
        }


    }

    public void setOnItemClickListener(CartItemAdapter.OnRecyclerCartViewClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setPriceCalculateListener(CartItemAdapter.calculatePriceListener priceCalculateListener) {
        this.calculatePriceListener = priceCalculateListener;
    }

    interface OnRecyclerCartViewClickListener{

        //void onItemClick(Context context, CartItemsModel itemAtPosition);
        //void onMinusClick(Context context, int quantity);
        //void onPlusClick(Context context, int quantity);
        //void calculatePrice(Context context, int quantity, double basePrice);
    }

    interface calculatePriceListener{
       // void calculateTotal(int quantity, double basePrice);
    }
}
