package uz.gullbozor.gullbozor.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.gullbozor.gullbozor.entity.CompanyEntity;

import java.util.List;

public interface CompanyRepo extends JpaRepository<CompanyEntity, Long> {

    boolean existsByCompanyOwnerId(Long companyOwnerId);
    List<CompanyEntity> findAllByRegionId(Integer regionId);
    List<CompanyEntity> findAllByCityId(Integer cityId);


}
