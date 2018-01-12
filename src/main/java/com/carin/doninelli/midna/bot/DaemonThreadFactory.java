package com.carin.doninelli.midna.bot;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ThreadFactory;

public final class DaemonThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(@NotNull Runnable r) {
        Thread thread = new Thread(r);
        thread.setDaemon(true);
        return thread;
    }
}
