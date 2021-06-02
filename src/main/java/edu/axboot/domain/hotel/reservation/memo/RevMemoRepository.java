package edu.axboot.domain.hotel.reservation.memo;

import com.chequer.axboot.core.domain.base.AXBootJPAQueryDSLRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RevMemoRepository extends AXBootJPAQueryDSLRepository<RevMemo, Long> {
}
