package com.zonecafe.friend.friendzonecafe;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DetailsMenuActivity extends AppCompatActivity {

    private ArrayList list;
    private DetailsAdapter adapter;

    private Toolbar toolbar;
    private GridView gridView;
    private Button button;

    int counter = 0;
    String text;
    Bundle bundle = new Bundle();
    Intent intent = new Intent();
    List<Coffee> coffees = new ArrayList<>();
    List<Waffles> waffles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_menu);
        setBindElements();
        setIntentValues();

        list = getItemsCoffee();
        adapter = new DetailsAdapter(this, list, new DetailsAdapter.BtnClickListener() {
            @Override
            public void onBtnClick(String name, int kindPt) {
                Toast.makeText(DetailsMenuActivity.this,"Se ha agregado 1 " + name+" al pedido",Toast.LENGTH_LONG).show();
                counter += 1;
                text = getText();
                int price = priceCoffe(kindPt);
                button.setText("Ver Pedido con : "+counter+ " "+ text);
                coffees.add(new Coffee(kindPt,name,price,"",0,counter));
            }
        });
        gridView.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    bundle.putSerializable("coffees",(Serializable) coffees);
                    bundle.putSerializable("waffles", (Serializable) waffles);
                    Intent intent = new Intent(getApplicationContext(),OrdeerActivity.class);
                    intent.putExtra("bundle", bundle);
                    startActivity(intent);
                } catch (Exception e){
                    System.out.println("Error to orderActivity : "+ e.getMessage());
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if (android.R.id.home == item.getItemId()) {
            intent = new Intent(getApplicationContext(),MainActivity.class);
            bundle.putSerializable("coffees", (Serializable) coffees);
            if (getCounter()){
                intent.putExtra("counter",counter);
            }
            intent.putExtra("bundle",bundle);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private ArrayList<Coffee> getItemsCoffee(){
        return new ArrayList<Coffee>(){{
            add(new Coffee(1,"Negro",35,"",R.drawable.image_1,0));
            add(new Coffee(2,"Capucchino",40,"",R.drawable.image_1,0));
        }};
    }

    private String getText(){
        return counter > 1 ? "productos" : "producto";
    }

    private boolean getCounter(){
        return counter >= 1 ? true: false;
    }

    private int priceCoffe(int kind){
        switch (kind){
            case 0:
                return 30; // black

            case 1:
                return 35; // latte

            case 2:
                return 40; // mocha

            case 3:
                return 37; // capucchino

        }
        return 0;
    }

    private void setBindElements(){
        toolbar = findViewById(R.id.toolbar);
        gridView = findViewById(R.id.gridview_detail);
        button = findViewById(R.id.btn_viewCar);

        setToolbar(toolbar);
    }

    private void setToolbar(Toolbar toolbar){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getIntent().getStringExtra("title"));
        getSupportActionBar().setLogo(R.mipmap.ic_logo_foreground);
    }

    private void setIntentValues(){
        intent = getIntent();
        //final int menuId = intent.getIntExtra("id",0);
        bundle = intent.getExtras();
        if (bundle != null){
            if(bundle.containsKey("counter")) {
                counter = bundle.getInt("counter");
                text = getText();
                button.setVisibility(View.VISIBLE);
                button.setText("Ver pedido con: "+ counter+ " "+ text);
            }

            if(bundle.containsKey("bundle")) {
                bundle = intent.getBundleExtra("bundle");
                coffees = (List<Coffee>) bundle.getSerializable("coffees");
                waffles = (List<Waffles>) bundle.getSerializable("waffles");

                if (coffees == null){
                    coffees = new ArrayList<>();
                }

                if (waffles == null){
                    waffles = new ArrayList<>();
                }
            }
        }
    }
}
