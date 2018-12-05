package cn.itcast.controller;


import cn.itcast.domain.SysLog;
import cn.itcast.service.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {


    @Autowired
    private SysLogService sysLogService;

    @Autowired
    private HttpServletRequest request;

    private Date startTime;//访问时间
    private Class executionClass;//访问的类
    private Method executionMethod;//访问的方法

    @Before("execution(* cn.itcast.controller.*.*(..)) && !execution(* cn.itcast.controller.SysLogController.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        startTime = new Date();
        //获取访问的类
        executionClass = jp.getTarget().getClass();
        //获取访问的方法名
        String methodName = jp.getSignature().getName();
        //将方法名转换为方法
        Object[] args = jp.getArgs();//获取方法参数
        if (args == null || args.length == 0){//无参
            executionMethod = executionClass.getMethod(methodName);//无参构造
        } else {
            Class[] argsClass = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                argsClass[i] = args[i].getClass();
            }
            executionMethod = executionClass.getMethod(methodName,argsClass);//有参方法
        }
    }

    @After("execution(* cn.itcast.controller.*.*(..)) && ! execution(* cn.itcast.controller.SysLogController.*(..))")
    public void doAfter(JoinPoint jp) throws Exception {
        Long time = new Date().getTime() - startTime.getTime();
        //获取URL
        //先获取类上的RequestMapping名称
        RequestMapping classAnnotation = (RequestMapping) executionClass.getAnnotation(RequestMapping.class);
        String url = "";
        if (classAnnotation != null) {
            //获取方法上的RequestMapping名称
            RequestMapping methodAnnotation = (RequestMapping) executionMethod.getAnnotation(RequestMapping.class);
            if (methodAnnotation != null){
                url = classAnnotation.value()[0]+methodAnnotation.value()[0];
            }
        }

        //获取ip
        String ip = request.getRemoteAddr();
        //获取用户名  可以从给两种途径获取
        SecurityContext context = SecurityContextHolder.getContext();
        String username = ((User) context.getAuthentication().getPrincipal()).getUsername();
        //封装进实体类
        SysLog sysLog = new SysLog();
        sysLog.setVisitTime(startTime);
        sysLog.setExecutionTime(time);
        sysLog.setIp(ip);
        sysLog.setUrl(url);
        sysLog.setUsername(username);
        sysLog.setMethod("[类名] "+executionClass.getName()+"[方法名] "+executionMethod.getName());

        sysLogService.save(sysLog);

    }
}
