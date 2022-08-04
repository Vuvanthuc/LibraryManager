package vn.cpa.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public abstract class AbsPagingResponse {
    /**
     * entity count
     */
    protected Long count;
    /**
     * page number, 0 indicate the first page.
     */
    protected Long pageNumber;
    /**
     * size of page, 0 indicate infinite-sized.
     */
    protected Long pageSize;
    /**
     * Offset from the of pagination.
     */
    protected Long pageOffset;
    /**
     * the number total of pages.
     */
    protected Long pageTotal;
    /**
     * elements of page.
     */
}
