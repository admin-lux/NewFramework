package com.newframework.ui.stores;

import com.newframework.ui.actions.Action;

import org.greenrobot.eventbus.EventBus;

/**
 * Flux的Store模块
 * Created by ntop on 18/12/15.
 */
public abstract class Store {

    protected Store() {
    }

    public void register(final Object view) {
        EventBus.getDefault().register(view);
    }

    public void unregister(final Object view) {
        EventBus.getDefault().unregister(view);
    }

    void emitStoreChange() {
        EventBus.getDefault().post(changeEvent());
    }

    public abstract StoreChangeEvent changeEvent();
    public abstract void onAction(Action action);

    public class StoreChangeEvent {}
}
