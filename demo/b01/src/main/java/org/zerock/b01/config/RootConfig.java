package org.zerock.b01.config;


import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.Banner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration  //  스프링 컨테이너에게 해당 클래스가 구성 클래스임을 알려줌   스프링컨테이너는 이클래스를 스캔하여 빈을 구성하고 관리 .
public class RootConfig {

    @Bean //이 어노테이션은 해당 메서드가 스프링 빈을 생성하고 반환하는 메서드임을 나타냄   이클래스에서는 modelMapper라는 빈을 정의하고 반환하는 메서드를 정의합니다.
    public ModelMapper modelMapper(){//이메서드는 modelmapper 클래스의 인스턴스를 생성하고 반환

        ModelMapper modelMapper = new ModelMapper();  //인스턴스생성: 이코드는 Model Mapper 클래스의 인스턴스를 생성  ModelMapper는 java객체간의
                                                        // 매핑을 수행하는 라이브러리로  서로 다른 클래스 간의 필드값을 복사하거나 매핑할때사용
        modelMapper.getConfiguration() // modelmapper의 설정을 얻기위한 메소드 호출


                .setFieldMatchingEnabled(true)   // 필드 매칭을활성화 modelmapper가 클래스의 필드이름과 유사한 이름을가진 필드를 자동으로 매핑하도록하는설정
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)//필드접근레벨을 private로 설정 이렇게하면 modelmapper가 비공개(private) 에접근 가능
                .setMatchingStrategy(MatchingStrategies.LOOSE);//a매칭전략을 loose로 설정 loose매칭전략을 대소문자를 무시하고 띄어쓰기를 언터스코어로 처리하는등 유연한 매칭제공
        return modelMapper;
    }
}
