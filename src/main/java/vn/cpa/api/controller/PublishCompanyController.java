package vn.cpa.api.controller;

import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.cpa.api.dto.ApiResponseDto;
import vn.cpa.api.request.PublishCompanyCreateRequest;
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
}
