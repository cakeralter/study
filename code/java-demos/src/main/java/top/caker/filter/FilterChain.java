package top.caker.filter;

import java.util.ArrayList;
import java.util.List;

/**
 * FilterChain
 *
 * @author cakeralter
 * @date 2022/6/19
 * @since 1.0
 */
public class FilterChain implements Filter {

    private final List<Filter> filters = new ArrayList<>();
    private int index = 0;
    private Target target;

    @Override
    public void doFilter(String request, String response, FilterChain chain) {
        if (index < filters.size()) {
            filters.get(index++).doFilter(request, response, chain);
        } else {
            target.invoke();
        }
    }

    public void addFilter(Filter filter) {
        filters.add(filter);
    }

    public void setTarget(Target target) {
        this.target = target;
    }
}
