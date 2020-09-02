package com.zhougl.javadesignpatterndemo.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

/**
 * @author by zhougl
 * @classname AnnotationAspect
 * @desc TODO
 * @date 2019/5/16 17:22
 */
@Aspect
@Component
public class AnnotationAspect {
    @Pointcut("execution(* com.zhougl.javadesignpatterndemo.domain..*DO(..)) && @within(com.zhougl.javadesignpatterndemo.annotation.DefaultValue)")
    private void ecoAnotationPoint() {
    }

    @Pointcut("@annotation(com.zhougl.javadesignpatterndemo.annotation.DefaultValue)")
    private void ecoAnotationPoint1() {
    }

    @Around("ecoAnotationPoint()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature)point.getSignature();
        //获取当前访问的类方法
        Method method = signature.getMethod();
        DefaultValue annotation = method.getAnnotation(DefaultValue.class);

//        Class<? extends DefaultValue> aClass = annotation.getClass();

        String value = annotation.value();
        if(StringUtils.isEmpty(value)){
            value = "";
        }

        // TODO something
        return point.proceed(); // 不调用point.proceed()不会执行目标方法
    }
}
