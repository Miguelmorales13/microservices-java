package micro.mike.products.configurations;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Log4j2
public class ExecutionTimeTrackerAdvice {

    @Around("@annotation(micro.mike.commons.aspects.TrackTime)")
    Object trackTime(ProceedingJoinPoint pjp) throws Throwable {
        String[] className = pjp.getThis().getClass().getSimpleName().split("[$$]");
        String beforeEntry = MDC.get("entry");
        String beforePath = MDC.get("path");
        MDC.put("entry", className[0] + "-" + pjp.getSignature().getName());
        MDC.put("path", "PROCESS");
        log.info("[Entry: {}] ", pjp.getArgs());
        long startTime = System.currentTimeMillis();
        Object proceed = pjp.proceed();
        long endsTime = System.currentTimeMillis();
        log.info("[Output: {}][{}ms] ", proceed, endsTime - startTime);
        MDC.put("entry", beforeEntry);
        MDC.put("path", beforePath);
        return proceed;
    }
}
