package core;

import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.github.rholder.retry.StopStrategies;
import com.github.rholder.retry.WaitStrategies;

import java.util.concurrent.TimeUnit;

public class RetryCaller {
    public static <R, E extends Exception> Retryer<R> retry(Class<E> exceptionType) {
        return retry(exceptionType, 1);
    }

    public static <R, E extends Exception> Retryer<R> retry(Class<E> exceptionType, Integer waitTime) {
        return RetryerBuilder.<R>newBuilder()
                .retryIfExceptionOfType(exceptionType)
                .withWaitStrategy(WaitStrategies.fibonacciWait(waitTime, TimeUnit.MINUTES))
                .withStopStrategy(StopStrategies.stopAfterDelay(waitTime, TimeUnit.MINUTES))
                .build();
    }

    public static <R, E extends Exception> Retryer<R> retry(Class<E> exceptionType, Integer waitTime, TimeUnit timeUnit) {
        return RetryerBuilder.<R>newBuilder()
                .retryIfExceptionOfType(exceptionType)
                .withWaitStrategy(WaitStrategies.fibonacciWait(waitTime, timeUnit))
                .withStopStrategy(StopStrategies.stopAfterDelay(waitTime, timeUnit))
                .build();
    }
}
