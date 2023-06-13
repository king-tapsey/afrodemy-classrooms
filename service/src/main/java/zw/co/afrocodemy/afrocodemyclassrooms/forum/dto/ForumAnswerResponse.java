package zw.co.afrocodemy.afrocodemyclassrooms.forum.dto;

import lombok.Builder;
import lombok.Data;
import zw.co.afrocodemy.afrocodemyclassrooms.forum.ForumAnswer;
import zw.co.afrocodemy.afrocodemyclassrooms.forum.ForumAnswerComment;
import zw.co.afrocodemy.afrocodemyclassrooms.forum.ForumVoteType;

import java.util.Set;

@Data
@Builder
public class ForumAnswerResponse {
    private ForumAnswer answer;
    private Integer answerVotes;
    private ForumVoteType userForumVoteType;
    private Set<ForumAnswerComment> comments;
}
