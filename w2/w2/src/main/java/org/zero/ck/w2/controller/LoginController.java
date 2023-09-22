package org.zero.ck.w2.controller;


import lombok.extern.java.Log;
import org.zero.ck.w2.Service.MemberService;
import org.zero.ck.w2.dto.MemberDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
@Log
public class LoginController  extends HttpServlet {
    @Override

    protected  void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
            log.info("login get.........");
            req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req,resp);

    }
    @Override
    protected  void doPost(HttpServletRequest req,HttpServletResponse resp) throws  IOException ,ServletException{

            log.info("login post.........");

            String mid = req.getParameter("mid");
            String mpw = req.getParameter("mpw");



        try {
            MemberDTO memberDTO = MemberService.INSTANCE.login(mid,mpw);
            HttpSession session = req.getSession();
            session.setAttribute("loginInfo",memberDTO);
            resp.sendRedirect("/todo/list");
        }catch (Exception e){
                resp.sendRedirect("/login?result=error");
        }
    }
}
