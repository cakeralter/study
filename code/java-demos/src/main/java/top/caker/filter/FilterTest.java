package top.caker.filter;

/**
 * FilterTest
 *
 * @author cakeralter
 * @date 2022/6/19
 * @since 1.0
 */
public class FilterTest {

    public static void main(String[] args) {
        FilterChain chain = new FilterChain();
        chain.setTarget(new Target());

        chain.addFilter(new RateLimitFilter());
        chain.addFilter(new AuthenticationFilter());

        chain.doFilter("req", "res", chain);
    }
}
