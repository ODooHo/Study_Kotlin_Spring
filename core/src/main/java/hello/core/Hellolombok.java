package hello.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Hellolombok {

    private int age;
    private String name;

    public static void main(String[] args) {
        Hellolombok hellolombok = new Hellolombok();
        hellolombok.setAge(10);
        hellolombok.setName("hello");
        String name1 = hellolombok.getName();
        System.out.println("name1 = " + name1);
        System.out.println("hellolombok = " + hellolombok);
    }


}
