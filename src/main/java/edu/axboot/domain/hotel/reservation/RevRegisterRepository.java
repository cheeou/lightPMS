package edu.axboot.domain.hotel.reservation;

import com.chequer.axboot.core.domain.base.AXBootJPAQueryDSLRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RevRegisterRepository extends AXBootJPAQueryDSLRepository<RevRegister, Long> {
}
