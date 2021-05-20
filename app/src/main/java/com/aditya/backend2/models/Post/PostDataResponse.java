package com.aditya.backend2.models.Post;

import java.util.List;

public class PostDataResponse {
    public List<PostData> data;
    public int total;
    public int page;
    public int limit;
    public int offset;

    public PostDataResponse() {
    }

    public PostDataResponse(List<PostData> data, int total, int page, int limit, int offset) {
        this.data = data;
        this.total = total;
        this.page = page;
        this.limit = limit;
        this.offset = offset;
    }

    public List<PostData> getData() {
        return data;
    }

    public void setData(List<PostData> data) {
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
