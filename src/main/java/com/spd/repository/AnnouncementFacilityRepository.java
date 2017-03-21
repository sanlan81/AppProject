package com.spd.repository;

import com.spd.entity.AnnouncementFacility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnouncementFacilityRepository extends JpaRepository<AnnouncementFacility, Integer> {

}
