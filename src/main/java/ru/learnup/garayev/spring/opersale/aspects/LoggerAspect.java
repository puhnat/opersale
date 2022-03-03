package ru.learnup.garayev.spring.opersale.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.sql.Array;
import java.util.Arrays;

@Component
@Aspect
public class LoggerAspect {

    //@Pointcut("execution(* ru.learnup.garayev.spring.opersale.service.TheatreSeason.*(..))")
    @Pointcut("@annotation(ru.learnup.garayev.spring.opersale.annotations.Loggable)")
    public void operSaleLog(){};

    @AfterReturning("operSaleLog()")
    public void afterSuccess(JoinPoint point){
        StringBuilder sb = new StringBuilder();
        Object[] args = point.getArgs();
        for (int i = 0; i < args.length; i++){
            sb.append("[" + args[i].toString() + "]");
        }
        System.out.println("Завершился метод : " + point.getSignature().getName() + " с параметрами (" + sb.toString() + ")");
    }

    @AfterThrowing("operSaleLog()")
    public void afterError(JoinPoint point){
        StringBuilder sb = new StringBuilder();
        Object[] args = point.getArgs();
        for (int i = 0; i < args.length; i++){
            sb.append("[" + args[i].toString() + "]");
        }
        System.out.println("Метод : " + point.getSignature().getName() + " с параметрами (" + sb.toString() + ") завершился не удачно");
    }

    public void print(String msg){
        System.out.println(msg);
    }
}
