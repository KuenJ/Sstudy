package org.zero.ck.w2.controller;

import lombok.extern.log4j.Log4j2;
import org.zero.ck.w2.Service.TodoService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "TodoRemoveController",urlPatterns = "/todo/remove")
@Log4j2
public class TodoRemoveController  extends HttpServlet {

        private TodoService todoService =TodoService.INSTANCE;


        @Override
        protected  void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{

            Long tno =Long.parseLong(req.getParameter("tno"));
            log.info("tno:" + tno);


            try {
                todoService.remove(tno);
            }catch (Exception e){
                log.error(e.getMessage());
                throw  new ServletException("read error");
            }
            resp.sendRedirect("/todo/list");
        }
}
