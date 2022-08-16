package vn.cpa.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookUpdateRequest {

    private Long id;

    private String code;

    private String name;

    @JsonProperty("author_id")
    private Long authorId;

    @JsonProperty("publish_company")
    private Long publishCompany;

    @JsonProperty("page_number")
    private Integer pageNumber;

    @JsonProperty("type_book_id")
    private Long typeBookId;

    @JsonProperty("publish_year")
    private Integer publishYear;

    @JsonProperty("price")
    private Double price;

    private Integer amount;

    private Integer status;
}
