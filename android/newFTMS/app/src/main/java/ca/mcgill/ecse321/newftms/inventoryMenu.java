package ca.mcgill.ecse321.newftms;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import ca.mcgill.ecse321.FTMS.controller.FTMSController;
import ca.mcgill.ecse321.FTMS.model.Equipment;
import ca.mcgill.ecse321.FTMS.model.Menu;
import ca.mcgill.ecse321.FTMS.model.OrderManager;
import ca.mcgill.ecse321.FTMS.model.Supply;

public class inventoryMenu extends AppCompatActivity {

    private FTMSController fc = new FTMSController();
    private String equipmentSelected;
    private String dishSelected;
    private String supplySelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_menu);

        OrderManager om = OrderManager.getInstance();

        // Initiate equipment spinner
        TextView t_e = (TextView) findViewById(R.id.equipment_inventory_label);
        t_e.setTextColor(Color.rgb(0, 0, 200));
        Spinner equipment = (Spinner)findViewById(R.id.equipment_spinner);
        int numberOfE = om.getEquipments().size();
        final String[] itemsE = new String[numberOfE];
        final String[] displayedE = new String[numberOfE];
        for (int i=0; i<numberOfE; i++) {
            itemsE[i] = om.getEquipment(i).getEquipmentName();
            displayedE[i] = om.getEquipment(i).getEquipmentName() + "   (" + om.getEquipment(i).getEquipmentQty() + " left)";
        }
        ArrayAdapter<String> adapterE = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, displayedE);
        equipment.setAdapter(adapterE);
        equipment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                equipmentSelected = itemsE[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // avoids error
            }
        });

        // Initiate dishes spinner
        TextView t_d = (TextView) findViewById(R.id.dishes_inventory_label);
        t_d.setTextColor(Color.rgb(0, 0, 200));
        Spinner dishes = (Spinner)findViewById(R.id.dishes_spinner);
        int numberOfD = om.getMenus().size();
        final String[] itemsD = new String[numberOfD];
        final String[] displayedD = new String[numberOfD];
        for (int i=0; i<numberOfD; i++) {
            itemsD[i] = om.getMenu(i).getMealName();
            displayedD[i] = om.getMenu(i).getMealName() + "   (popularity " + om.getMenu(i).getPopularity() + ")";
        }
        ArrayAdapter<String> adapterD = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, displayedD);
        dishes.setAdapter(adapterD);
        dishes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                dishSelected = itemsD[position];
                updateDishInfo();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // avoids error
            }
        });

        // Initiate supplies spinner
        TextView t_s = (TextView) findViewById(R.id.supplies_inventory_label);
        t_s.setTextColor(Color.rgb(0, 0, 200));
        Spinner supplies = (Spinner)findViewById(R.id.supplies_spinner);
        int numberOfS = om.getEquipments().size();
        final String[] itemsS = new String[numberOfS];
        final String[] displayedS = new String[numberOfS];
        for (int i=0; i<numberOfS; i++) {
            itemsS[i] = om.getFoodSupply(i).getFoodName();
            displayedS[i] = om.getFoodSupply(i).getFoodName() + "   (" + om.getFoodSupply(i).getFoodQty() + " left)";
        }
        ArrayAdapter<String> adapterS = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, displayedS);
        supplies.setAdapter(adapterS);
        supplies.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                supplySelected = itemsS[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // avoids error
            }
        });
    }

    // Equipment functions ****
    private void updateEquipmentSpinner(int spinnerPosition) {
        OrderManager om = OrderManager.getInstance();

        Spinner equipment = (Spinner)findViewById(R.id.equipment_spinner);
        int numberOfItems = om.getEquipments().size();
        String[] displayed = new String[numberOfItems];
        for (int i=0; i<numberOfItems; i++) {
            displayed[i] = om.getEquipment(i).getEquipmentName() + "   (" + om.getEquipment(i).getEquipmentQty() + " left)";
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, displayed);
        equipment.setAdapter(adapter);
        equipment.setSelection(spinnerPosition);
    }

    public void increaseEquipment(View v) {
        OrderManager om = OrderManager.getInstance();
        Equipment e = om.getEquipment(equipmentSelected);
        e.setEquipmentQty(e.getEquipmentQty()+1);
        int position = 0;
        for (int i=0; i<om.getEquipments().size(); i++) {
            if (e == om.getEquipment(i))
                position = i;
        }
        updateEquipmentSpinner(position);
    }

    public void decreaseEquipment(View v) {
        OrderManager om = OrderManager.getInstance();
        Equipment e = om.getEquipment(equipmentSelected);
        if (e.getEquipmentQty() > 0) {
            e.setEquipmentQty(e.getEquipmentQty()-1);
            int position = 0;
            for (int i=0; i<om.getEquipments().size(); i++) {
                if (e == om.getEquipment(i))
                    position = i;
            }
            updateEquipmentSpinner(position);
        }
    }
    // ****

    // Dishes functions ****
    private void updateDishSpinner(int spinnerPosition) {
        OrderManager om = OrderManager.getInstance();

        Spinner dish = (Spinner)findViewById(R.id.dishes_spinner);
        int numberOfItems = om.getMenus().size();
        String[] displayed = new String[numberOfItems];
        for (int i=0; i<numberOfItems; i++) {
            displayed[i] = om.getMenu(i).getMealName() + "   (popularity " + om.getMenu(i).getPopularity() + ")";
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, displayed);
        dish.setAdapter(adapter);
        dish.setSelection(spinnerPosition);
    }

    public void updateDishInfo() {
        OrderManager om = OrderManager.getInstance();
        Menu myDishSelected = om.getMenu(dishSelected);
        TextView dishesLeft = (TextView) findViewById(R.id.dishes_left_text);
        dishesLeft.setText(myDishSelected.getMealsLeft() + " dishes of " + myDishSelected.getMealName() + " remaining.");
        TextView dishIngredients = (TextView) findViewById(R.id.dishes_ingredients_text);
        String ingredients = myDishSelected.getMealName() + " is made of ";
        for (int i=0; i<myDishSelected.getIngredients().size(); i++) {
            ingredients += myDishSelected.getIngredients().get(i).getFoodName();
            if (i == myDishSelected.getIngredients().size()-1)
                ingredients += ".";
            else if (i == myDishSelected.getIngredients().size()-2)
                ingredients += " and ";
            else
                ingredients += ", ";
        }
        dishIngredients.setText(ingredients);
    }

    public void placeOrder(View v) {
        Button order = (Button) findViewById(R.id.order_button);
        OrderManager om = OrderManager.getInstance();
        int position = 0;
        try {
            fc.placeOrder(dishSelected);
            for (int i=0; i<om.getMenus().size(); i++) {
                if (om.getMenu(dishSelected) == om.getMenu(i))
                    position = i;
            }
        } catch (Exception e) {
            order.setError(e.getMessage());
        }
        updateDishSpinner(position);
        updateSuppliesSpinner(0);
    }
    // ****

    // Supplies functions ****
    private void updateSuppliesSpinner(int spinnerPosition) {
        OrderManager om = OrderManager.getInstance();

        Spinner supplies = (Spinner)findViewById(R.id.supplies_spinner);
        int numberOfItems = om.getFoodSupplies().size();
        String[] displayed = new String[numberOfItems];
        for (int i=0; i<numberOfItems; i++) {
            displayed[i] = om.getFoodSupply(i).getFoodName() + "   (" + om.getFoodSupply(i).getFoodQty() + " left)";
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, displayed);
        supplies.setAdapter(adapter);
        supplies.setSelection(spinnerPosition);
    }

    public void increaseSupply(View v) {
        OrderManager om = OrderManager.getInstance();
        Supply s = om.getFoodSupply(supplySelected);
        s.setFoodQty(s.getFoodQty()+1);
        int position = 0;
        for (int i=0; i<om.getFoodSupplies().size(); i++) {
            if (s == om.getFoodSupply(i))
                position = i;
        }
        updateSuppliesSpinner(position);
        updateDishSpinner(0);
    }

    public void decreaseSupply(View v) {
        OrderManager om = OrderManager.getInstance();
        Supply s = om.getFoodSupply(supplySelected);
        if (s.getFoodQty() > 0) {
            s.setFoodQty(s.getFoodQty()-1);
            int position = 0;
            for (int i=0; i<om.getFoodSupplies().size(); i++) {
                if (s == om.getFoodSupply(i))
                    position = i;
            }
            updateSuppliesSpinner(position);
            updateDishSpinner(0);
        }
    }
    // ****
}
