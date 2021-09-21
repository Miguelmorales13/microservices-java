package micro.mike.security.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Aspect
@Component
public class ExecutionTimeTrackerAdvice {
    Logger logger =LoggerFactory.getLogger(ExecutionTimeTrackerAdvice.class);
    @Around("@annotation(micro.mike.security.aspects.TrackTime)")
    Object trackTime(ProceedingJoinPoint pjp) throws Throwable {
        long startTime= System.currentTimeMillis();
        Object proceed = pjp.proceed();
        logger.info("getArgs: {}: ",pjp.getArgs());
        logger.info("getTarget: {}: ",pjp.getTarget());
        logger.info("getSignature: {}: ",pjp.getSignature());
        logger.info("getThis: {}: ",pjp.getThis());
        logger.info("getThis|getClass: {}: ",pjp.getThis().getClass());
        for (Field f:pjp.getThis().getClass().getDeclaredFields()) {
        logger.info("getDeclaredFields: {}: {} ",f.getName(),f.getDeclaringClass());

        }
        logger.info("getKind: {}: ",pjp.getKind());
        logger.info("getStaticPart: {}: ",pjp.getStaticPart());
        logger.info("getClass: {}: ",pjp.getClass());
        logger.info("getSourceLocation: {}: ",pjp.getSourceLocation());
        long endsTime= System.currentTimeMillis();
        return proceed;
    }
}
