package cn.edu.pku.blog.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;


@Aspect   //进行切面操作
@Component
public class LogAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass()); //拿到日志记录器

    @Pointcut("execution(* cn.edu.pku.blog.web.*.*(..))")  //execution规定这个切面拦截哪些类，第一个*是所有的方法种类
    //cn.edu.pku.blog.web下的所有类的所有方法，任何参数
    public void log(){
    }

    //在切面之前做
    @Before("log()") //doBefore在切面log()之前执行
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + '.' + joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        RequestLog requestLog = new RequestLog(url, ip, classMethod, args);
        logger.info("Request : {}", requestLog); //在切面之前执行
    }

    @After("log()")
    public void doAfter(){
        //logger.info("-------------doAfter----------------");
    }

    //方法执行完返回的时候拦截它，比如要在日志中记录返回给了客户端什么类容就需要这个方法
    @AfterReturning(returning = "result", pointcut = "log()")
    public void doAfterReturn(Object result){
        logger.info("Result: {}", result);
    }

    private class RequestLog{
        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;

        public RequestLog(String url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }

        @Override
        public String toString() {
            return "{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }
}
