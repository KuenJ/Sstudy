package org.zero.ck.w2.Service;

import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.zero.ck.w2.dao.TodoDAO;
import org.zero.ck.w2.domain.TodoVO;
import org.zero.ck.w2.util.MapperUtil;
import org.zerock.jdbcex.dto.TodoDTO;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public enum TodoService {
    INSTANCE;


    private TodoDAO dao; //TodoDAO 클래스의 인스턴스를 저장하는 필드로 데이터베이스와 상호작용하기위해서 DAO를 사용
    private ModelMapper modelMapper;// ModelMapper 클래스의 인스턴스를 저장하는필드로 DTO,VO간의 데이터 변환을 처리하기위해
    //                                 매퍼를 생성

    TodoService() {
        dao = new TodoDAO();                                   //생성자는 private로 선언되어 외부에서 인스턴스 직접생성 x
        modelMapper = MapperUtil.INSTANCE.get();               //생성자 내부에서 todoDAO와 modelMapper 인스턴스를초기화하고설정
    }

    public void register(TodoDTO todoDTO) throws Exception {         //registervoid register(TodoDTO todoDTO) throws exception 메서드는
        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);        //todoDTO를 인자로 받아서 todoVO로 변환후 데이터베이스에저장하는 역할
        log.info(todoVO);
        dao.insert(todoVO);//int를 반환하므로 이를이용해서 예외처리
    }

    public List<TodoDTO> listAll() throws Exception {     //Dao로부터 값을받아서 서비스로 가져와서
        List<TodoVO> voList = dao.selectAll();             //DTO로부터 받아온데이터를 VO로 변환하는과정
        log.info("voList...........");
        log.info(voList);


        List<TodoDTO> dtoList = voList.stream()
                .map(vo -> modelMapper.map(vo, TodoDTO.class))
                .collect(Collectors.toList());

        return dtoList;
    }

    public TodoDTO get(Long tno) throws Exception {

        log.info("tno:" + tno);
        TodoVO todoVO = dao.selectOne(tno);                              /**  get()에서는 TodoDAO의 SELECTONE()을통하여
         TodoVO객체를 가져오고 MODelMap-per를이용해 이를 DTO로 변환 */
        TodoDTO todoDTO = modelMapper.map(todoVO, TodoDTO.class);

        return todoDTO;
    }

    public void remove(Long tno) throws Exception {
        log.info("tno:" + tno);
        dao.deleteOne(tno);
    }

    public void modify(TodoDTO todoDTO) throws Exception {
        log.info("todoDTO:" + todoDTO);
        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);
        dao.updateOne(todoVO);
    }


}
