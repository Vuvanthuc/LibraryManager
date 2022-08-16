package vn.cpa.api.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.cpa.api.entity.Book;
import vn.cpa.api.repository.book.BookRepository;
import vn.cpa.api.request.BookCreateRequest;
import vn.cpa.api.request.BookUpdateRequest;
import vn.cpa.api.util.DbConstant;

@Service
@Transactional
@Log4j2
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public void create(BookCreateRequest request) {
        Book book = new Book();
        book.setBookName(request.getName().trim());
        book.setAmount(request.getAmount());
        book.setAuthorId(request.getAuthorId());
        book.setCompanyId(request.getPublishCompany());
        book.setTypeBookId(request.getTypeBookId());
        book.setPublishingYear(request.getPublishYear());
        book.setPrice(request.getPrice());
        book.setStatus(DbConstant.STATUS_ACTIVE);
        book.setPageNumber(request.getPageNumber());

        bookRepository.save(book);
    }

    public void update(BookUpdateRequest request) {
        Book book = bookRepository.findById(request.getId()).get();
        book.setBookName(request.getName().trim());
        book.setAmount(request.getAmount());
        book.setAuthorId(request.getAuthorId());
        book.setCompanyId(request.getPublishCompany());
        book.setTypeBookId(request.getTypeBookId());
        book.setPublishingYear(request.getPublishYear());
        book.setPrice(request.getPrice());
        book.setStatus(DbConstant.STATUS_ACTIVE);
        book.setPageNumber(request.getPageNumber());

        bookRepository.save(book);
    }

    public Book findDetail(Long id) {
        return bookRepository.findById(id).get();
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
}
