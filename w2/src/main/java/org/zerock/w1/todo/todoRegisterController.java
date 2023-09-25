package org.zerock.w1.todo;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "todoRegisterController", urlPatterns = "/todo/register")
public class todoRegisterController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        System.out.println("입력 화면 볼수 있도록 구성");


        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/todo/register.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req,HttpServletResponse resp) throws  IOException, ServletException{
        System.out.println("입력을 처리 하고 목록페이지로 이동");

//        브라우저를 호출해야하는 주소
        resp.sendRedirect("/todo/list");
    }

}
