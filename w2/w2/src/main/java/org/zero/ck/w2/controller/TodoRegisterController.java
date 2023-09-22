package org.zero.ck.w2.controller;

import lombok.extern.log4j.Log4j2;
import org.zero.ck.w2.Service.TodoService;
import org.zerock.jdbcex.dto.TodoDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "TodoRegisterController", urlPatterns = "/todo/register")
@Log4j2
public class TodoRegisterController extends HttpServlet {

    private TodoService todoService = TodoService.INSTANCE;
    private final DateTimeFormatter DATEFOFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        log.info("/todo/register GET........");


        HttpSession session = req.getSession();


        if (session.isNew()){//기존에 JSESSIONID가 없는 새로운 사용자!
            log.info("JSESSIONID 쿠키가 새로 만들어진 사용자");
            resp.sendRedirect("/login");
            return;
        }
        if (session.getAttribute("loginInfo")== null){
            log.info("로그인한 정보가없는사용자");
            resp.sendRedirect("/login");
            return;
        }
        //정상적인경우는 입력화면으로
        req.getRequestDispatcher("/WEB-INF/todo/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        TodoDTO todoDTO = TodoDTO.builder()
                .title(req.getParameter("title"))
                .dueDate(LocalDate.parse(req.getParameter("dueDate"), DATEFOFormatter))
                .build();

        log.info("/todo/register POST......");
        log.info(todoDTO);

        try{
            todoService.register(todoDTO);
        }catch (Exception e){
            e.printStackTrace();
        }
        resp.sendRedirect("/todo/list");




    }


}
