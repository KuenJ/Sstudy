package org.zero.ck.w2.Service;


import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.zero.ck.w2.dao.MemberDAO;
import org.zero.ck.w2.domain.MemberVO;
import org.zero.ck.w2.dto.MemberDTO;
import org.zero.ck.w2.util.MapperUtil;

@Log4j2
public enum MemberService {

    INSTANCE;

    private MemberDAO dao;
    private ModelMapper modelMapper;


    MemberService(){
        dao =new MemberDAO();
        modelMapper = MapperUtil.INSTANCE.get();

    }
    public MemberDTO login(String mid,String mpw)throws  Exception{
        MemberVO vo =dao.getWithPassword(mid,mpw);
        MemberDTO memberDTO =modelMapper.map(vo, MemberDTO.class);

        return memberDTO;
    }
}