package vn.cpa.api.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorCreateRequest {

    private String name;
    private String address;
    private String note;
}
