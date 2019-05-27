package com.zonecafe.friend.friendzonecafe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class OrdeerActivity extends AppCompatActivity {

    private OrderAdapter adapter;
    private List<Order> orderList = new ArrayList<>();
    private ListView listView;
    private List<Coffee> coffees = new ArrayList<>();
    private List<Waffles> waffles = new ArrayList<>();
    private int cuantity0;
    private int cuantity1;
    private int cuantity2;
    private int cuantity3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordeer);
        setBindElements();
        setIntentValues();
        setOrderPts();
        adapter = new OrderAdapter(this,orderList);
        listView.setAdapter(adapter);
    }

    private void adminCoffees(List<Coffee> coffees){
            resetCuantity();
            int price0 = 0;
            int price1 = 0;
            int price2 = 0;
            int price3 = 0;

            for (Coffee coffee: coffees){
                switch (coffee.getId()){
                    case 0:
                        price0 += 35;
                        cuantity0 += 1;

                        break;
                    case 1:
                        price1 += 37;
                        cuantity1 += 1;

                        break;
                    case 2:
                        price2 += 40;
                        cuantity2 += 1;

                    case 3:
                        price3 += 45;
                        cuantity3 += 1;

                }
            }

            coffees.clear();

            List<String> listCoffee = new Coffee().kindCoffee();

            if(price0 != 0) setCoffees(coffees,price0,cuantity0,listCoffee,0);
            if(price1 != 0) setCoffees(coffees,price0,cuantity0,listCoffee,1);
            if(price2 != 0) setCoffees(coffees,price0,cuantity0,listCoffee,2);
            if(price3 != 0) setCoffees(coffees,price0,cuantity0,listCoffee,3);
                /*coffees.add(new Coffee(3, listCoffee.get(3), price3, "", 0, cuantity3));
                orderList.add(new Order(1,
                        "Café ",
                        coffees.get(3).getName(),
                        coffees.get(3).getCuantity(),
                        coffees.get(3).getPrice()));*/
    }

    private void adminWaffles(List<Waffles> waffles){
        resetCuantity();
        int price = 35;
        List<Waffles> wafflesList = new ArrayList<>();
        Waffles w = new Waffles();
        String[] flavors;

        for (Waffles waffle: waffles) {
            switch (waffle.getFlavor()) {
                case 0:
                    cuantity0 += 1;
                    break;
                case 1:
                    cuantity1 += 1;
                    break;
                case 2:
                    cuantity2 += 1;
                    break;
                case 3:
                    cuantity3 += 1;
                    break;

            }
        }

            flavors = w.getFlavors;

            if(cuantity0 != 0) setWaffles(wafflesList, price, cuantity0, flavors, 0);
            if(cuantity1 != 0) setWaffles(wafflesList, price, cuantity0, flavors, 1);
            if(cuantity2 != 0) setWaffles(wafflesList, price, cuantity0, flavors, 2);
            if(cuantity3 != 0) setWaffles(wafflesList, price, cuantity0, flavors, 3);
                /*price = 35 * cuantity3;
                wafflesList.add(new Waffles(3,cuantity3));
                orderList.add(new Order(1,
                        "Waffle ",
                        flavors[wafflesList.get(3).getFlavor()],
                        wafflesList.get(3).getCuantity(),
                        price));
                price = 0;*/
    }

    private void setWaffles(List<Waffles> wafflesList,
                            int price,
                            int cuantity,
                            String[] flavors,
                            int flavor){
        wafflesList.add(new Waffles(flavor,cuantity));
        price *= cuantity;
        orderList.add(new Order(1,
                "Waffle ",
                flavors[wafflesList.get(flavor).getFlavor()],
                wafflesList.get(flavor).getCuantity(),
                price));
    }

    private void setCoffees(List<Coffee> coffeesList,
                            int price,
                            int cuantity,
                            List<String> flavors,
                            int flavor){
        coffeesList.add(new Coffee(flavor, flavors.get(flavor), price, "", 0, cuantity));
        orderList.add(new Order(1,
                "",
                coffeesList.get(flavor).getName(),
                coffeesList.get(flavor).getCuantity(),
                coffeesList.get(flavor).getPrice()));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setBindElements(){
        Toolbar toolbar = findViewById(R.id.toolbar_order);
        listView = findViewById(R.id.lvOrder);
        setToolbar(toolbar);
    }

    private void setToolbar(Toolbar toolbar){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Orden Actual");
    }

    private void setIntentValues(){
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        coffees = (List<Coffee>) bundle.getSerializable("coffees");
        waffles = (List<Waffles>) bundle.getSerializable("waffles");
    }

    private void setOrderPts(){
        try {
            if (coffees != null){
                adminCoffees(coffees);
            }
            if (waffles != null){
                adminWaffles(waffles);
            }
        } catch (Exception e){
            System.out.println("error Ordeer activity: "+ e.getMessage());
        }
    }

    private void resetCuantity(){
        cuantity0 = 0;
        cuantity1 = 0;
        cuantity2 = 0;
        cuantity3 = 0;
    }
}
