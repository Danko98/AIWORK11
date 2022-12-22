package uz.gullbozor.gullbozor.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.gullbozor.gullbozor.entity.OthersPrice;

import java.util.Optional;

public interface OtherPriceRepo extends JpaRepository<OthersPrice, Long> {

    boolean existsByCompanyId(Long companyId);


    Optional<OthersPrice> findByCompanyId(Long companyId);
}
