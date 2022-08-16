package vn.cpa.api.controller;

import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j2;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.cpa.api.dto.ApiResponseDto;
import vn.cpa.api.request.BookCreateRequest;
import vn.cpa.api.request.BookFindAllRequest;
import vn.cpa.api.request.BookUpdateRequest;
import vn.cpa.api.service.BookService;

@Api(tags = "API Book")
@RestController
@RequestMapping("/api/v1/book")
@Validated
@Log4j2
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody BookCreateRequest request) {
        bookService.create(request);
        return ApiResponseDto.createdWithMessage("Thành công", HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody BookUpdateRequest request) {
        bookService.update(request);
        return ApiResponseDto.createdWithMessage("Thành công", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> findDetail(@RequestParam Long id) {
        return ApiResponseDto.ok(bookService.findDetail(id), "Thành công");
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam Long id) {
        bookService.delete(id);
        return ApiResponseDto.createdWithMessage("Thành công", HttpStatus.OK);
    }

    @GetMapping("find-all")
    public ResponseEntity<?> findAll(@And({
            @Spec(path = "page", params = "page", spec = Like.class),
            @Spec(path = "size", params = "size", spec = Like.class),
            @Spec(path = "sortOrder", params = "sortOrder", spec = Like.class),
            @Spec(path = "sortField", params = "sortField", spec = Like.class),
            @Spec(path = "keyword", params = "keyword", spec = Like.class)
    }) BookFindAllRequest request) {
//        Page<>
        return null;
    }


}
