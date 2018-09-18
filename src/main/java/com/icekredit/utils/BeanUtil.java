package com.icekredit.utils;

import java.util.List;

import com.github.pagehelper.Page;

public class BeanUtil {

    public static <T> PageResult<T> toPagedResult(List<T> datas) {
        PageResult<T> result = new PageResult<T>();
        if (datas instanceof Page) {
            Page page = (Page) datas;
            result.setCurrentPage(page.getPageNum());
            result.setSize(page.getPageSize());
            result.setDataList(page.getResult());
            result.setTotalPage(page.getPages());
            result.setAllRow((int) page.getTotal());
        }
        else {
            result.setCurrentPage(1);
            result.setSize(datas.size());
            result.setDataList(datas);
            result.setTotalPage(datas.size());
        }

        return result;
    }
}