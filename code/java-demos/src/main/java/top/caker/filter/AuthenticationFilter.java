package top.caker.filter;

/**
 * AuthenticationFilter
 *
 * @author cakeralter
 * @date 2022/6/19
 * @since 1.0
 */
public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(String request, String response, FilterChain chain) {
        System.out.println("AuthenticationFilter.doFilter before...");
        chain.doFilter(request, response, chain);
        System.out.println("AuthenticationFilter.doFilter after...");
    }
}
