package ca.mcgill.ecse321.ftms;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;

import ca.mcgill.ecse321.FTMS.controller.OrderController;
import ca.mcgill.ecse321.FTMS.model.FTMSManager;
import ca.mcgill.ecse321.FTMS.model.MenuItem;
import ca.mcgill.ecse321.FTMS.model.Order;
import ca.mcgill.ecse321.FTMS.persistence.PersistenceFTMS;
import ca.mcgill.ecse321.FTMS.persistence.PersistenceXStream;

public class MainActivity extends AppCompatActivity {


    /*List<Item> newItems = databaseHandler.getItems();
    ListArrayAdapter.clear();
    ListArrayAdapter.addAll(newItems);
    ListArrayAdapter.notifyDataSetChanged();
    databaseHandler.close();*/

    ArrayList<String> allMenuItems = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        PersistenceXStream.setFilename(getFilesDir().getAbsolutePath() + "/ftms.xml");
        PersistenceXStream.loadFromXMLwithXStream(); //PersistenceFTMS.loadFTMSModel();

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

        ListView mil = (ListView)findViewById(R.id.menuitemlist);
        mil.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        String[] menuItems = {"Poutine", "Fries", "Hot Dog", "Smoked Meat Sandwich"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.menuitemcheck, R.id.menuitemcheckbox, menuItems);
        mil.setAdapter(adapter);

        ListAdapter listAdapter = mil.getAdapter();
        int numberOfItems = listAdapter.getCount();
        int totalItemsHeight = 0;
        for (int itemPos = 0; itemPos < numberOfItems; itemPos++) {
            View item = listAdapter.getView(itemPos, null, mil);
            item.measure(0, 0);
            totalItemsHeight += item.getMeasuredHeight();
        }
        int totalDividersHeight = mil.getDividerHeight() *
                (numberOfItems - 1);

        // Set list height.
        ViewGroup.LayoutParams params = mil.getLayoutParams();
        params.height = totalItemsHeight + totalDividersHeight;
        mil.setLayoutParams(params);
        mil.requestLayout();

