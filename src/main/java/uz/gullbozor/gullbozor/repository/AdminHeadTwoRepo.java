package uz.gullbozor.gullbozor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.expression.spel.ast.OpAnd;
import uz.gullbozor.gullbozor.entity.AdminHeadOne;
import uz.gullbozor.gullbozor.entity.AdminHeadTwo;

import java.util.Optional;

public interface AdminHeadTwoRepo extends JpaRepository<AdminHeadTwo, Long> {

    Optional<AdminHeadTwo> findByYearAndMonth(Short year, Byte month);
    Optional<AdminHeadTwo> findByYear(Short year);
    Optional<AdminHeadTwo> findByMonth(Byte month);

    boolean existsByYearAndMonth(Short year, Byte month);
    boolean existsByYear(Short year);
    boolean existsByMonth(Byte month);


}
