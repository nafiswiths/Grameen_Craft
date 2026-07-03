package com.grameencraft.backend.repository;

import com.grameencraft.backend.model.Artisan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtisanRepository extends JpaRepository<Artisan, String> {
}
