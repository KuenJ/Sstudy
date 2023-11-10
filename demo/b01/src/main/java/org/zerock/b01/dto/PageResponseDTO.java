package org.zerock.b01.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import java.util.List;

@Getter
@ToString
public class PageResponseDTO<E> {
    // 제네릭 타입 매개변수 E를 사용하여 재사용 가능한 페이지 응답 DTO 클래스를 정의합니다.

    // 페이지 응답 DTO는 화면에 목록을 표시하고 시작 페이지 및 끝 페이지를 계산하는 데 사용됩니다.

    private int page;  // 현재 페이지 번호
    private int size;  // 페이지당 항목 수
    private int total; // 전체 항목 수

    // 시작 페이지 번호와 끝 페이지 번호를 나타내는 필드
    private int start;
    private int end;

    // 이전 페이지의 존재 여부와 다음 페이지의 존재 여부를 나타내는 필드
    private boolean prev;
    private boolean next;

    private List<E> dtoList; // DTO 객체 목록

    @Builder(builderMethodName = "withAll") //이렇게 설정하면  클래스에 withAll라는 이름의 빌더 메서드가 생성됩니다.
    public PageResponseDTO(PageRequestDTO pageRequestDTO, List<E> dtoList, int total) {
        // @Builder 어노테이션을 사용하여 빌더 메서드를 정의합니다.

        if (total <= 0) {
            return;
        }

        this.page = pageRequestDTO.getPage(); // 현재 페이지 번호 설정
        this.size = pageRequestDTO.getSize(); // 페이지당 항목 수 설정
        this.total = total; // 전체 항목 수 설정
        this.dtoList = dtoList; // DTO 객체 목록 설정

        // 화면에 표시할 마지막 페이지 번호를 계산합니다.
        this.end = (int) (Math.ceil(this.page / 10.0)) * 10;
        // 화면에 표시할 시작 페이지 번호를 계산합니다.
        this.start = this.end - 9;

        int last = (int) (Math.ceil((total / (double) size))); // 데이터의 개수를 계산한 마지막 페이지 번호

        this.end = end > last ? last : end; // 마지막 페이지 번호가 실제 마지막 페이지보다 크면 수정

        this.prev = this.start > 1; // 이전 페이지가 있는지 여부를 설정
        this.next = total > this.end * this.size; // 다음 페이지가 있는지 여부를 설정
    }
}
