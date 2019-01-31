package com.romanenko.lew.progforcetask.helpers;


import com.romanenko.lew.progforcetask.model.POJO.List;

public class Pair {

    private final Integer left;
    private final List right;

    public Pair(Integer left, List right) {
        this.left = left;
        this.right = right;
    }


    public Integer getLeft() { return left; }
    public List getRight() { return right; }


}
