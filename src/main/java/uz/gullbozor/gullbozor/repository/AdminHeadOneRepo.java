package uz.gullbozor.gullbozor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.gullbozor.gullbozor.entity.AdminEntity;
import uz.gullbozor.gullbozor.entity.AdminHeadOne;

import java.util.Optional;

public interface AdminHeadOneRepo extends JpaRepository<AdminHeadOne, Long> {

    Optional<AdminHeadOne> findByYearAndMonth(Short year, Byte month);
    Optional<AdminHeadOne> findByYear(Short year);
    Optional<AdminHeadOne> findByMonth(Byte month);

    boolean existsByYearAndMonth(Short year, Byte month);
    boolean existsByYear(Short year);
    boolean existsByMonth(Byte month);

}
