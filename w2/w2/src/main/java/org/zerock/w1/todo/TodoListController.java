package org.zerock.w1.todo;


import org.zerock.w1.todo.dto.TodoDTO;
import org.zerock.w1.todo.service.TodoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "todoListController", urlPatterns = "/todo/list")

public class TodoListController extends HttpServlet {

    @Override

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("/todo/list");

        List<TodoDTO> dtoList = TodoService.INSTANCE.getList();
        req.setAttribute("list",dtoList);  //req는 서블릿 요청을 나타내는 객체인데 여기서 setAttribute메서드를 사용하여 속상을추가 list라는 이름   속성에 dto List를 추가한다 이렇게하
        // 서블릿에서 JSP나 다른 뷰페이지로 데이터를 전달할수있음.
        req.getRequestDispatcher("/WEB-INF/todo/list.jsp")
                .forward(req, resp);
    }

}
