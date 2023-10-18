package org.zero.ck.w2.listener;


import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


/**
 * W2ppListenr는 ServletContextListner 인터페이스를 구현하도록 구현하고 ,@WEBListner라는 어노테이션을 추가한다
 * 클래스내부에는 contextInitiallized()와 contextDestroyed()를 오버라이드 합니다 .
 * */
@WebListener
@Log4j2
public class W2AppListener implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent sce){


        log.info("-------------init------------------");
        log.info("-------------init------------------");
        log.info("-------------init------------------");


        ServletContext servletContext =sce.getServletContext();
        servletContext.setAttribute("appName","W2");

    }
    @Override
    public  void contextDestroyed(ServletContextEvent sce){
        log.info("-------------destroy------------------");
        log.info("-------------destroy------------------");
        log.info("-------------destroy------------------");
    }
}
