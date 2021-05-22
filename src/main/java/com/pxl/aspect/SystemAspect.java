package com.pxl.aspect;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pxl.common.annotation.Log;
import com.pxl.common.utils.RequestUtil;
import com.pxl.entity.DTO.Systemlog;
import com.pxl.service.LogService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
@RequiredArgsConstructor
public class SystemAspect {

    //得到Logger对象，定位到该类。
    private static final Logger logger = LoggerFactory.getLogger(SystemAspect.class);

    private final LogService logService;

    /**
     * 配置切入点
     */
    @Pointcut("@annotation(com.pxl.common.annotation.Log)")
    public void logPointcut(){
        //该方法无方法体，主要为了让同类中其他方法使用此切入点（添加了一个注解Log）
    }


    /**
     * 配置后置通知，使用在方法logPointcut（）上注册的切入点
     */
    @After("logPointcut()")
    public void writeLog(JoinPoint joinPoint) throws JsonProcessingException {
        Systemlog systemlog = new Systemlog();
        //1、设置日志类型
        systemlog.setLogType("INFO");
        //2、设置时间
        systemlog.setOptime(new Date());
        //3、设置ip地址 添加拦截器->获取请求对象
        HttpServletRequest request = RequestUtil.getRequest();
        if(request != null){
            String IP = request.getRemoteAddr();
            systemlog.setIp(IP);
        }
        //4、获取方法
        //获取目标执行方法的全路径
        String pathName = joinPoint.getTarget().getClass().getName();
        //获取方法名称：
        String methodName = joinPoint.getSignature().getName();
        String allName = pathName + ":" + methodName;
        systemlog.setFunc(allName);
        //5、获取方法参数
        ObjectMapper objectMapper = new ObjectMapper();
        String params = objectMapper.writeValueAsString(joinPoint.getArgs());
        systemlog.setParams(params);
        //6、获取注解的description
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Log annotation = method.getAnnotation(Log.class);
        String value = annotation.value();
        systemlog.setDescription(value);
        //7、获取Browser
        String browser = RequestUtil.getBrowser(request);
        systemlog.setBrowser(browser);
        /*插入到数据库中*/
        logService.insert(systemlog);
    }

    /**
     * 配置异常通知
     */
    @AfterThrowing(value = "logPointcut()",throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint,Throwable e) throws JsonProcessingException {
        Systemlog systemlog = new Systemlog();
        systemlog.setLogType("ERROR");
        systemlog.setOptime(new Date());
        //3、设置ip地址 添加拦截器->获取请求对象
        HttpServletRequest request = RequestUtil.getRequest();
        if(request != null){
            String IP = request.getRemoteAddr();
            systemlog.setIp(IP);
        }
        //4、获取方法
        //获取目标执行方法的全路径
        String pathName = joinPoint.getTarget().getClass().getName();
        //获取方法名称：
        String methodName = joinPoint.getSignature().getName();
        String allName = pathName + ":" + methodName;
        systemlog.setFunc(allName);
        //5、获取方法参数
        ObjectMapper objectMapper = new ObjectMapper();
        String params = objectMapper.writeValueAsString(joinPoint.getArgs());
        systemlog.setParams(params);
        //6、获取注解的description
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Log annotation = method.getAnnotation(Log.class);
        String value = annotation.value();
        systemlog.setDescription(value);
        //7、获取Browser
        String browser = RequestUtil.getBrowser(request);
        systemlog.setBrowser(browser);
        //8、设置异常信息：
        systemlog.setExceptionDetail(e.getMessage());
        /*插入到数据库中*/
        logService.insert(systemlog);
    }


}
