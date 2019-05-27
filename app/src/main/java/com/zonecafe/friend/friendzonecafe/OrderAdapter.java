package com.zonecafe.friend.friendzonecafe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class OrderAdapter extends BaseAdapter {
    private Context context;
    private List<Order> orderList;

    public OrderAdapter(Context context, List<Order> orderList){
        this.context = context;
        this.orderList = orderList;
    }

    @Override
    public int getCount() {
        return this.orderList.size();
    }

    @Override
    public Order getItem(int i) {
        return orderList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.list_item_order, viewGroup,false);
        }

        TextView textViewName = view.findViewById(R.id.tv_order_name);
        TextView textViewPrice = view.findViewById(R.id.tv_order_price);
        TextView textViewCuantity = view.findViewById(R.id.tv_order_cuantity);

        textViewName.setText(orderList.get(i).getName() + " "+ orderList.get(i).getFlavor());
        textViewCuantity.setText(String.valueOf(orderList.get(i).getPrice()));
        textViewPrice.setText(String.valueOf(orderList.get(i).getCuantity()));
        i++;
        return view;
    }
}
