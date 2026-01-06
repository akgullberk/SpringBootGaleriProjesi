package com.akgulberk.gallerist.repository;

import com.akgulberk.gallerist.model.GalleristCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GalleristCarRepository extends JpaRepository<GalleristCar,Long> {
}

