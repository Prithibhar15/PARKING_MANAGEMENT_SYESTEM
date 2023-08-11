package com.Parking.DAO;

import PARKING.ParkingLot;
import PARKING.Vehicle;
import PARKING.Helper;
import PARKING.ParkingEntry;

import org.hibernate.Session;

import java.util.List;

public class ParkingLotDAO implements GenericDAO<ParkingLot> {

    public ParkingLotDAO(String string, int i, String string2) {
		// TODO Auto-generated constructor stub
	}


	public ParkingLotDAO() {
		// TODO Auto-generated constructor stub
	}


	@Override
    public void add(ParkingLot parkingLot) {
        try (Session session = Helper.getSession()) {
            session.beginTransaction();
            session.save(parkingLot);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(ParkingLot parkingLot) {
        try (Session session = Helper.getSession()) {
            session.beginTransaction();
            session.update(parkingLot);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (Session session = Helper.getSession()) {
            session.beginTransaction();
            ParkingLot parkingLot = session.get(ParkingLot.class, id);
            if (parkingLot != null) {
                session.delete(parkingLot);
                session.getTransaction().commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ParkingLot getById(int id) {
        try (Session session = Helper.getSession()) {
            return session.get(ParkingLot.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ParkingLot> getAll() {
        try (Session session = Helper.getSession()) {
            return session.createQuery("FROM ParkingLot", ParkingLot.class).getResultList();
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
