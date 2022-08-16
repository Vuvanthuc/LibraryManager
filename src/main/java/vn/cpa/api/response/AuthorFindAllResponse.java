package vn.cpa.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
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
