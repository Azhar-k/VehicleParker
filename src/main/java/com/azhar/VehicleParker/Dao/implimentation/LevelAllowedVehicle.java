package com.azhar.VehicleParker.Dao.implimentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

@Repository
public class LevelAllowedVehicle implements com.azhar.VehicleParker.Dao.LevelAllowedVehicle {

    @Autowired
    EntityManagerFactory entityManagerFactory;
    @Override
    public List getLevelAllowedVehicle() {
        EntityManager entityManager=entityManagerFactory.createEntityManager();

        Query query = entityManager.createQuery("select a.id from Level l join AllowedVehicle a on l.levelNumber=a.id");

        System.out.println(query.getResultList().toString());
        return null;
    }
}
