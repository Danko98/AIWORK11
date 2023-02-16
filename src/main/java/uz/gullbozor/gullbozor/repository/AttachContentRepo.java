package uz.gullbozor.gullbozor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.gullbozor.gullbozor.entity.AttachContent;

import java.util.UUID;

public interface AttachContentRepo extends JpaRepository<AttachContent, UUID> {


}
