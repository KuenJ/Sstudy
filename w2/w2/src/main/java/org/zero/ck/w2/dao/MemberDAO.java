package org.zero.ck.w2.dao;


import lombok.Cleanup;
import org.zero.ck.w2.domain.MemberVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MemberDAO {

        public MemberVO getWithPassword(String mid,String mpw) throws  Exception{



            String query ="select mid, mpw, mname from tbl_member where mid=? and mpw =?";

            MemberVO memberVO =null;

            @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
            @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1,mid);
            preparedStatement.setString(2,mpw);
                //Clean은  자원을 자동으로 닫아주는 역할을 한다. Connection,resultset,preparedstatement등을  리소스유출방지가능.
            @Cleanup ResultSet resultSet =preparedStatement.executeQuery();
            resultSet.next();


            memberVO = memberVO.builder()
                    .mid(resultSet.getString(1))
                    .mpw(resultSet.getString(2))
                    .mname(resultSet.getString(3))
                    .build();
            return  memberVO;
        }
}
