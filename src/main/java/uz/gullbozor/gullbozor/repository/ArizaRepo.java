package uz.gullbozor.gullbozor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.gullbozor.gullbozor.entity.ArizaEntity;

public interface ArizaRepo extends JpaRepository<ArizaEntity, Long> {

    boolean existsByPhoneNumber(String phoneNumber);
    ArizaEntity findByPhoneNumber(String phoneNumber);



}