        mil.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = ((TextView)view).getText().toString();
                if(allMenuItems.contains(selectedItem))
                    allMenuItems.remove(selectedItem);
                else
                    allMenuItems.add(selectedItem);
            }
        });
    }

    public void placeMyOrder(View v) {
        TextView tv = (TextView) findViewById(R.id.placingorder_text);
        try {
            OrderController.placeOrder(getSelectedMenuItems());
        } catch (Exception e) {
            tv.setError(e.getMessage());
        }

        // update visuals
        refreshData();
    }

    private ArrayList<MenuItem> getSelectedMenuItems() {
        ArrayList<MenuItem> items = new ArrayList<MenuItem>();

        for (String item : allMenuItems) {
            items.add(OrderController.FromStringToMenuItem(item));
        }

        return items;
    }

    public void addMenuItem(View v) {
        refreshData();
    }

    public void addEquipment(View v) {
        TextView tv1 = (TextView) findViewById(R.id.newequipment_name);
        TextView tv2 = (TextView) findViewById(R.id.newequipment_quantity);

        F
        try {
            fc.createEquipment(tv1.getText().toString(), Integer.parseInt(tv2.getText().toString()));
            refreshData();
        } catch (Exception e) {
            tv1.setError(e.getMessage());
        }

        refreshData();
    }

    public void addSupply(View v) {
        refreshData();
    }

    public void addStaff(View v) {
        refreshData();
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

        FTMSManager ftms = FTMSManager.getInstance();

        // remove this once fixed
        if (ftms.getMenu() != null) {
            Iterator<MenuItem> mIt = ftms.getMenu().getMenuItems().iterator();
            allMenuItems.clear();
            while (mIt.hasNext()) {
                MenuItem m = mIt.next();
                allMenuItems.add(m.getName());
            }
        }

        // UPDATE LISTVIEW
        ListView mil = (ListView)findViewById(R.id.menuitemlist);
        mil.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        String[] menuItems = new String[allMenuItems.size()];
        for(int i=0; i<allMenuItems.size(); i++) {
            menuItems[i] = allMenuItems.get(i);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.menuitemcheck, R.id.menuitemcheckbox, menuItems);
        mil.setAdapter(adapter);

        ListAdapter listAdapter = mil.getAdapter();
        int numberOfItems = listAdapter.getCount();
        int totalItemsHeight = 0;
        for (int itemPos = 0; itemPos < numberOfItems; itemPos++) {
            View item = listAdapter.getView(itemPos, null, mil);
            item.measure(0, 0);
            totalItemsHeight += item.getMeasuredHeight();
        }
        int totalDividersHeight = mil.getDividerHeight() *
                (numberOfItems - 1);

        // Set list height.
        ViewGroup.LayoutParams params = mil.getLayoutParams();
        params.height = totalItemsHeight + totalDividersHeight;
        mil.setLayoutParams(params);
        mil.requestLayout();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    /*private HashMap<Integer, Participant> participants;
    private HashMap<Integer, Event> events;
    RegistrationManager rm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

        refreshData();
    }

    private void refreshData() {
        TextView tvP = (TextView) findViewById(R.id.newparticipant_name);
        tvP.setText("");
        TextView tvE = (TextView) findViewById(R.id.newevent_name);
        tvE.setText("");


        //TODO: anything else to add here?

        rm = RegistrationManager.getInstance();

        // Initialize the data in the participant spinner
        Spinner pSpinner = (Spinner) findViewById(R.id.participantspinner);
        ArrayAdapter<CharSequence> participantAdapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item);
        participantAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.participants = new HashMap<Integer, Participant>();

        int i = 0;
        for (Iterator<Participant> participants = rm.getParticipants().iterator(); // registration manager
             participants.hasNext(); i++) {
            Participant p = participants.next();
            participantAdapter.add(p.getName());
            this.participants.put(i, p);
        }
        pSpinner.setAdapter(participantAdapter);

        // Initialize the data in the event spinner
        Spinner eSpinner = (Spinner) findViewById(R.id.eventspinner);
        ArrayAdapter<CharSequence> eventAdapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item);
        eventAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.events = new HashMap<Integer, Event>();

        int j = 0;
        for (Iterator<Event> events = rm.getEvents().iterator();
             events.hasNext(); j++) {
            Event e = events.next();
            eventAdapter.add(e.getName());
            this.events.put(j, e);
        }
        eSpinner.setAdapter(eventAdapter);
    }

    public void addParticipant(View v) {
        TextView tv = (TextView) findViewById(R.id.newparticipant_name);
        EventRegistrationController pc = new EventRegistrationController();
        try {
            pc.createParticipant(tv.getText().toString());
        } catch (InvalidInputException e) {
            tv.setError(e.getMessage());
        }

        refreshData();
    }

    public void addEvent(View v) {
        TextView tvN = (TextView) findViewById(R.id.newevent_name);

        TextView tvD = (TextView) findViewById(R.id.newevent_date);
        String[] d = tvD.getText().toString().split("-");
        int day = Integer.parseInt(d[0]);
        int month = Integer.parseInt(d[1]);
        int year = Integer.parseInt(d[2]);
        Calendar c = Calendar.getInstance();
        c.set(year, month, day);
        Date date = new Date(c.getTimeInMillis());

        TextView tvS = (TextView) findViewById(R.id.newevent_start);
        String[] t1 = tvS.getText().toString().split(":");
        int h1 = Integer.parseInt(t1[0]);
        int m1 = Integer.parseInt(t1[1]);
        c.set(year, month, day, h1, m1);
        Time tS = new Time(c.getTimeInMillis());

        TextView tvE = (TextView) findViewById(R.id.newevent_end);
        String[] t2 = tvE.getText().toString().split(":");
        int h2 = Integer.parseInt(t2[0]);
        int m2 = Integer.parseInt(t2[1]);
        c.set(year, month, day, h2, m2);
        Time tE = new Time(c.getTimeInMillis());

        EventRegistrationController pc = new EventRegistrationController();
        try {
            pc.createEvent(tvN.getText().toString(), date, tS, tE);
        } catch (Exception e) {
            tvN.setError(e.getMessage());
        }

        refreshData();
    }

    public void addPair(View v) {
        TextView tv = (TextView) findViewById(R.id.participantspinner_view);
        Spinner pS = (Spinner) findViewById(R.id.participantspinner);
        Spinner eS = (Spinner) findViewById(R.id.eventspinner);
        int pIndex = pS.getSelectedItemPosition();
        Participant p = this.participants.get(pIndex);
        int eIndex = eS.getSelectedItemPosition();
        Event e = this.events.get(eIndex);

        RegistrationManager rm = RegistrationManager.getInstance();
        boolean existsAlready = rm.getRegistrations().contains(new Registration(p, e)); // not working
        EventRegistrationController pc = new EventRegistrationController();
        try {
            if (!existsAlready)
                pc.register(p, e);
            else
                tv.setError("The participant has already been registered to this event");
        } catch (InvalidInputException er) {
            tv.setError(er.getMessage());
        }

        refreshData();
    }

    public void showDatePickerDialog(View v) {
        TextView tf = (TextView) v;
        Bundle args = getDateFromLabel(tf.getText());
        args.putInt("id", v.getId());

        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.setArguments(args);
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    private Bundle getDateFromLabel(CharSequence text) {
        Bundle rtn = new Bundle();
        String comps[] = text.toString().split("-");
        int day = 1;
        int month = 1;
        int year = 1;

        if (comps.length == 3) {
            day = Integer.parseInt(comps[0]);
            month = Integer.parseInt(comps[1]);
            year = Integer.parseInt(comps[2]);
        }

        rtn.putInt("day", day);
        rtn.putInt("month", month-1);
        rtn.putInt("year", year);

        return rtn;
    }

    public void showTimePickerDialog(View v) {
        TextView tf = (TextView) v;
        Bundle args = getTimeFromLabel(tf.getText());
        args.putInt("id", v.getId());

        TimePickerFragment newFragment = new TimePickerFragment();
        newFragment.setArguments(args);
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    private Bundle getTimeFromLabel(CharSequence text) {
        Bundle rtn = new Bundle();
        String comps[] = text.toString().split(":");
        int hour = 12;
        int minute = 0;

        if (comps.length == 2) {
            hour = Integer.parseInt(comps[0]);
            minute = Integer.parseInt(comps[1]);
        }

        rtn.putInt("hour", hour);
        rtn.putInt("minute", minute);

        return rtn;
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

    public void setDate(int id, int day, int month, int year) {
        TextView tv = (TextView) findViewById(id);
        tv.setText(String.format("%02d-%02d-%04d", day, month + 1, year));
    }

    public void setTime(int id, int hourOfDay, int minute) {
        TextView tv = (TextView) findViewById(id);
        tv.setText(String.format("%02d:%02d", hourOfDay, minute));
    }*/

}
