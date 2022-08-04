package vn.cpa.api.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import vn.cpa.api.response.AbsPagingResponse;

public class PageUtils {
    public static Pageable buildPage(Integer pageNo, Integer pageSize) {
        return buildPageRequest(pageNo, pageSize);
    }

    /**
     * Build page
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    private static Pageable buildPageRequest(Integer pageNo, Integer pageSize) {
        return PageRequest.of(pageNo, pageSize);
    }

    /**
     * Create response infor
     *
     * @param response
     * @return
     */
    public static HttpHeaders returnHttpHeaders(AbsPagingResponse response) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(PagingHeaders.COUNT.getName(), String.valueOf(response.getCount()));
        headers.set(PagingHeaders.PAGE_SIZE.getName(), String.valueOf(response.getPageSize()));
        headers.set(PagingHeaders.PAGE_OFFSET.getName(), String.valueOf(response.getPageOffset()));
        headers.set(PagingHeaders.PAGE_NUMBER.getName(), String.valueOf(response.getPageNumber()));
        headers.set(PagingHeaders.PAGE_TOTAL.getName(), String.valueOf(response.getPageTotal()));
        return headers;
    }
}
