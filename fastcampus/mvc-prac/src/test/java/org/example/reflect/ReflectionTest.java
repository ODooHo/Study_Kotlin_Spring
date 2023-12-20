package org.example.reflect;

import org.example.reflect.annotation.Controller;
import org.example.reflect.annotation.Service;
import org.example.reflect.model.User;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

/**
 * @Controller 애노테이션이 설정돼있는 모든 클래스를 찾아서 출력한다.
 */
public class ReflectionTest {


    private static final Logger logger = LoggerFactory.getLogger(ReflectionTest.class);

    @Test
    void controllerScan() {
        Set<Class<?>> beans = getTypesAnnotatedWith(List.of(Controller.class,Service.class));


        logger.debug("beans: [{}]",beans);
    }

    @Test
    void showClass(){
        Class<User> clazz = User.class;

        logger.debug(clazz.getName());

        logger.debug("User all declared fields: [{}]", Arrays.stream(clazz.getDeclaredFields()).collect(Collectors.toList()));
        logger.debug("User all declared constructors: [{}]", Arrays.stream(clazz.getDeclaredConstructors()).collect(Collectors.toList()));
        logger.debug("User all declared methods: [{}]", Arrays.stream(clazz.getDeclaredMethods()).collect(Collectors.toList()));
    }


    /**
     * 힙 영역에서 클래스를 가져오는 3가지 방법
     */
    @Test
    void load() throws ClassNotFoundException {
        //1
        Class<User> clazz = User.class;

        //2
        User user = new User("test","오두호");
        Class<? extends User> clazz2 = user.getClass();

        //3
        Class<?> clazz3 = Class.forName("org.example.reflect.model.User");

        logger.debug("clazz [{}]",clazz);
        logger.debug("clazz [{}]",clazz2);
        logger.debug("clazz [{}]",clazz3);

        assertThat(clazz == clazz2).isTrue();
        assertThat(clazz == clazz3).isTrue();
        assertThat(clazz2 == clazz3).isTrue();



    }

    private Set<Class<?>> getTypesAnnotatedWith(List<Class<? extends Annotation>>annotations) {
        Reflections reflections = new Reflections("org.example");

        Set<Class<?>> beans = new HashSet<>();
        annotations.forEach(annotation -> beans.addAll(reflections.getTypesAnnotatedWith(annotation)));
        return beans;
    }
}
