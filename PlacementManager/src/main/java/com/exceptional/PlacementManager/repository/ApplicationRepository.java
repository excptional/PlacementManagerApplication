package com.exceptional.PlacementManager.repository;

import com.exceptional.PlacementManager.entity.ApplicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<ApplicationEntity, Long> {
    List<ApplicationEntity> findApplicationByOfferCompany(String company);
}
