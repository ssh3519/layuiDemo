package com.xinhai.demo01;

import com.xinhai.demo01.bean.User;
import com.xinhai.demo01.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class Demo01ApplicationTests {

    @Autowired
    UserService userService;
    @Test
    void contextLoads() {
//        User user = new User();
//        user.setId(4);
//        user.setAge(24);
//        userService.update(user);
//        userService.selectAll().forEach(System.out::println);
//        userService.delete(4);
        System.out.println(userService.selectById(1));
    }

    @Test
    void name() {
//        List list = new ArrayList<>(Arrays.asList("a", "b", "c"));
//        list.add("d");
//        System.out.println(list);
        Integer [] myArray = { 1, 2, 3 };
        List myList = Arrays.stream(myArray).collect(Collectors.toList());
        myList.add(4);
        System.out.println(myList);
//基本类型也可以实现转换（依赖boxed的装箱操作）
//        int [] myArray2 = { 1, 2, 3 };
//        List myList = Arrays.stream(myArray2).boxed().collect(Collectors.toList());
    }

    @Test
    void name2() {
        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        /*Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()){
            String item = iterator.next();
            if ("1".equals(item)){
                iterator.remove();
            }
        }
        System.out.println(list);*/
        for (String item : list) {
            if ("2".equals(item)){
                list.remove(item);
            }
        }
        System.out.println(list);
    }
}
