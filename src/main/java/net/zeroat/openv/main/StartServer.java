package net.zeroat.openv.main;

import java.io.File;

import org.apache.catalina.startup.Tomcat;

public class StartServer {
    private StartServer() {
    }

    public static void main(String[] args) throws Exception {
        
        // We will set the basedir Systemproperty accordingly when running the Program
        // Actually the mavenplugin appassembler will do this for us
        String basedir = System.getProperty("basedir");
        if (null == basedir)
        	basedir = "target/openv";
        String webappLocation = new File(basedir + "/webapp").getAbsolutePath();
        int port = 8088;

        Tomcat tomcat = new Tomcat();
        tomcat.setPort(port);
        tomcat.addWebapp("/", webappLocation);
        tomcat.start();
        tomcat.getServer().await();
    }
}
