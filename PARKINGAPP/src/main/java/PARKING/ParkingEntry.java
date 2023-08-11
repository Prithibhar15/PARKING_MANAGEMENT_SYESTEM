package PARKING;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ParkingEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int entryId;
    @ManyToOne
    @JoinColumn(name = "lot_id")
    private ParkingLot parkingLot;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private String status;

    public ParkingEntry() {
    }

    public ParkingEntry(ParkingLot parkingLot, Vehicle vehicle) {
        this.parkingLot = parkingLot;
        this.vehicle = vehicle;
        this.entryTime = LocalDateTime.now();
        this.status = "Parked";
    }

    public ParkingEntry(ParkingLot parkingLot2, Vehicle vehicle2, LocalDateTime now, Object object, String string) {
	}

	
    public int getEntryId() {
        return entryId;
    }

    public void setEntryId(int entryId) {
        this.entryId = entryId;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public void setExitTime() {
        this.exitTime = LocalDateTime.now();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    @Override
    public String toString() {
        return " [entryId=" + entryId +
               ", parkingLot=" + parkingLot.getName() +  // Assuming ParkingLot has a getName() method
               ", vehicle=" + vehicle.getPlateNumber() + // Assuming Vehicle has a getPlateNumber() method
               ", entryTime=" + entryTime +
               ", exitTime=" + exitTime +
               ", status=" + status + "]";
    }

}