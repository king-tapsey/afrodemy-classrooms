package zw.co.afrocodemy.afrocodemyclassrooms.forum.dto;

import lombok.Data;
import zw.co.afrocodemy.afrocodemyclassrooms.forum.ForumVoteType;

@Data
public class ForumVotesDto {
    private ForumVoteType voteType;
    private Integer netVotes;
}
