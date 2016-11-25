package ca.mcgill.ecse321.newftms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

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

        ImageButton to_addMenu = (ImageButton) findViewById(R.id.addMenuButton);

        to_addMenu.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, addMenu.class));
            }
        });
    }
}
