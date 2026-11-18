package com.mycompany.personalexpensetracker;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;

@WebListener
public class CleanupListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("✅ Context initialized. No startup cleanup needed.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println(">>> CLEANUP LISTENER: contextDestroyed called!");

        System.out.println("🔁 Cleaning up JDBC drivers...");

        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
                System.out.println("✅ Deregistered JDBC driver: " + driver);
            } catch (SQLException e) {
                System.err.println("❌ Error deregistering driver " + driver + ": " + e);
            }
        }

        try {
            AbandonedConnectionCleanupThread.checkedShutdown();
            System.out.println("✅ AbandonedConnectionCleanupThread shutdown successfully.");
        } catch (Exception e) {
            System.err.println("❌ Error shutting down cleanup thread: " + e);
        }
    }
}
