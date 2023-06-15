package zw.co.afrocodemy.afrocodemyclassrooms.forum.dto;

import lombok.Builder;
import lombok.Data;
import zw.co.afrocodemy.afrocodemyclassrooms.forum.ForumQuestion;
import zw.co.afrocodemy.afrocodemyclassrooms.forum.ForumQuestionComment;
import zw.co.afrocodemy.afrocodemyclassrooms.forum.ForumVoteType;

import java.util.List;

@Data
@Builder
public class ForumQuestionResponse {
    private ForumQuestion question;
    private Integer questionVotes;
    private ForumVoteType userForumVoteType;
    private List<ForumQuestionComment> comments;
    private List<ForumAnswerResponse> forumAnswerList;
}
