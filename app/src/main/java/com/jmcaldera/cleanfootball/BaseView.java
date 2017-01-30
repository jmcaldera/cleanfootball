package com.jmcaldera.cleanfootball;

/**
 * Created by jmcaldera on 29/01/2017.
 */

public interface BaseView<T extends BasePresenter> {

    void setPresenter(T presenter);
}
