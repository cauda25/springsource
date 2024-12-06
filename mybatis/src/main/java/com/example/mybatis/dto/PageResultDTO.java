package com.example.mybatis.dto;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

// Page<Book> result 결과를 담는 dto
// Entity ==> DTO : result.getContent() ==> List<BookDTO> 변경

@Data
public class PageResultDTO<DTO> {

    // 화면에 보여줄 dto 리스트
    private List<DTO> dtoList;

    // 총 개수
    private int total;
    private PageRequestDTO requestDTO;

    // 시작 페이지,끝 페이지 번호
    private int start, end;

    // 총 페이지 수
    private int totalPage;

    // 이전,다음 여부
    private boolean prev, next;

    // 화면에 보여줄 페이지 번호 목록
    private List<Integer> pageList;

    public PageResultDTO(PageRequestDTO requestDTO, int total, List<DTO> dtoList) {
        this.total = total;
        this.dtoList = dtoList;

        int tempEnd = (int) (Math.ceil(requestDTO.getPage() / 10.0)) * requestDTO.getSize();
        totalPage = (int) (Math.ceil((total / 1.0) / requestDTO.getSize()));

        this.start = tempEnd - 9;
        this.end = totalPage > tempEnd ? tempEnd : totalPage;

        this.prev = this.start > 1;
        this.next = totalPage > tempEnd;

        // IntStream.rangeClosed(start, end) : int
        pageList = IntStream.rangeClosed(start, end)
                .boxed() // int ==> Integer
                .collect(Collectors.toList());
    }

}
