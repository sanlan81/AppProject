package com.spd.repository;

import com.spd.entity.Attender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttenderRepository extends JpaRepository<Attender, Integer> {

}
