package top.caker.object;

import org.junit.Test;

/**
 * @author cakeralter
 * @date 2020/8/18
 */
public class ExtendTest {

    @Test
    public void testPerson() {
        Person p = new Person();
    }
}

class Person {

    Son son = new Son();

    public Person() {
        System.out.println("Person constructor!");
    }
}

class Son extends Person {

    public Son() {
        System.out.println("Son constructor!");
    }
}
