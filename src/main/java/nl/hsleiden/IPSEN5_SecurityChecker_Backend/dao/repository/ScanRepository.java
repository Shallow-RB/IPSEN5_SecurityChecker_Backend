package nl.hsleiden.IPSEN5_SecurityChecker_Backend.dao.repository;

import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.scan.Scan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScanRepository extends JpaRepository<Scan, String> {

}