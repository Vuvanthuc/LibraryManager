package vn.cpa.api.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.cpa.api.entity.Author;
import vn.cpa.api.exception.NotFoundException;
import vn.cpa.api.repository.author.AuthorRepository;
import vn.cpa.api.request.AuthorCreateRequest;
import vn.cpa.api.request.AuthorFindAllRequest;
import vn.cpa.api.request.AuthorUpdateRequest;
import vn.cpa.api.response.AuthorFindAllResponse;
import vn.cpa.api.response.AuthorFindDetailResponse;
import vn.cpa.api.util.PageUtils;

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

    public Page<AuthorFindAllResponse> findAll(AuthorFindAllRequest request) {
        Pageable pageable = PageUtils.buildPage(request.getPage(), request.getSize());
        return authorRepository.findAll(request, pageable);
    }

    public AuthorFindDetailResponse findDetail(Long id) {
        Author author = authorRepository.findById(id).get();
        AuthorFindDetailResponse response = new AuthorFindDetailResponse();
        response.setId(author.getAuthorId());
        response.setAddress(author.getAddress());
        response.setCode(author.getCode());
        response.setName(author.getAuthorName());
        response.setNote(author.getNote());
        response.setPosition(author.getPosition());
        return response;
    }
}
