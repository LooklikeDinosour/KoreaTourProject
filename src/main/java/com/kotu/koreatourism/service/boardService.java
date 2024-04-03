package com.kotu.koreatourism.service;

import java.util.List;

public interface boardService {

    //글 작성하기
    public void registerPost();

    //글 수정하기
    public void updatePost();

    //글 제거하기
    public void deletePost();

    //글 조회하기
    //boardtype이 다른데 어떻게 조회를 해오나.. class는 상속받아야하나?? 흠.. 잠시 보류


}
