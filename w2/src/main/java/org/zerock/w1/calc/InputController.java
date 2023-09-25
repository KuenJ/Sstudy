package org.zerock.w1.calc;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
// 이것이 컨트롤로 뷰를 호출한 코드
@WebServlet(name = "InputController",urlPatterns = "/calc/input")
public class InputController extends HttpServlet {

    @Override
     protected  void  doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        System.out.println("InputController..doGet...");

        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/calc/input.jsp");

        dispatcher.forward(req,resp);




    }
}
