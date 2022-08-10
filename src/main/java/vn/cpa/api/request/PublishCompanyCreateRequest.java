package vn.cpa.api.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PublishCompanyCreateRequest {

    private String name;
    private String code;
    private String address;
    private String email;
}
