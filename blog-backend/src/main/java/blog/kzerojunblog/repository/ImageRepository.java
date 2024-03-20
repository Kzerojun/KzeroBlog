package blog.kzerojunblog.repository;

import blog.kzerojunblog.entity.ImageEntity;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<ImageEntity,Integer> {
	List<ImageEntity> findByBoardNumber(Integer boardNumber);

	@Transactional
	void deleteByBoardNumber(Integer boardNumber);
}
