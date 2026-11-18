package com.mycompany.personalexpensetracker;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;

public class MyContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // No specific startup action needed
    }

    @Override
public void contextDestroyed(ServletContextEvent sce) {
    AbandonedConnectionCleanupThread.checkedShutdown();
}
}
