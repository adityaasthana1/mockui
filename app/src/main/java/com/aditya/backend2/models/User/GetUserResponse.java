package com.aditya.backend2.models.User;

import java.util.List;

public class GetUserResponse {
    public List<UserData> data;
    public int total;
    public int page;
    public int limit;
    public int offset;

    public GetUserResponse(){}

    public GetUserResponse(List<UserData> data, int total, int page, int limit, int offset) {
        this.data = data;
        this.total = total;
        this.page = page;
        this.limit = limit;
        this.offset = offset;
    }

    public List<UserData> getData() {
        return data;
    }

    public void setData(List<UserData> data) {
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
