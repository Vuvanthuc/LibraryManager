package vn.cpa.api.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.cpa.api.entity.Author;
import vn.cpa.api.exception.NotFoundException;
import vn.cpa.api.repository.author.AuthorRepository;
import vn.cpa.api.request.AuthorCreateRequest;
import vn.cpa.api.request.AuthorUpdateRequest;

@Service
@Log4j2
@Transactional
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;


    public void create(AuthorCreateRequest request) {
        Author author = new Author();
        author.setAuthorName(request.getName());
        author.setAddress(request.getAddress());
        author.setNote(request.getNote());
        authorRepository.save(author);
    }

    public void update(AuthorUpdateRequest request) {
        Author author = authorRepository.findById(request.getId()).get();
        author.setAuthorName(request.getName());
        author.setAddress(request.getAddress());
        author.setNote(request.getNote());
        authorRepository.save(author);
    }

    public void delete(Long id) throws NotFoundException {
        Author author = authorRepository.findById(id).orElseThrow(NotFoundException::new);
        authorRepository.deleteById(id);
    }
}
