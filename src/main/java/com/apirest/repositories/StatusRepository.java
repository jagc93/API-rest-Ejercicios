package com.apirest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apirest.entities.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, String> {

	boolean existsByStatusIDIgnoreCase(String statusID);

	boolean existsByStatusNameIgnoreCase(String statusName);
}
