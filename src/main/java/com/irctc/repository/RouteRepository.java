package com.irctc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.irctc.model.Route;
@Repository
public interface RouteRepository extends JpaRepository<Route, Integer> {

}
