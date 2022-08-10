package vn.cpa.api.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorFindAllResponse {
    private Long id;
    private String code;
    private String name;
    private String address;
    private String position;
    private String note;
}
