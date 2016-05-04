package my.service;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * This advice is responsible for enforcing security and only allowing administrators
 * to modify users. Users are allowed to modify themselves.
 *
 * @author mraible
 */
public class UserSecurityAdvice implements MethodBeforeAdvice, AfterReturningAdvice {

    @Override
    public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {
        //todo_need
    }

    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        //todo_need
    }
}
