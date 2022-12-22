package uz.gullbozor.gullbozor.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.gullbozor.gullbozor.entity.CompanyEntity;

public interface CompanyRepo extends JpaRepository<CompanyEntity, Long> {
}
