package top.caker.filter;

/**
 * RateLimitFilter
 *
 * @author cakeralter
 * @date 2022/6/19
 * @since 1.0
 */
public class RateLimitFilter implements Filter {

    @Override
    public void doFilter(String request, String response, FilterChain chain) {
        System.out.println("RateLimitFilter.doFilter before...");
        chain.doFilter(request, response, chain);
        System.out.println("RateLimitFilter.doFilter after...");
    }
}
