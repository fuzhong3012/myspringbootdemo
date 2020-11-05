package org.spring.springboot.services;

import jdk.nashorn.internal.objects.annotations.Function;

/**
 * @Author : fuzhong
 * @CreateTime : 2020/4/24 15:44
 * @Description :
 **/
@FunctionalInterface
public interface LambdaService<T> {

    boolean test (T name);
}
