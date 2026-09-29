package com.apimonumentos.demo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MonumentRepository
        extends JpaRepository<Monument, Long> {}
