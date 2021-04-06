package source.backend;

public class Tile {
	private Owner    owner     = Owner.NONE; 
	private Position position  = Position.NONE; 
	
	public Tile() {
		this.owner = Owner.NONE;
		this.position = Position.NONE;
	}
	public Tile(Owner owner, Position position) {
		this.owner = owner;
		this.position = position;
	}

	public Owner    getOwner() {
		return owner;
	}
	public void     setOwner(Owner owner) {
			this.owner = owner;
	}
	public Position getPosition() {
		return position;
	}
	public void     setPosition(Position position) {
		if(this.position == Position.NONE) {
			this.position = position;
		}
	}
	@Override

	public String toString() {
		switch (this.owner) {
		case NONE:
			return " ";
		
		case COMPUTER:
			if(SettingsAndOptions.getComputersMarker().equals(Marker.x)) {
				return "x";
			}
			else {
				return "o";
			}
		
		case USER:
			if(SettingsAndOptions.getUsersMarker().equals(Marker.x)) {
				return "x";
			}
			else {
				return "o";
			}
		default:
			return " ";
		}
	}
	
	
	
	
}
