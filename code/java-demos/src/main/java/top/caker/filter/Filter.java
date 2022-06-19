package top.caker.filter;

/**
 * Filter
 *
 * @author cakeralter
 * @date 2022/6/19
 * @since 1.0
 */
public interface Filter {

    /**
     * doFilter
     *
     * @param request
     * @param response
     * @param chain
     */
    void doFilter(String request, String response, FilterChain chain);
}
