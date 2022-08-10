package vn.cpa.api.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageBaseRequest {

    private Integer page;
    private Integer size;

    @JsonProperty("sort_field")
    private String sortField;

    @JsonProperty("sort_order")
    private String sortOrder;
}
