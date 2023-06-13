package zw.co.afrocodemy.afrocodemyclassrooms.forum.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import zw.co.afrocodemy.afrocodemyclassrooms.DtoMapper;
import zw.co.afrocodemy.afrocodemyclassrooms.exceptions.InvalidRequestException;
import zw.co.afrocodemy.afrocodemyclassrooms.forum.*;
import zw.co.afrocodemy.afrocodemyclassrooms.forum.dto.ForumRequest;

@Service
@RequiredArgsConstructor
public class ForumServiceImpl implements ForumService {
    private final CourseForumRepository forumRepository;
    private final DtoMapper mapper;

    @Override
    public ResponseEntity<?> getAll(Pageable pageable) {
        return ResponseEntity.ok(forumRepository.findAll(pageable));
    }

    @Override
    public ResponseEntity<?> getById(Long id) {
        Forum forum = forumRepository.findById(id).orElse(null);
        if(forum == null){
            throw new InvalidRequestException("Could not find Forum with id: " + id);
        }
        return ResponseEntity.ok(forum);
    }

    @Override
    public ResponseEntity<?> create(ForumRequest request) {
        if(request.getAssociatedUsername() == null || request.getName() == null){
            throw new InvalidRequestException("Creator Username or Forum Name cannot be null");
        }
        CourseForum forum = mapper.requestToForumCreate(request);
        return ResponseEntity.ok(forumRepository.save(forum));
    }
    @Override
    public ResponseEntity<?> update(ForumRequest request) {
        if(request.getId() == null){
            throw new InvalidRequestException("Forum Id cannot be null");
        }
        if(request.getAssociatedUsername() == null || request.getName() == null){
            throw new InvalidRequestException("Creator Username or Forum Name cannot be null");
        }
        CourseForum forum = mapper.requestToForumUpdate(request);
        return ResponseEntity.ok(forumRepository.save(forum));
    }

    @Override
    public ResponseEntity<?> delete(Long forumId, String username) {
        CourseForum forum = forumRepository.findById(forumId).orElse(null);
        if(forum == null){
            throw new InvalidRequestException("Could not find forum with Id: " + forumId);
        }
        forumRepository.delete(forum);
        return ResponseEntity.ok("Successfully deleted forum with Id: " + forumId);
    }
}
