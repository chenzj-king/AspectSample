package com.dreamliner.lib.aspectj.sample.aspectj;

import android.view.View;

import com.dreamliner.lib.aspectj.sample.R;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class SingleClickAspectj {

    public static final int MIN_CLICK_DELAY_TIME = 500;
    static int TIME_TAG = R.id.click_time;

    @Pointcut("execution(@com.dreamliner.lib.aspectj.sample.annotation.SingleClick * *(..))")//方法切入点
    public void methodAnnotated() {
    }

    @Around("methodAnnotated()")//在连接点进行方法替换
    public void aroundJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        View view = null;
        for (Object arg : joinPoint.getArgs()) {
            if (arg instanceof View) view = ((View) arg);
        }
        if (view != null) {
            Object tag = view.getTag(TIME_TAG);
            long lastClickTime = (tag != null) ? (long) tag : 0;
            long currentTime = System.currentTimeMillis();
            //过滤掉600毫秒内的连续点击
            if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
                view.setTag(TIME_TAG, currentTime);
                //执行原方法
                joinPoint.proceed();
            }
        }
    }
}
