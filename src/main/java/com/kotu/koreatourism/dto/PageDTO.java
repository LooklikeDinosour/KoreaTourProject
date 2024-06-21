package com.kotu.koreatourism.dto;

import com.kotu.koreatourism.domain.Criteria;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Getter
@Setter
public class PageDTO {

    private int start; //페이지네이션 시작숫자
    private int end; //페이지네이션 끝숫자
    private boolean prev; // 이전버튼 활성여부
    private boolean next; // 다음버튼 활성여부
    private int total; // 전체 게시글
    private int realEnd; //실제 끝번호

    private int page; // criteria에서 현재 조회하는 페이지
    private int amount; // criteria에 있는 현재 페이지 데이터 갯수
    private Criteria criteria; // 페이지 기준

    private int pnCount = 10; // 페이지네이션 block 내의 갯수
    private List<Integer> pageList; // 페이지네이션 리스트로 저장

    public PageDTO(Criteria criteria, int total) {
        this.criteria = criteria;
        this.page = criteria.getPage();
        this.amount = criteria.getAmount();
        this.total = total;

        //end 페이지 계산
        // ex) page 3, end= 10 / page 12, end = 20
        this.end = (int)(Math.ceil(this.page/(double)this.pnCount)) * this.pnCount; //end 페이지 계산

        //start 페이지 계산
        // 10 - 10 + 1 = 1 / 5 - 5 + 1 = 1
        this.start = this.end - this.pnCount + 1;

        //readEnd
        this.realEnd = (int)(Math.ceil(this.total/(double)this.amount));

        //끝 값 적용하기
        if(this.end > this.realEnd) {
            this.end = this.realEnd;
        }

        //prev 여부
        this.prev = this.start > 1;

        //next 여부
        this.next = this.realEnd > this.end;

        //
        this.pageList = IntStream.rangeClosed(this.start, this.end).boxed().collect(Collectors.toList());

    }

    @Override
    public String toString() {
        return "PageDTO{" +
                "start=" + start +
                ", end=" + end +
                ", prev=" + prev +
                ", next=" + next +
                ", total=" + total +
                ", realEnd=" + realEnd +
                ", page=" + page +
                ", amount=" + amount +
                ", criteria=" + criteria +
                ", pnCount=" + pnCount +
                ", pageList=" + pageList +
                '}';
    }
}
