package vn.cpa.api.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PublishCompanyUpdateRequest {

    private Long id;
    private String name;
    private String code;
    private String address;
    private String email;
}
