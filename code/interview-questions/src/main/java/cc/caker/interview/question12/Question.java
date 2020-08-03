package cc.caker.interview.question12;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author cakeralter
 * @date 2020/8/3
 */
public class Question {

    public static void main(String[] args) {
        Set<Person> set = new HashSet<>();
        Person p1 = new Person(1, "wangmin");
        Person p2 = new Person(2, "wangjie");

        set.add(p1);
        set.add(p2);
        // [Person{id=1, name='wangmin'}, Person{id=2, name='wangjie'}]
        System.out.println(set);
        System.out.println("--------------");

        p1.name = "luqian";
        set.remove(p1);
        // p1的hashcode改变了，remove的时候大概率不在数组同一索引处
        // [Person{id=1, name='luqian'}, Person{id=2, name='wangjie'}]
        System.out.println(set);
        System.out.println("--------------");

        set.add(new Person(1, "luqian"));
        // [Person{id=1, name='luqian'}, Person{id=2, name='wangjie'}, Person{id=1, name='luqian'}]
        System.out.println(set);
        System.out.println("--------------");

        set.add(new Person(1, "wangmin"));
        // 说明HashSet是先根据hashCode方法确定数组下标，再根据hashCode和equals方法确认位置
        // [Person{id=1, name='luqian'}, Person{id=1, name='wangmin'}, Person{id=2, name='wangjie'}, Person{id=1, name='luqian'}]
        System.out.println(set);
    }
}

class Person {

    int id;
    String name;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id &&
                Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
