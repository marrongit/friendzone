package com.zonecafe.friend.friendzonecafe;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    int[] sampleImages = {R.drawable.image_1, R.drawable.image_2};
    private int counter = 0;
    private AlertDialog alertDialog;
    private String text;
    private List<Waffles> waffles = new ArrayList<>();
    private List<Coffee> coffees = new ArrayList<>();
    int wafflesCuantity = 0;
    private ArrayList orderList = new ArrayList<>();
    final Waffles waffle = new Waffles();
    int orderId = 0;
    Bundle bundle = new Bundle();
    Intent intent1 = new Intent();
    int waffleItem = 0;
    int itemFlavor = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        final Button btnViewCar = findViewById(R.id.btn_viewCarMain);
        btnViewCar.setVisibility(View.INVISIBLE);

        intent1 = getIntent();
        Bundle extras = intent1.getExtras();
        if (extras != null){
            if(extras.containsKey("counter")) {
                counter = extras.getInt("counter");
                text = getText();
                btnViewCar.setVisibility(View.VISIBLE);
                btnViewCar.setText("Ver pedido con: "+ counter+ " "+ text);
            }

            if(extras.containsKey("bundle")) {
                bundle = intent1.getBundleExtra("bundle");
                coffees = (List<Coffee>) bundle.getSerializable("coffees");
                System.out.println("lista cafes "+coffees);
            }
        }

        CarouselView carouselView;
        GridView gridView;

        carouselView = findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);

        gridView = findViewById(R.id.gridview);
        MenuPrincipalAdapter adapter = new MenuPrincipalAdapter(this);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 1){
                    wafflesMenu(btnViewCar);
                } else {
                    MenuPrincipal item = MenuPrincipal.ITEMS[position];
                    bundle.putSerializable("waffles",(Serializable) waffles);
                    Intent intent = new Intent(view.getContext(),DetailsMenuActivity.class);
                    intent.putExtra("id",item.getId());
                    intent.putExtra("title",item.getTitle());
                    intent.putExtra("bundle",bundle);
                    intent.putExtra("counter",counter);
                    startActivity(intent);
                    finish();
                }
            }
        });

        gridView.setAdapter(adapter);

        btnViewCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),OrdeerActivity.class);
                bundle.putSerializable("coffees",(Serializable) coffees);
                bundle.putSerializable("waffles", (Serializable) waffles);
                intent.putExtra("bundle",bundle);
                startActivity(intent);
            }
        });
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            Picasso.get().load(sampleImages[position]).into(imageView);
        }
    };

    private String getText(){
        return counter > 1 ? "productos" : "producto";
    }

    private void wafflesMenu(final Button btn) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        Toast.makeText(this, "Viene con una bola de helado Napolitano y una porcion de crema chantilly", Toast.LENGTH_LONG).show();
        builder.setTitle("Elija Sabor:");
        builder.setSingleChoiceItems(waffle.getFlavors, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                itemFlavor = which;
            }
        });

        builder.setPositiveButton("Agregar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                wafflesCuantity+=1;
                counter+=1;
                waffles.add(new Waffles(waffleItem,wafflesCuantity));

                //orderId = getOrderNumber(orderList);
                text = getText();
                btn.setText("Ver Carrito con: "+ counter +" "+ text);
                btn.setVisibility(View.VISIBLE);
                Toast.makeText(MainActivity.this,
                        "Se ha agregado 1 Waffle de "+getNameWaffle(itemFlavor) + " a su pedido",
                        Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });

        alertDialog = builder.create();
        alertDialog.show();
    }

    private int getOrderNumber(ArrayList<Order> list){
        int id = 0;
        if(list.size() == 0) return 1;
        for(Order order: list){
            id = order.getId();
        }
        return id +1;
    }

    private String getNameWaffle(int flavor){
        return new Waffles().getFlavors[flavor];
    }
}
