package org.zerock.w1.todo.service;

import com.sun.tools.javac.comp.Todo;
import org.zerock.w1.todo.dto.TodoDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public enum  TodoService {
    INSTANCE;
    public  void register(TodoDTO todoDTO){                                /*TodoService ENUM 타입: INSTANCE라는 하나의 ENUM 상수를 가지고 있습니다.
                                                                이렇게 하나의 상수만 가지는 ENUM은 싱글톤 패턴을 구현하는 데 사용됩니다. 즉,
                                                                TodoService는 하나의 인스턴스만을 가질 수 있습니다.
                                                                register 메서드: TodoDTO를 매개변수로 받아서 해당 TODO 항목을 등록하는 메서드입니다.
                                                                현재는 매개변수로 받은 TodoDTO 객체를 콘솔에 출력하는 기능만을 수행합니다.*/

        System.out.println("DEBUG....."+todoDTO);


    }
    public List<TodoDTO> getList(){
                                                                /*getList 메서드: 0부터 9까지의 숫자 범위를 사용하여 TodoDTO의 리스트를 생성하는 메서드입니다.
                                                                                        각 TodoDTO 객체는 번호(tno), 제목(title), 그리고 현재 날짜(LocalDate.now())를 설정하여 생성됩니다.
                                                                            이렇게 생성된 todo항목들은 list로반환된다.*/
            List<TodoDTO> todoDTOS = IntStream.range(0,10).mapToObj(i ->{

                TodoDTO dto = new TodoDTO();
                dto.setTno((long)i);
                dto.setTitle("Todo.."+i);
                dto.setDueDate(LocalDate.now());

                return  dto;

            }).collect(Collectors.toList());

        return  todoDTOS;
    }
        public  TodoDTO get(Long tno){


          TodoDTO dto =new TodoDTO();
          dto.setTno(tno);
          dto.setTitle("Sample Todo");
          dto.setDueDate(LocalDate.now());
          dto.setFinished(true);
          return dto;
        }
}
