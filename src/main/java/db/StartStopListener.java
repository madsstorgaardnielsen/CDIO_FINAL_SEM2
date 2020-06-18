package db;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class StartStopListener {

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("Servlet has been started.");
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        DBConnection dbConnection = new DBConnection();
        dbConnection.closeDBConnection();
        System.out.println("Servlet has been stopped.");
    }
}
