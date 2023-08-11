package com.Parking.DAO;


import PARKING.Vehicle;
import PARKING.Helper;
import PARKING.ParkingEntry;

import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class VehicleDAO implements GenericDAO<Vehicle> {

    public VehicleDAO(String string, String string2, String string3) {
		// TODO Auto-generated constructor stub
	}

	public VehicleDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
    public void add(Vehicle vehicle) {
        try (Session session = Helper.getSession()) {
            session.beginTransaction();
            session.save(vehicle);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Vehicle vehicle) {
        try (Session session = Helper.getSession()) {
            session.beginTransaction();
            session.update(vehicle);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (Session session = Helper.getSession()) {
            session.beginTransaction();
            Vehicle vehicle = session.get(Vehicle.class, id);
            if (vehicle != null) {
                session.delete(vehicle);
                session.getTransaction().commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Vehicle getById(int id) {
        try (Session session = Helper.getSession()) {
            return session.get(Vehicle.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Vehicle> getAll() {
        try (Session session = Helper.getSession()) {
            return session.createQuery("FROM Vehicle", Vehicle.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public Vehicle getByPlateNumber(String plateNumber) {
        try (Session session = Helper.getSession()) {
            Query<Vehicle> query = session.createQuery("FROM Vehicle WHERE plateNumber = :plateNumber", Vehicle.class);
            query.setParameter("plateNumber", plateNumber);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

	@Override
	public ParkingEntry getUnexitedEntryByPlateNumber(String plateNumber) {
		// TODO Auto-generated method stub
		return null;
	}
}
