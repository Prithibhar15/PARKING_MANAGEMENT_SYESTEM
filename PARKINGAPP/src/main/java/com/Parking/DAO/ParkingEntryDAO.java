package com.Parking.DAO;

import PARKING.ParkingEntry;
import PARKING.Vehicle;
import PARKING.Helper;
import org.hibernate.Session;

import java.util.List;

public class ParkingEntryDAO implements GenericDAO<ParkingEntry> {

    @Override
    public void add(ParkingEntry parkingEntry) {
        try (Session session = Helper.getSession()) {
            session.beginTransaction();
            session.save(parkingEntry);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(ParkingEntry parkingEntry) {
        try (Session session = Helper.getSession()) {
            session.beginTransaction();
            session.update(parkingEntry);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (Session session = Helper.getSession()) {
            session.beginTransaction();
            ParkingEntry parkingEntry = session.get(ParkingEntry.class, id);
            if (parkingEntry != null) {
                session.delete(parkingEntry);
                session.getTransaction().commit();
            }
        } catch (Exception e) { 
            e.printStackTrace();
        }
    }

    @Override
    public ParkingEntry getById(int id) {
        try (Session session = Helper.getSession()) {
            return session.get(ParkingEntry.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ParkingEntry> getAll() {
        try (Session session = Helper.getSession()) {
            return session.createQuery("FROM ParkingEntry", ParkingEntry.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

	@Override
	public Vehicle getByPlateNumber(String plateNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ParkingEntry getUnexitedEntryByPlateNumber(String plateNumber) {
		// TODO Auto-generated method stub
		return null;
	}
}
