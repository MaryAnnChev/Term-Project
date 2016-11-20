/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package ca.mcgill.ecse321.FTMS.model;
import java.util.*;

// line 44 "../../../../../FTMS.ump"
// line 110 "../../../../../FTMS.ump"
public class OrderManager
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static OrderManager theInstance = null;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //OrderManager Associations
  private List<Equipment> equipments;
  private List<Supply> supplies;
  private List<Menu> menus;
  private List<OrderTracker> tracker;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  private OrderManager()
  {
    equipments = new ArrayList<Equipment>();
    supplies = new ArrayList<Supply>();
    menus = new ArrayList<Menu>();
    tracker = new ArrayList<OrderTracker>();
  }

  public static OrderManager getInstance()
  {
    if(theInstance == null)
    {
      theInstance = new OrderManager();
    }
    return theInstance;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public Equipment getEquipment(int index)
  {
    Equipment aEquipment = equipments.get(index);
    return aEquipment;
  }

  public List<Equipment> getEquipments()
  {
    List<Equipment> newEquipments = Collections.unmodifiableList(equipments);
    return newEquipments;
  }

  public int numberOfEquipments()
  {
    int number = equipments.size();
    return number;
  }

  public boolean hasEquipments()
  {
    boolean has = equipments.size() > 0;
    return has;
  }

  public int indexOfEquipment(Equipment aEquipment)
  {
    int index = equipments.indexOf(aEquipment);
    return index;
  }

  public Supply getSupply(int index)
  {
    Supply aSupply = supplies.get(index);
    return aSupply;
  }

  public List<Supply> getSupplies()
  {
    List<Supply> newSupplies = Collections.unmodifiableList(supplies);
    return newSupplies;
  }

  public int numberOfSupplies()
  {
    int number = supplies.size();
    return number;
  }

  public boolean hasSupplies()
  {
    boolean has = supplies.size() > 0;
    return has;
  }

  public int indexOfSupply(Supply aSupply)
  {
    int index = supplies.indexOf(aSupply);
    return index;
  }

  public Menu getMenus(int index)
  {
    Menu aMenus = menus.get(index);
    return aMenus;
  }

  public List<Menu> getMenus()
  {
    List<Menu> newMenus = Collections.unmodifiableList(menus);
    return newMenus;
  }

  public int numberOfMenus()
  {
    int number = menus.size();
    return number;
  }

  public boolean hasMenus()
  {
    boolean has = menus.size() > 0;
    return has;
  }

  public int indexOfMenus(Menu aMenus)
  {
    int index = menus.indexOf(aMenus);
    return index;
  }

  public OrderTracker getTracker(int index)
  {
    OrderTracker aTracker = tracker.get(index);
    return aTracker;
  }

  public List<OrderTracker> getTracker()
  {
    List<OrderTracker> newTracker = Collections.unmodifiableList(tracker);
    return newTracker;
  }

  public int numberOfTracker()
  {
    int number = tracker.size();
    return number;
  }

  public boolean hasTracker()
  {
    boolean has = tracker.size() > 0;
    return has;
  }

  public int indexOfTracker(OrderTracker aTracker)
  {
    int index = tracker.indexOf(aTracker);
    return index;
  }

  public static int minimumNumberOfEquipments()
  {
    return 0;
  }

  public boolean addEquipment(Equipment aEquipment)
  {
    boolean wasAdded = false;
    if (equipments.contains(aEquipment)) { return false; }
    equipments.add(aEquipment);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeEquipment(Equipment aEquipment)
  {
    boolean wasRemoved = false;
    if (equipments.contains(aEquipment))
    {
      equipments.remove(aEquipment);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addEquipmentAt(Equipment aEquipment, int index)
  {  
    boolean wasAdded = false;
    if(addEquipment(aEquipment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEquipments()) { index = numberOfEquipments() - 1; }
      equipments.remove(aEquipment);
      equipments.add(index, aEquipment);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveEquipmentAt(Equipment aEquipment, int index)
  {
    boolean wasAdded = false;
    if(equipments.contains(aEquipment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEquipments()) { index = numberOfEquipments() - 1; }
      equipments.remove(aEquipment);
      equipments.add(index, aEquipment);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addEquipmentAt(aEquipment, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfSupplies()
  {
    return 0;
  }

  public boolean addSupply(Supply aSupply)
  {
    boolean wasAdded = false;
    if (supplies.contains(aSupply)) { return false; }
    supplies.add(aSupply);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSupply(Supply aSupply)
  {
    boolean wasRemoved = false;
    if (supplies.contains(aSupply))
    {
      supplies.remove(aSupply);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addSupplyAt(Supply aSupply, int index)
  {  
    boolean wasAdded = false;
    if(addSupply(aSupply))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSupplies()) { index = numberOfSupplies() - 1; }
      supplies.remove(aSupply);
      supplies.add(index, aSupply);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSupplyAt(Supply aSupply, int index)
  {
    boolean wasAdded = false;
    if(supplies.contains(aSupply))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSupplies()) { index = numberOfSupplies() - 1; }
      supplies.remove(aSupply);
      supplies.add(index, aSupply);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSupplyAt(aSupply, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfMenus()
  {
    return 0;
  }

  public boolean addMenus(Menu aMenus)
  {
    boolean wasAdded = false;
    if (menus.contains(aMenus)) { return false; }
    menus.add(aMenus);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeMenus(Menu aMenus)
  {
    boolean wasRemoved = false;
    if (menus.contains(aMenus))
    {
      menus.remove(aMenus);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addMenusAt(Menu aMenus, int index)
  {  
    boolean wasAdded = false;
    if(addMenus(aMenus))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMenus()) { index = numberOfMenus() - 1; }
      menus.remove(aMenus);
      menus.add(index, aMenus);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveMenusAt(Menu aMenus, int index)
  {
    boolean wasAdded = false;
    if(menus.contains(aMenus))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMenus()) { index = numberOfMenus() - 1; }
      menus.remove(aMenus);
      menus.add(index, aMenus);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addMenusAt(aMenus, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfTracker()
  {
    return 0;
  }

  public boolean addTracker(OrderTracker aTracker)
  {
    boolean wasAdded = false;
    if (tracker.contains(aTracker)) { return false; }
    tracker.add(aTracker);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeTracker(OrderTracker aTracker)
  {
    boolean wasRemoved = false;
    if (tracker.contains(aTracker))
    {
      tracker.remove(aTracker);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addTrackerAt(OrderTracker aTracker, int index)
  {  
    boolean wasAdded = false;
    if(addTracker(aTracker))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTracker()) { index = numberOfTracker() - 1; }
      tracker.remove(aTracker);
      tracker.add(index, aTracker);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTrackerAt(OrderTracker aTracker, int index)
  {
    boolean wasAdded = false;
    if(tracker.contains(aTracker))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTracker()) { index = numberOfTracker() - 1; }
      tracker.remove(aTracker);
      tracker.add(index, aTracker);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addTrackerAt(aTracker, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    equipments.clear();
    supplies.clear();
    menus.clear();
    tracker.clear();
  }

}