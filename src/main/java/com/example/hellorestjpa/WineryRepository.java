package com.example.hellorestjpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WineryRepository extends JpaRepository<Winery,Integer> {
}
