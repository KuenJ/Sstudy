package org.zerock.dao.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.zerock.jdbcex.Service.TodoService;
import org.zerock.jdbcex.dto.TodoDTO;

import java.time.LocalDate;

public class TodoServiceTests {

    private TodoService todoService;

    @BeforeEach
    public void ready() {
        todoService = TodoService.INSTANCE;


    }
    @Test
    public  void testRegister()throws  Exception{

        TodoDTO todoDTO = TodoDTO.builder()
                .title("JDBC Test Title")
                .dueDate(LocalDate.now())
                .build();
        todoService.register(todoDTO);
    }


}
