package cc.caker.boot.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author cakeralter
 * @date 2020/9/1
 */
@Getter
@RequiredArgsConstructor
public enum RedisEnum {
    SPIKE_HASH_KEY("SPIKE_HASH_KEY"),
    SPIKE_ACCESS_KEY("SPIKE_ACCESS_KEY");

    private final String key;
}
