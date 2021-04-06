package source.backend;

public class SettingsAndOptions {
	private static Marker usersMarker = Marker.x;
	private static Marker computersMarker = Marker.o;
	
	private static Owner firstPlayer;
	
	private static boolean isGUIActive = false;
	
	
	
	

	public static boolean isGUIActive() {
		return isGUIActive;
	}
	public static void setGUIActive(boolean isGUIActive) {
		SettingsAndOptions.isGUIActive = isGUIActive;
	}
	public static Owner getFirstPlayer() {
		return firstPlayer;
	}
	public static void setFirstPlayer(Owner firstPlayer) {
		SettingsAndOptions.firstPlayer = firstPlayer;
	}
	public static Marker getUsersMarker() {
		return usersMarker;
	}
	public static Marker getComputersMarker() {
		return computersMarker;
	}
	
	public static void setMarkers(Marker usersChoice) {
		
		if(usersChoice.equals(Marker.x)) {
			usersMarker = Marker.x;
			computersMarker  = Marker.o;
		}
		else {
			usersMarker = Marker.o;
			computersMarker = Marker.x;
		}
		
	}
	
	public static void main(String[] args) {
		setMarkers(Marker.o);
		System.out.println(getComputersMarker());
		System.out.println(getUsersMarker());
		
		setMarkers(Marker.x);
		System.out.println(getComputersMarker());
		System.out.println(getUsersMarker());
	}
	
}
