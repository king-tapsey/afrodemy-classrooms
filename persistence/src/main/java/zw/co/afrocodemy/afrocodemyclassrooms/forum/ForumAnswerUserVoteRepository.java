package zw.co.afrocodemy.afrocodemyclassrooms.forum;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ForumAnswerUserVoteRepository extends JpaRepository<ForumAnswerUserVote, Long> {
    Integer countAllByVoteTypeAndAnswer(ForumVoteType voteType, ForumAnswer answer);
    Optional<ForumAnswerUserVote> findByAnswerAndVoterUsername(ForumAnswer answer, String username);
}
