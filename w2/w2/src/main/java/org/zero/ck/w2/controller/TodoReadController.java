package org.zero.ck.w2.controller;

import lombok.extern.log4j.Log4j2;
import org.zero.ck.w2.Service.TodoService;
import org.zerock.jdbcex.dto.TodoDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "TodoReadController", urlPatterns = "/todo/read")
@Log4j2


public class TodoReadController extends HttpServlet {

    private TodoService todoService = TodoService.INSTANCE;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        try {
            // 요청 파라미터에서 'tno' 값을 읽어옵니다.
            Long tno = Long.parseLong(req.getParameter("tno"));
            // TodoService를 사용하여 'tno'에 해당하는 할 일(Todo) 항목을 가져옵니다.
            TodoDTO todoDTO = todoService.get(tno);

            //데이터담기// 가져온 데이터를 서블릿 요청 객체에 저장합니다.
            req.setAttribute("dto", todoDTO);
            // "/WEB-INF/todo/read.jsp" 경로의 JSP 페이지로 데이터를 전달하고 포워딩합니다.
            req.getRequestDispatcher("/WEB-INF/todo/read.jsp").forward(req, resp);
        } catch (Exception e) {
            // 예외 발생 시 에러 로그를 남기고 ServletException을 던집니다.
            log.error(e.getMessage());
            throw new ServletException("read error");
        }
    }
}
