package com.rp.djIndexes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rp.djIndexes.model.DJIndex;

import java.util.List;

@Repository
public interface DJIndexRepository extends JpaRepository<DJIndex, Long> {
    List<DJIndex>  findByStock(String stock);
}

