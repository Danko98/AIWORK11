package uz.gullbozor.gullbozor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.gullbozor.gullbozor.entity.Attach;

import java.util.List;

public interface AttachRepo extends JpaRepository<Attach, Long> {
    List<Attach> findAllByMainAttachId(Long mainAttachId);


}
