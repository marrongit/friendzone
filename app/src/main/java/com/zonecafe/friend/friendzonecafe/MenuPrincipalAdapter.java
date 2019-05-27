package com.zonecafe.friend.friendzonecafe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuPrincipalAdapter extends BaseAdapter {

    private final Context context;

    public MenuPrincipalAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return MenuPrincipal.ITEMS.length;
    }

    @Override
    public MenuPrincipal getItem(int position) {
        return MenuPrincipal.ITEMS[position];
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.grid_item,parent,false);
        }

        ImageView imagePto = convertView.findViewById(R.id.imagen_pto);
        TextView imageText = convertView.findViewById(R.id.nombre_pto);

        final MenuPrincipal item = getItem(position);
        imagePto.setImageResource(item.getImage());
        imageText.setText(item.getTitle());
        return convertView;
    }
}
