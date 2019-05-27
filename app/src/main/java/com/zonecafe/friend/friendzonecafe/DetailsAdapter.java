package com.zonecafe.friend.friendzonecafe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class DetailsAdapter extends BaseAdapter {
    private Context context;
    private List<Coffee> list;
    private BtnClickListener mClickListener;
    private Coffee coffee = new Coffee();

    public DetailsAdapter(Context context, List<Coffee> list, BtnClickListener btnClickListener) {
        this.context = context;
        this.list = list;
        this.mClickListener = btnClickListener;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Coffee getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_item,parent,false);
        }

        ImageView imageView = convertView.findViewById(R.id.image_view_detail);
        TextView textView = convertView.findViewById(R.id.text_view_detail);
        TextView textViewDescription = convertView.findViewById(R.id.tv_listItemDescription);
        Button button = convertView.findViewById(R.id.btn_addItem);

        coffee = getItem(position);
        imageView.setImageResource(coffee.getImage());
        textView.setText(coffee.getName());
        textViewDescription.setText(coffee.DescItemCoffee().get(position));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mClickListener.onBtnClick(coffee.kindCoffee().get(position), position);
            }
        });
        return convertView;
    }

    public interface BtnClickListener {
        void onBtnClick(String name, int position);
    }

}
