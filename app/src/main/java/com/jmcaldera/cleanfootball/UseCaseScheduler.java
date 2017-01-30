package com.jmcaldera.cleanfootball;

/**
 * Created by jmcaldera on 29/01/2017.
 *
 * Interface for schedulers, see {@link UseCaseThreadPoolScheduler}.
 */

public interface UseCaseScheduler {

    void execute(Runnable runnable);

    <V extends UseCase.ResponseValue> void notifyResponse(final V response,
                                                          final UseCase.UseCaseCallback<V> useCaseCallback);

    <V extends UseCase.ResponseValue> void onError(
            final UseCase.UseCaseCallback<V> useCaseCallback);
}
