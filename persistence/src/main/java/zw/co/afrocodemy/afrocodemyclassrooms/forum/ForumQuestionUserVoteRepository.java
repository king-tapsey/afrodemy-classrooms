package zw.co.afrocodemy.afrocodemyclassrooms.forum;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ForumQuestionUserVoteRepository extends JpaRepository<ForumQuestionUserVote, Long> {
    Integer countAllByVoteTypeAndQuestion(ForumVoteType voteType, ForumQuestion question);
    Optional<ForumQuestionUserVote> findByQuestionAndVoterUsername(ForumQuestion question, String username);
}
