package prv.fries.versandservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import prv.fries.versandservice.entity.Versandauftrag;

import java.util.UUID;

@Repository
public interface VersandRepository extends JpaRepository<Versandauftrag, UUID> {
}
