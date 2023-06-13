package zw.co.afrocodemy.afrocodemyclassrooms.topic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicMaterialRepository extends JpaRepository<TopicMaterial, Long> {

}
