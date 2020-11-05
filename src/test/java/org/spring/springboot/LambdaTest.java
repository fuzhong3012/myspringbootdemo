package org.spring.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.spring.springboot.services.LambdaService;
import org.spring.springboot.services.lambda.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @Author : fuzhong
 * @CreateTime : 2020/4/24 15:46
 * @Description :
 **/
//@RunWith(SpringRunner.class)
@SpringBootTest
public class LambdaTest {

    public class Book {
        private String bookNames;
        private int id;

        public Book(){
            super();
        }

        public Book(String bookNames, int id){
            super();
            this.bookNames = bookNames;
            this.id = id;
        }

        public String getBookNames() {
            return bookNames;
        }

        public void setBookNames(String bookNames) {
            this.bookNames = bookNames;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    @Test
    public void test (){
        /*LambdaService<String> lambdaService = (String name) -> {
            System.out.println("Lambda表达式的测试：" + name);
            return false;
        };
        lambdaService.test("fuzhong");*/

        /*LambdaService<String> lambdaService = name -> true;
        lambdaService.test("fuzhong");
        Predicate<Integer> predicate = integer -> false;
        new ArrayList<String>().forEach(n -> System.out.println(n));*/

        /*List<Book> list = Arrays.asList(new Book("aa",1), new Book("bb",2),new Book("cc",3),new Book("dddd",4));
        List<String> bookNames = list.stream().map(Book::getBookNames).collect(Collectors.toList());
        bookNames.stream().forEach(System.out::println);*/

        /*Thread t = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(2 + ":" + i);
            }
        });
        t.start();*/

        /*//计算 count, min, max, sum, and average for numbers
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        IntSummaryStatistics stats = numbers
                .stream()
                .mapToInt((x) -> x)
                .summaryStatistics();
        System.out.println("List中最大的数字 : " + stats.getMax());
        System.out.println("List中最小的数字 : " + stats.getMin());
        System.out.println("所有数字的总和   : " + stats.getSum());
        System.out.println("所有数字的平均值 : " + stats.getAverage());*/

        /**
         * 1.简化参数类型，可以不写参数类型，但是必须所有参数都不写
         * 2.简化参数小括号，如果只有一个参数则可以省略参数小括号
         * 3.简化方法体大括号，如果方法体只有一条语句，则可以省略方法体大括号
         * 4.如果方法体只有一条语句，并且是return语句，则可以圣洛方法体大括号和return关键字
         */
        NoReturnMultiParam noReturnMultiParam = (int a, int b) -> {
            System.out.println("NoReturnMultiParam" + a + b);
        };
        noReturnMultiParam.method(1,2);

        NoReturnNoParam noReturnNoParam = () -> {
            System.out.println("NoReturnNoParam");
        };
        noReturnNoParam.method();

        NoReturnOneParam noReturnOneParam = (int a) -> {
            System.out.println("NoReturnOneParam" + a);
        };
        noReturnOneParam.method(1);

        System.out.println("--------------------------------");

        ReturnMultiParam returnMultiParam = (int a, int b) -> {
            System.out.println("ReturnMultiParam" + a + b);
            return 1111;
        };
        System.out.println(returnMultiParam.method(11,22));

        ReturnNoParam returnNoParam = () -> {
            System.out.println("ReturnNoParam");
            return 2222;
        };
        System.out.println(returnNoParam.method());

        ReturnOneParam returnOneParam = (int a) -> {
            System.out.println("ReturnOneParam" + a);
            return 3333;
        };
        System.out.println(returnOneParam.method(11));

        /**
         * lambda表达式引用方法
         * 有时我们不是必须要自己重写某个匿名内部类的方法，我们可以利用lambda表达式的接口快速指向一个已经被实现的方法。
         * 语法    方法归属者::方法名
         *      静态方法的归属者为类名
         *      普通方法的归属者为对象
         */

    }

}
