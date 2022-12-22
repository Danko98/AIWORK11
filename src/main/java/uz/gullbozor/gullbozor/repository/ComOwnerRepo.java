package uz.gullbozor.gullbozor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.gullbozor.gullbozor.entity.CompanyOwner;
import uz.gullbozor.gullbozor.entity.UserEntity;

import java.util.Optional;

public interface ComOwnerRepo extends JpaRepository<CompanyOwner, Long> {


    boolean existsByUsername(String phoneNumber);
    boolean existsByUsernameAndPassword(String phoneNumber, String password);






    Optional<UserEntity> findByUsername(String userName);

//    boolean existsByEmail(String email);
}
