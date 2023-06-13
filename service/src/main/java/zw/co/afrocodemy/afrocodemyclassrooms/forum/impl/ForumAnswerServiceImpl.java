package zw.co.afrocodemy.afrocodemyclassrooms.forum.impl;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import zw.co.afrocodemy.afrocodemyclassrooms.forum.ForumAnswerService;
import zw.co.afrocodemy.afrocodemyclassrooms.forum.dto.ForumQuestionRequest;

@Service
public class ForumAnswerServiceImpl implements ForumAnswerService {
    @Override
    public ResponseEntity<?> getAll(Pageable pageable) {
        return null;
    }

    @Override
    public ResponseEntity<?> getAllByNetVotes(Pageable pageable) {
        return null;
    }

    @Override
    public ResponseEntity<?> getById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<?> getAnswerComments(Long answerId) {
        return null;
    }

    @Override
    public ResponseEntity<?> create(ForumQuestionRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<?> update(ForumQuestionRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<?> delete(Long questionId, String username) {
        return null;
    }
}
