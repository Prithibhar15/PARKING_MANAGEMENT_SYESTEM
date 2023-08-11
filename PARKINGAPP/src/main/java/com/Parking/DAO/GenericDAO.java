package com.Parking.DAO;

import java.util.List;
import PARKING.Vehicle;
import PARKING.ParkingEntry;

public interface GenericDAO<T> {
    void add(T entity);
    void update(T entity);
    void delete(int id);
    T getById(int id);
    List<T> getAll();
    Vehicle getByPlateNumber(String plateNumber); // Method from VehicleDAO
    ParkingEntry getUnexitedEntryByPlateNumber(String plateNumber); // Method from ParkingEntryDAO
}

