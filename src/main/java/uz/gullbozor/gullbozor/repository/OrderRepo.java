package uz.gullbozor.gullbozor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.gullbozor.gullbozor.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepo extends JpaRepository<Order, Long> {

    List<Order> findAllByCompanyIdAndStatus(Long companyId, Byte status);

    List<Order> findAllByCompanyId(Long companyId);

}
