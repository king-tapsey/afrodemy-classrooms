package zw.co.afrocodemy.afrocodemyclassrooms.forum;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ForumUserVoteRepository extends JpaRepository<ForumUserVote, Long> {
    Boolean existsByVoterUsername(String username);
    Optional<ForumUserVote> findByVoterUsername(String username);

}
