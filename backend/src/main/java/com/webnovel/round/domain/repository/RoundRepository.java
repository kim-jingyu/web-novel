package com.webnovel.round.domain.repository;

import com.webnovel.round.domain.Round;
import com.webnovel.round.exception.RoundNonFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoundRepository extends JpaRepository<Round, Long> {
    default Round getById(Long roundId) {
        return findById(roundId)
                .orElseThrow(RoundNonFoundException::new);
    }
}
