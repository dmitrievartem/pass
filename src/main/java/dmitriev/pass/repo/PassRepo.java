package dmitriev.pass.repo;

import dmitriev.pass.domain.Pass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassRepo extends JpaRepository<Pass, String> {
}
