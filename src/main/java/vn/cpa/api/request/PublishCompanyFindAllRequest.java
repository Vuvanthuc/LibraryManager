package vn.cpa.api.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PublishCompanyFindAllRequest extends PageBaseRequest {

    private String keyword;
}
