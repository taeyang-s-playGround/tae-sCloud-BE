package project.taes_cloud.domain.tus.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.taes_cloud.domain.tus.domain.Tus;

public interface TusRepository extends JpaRepository<Tus, Long> {

}
