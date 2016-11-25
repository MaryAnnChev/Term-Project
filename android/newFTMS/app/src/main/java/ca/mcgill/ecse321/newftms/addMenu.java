package ca.mcgill.ecse321.newftms;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import ca.mcgill.ecse321.FTMS.controller.FTMSController;
import ca.mcgill.ecse321.FTMS.model.OrderManager;
import ca.mcgill.ecse321.FTMS.model.StaffManager;

public class addMenu extends AppCompatActivity {

    OrderManager om;
    StaffManager sm;
    FTMSController fc = new FTMSController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_menu);
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
        Button bn = (Button) findViewById(R.id.newequipment_button);

        try {
            fc.createEquipment(tv1.getText().toString(), Integer.parseInt(tv2.getText().toString()));
            refreshData();
        } catch (Exception e) {
            bn.setError(e.getMessage());
        }

        refreshData();

    }

    public void addSupply(View v) {

        TextView tv1 = (TextView) findViewById(R.id.newsupply_name);
        TextView tv2 = (TextView) findViewById(R.id.newsupply_quantity);
        Button bn = (Button) findViewById(R.id.newsupply_button);

        try {
            fc.createSupply(tv1.getText().toString(), Integer.parseInt(tv2.getText().toString()));
            refreshData();
        } catch (Exception e) {
            bn.setError(e.getMessage());
        }

        refreshData();

    }

    public void addStaff(View v) {

        TextView tv1 = (TextView) findViewById(R.id.newstaff_name);
        TextView tv2 = (TextView) findViewById(R.id.newstaff_role);
        TextView tv3 = (TextView) findViewById(R.id.newstaff_hours);
        Button bn = (Button) findViewById(R.id.newsupply_button);

        try {
            fc.createEmployee(tv1.getText().toString(), tv2.getText().toString(), Integer.parseInt(tv3.getText().toString()));
            refreshData();
        } catch (Exception e) {
            bn.setError(e.getMessage());
        }
        om = OrderManager.getInstance();
        sm = StaffManager.getInstance();
        om.

        refreshData();

    }

}
