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
import vn.cpa.api.entity.PublishCompany;
import vn.cpa.api.request.AuthorFindAllRequest;
import vn.cpa.api.request.PublishCompanyCreateRequest;
import vn.cpa.api.request.PublishCompanyFindAllRequest;
import vn.cpa.api.request.PublishCompanyUpdateRequest;
import vn.cpa.api.response.PublishComFindAllResponse;
import vn.cpa.api.service.PublishCompanyService;

@Api(tags = "API Publish Company")
@RestController
@RequestMapping("/api/v1/publish")
@Validated
@Log4j2
public class PublishCompanyController {

    @Autowired
    private PublishCompanyService publishCompanyService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody PublishCompanyCreateRequest request) {
        publishCompanyService.create(request);
        return ApiResponseDto.createdWithMessage("Thành công", HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody PublishCompanyUpdateRequest request) {
        publishCompanyService.update(request);
        return ApiResponseDto.createdWithMessage("Thành công", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> findDetail(@RequestParam Long id) {
        return ApiResponseDto.ok(publishCompanyService.findDetail(id), "Thành công");
    }

    @GetMapping("/find-all")
    public ResponseEntity<?> findAll(@And({
            @Spec(path = "page", params = "page", spec = Like.class),
            @Spec(path = "size", params = "size", spec = Like.class),
            @Spec(path = "sortOrder", params = "sortOrder", spec = Like.class),
            @Spec(path = "sortField", params = "sortField", spec = Like.class),
            @Spec(path = "keyword", params = "keyword", spec = Like.class)
    }) PublishCompanyFindAllRequest request) {
        Page<PublishComFindAllResponse> response = publishCompanyService.findAll(request);
        return ApiResponseDto.ok(ApiPageDto.build(response), "Thành công");
    }
}
