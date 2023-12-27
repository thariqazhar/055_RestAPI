package com.example.kontak.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface kontakRepo extends JpaRepository<kontak, Integer> {
}
