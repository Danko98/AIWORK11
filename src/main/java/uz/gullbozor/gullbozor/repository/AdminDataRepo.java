package uz.gullbozor.gullbozor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.gullbozor.gullbozor.entity.AdminEntity;

public interface AdminDataRepo extends JpaRepository<AdminEntity, Long> {



}
