package com.example.pruebaJPA.repository;

import com.example.pruebaJPA.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IServiceRepository extends JpaRepository<Service, Long> {
}
