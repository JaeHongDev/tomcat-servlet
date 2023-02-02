import java.io.File;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;

public class Main{
    public static void main(String ...args){
        String webappDirLocation = "webapp/";
        Tomcat tomcat = new Tomcat();
        String webPort = System.getenv("PORT");
        if (webPort == null || webPort.isEmpty()) {
            webPort = "8081";
        }

        tomcat.setPort(Integer.valueOf(webPort));
        Connector connector = tomcat.getConnector();
        connector.setURIEncoding("UTF-8");
        tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());
        System.out.println("configuring app with basedir: "
                + new File("./" + webappDirLocation).getAbsolutePath());
        try {
            tomcat.start();
        } catch (LifecycleException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        tomcat.getServer().await();
    }
}