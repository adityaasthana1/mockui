package com.aditya.backend2.models.Comment;

import java.util.List;

public class CommentResponse {
    public List<CommentData> data;
    public int total;
    public int page;
    public int limit;
    public int offset;

    public  CommentResponse(){}

    public CommentResponse(List<CommentData> data, int total, int page, int limit, int offset) {
        this.data = data;
        this.total = total;
        this.page = page;
        this.limit = limit;
        this.offset = offset;
    }

    public List<CommentData> getData() {
        return data;
    }

    public void setData(List<CommentData> data) {
        this.data = data;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}
