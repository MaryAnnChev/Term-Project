package ca.mcgill.ecse321.newftms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ca.mcgill.ecse321.FTMS.controller.FTMSController;
import ca.mcgill.ecse321.FTMS.model.OrderManager;
import ca.mcgill.ecse321.FTMS.model.Supply;
import ca.mcgill.ecse321.FTMS.persistence.PersistenceFTMSOrder;
import ca.mcgill.ecse321.FTMS.persistence.PersistenceXStreamOrder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        PersistenceFTMSOrder.setFilename(getFilesDir().getAbsolutePath() + "Order");
        //PersistenceXStreamOrder.setFilename(getFilesDir().getAbsolutePath() + "Order.xml");
        PersistenceFTMSOrder.loadFTMSInventoryModel();
        //PersistenceXStreamOrder.setFilename(getFilesDir().getAbsolutePath() + "OrderFTMS.xml");
        //PersistenceXStreamOrder.loadFromXMLwithXStream();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initiating various elements of the food truck
        OrderManager om = new OrderManager(); // completely new: old data is gone
        OrderManager.setInstance(om); // to get rid of old saved data
        FTMSController fc = new FTMSController();
        try { // initiating all items since persistence does not work
            fc.createSupply("fries", 10);
            fc.createSupply("cheese", 5);
            fc.createSupply("meat", 5);
            fc.createSupply("hotdog", 5);
            fc.createSupply("bread", 10);

            List<Supply> poutineIngredients = new ArrayList<Supply>(Arrays.asList(om.getFoodSupply("fries"), om.getFoodSupply("cheese")));
            fc.createMenu("poutine", poutineIngredients);

            List<Supply> friesIngredients = new ArrayList<Supply>(Arrays.asList(om.getFoodSupply("fries")));
            fc.createMenu("fries", friesIngredients);

            List<Supply> hotdogIngredients = new ArrayList<Supply>(Arrays.asList(om.getFoodSupply("hotdog"), om.getFoodSupply("bread")));
            fc.createMenu("hotdog", hotdogIngredients);

            List<Supply> meatSandwichIngredients = new ArrayList<Supply>(Arrays.asList(om.getFoodSupply("meat"), om.getFoodSupply("bread")));
            fc.createMenu("meat sandwich", meatSandwichIngredients);

            fc.createEquipment("grill", 1);
            fc.createEquipment("deep fryer", 2);
            fc.createEquipment("knives", 10);
            fc.createEquipment("napkins", 50);
        } catch (Exception e) {}

        ImageButton to_addMenu = (ImageButton) findViewById(R.id.addMenuButton);

        to_addMenu.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, addMenu.class));
            }
        });

        ImageButton to_inventoryMenu = (ImageButton) findViewById(R.id.inventoryMenuButton);

        to_inventoryMenu.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, inventoryMenu.class));
            }
        });
    }
}
