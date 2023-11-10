package org.zerock.b01.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
//페이징 관련 정보 외에 검색의 종류와 키워드를 추가해서 지정합니다
@Builder
@Data
@AllArgsConstructor //이 애노테이션은 모든 필드를 인자로 받는 생성자를 자동으로 생성하도록 지정합니다.
                    // 즉, 모든 필드를 초기화하는 생성자를 자동으로 생성합니다.
@NoArgsConstructor//이 애노테이션은 매개변수가 없는 생성자를 자동으로 생성하도록 지정합니다.

public class PageRequestDTO {

    @Builder.Default    //빌더 패턴은 객체 생성을 보다 가독성 있고 편리하게 만드는 디자인 패턴 중 하나입니다
    // @Builder.Default 애노테이션은 이 패턴을 사용할 때 필드의 기본 값을 설정할 수 있게 해줍니다.
    private int page = 1;

    @Builder.Default
    private int size = 10;


    private String type;// 검색의종류 t,c,w,tc,tw,twc
    private String keyword;

    private String link;  // URL을 캐시하는 데 사용되는 멤버 변수

    public String getLink() {
        if (link == null) {
            // link가 아직 생성되지 않은 경우 아래의 로직을 실행합니다.

            StringBuilder builder = new StringBuilder();  // URL을 구성하기 위한 StringBuilder 생성

            builder.append("page=" + this.page);  // 현재 페이지 번호를 추가
            builder.append("&size=" + this.size);  // 페이지 크기를 추가

            if (type != null && type.length() > 0) {
                builder.append("&type=" + type);  // 검색 유형을 추가 (만약 값이 존재하는 경우)
            }

            if (keyword != null) {
                try {
                    builder.append("&keyword=" + URLEncoder.encode(keyword, "UTF-8"));
                    // 특수 문자를 올바르게 처리하기 위해 검색 키워드를 UTF-8로 인코딩하여 추가
                } catch (UnsupportedEncodingException e) {
                    // 예외 처리 (UnsupportedEncodingException) - UTF-8 인코딩 오류 시
                    // (여기서는 예외를 무시하고 진행하도록 작성되어 있습니다)
                }
            }

            link = builder.toString();  // StringBuilder에 구성된 쿼리 문자열을 문자열로 변환하고, link 변수에 저장

        }
        return link;  // 생성된 또는 캐시된 link를 반환
    }


    public String[] getTypes() {  //이 메서드는 type 문자열을 분할하여 문자열 배열을 반환합니다. 주요 동작은 다음과 같습니다,.

        if (type == null || type.isEmpty()) {  //type 필드가 비어 있거나 null인 경우, 메서드는 null을 반환합니다.
            return null;
        }
        return type.split("");

    }
    public Pageable getPageable(String...props){
        return PageRequest.of(this.page-1,this.size, Sort.by(props).descending());
    }

    /**
     * getTypes 메서드:
     * type 필드가 비어 있거나 null인 경우, 메서드는 null을 반환합니다.
     * 그렇지 않은 경우, type 문자열을 한 글자씩 분할하여 문자열 배열에 저장한 후,
     * 이 배열을 반환합니다. type.split("")는 문자열을 한 글자씩 나누는데 사용되며,
     * 각 글자가 배열의 원소로 들어갑니다. 예를 들어, "abc"라는 type가 있으면 결과는 ["a", "b", "c"]가 됩니다.
     * 이 메서드는 주로 검색 종류(type)를 문자열로 나누어 배열로 반환하는데 사용됩니다.
     *
     *
     * getPageable 메서드:
     * 이 메서드는 페이지네이션 정보와 정렬 정보를 기반으로 Pageable 객체를 생성하여 반환합니다. 주요 동작은 다음과 같습니다:
     * getPageable 메서드는 Pageable 객체를 반환합니다. 이 객체는 페이지네이션 및 정렬 정보를 나타내는 인터페이스입니다.
     * 메서드의 매개변수 props는 문자열 가변 인자를 나타내며, 정렬에 사용할 속성(필드)을 나타냅니다. 이러한 속성들은 정렬의 기준이 됩니다.
     * PageRequest.of(this.page - 1, this.size, Sort.by(props).descending())는 PageRequest
     * 클래스의 정적 팩토리 메서드를 사용하여 Pageable 객체를 생성합니다.
     * 여기서 this.page - 1은 현재 페이지를 0부터 시작하도록 조정하고,
     * this.size는 페이지 크기를 나타냅니다. 그리고 Sort.by(props).descending()는 지정된 속성(들)에 대해 내림차순으로 정렬을 수행하도록 지정합니다.
     * 이 메서드는 주로 페이지 번호, 페이지 크기, 그리고 정렬 속성을 기반으로 페이지 요청 정보를 생성하여 반환하는데 사용됩니다.
     * */

}

