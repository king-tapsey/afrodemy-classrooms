package zw.co.afrocodemy.afrocodemyclassrooms.topic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicStudentRepository extends JpaRepository<TopicStudent, Long> {

}
