public class Hangar {
	private int totalSpeed;
	private Ship ship;

	public Hangar() {
		this.ship = new Ship();
	}

	public Hangar(Ship ship) {
		this.ship = ship;
	}

	public Ship getShip(){
		return ship;
	}

	public void setShip(Ship s){
		ship = s;
	}
	
}