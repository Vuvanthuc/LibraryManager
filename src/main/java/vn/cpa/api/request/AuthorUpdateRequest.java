package vn.cpa.api.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorUpdateRequest {

    private Long id;
    private String name;
    private String address;
    private String note;
}
