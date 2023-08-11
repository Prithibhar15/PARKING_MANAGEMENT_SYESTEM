package com.main;
import PARKING.Helper;
import PARKING.ParkingEntry;
import PARKING.Vehicle;
import PARKING.ParkingLot;
import com.Parking.DAO.*;

import java.util.List;
import java.util.Scanner;
public class MainApplication {
    public static void main(String[] args) {
        ParkingLotDAO parkingLotDAO = new ParkingLotDAO();
        VehicleDAO vehicleDAO = new VehicleDAO();
        ParkingEntryDAO parkingEntryDAO = new ParkingEntryDAO();
        createTables();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\nTwo-Wheeler Parking Management");
            System.out.println("=============================");
            System.out.println("1. Add Parking Lot");
            System.out.println("2. Add Vehicle");
            System.out.println("3. Park Vehicle");
            System.out.println("4. Exit Vehicle");
            System.out.println("5. View Parking Status");
			System.out.println("6. Update Parking Lot");
            System.out.println("7. Delete Parking Lot");
			System.out.println("8. Update Vehicle");
			System.out.println("9. Delete Vehicle");
			System.out.println("10. view All ParkingEntries");
			System.out.println("11. view All Vehicles");
			System.out.println("12. view All ParkingLots");
			System.out.println("13. view ParkingEntry by id");
			System.out.println("14. view Vehicle by id");
			System.out.println("15. view ParkingLot by id");
            System.out.println("16. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume the newline character left by sc.nextInt()

            switch (choice) {
                case 1:
                    addParkingLot(parkingLotDAO, sc);
                    break;
                case 2:
                    addVehicle(vehicleDAO, sc);
                    break;
                case 3:
                    parkVehicle(parkingLotDAO, vehicleDAO, parkingEntryDAO, sc);
                    break;
                case 4:
                    exitVehicle(parkingEntryDAO, sc);
                    break;
                case 5:
                    viewParkingStatus(parkingEntryDAO);
                    break;
				case 6:
                    updateParkingLot(parkingLotDAO, sc);
                    break;
                case 7:
                    deleteParkingLot(parkingLotDAO, sc);
                    break;
				
               case 8:
                    updateVehicle(vehicleDAO, sc);
                    break;
                case 9:
                    deleteVehicle(vehicleDAO, sc);
                    break;
                case 10:
                    viewAllParkingEntries(parkingEntryDAO);
                    break;
                case 11:
                    viewAllVehicles(vehicleDAO);
                    break;
                case 12:
                    viewAllParkingLots(parkingLotDAO);
                    break;
				case 13:
                    viewParkingEntryById(parkingEntryDAO, sc);
                    break;
                case 14:
                    viewVehicleById(vehicleDAO, sc);
                    break;
                case 15:
                    viewParkingLotById(parkingLotDAO, sc);
                    break;
				case 16:
                    System.out.println("Thank you for using the Two-Wheeler Parking Management App!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                
            }
        }
    }
// Method to create tables if they don't exist
    public static void createTables() {
        try {
            // Create ParkingLot table
            Helper.executeSQL("CREATE TABLE IF NOT EXISTS parking_lots (" +
                    "lot_id INT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(100) NOT NULL," +
                    "capacity INT NOT NULL," +
                    "location VARCHAR(100) NOT NULL" +
                    ")");

            // Create Vehicle table
            Helper.executeSQL("CREATE TABLE IF NOT EXISTS vehicles (" +
                    "vehicle_id INT AUTO_INCREMENT PRIMARY KEY," +
                    "plate_number VARCHAR(20) NOT NULL," +
                    "vehicle_type VARCHAR(20) NOT NULL," +
                    "owner_name VARCHAR(100) NOT NULL" +
                    ")");

            // Create ParkingEntry table
            Helper.executeSQL("CREATE TABLE IF NOT EXISTS parking_entries (" +
                    "entry_id INT AUTO_INCREMENT PRIMARY KEY," +
                    "lot_id INT NOT NULL," +
                    "vehicle_id INT NOT NULL," +
                    "entry_time DATETIME NOT NULL," +
                    "exit_time DATETIME," +
                    "status VARCHAR(20) NOT NULL," +
                    "FOREIGN KEY (lot_id) REFERENCES parking_lots(lot_id)," +
                    "FOREIGN KEY (vehicle_id) REFERENCES vehicles(vehicle_id)" +
                    ")");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	public static void addParkingLot(ParkingLotDAO parkingLotDAO, Scanner sc) {
        System.out.print("Enter parking lot name: ");
        String name = sc.nextLine();
        System.out.print("Enter parking lot capacity: ");
        int capacity = sc.nextInt();
        sc.nextLine(); // Consume the newline character left by sc.nextInt()
        System.out.print("Enter parking lot location: ");
        String location = sc.nextLine();

        ParkingLot parkingLot = new ParkingLot(name, capacity, location);
        parkingLotDAO.add(parkingLot);

        System.out.println("Parking lot added successfully!");
    }

    public static void addVehicle(VehicleDAO vehicleDAO, Scanner sc) {
        System.out.print("Enter vehicle plate number: ");
        String plateNumber = sc.nextLine();
        System.out.print("Enter vehicle type: ");
        String vehicleType = sc.nextLine();
        System.out.print("Enter vehicle owner name: ");
        String ownerName = sc.nextLine();

        Vehicle vehicle = new Vehicle(plateNumber, vehicleType, ownerName);
        vehicleDAO.add(vehicle);

        System.out.println("Vehicle added successfully!");
    }

    public static void parkVehicle(ParkingLotDAO parkingLotDAO, VehicleDAO vehicleDAO,
                                   ParkingEntryDAO parkingEntryDAO, Scanner sc) {
        System.out.print("Enter vehicle plate number: ");
        String plateNumber = sc.nextLine();
        System.out.print("Enter parking lot ID: ");
        int lotId = sc.nextInt();
        sc.nextLine(); // Consume the newline character left by sc.nextInt()

        Vehicle vehicle = vehicleDAO.getByPlateNumber(plateNumber);
        ParkingLot parkingLot = parkingLotDAO.getById(lotId);

        if (vehicle != null && parkingLot != null) {
            ParkingEntry parkingEntry = new ParkingEntry(parkingLot, vehicle);
            parkingEntryDAO.add(parkingEntry);
            System.out.println("Vehicle parked successfully!");
        } else {
            System.out.println("Error: Vehicle or parking lot not found.");
        }
    }

    public static void exitVehicle(ParkingEntryDAO parkingEntryDAO, Scanner sc) {
        System.out.print("Enter vehicle plate number: ");
        String plateNumber = sc.nextLine();

        ParkingEntry parkingEntry = parkingEntryDAO.getUnexitedEntryByPlateNumber(plateNumber);

        if (parkingEntry != null) {
            parkingEntry.setExitTime();
            parkingEntry.setStatus("Exited");
            parkingEntryDAO.update(parkingEntry);
            System.out.println("Vehicle exited successfully!");
        } else {
            System.out.println("Error: Vehicle not found or already exited.");
        }
    }

    public static void viewParkingStatus(ParkingEntryDAO parkingEntryDAO) 
	{
        System.out.println("\nParking Status:");
        System.out.println("=====================================");
        System.out.printf("%-10s%-15s%-15s%-20s%-20s%-20s%-20s%-15s\n",
                "Entry ID", "Plate Number", "Vehicle Type", "Owner Name", "Lot Name", "Entry Time", "Exit Time", "Status");

        for (ParkingEntry entry : parkingEntryDAO.getAll()) {
            System.out.printf("%-10d%-15s%-15s%-20s%-20s%-20s%-20s%-15s\n",
                    entry.getEntryId(), entry.getVehicle().getPlateNumber(), entry.getVehicle().getVehicleType(),
                    entry.getVehicle().getOwnerName(), entry.getParkingLot().getName(),
                    entry.getEntryTime(), entry.getExitTime(), entry.getStatus());
        }
    }
	public static void updateParkingLot(ParkingLotDAO parkingLotDAO, Scanner sc) 
	{
        System.out.print("Enter parking lot ID to update: ");
        int lotId = sc.nextInt();
        sc.nextLine(); // Consume the newline character left by sc.nextInt()

        ParkingLot parkingLot = parkingLotDAO.getById(lotId);
        if (parkingLot != null) {
            System.out.print("Enter new parking lot name: ");
            String newName = sc.nextLine();
            System.out.print("Enter new parking lot capacity: ");
            int newCapacity = sc.nextInt();
            sc.nextLine(); // Consume the newline character left by sc.nextInt()
            System.out.print("Enter new parking lot location: ");
            String newLocation = sc.nextLine();

            parkingLot.setName(newName);
            parkingLot.setCapacity(newCapacity);
            parkingLot.setLocation(newLocation);

            parkingLotDAO.update(parkingLot);
            System.out.println("Parking lot updated successfully!");
        } else {
            System.out.println("Error: Parking lot not found.");
        }
    }
	public static void deleteParkingLot(ParkingLotDAO parkingLotDAO, Scanner sc) 
	{
        System.out.print("Enter parking lot ID to delete: ");
        int lotId = sc.nextInt();
        sc.nextLine(); // Consume the newline character left by sc.nextInt()

        ParkingLot parkingLot = parkingLotDAO.getById(lotId);
        if (parkingLot != null) {
            parkingLotDAO.delete(lotId);
            System.out.println("Parking lot deleted successfully!");
        } else {
            System.out.println("Error: Parking lot not found.");
        }
    }
	public static void updateVehicle(VehicleDAO vehicleDAO, Scanner sc) 
	{
        System.out.print("Enter vehicle ID to update: ");
        int vehicleId = sc.nextInt();
        sc.nextLine(); // Consume the newline character left by sc.nextInt()

        Vehicle vehicle = vehicleDAO.getById(vehicleId);
        if (vehicle != null) {
            System.out.print("Enter new plate number: ");
            String newPlateNumber = sc.nextLine();
            System.out.print("Enter new vehicle type: ");
            String newVehicleType = sc.nextLine();
            System.out.print("Enter new owner name: ");
            String newOwnerName = sc.nextLine();

            vehicle.setPlateNumber(newPlateNumber);
            vehicle.setVehicleType(newVehicleType);
            vehicle.setOwnerName(newOwnerName);

            vehicleDAO.update(vehicle);
            System.out.println("Vehicle updated successfully!");
        } else {
            System.out.println("Error: Vehicle not found.");
        }
    }
	public static void deleteVehicle(VehicleDAO vehicleDAO, Scanner sc) 
	{
        System.out.print("Enter vehicle ID to delete: ");
        int vehicleId = sc.nextInt();
        sc.nextLine(); // Consume the newline character left by sc.nextInt()

        Vehicle vehicle = vehicleDAO.getById(vehicleId);
        if (vehicle != null) {
            vehicleDAO.delete(vehicleId);
            System.out.println("Vehicle deleted successfully!");
        } else {
            System.out.println("Error: Vehicle not found.");
        }
    }
	public static void viewAllParkingEntries(ParkingEntryDAO parkingEntryDAO) 
	{
        List<ParkingEntry> parkingEntries = parkingEntryDAO.getAll();
        System.out.println("\nAll Parking Entries:");
        System.out.println("=======================");
        for (ParkingEntry entry : parkingEntries) {
            System.out.println(entry);
        }
    }

    public static void viewAllVehicles(VehicleDAO vehicleDAO) 
	{
        List<Vehicle> vehicles = vehicleDAO.getAll();
        System.out.println("\nAll Vehicles:");
        System.out.println("=======================");
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle);
        }
    }

    public static void viewAllParkingLots(ParkingLotDAO parkingLotDAO) 
	{
        List<ParkingLot> parkingLots = parkingLotDAO.getAll();
        System.out.println("\nAll Parking Lots:");
        System.out.println("=======================");
        for (ParkingLot lot : parkingLots) {
            System.out.println(lot);
        }
    }
	public static void viewParkingEntryById(ParkingEntryDAO parkingEntryDAO, Scanner sc) 
	{
        System.out.print("Enter Parking Entry ID: ");
        int entryId = sc.nextInt();
        sc.nextLine(); // Consume the newline character left by sc.nextInt()

        ParkingEntry parkingEntry = parkingEntryDAO.getById(entryId);
        if (parkingEntry != null) {
            System.out.println("\nParking Entry Details:");
            System.out.println("=======================");
            System.out.println(parkingEntry);
        } else {
            System.out.println("Parking Entry not found.");
        }
    }

    public static void viewVehicleById(VehicleDAO vehicleDAO, Scanner sc) 
	{
        System.out.print("Enter Vehicle ID: ");
        int vehicleId = sc.nextInt();
        sc.nextLine(); // Consume the newline character left by sc.nextInt()

        Vehicle vehicle = vehicleDAO.getById(vehicleId);
        if (vehicle != null) {
            System.out.println("\nVehicle Details:");
            System.out.println("=======================");
            System.out.println(vehicle);
        } else {
            System.out.println("Vehicle not found.");
        }
    }

    public static void viewParkingLotById(ParkingLotDAO parkingLotDAO, Scanner sc) 
	{
        System.out.print("Enter Parking Lot ID: ");
        int lotId = sc.nextInt();
        sc.nextLine(); // Consume the newline character left by sc.nextInt()

        ParkingLot parkingLot = parkingLotDAO.getById(lotId);
        if (parkingLot != null) {
            System.out.println("\nParking Lot Details:");
            System.out.println("=======================");
            System.out.println(parkingLot);
        } else {
            System.out.println("Parking Lot not found.");
        }
    }
}




