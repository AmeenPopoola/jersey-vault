package com.example.jerseyvault.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.jerseyvault.model.Jersey;

@Repository
public interface JerseyRepository extends JpaRepository<Jersey, Long> {

    List<Jersey> findByTeam_League_LeagueNameIgnoreCase(String league);

    List<Jersey> findByTeam_TeamNameIgnoreCase(String teamName);
    
    @Query("SELECT j FROM Jersey j JOIN j.prices p WHERE p.price <= :price")
    List<Jersey> findByPriceLessThanEqual(@Param("price") double price);
}
