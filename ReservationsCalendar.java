import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * A calendar of guest reserved room.
 * This class is for Manager functions uses.
 * @author DuocNguyen
 *
 */
public class ReservationsCalendar{

	private TreeMap<Date, ArrayList<Room>> reservationsMap;
	private Reservations res;
	private final String FILE_NAME = "reservations.txt";

	
	/**
	 * Constructs a calendar of reservations.
	 */
	public ReservationsCalendar() {
		reservationsMap = new TreeMap<Date, ArrayList<Room>>();
		res = deserialize();
	}


	/**
	 * Gets all reservations from guestmodel.txt file.
	 * @return	Events Calendar.
	 */
	public Reservations deserialize(){
		Reservations result = null;

		try {
			File file = new File(FILE_NAME);

			if(!file.exists()){
				return new Reservations();	//returns a new GuestModel for the very first run
			}

			FileInputStream fileIn = new FileInputStream(FILE_NAME);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			result = (Reservations) in.readObject();
			in.close();
			fileIn.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Load all reservations info to hash map.
	 */
	public void load(){
		//function is called when manager clicked on load;
		//convert hotel hash map to reservation tree map for viewPanel display especially room view.
		HashMap<Guest, ArrayList<Room>> hm = res.getData();
		for(Guest g: hm.keySet()){
			for(Room r: hm.get(g)){
				r.setGuest(g);
				insert(r);
			}
		}
		
	}
	
	/**
	 * Insert room into tree map of guest room reservations.
	 * @param r the room.
	 */
	public void insert(Room r){
		Date startDate = r.getStartDate();
		Date endDate = r.getEndDate();
		Date key = new Date(startDate.getYear(), startDate.getMonth(), startDate.getDate());
		while(key.compareTo(endDate) <= 0){
			key.setHours(0);
			key.setMinutes(0);
			key.setSeconds(0);
			ArrayList<Room> rooms = new ArrayList<Room>();
			if(reservationsMap.containsKey(key)){
				rooms = reservationsMap.get(key);
			}
			rooms.add(r);
			reservationsMap.put(key, rooms);		
			key = new Date(key.getYear(), key.getMonth(), key.getDate()+1);
		}
	}
	
	
	/**
	 * Gets the reservations map.
	 * @return	Reservations map.
	 */
	public TreeMap<Date, ArrayList<Room>> getReservationsMap(){
		return reservationsMap;
	}
}
