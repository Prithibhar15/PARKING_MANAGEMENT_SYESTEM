package PARKING;

	import javax.persistence.Entity;
	import javax.persistence.GeneratedValue;
	import javax.persistence.GenerationType;
	import javax.persistence.Id;

	@Entity
	public class Vehicle {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int vehicleId;

	    private String plateNumber;
	    private String vehicleType;
	    private String ownerName;

	    public Vehicle() {
	    }

	    public Vehicle(String plateNumber, String vehicleType, String ownerName) {
	        this.plateNumber = plateNumber;
	        this.vehicleType = vehicleType;
	        this.ownerName = ownerName;
	    }

	    
	    public int getVehicleId() {
	        return vehicleId;
	    }

	    public void setVehicleId(int vehicleId) {
	        this.vehicleId = vehicleId;
	    }

	    public String getPlateNumber() {
	        return plateNumber;
	    }

	    public void setPlateNumber(String plateNumber) {
	        this.plateNumber = plateNumber;
	    }

	    public String getVehicleType() {
	        return vehicleType;
	    }

	    public void setVehicleType(String vehicleType) {
	        this.vehicleType = vehicleType;
	    }

	    public String getOwnerName() {
	        return ownerName;
	    }

	    public void setOwnerName(String ownerName) {
	        this.ownerName = ownerName;
	    }
	    @Override
	    public String toString() {
	        return "[vehicleId=" + vehicleId +
	               ", plateNumber=" + plateNumber +
	               ", vehicleType=" + vehicleType +
	               ", ownerName=" + ownerName + "]";
	    }
	}


