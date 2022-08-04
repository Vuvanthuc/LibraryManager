package vn.cpa.api.controller;

import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.cpa.api.dto.ApiResponseDto;
import vn.cpa.api.exception.NotFoundException;
import vn.cpa.api.request.AuthorCreateRequest;
import vn.cpa.api.request.AuthorUpdateRequest;
import vn.cpa.api.service.AuthorService;

@Api(tags = "API Author")
@RestController
@RequestMapping("/api/v1/author")
@Validated
@Log4j2
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody AuthorCreateRequest request) {
        authorService.create(request);
        return ApiResponseDto.createdWithMessage("Lưu tác giả thành công", HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody AuthorUpdateRequest request) {
        authorService.update(request);
        return ApiResponseDto.createdWithMessage("Lưu tác giả thành công", HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam Long id) throws NotFoundException {
        authorService.delete(id);
        return ApiResponseDto.createdWithMessage("Lưu tác giả thành công", HttpStatus.CREATED);
    }

//    @GetMapping
//    @DeleteMapping
//    public ResponseEntity<?> findAll(@RequestParam Long id) throws NotFoundException {
//        authorService.findAll(id);
//        return ApiResponseDto.createdWithMessage("Lưu tác giả thành công", HttpStatus.CREATED);
//    }
}
