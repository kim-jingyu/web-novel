package com.webnovel.subscribe.domain.repository;

import com.webnovel.subscribe.domain.Subscribe;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscribeRepository extends JpaRepository<Subscribe, Long> {
    
}
