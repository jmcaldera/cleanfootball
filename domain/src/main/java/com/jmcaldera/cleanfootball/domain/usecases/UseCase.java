package com.jmcaldera.cleanfootball.domain.usecases;

/**
 * Created by jmcaldera on 17/07/2017.
 */

public interface UseCase<P> {

    interface Callback {

        void onSuccess();

        void onError();
    }

    void execute(P parameter, Callback callback);
}
