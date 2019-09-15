package com.program.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class PageDto {
    private List<QuestionDto> questionDtosList ;
    //前面是否还有页
    private boolean showPrevious;
    //是否是第一页
    private boolean showFirstPage;
    //后面是否有页
    private boolean showNext;
    //是否是最后一页
    private boolean showEndPage;
    //当前页
    private Integer pageNum;
    //当前多页
    private List<Integer> pages = new ArrayList<>();
    private Integer totalPage;

    public void setPagination(Integer totalCount, Integer pageNum, Integer pageSize) {

        Integer totalPage;
        if (totalCount % pageSize == 0){
            totalPage = totalCount / pageSize ;
        }else {
            totalPage = (totalCount / pageSize) + 1 ;
        }
        this.totalPage = totalPage ;

        if (pageNum < 1){
            pageNum = 1;
        }
        if (pageNum > totalPage){
            pageNum = totalPage;
        }
        this.pageNum = pageNum ;

        pages.add(pageNum);
        //向pages中放值
        for (int i = 1; i <= 3; i++){
            if (pageNum - i > 0){
                //每次都加到list的0位置
                pages.add(0,pageNum - i);
            }
            if (pageNum + i <= totalPage){
                pages.add(pageNum + i);
            }
        }


        //是否展示 <
        if (pageNum == 1){
            showPrevious = false ;
        }else {
            showPrevious = true ;
        }
        //是否展示 >
        if (pageNum == totalPage){
            showNext = false ;
        }else {
            showNext = true ;
        }

        //是否展示 <<
        if (pages.contains(1)){
            showFirstPage = false;
        }else {
            showFirstPage = true ;
        }

        //是否展示 >>
        if (pages.contains(totalPage)){
            showEndPage = false;
        }else {
            showEndPage = true ;
        }

    }
}
