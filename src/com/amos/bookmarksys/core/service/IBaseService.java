package com.amos.bookmarksys.core.service;


import com.amos.bookmarksys.util.BookMarkResult;
import com.amos.bookmarksys.util.Page;

public interface IBaseService<T> {

	public BookMarkResult save(T t) throws Exception;
	public BookMarkResult delete(T t) throws Exception;
	public BookMarkResult find(T t);
	public BookMarkResult findByPage(Page page,T t);
}
