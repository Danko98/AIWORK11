package uz.gullbozor.gullbozor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.gullbozor.gullbozor.entity.ArizaEntity;
import uz.gullbozor.gullbozor.entity.NewCom;

import java.util.List;
import java.util.Optional;

public interface NewComRepo extends JpaRepository<NewCom, Long> {

    boolean existsByOwnerPhone(String phoneNumber);
    Optional<NewCom> findByCity(String city);
    Optional<NewCom> findByRegion(String region);
    Optional<NewCom> findByOwnerPhone(String ownerPhone);

    boolean deleteByOwnerPhone(String ownerPhone);

    List<NewCom> findAllByRegion(String region);



}
