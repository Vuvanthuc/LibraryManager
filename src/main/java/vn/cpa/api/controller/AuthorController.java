package vn.cpa.api.controller;

import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j2;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.cpa.api.dto.ApiPageDto;
import vn.cpa.api.dto.ApiResponseDto;
import vn.cpa.api.exception.NotFoundException;
import vn.cpa.api.request.AuthorCreateRequest;
import vn.cpa.api.request.AuthorFindAllRequest;
import vn.cpa.api.request.AuthorUpdateRequest;
import vn.cpa.api.response.AuthorFindAllResponse;
import vn.cpa.api.response.AuthorFindDetailResponse;
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

    @GetMapping
    public ResponseEntity<?> findAll(@And({
            @Spec(path = "page", params = "page", spec = Like.class),
            @Spec(path = "size", params = "size", spec = Like.class),
            @Spec(path = "sortOrder", params = "sortOrder", spec = Like.class),
            @Spec(path = "sortField", params = "sortField", spec = Like.class),
            @Spec(path = "keyword", params = "keyword", spec = Like.class)
    })AuthorFindAllRequest request) throws NotFoundException {
        Page<AuthorFindAllResponse> response =  authorService.findAll(request);
        return ApiResponseDto.ok(ApiPageDto.build(response), "Thành công");
    }

    @GetMapping("find-detail")
    public ResponseEntity<?> findDetail(@RequestParam Long id) {
        AuthorFindDetailResponse response = authorService.findDetail(id);
        return ApiResponseDto.ok(response, "Lấy ra chi tiết thành công");
    }
}
