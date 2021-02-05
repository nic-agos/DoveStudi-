package logic.bean;

public class RoomBean {
	
	private int id;
	
	private String name;
	
	private String address;
	
	private int numPartecipants;

	private int numAvailableSeats;
	
	private String owner;
	
	private int specification;
	
	public RoomBean() {
		
	}
	
	public RoomBean(String name, String address, int numPartecipants, int numAvailableSeats, String owner, int specification) {
		
		this.name = name;
		
		this.address = address;
		
		this.numPartecipants = numPartecipants;
		
		this.numAvailableSeats = numAvailableSeats;
		
		this.owner = owner;
		
		this.specification = specification;
		
	}
	
	public RoomBean(int id, String name, String address, int numPartecipants, int numAvailableSeats, String owner, int specification) {
		
		this(name, address, numPartecipants, numAvailableSeats, owner, specification);
		
		this.id = id;
		
	}
	
	public void setId (int id) {
		this.id = id;
		
	}
	
	public int getId () {
		return this.id;
		
	}
	
	public void setName(String name) {
		this.name = name;
		
	}

	public String getName() {
		return this.name;
		
	}
	
	public void setAddress(String address) {
		this.address = address;
		
	}
	
	public String getAddress() {
		return this.address;
		
	}
	
	public void setNumPartecipants(int numPartecipants) {
		this.numPartecipants = numPartecipants;
		
	}
	
	public int getNumPartecipants() {
		return this.numPartecipants;
		
	}
	
	public void setNumAvailableSeats(int numAvailableSeats) {
		this.numAvailableSeats = numAvailableSeats;
		
	}
	
	public int getNumAvailableSeats() {
		return this.numAvailableSeats;
		
	}
	
	public void setOwner(String owner) {
		this.owner = owner;
		
	}
	
	public String getOwner() {
		return this.owner;
		
	}
	
	public void setSpecification(int specification) {
		this.specification = specification;
		
	}
	
	public int getSpecification() {
		return this.specification;
		
	}
}
