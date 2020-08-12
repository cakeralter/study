package top.caker.enums;

import org.junit.Test;

import java.util.Arrays;

/**
 * 枚举测试
 *
 * @author cakeralter
 * @date 2020/8/1
 */
public class EnumTest {

    @Test
    public void testCustomEnum() {
        System.out.println(App.WE_CHAT);
        System.out.println(App.QQ);
    }

    @Test
    public void testEnum() {
        System.out.println(Arrays.toString(GameConsole.values()));
    }
}

/**
 * jdk5之前自定义枚举类
 */
class App {

    public final static App WE_CHAT = new App("WE_CHAT", "Android");
    public final static App QQ = new App("QQ", "IOS");

    private final String name;
    private final String platform;

    private App(String name, String platform) {
        this.name = name;
        this.platform = platform;
    }

    public String getName() {
        return name;
    }

    public String getPlatform() {
        return platform;
    }

    @Override
    public String toString() {
        return name;
    }
}

enum GameConsole {
    SWITCH("任天堂"),
    PS4("索尼"),
    XBOX("微软");

    private String platform;

    GameConsole(String platform) {
        this.platform = platform;
    }

    public String getPlatform() {
        return platform;
    }
}
