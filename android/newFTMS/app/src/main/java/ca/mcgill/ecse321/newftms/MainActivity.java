package ca.mcgill.ecse321.newftms;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Iterator;

import ca.mcgill.ecse321.FTMS.controller.FTMSController;
import ca.mcgill.ecse321.FTMS.persistence.PersistenceFTMSOrder;
import ca.mcgill.ecse321.FTMS.persistence.PersistenceXStreamOrder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        PersistenceFTMSOrder.setFilename(getFilesDir().getAbsolutePath() + "Order");
        PersistenceXStreamOrder.setFilename(getFilesDir().getAbsolutePath() + "Order.xml");
        PersistenceFTMSOrder.loadFTMSInventoryModel();
        //PersistenceXStreamOrder.setFilename(getFilesDir().getAbsolutePath() + "OrderFTMS.xml");
        //PersistenceXStreamOrder.loadFromXMLwithXStream();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void refreshData() {
        TextView tvEN = (TextView) findViewById(R.id.newequipment_name);
        tvEN.setText("");
        TextView tvEQ = (TextView) findViewById(R.id.newequipment_quantity);
        tvEQ.setText("");
        TextView tvSN = (TextView) findViewById(R.id.newsupply_name);
        tvSN.setText("");
        TextView tvSQ = (TextView) findViewById(R.id.newsupply_quantity);
        tvSQ.setText("");
        TextView tvST = (TextView) findViewById(R.id.newstaff_role);
        tvST.setText("");



    }

    public void addEquipment(View v) {

        TextView tv1 = (TextView) findViewById(R.id.newequipment_name);
        TextView tv2 = (TextView) findViewById(R.id.newequipment_quantity);

        FTMSController fc = new FTMSController();
        try {
            fc.createEquipment(tv1.getText().toString(), Integer.parseInt(tv2.getText().toString()));
            refreshData();
        } catch (Exception e) {
            tv1.setError(e.getMessage());
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
