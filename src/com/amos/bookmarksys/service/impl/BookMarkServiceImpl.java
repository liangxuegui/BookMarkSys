package com.amos.bookmarksys.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.amos.bookmarksys.service.IBookMarkService;
import com.amos.bookmarksys.util.BookMarkResult;
import com.amos.bookmarksys.util.FileUtil;
import com.amos.bookmarksys.util.JsonUtil;
import com.amos.bookmarksys.util.Page;
import com.amos.bookmarksys.vo.BookMarkVo;

@Service("bookService")
public class BookMarkServiceImpl implements IBookMarkService {
	@Override
	public BookMarkResult save(BookMarkVo bookMark) throws Exception {
		BookMarkResult result = new BookMarkResult();
		bookMark.setCreated(System.currentTimeMillis()+"");
		boolean isSave = true;
		List list = JsonUtil.jsonToList(FileUtil.path(), null);
		for (int i = 0; i < list.size(); i++) {
			if (((Map) list.get(i)).get("title").equals(bookMark.getTitle())) {
				isSave = false;
				break;
			}
		}
		if(isSave){
			boolean flag = false;
			flag = list.add(bookMark);
			if(flag){
				result.setStatus(1);
				result.setMsg("����ɹ�");
				JsonUtil.lsitToFile(list,FileUtil.path());
			}else{
				result.setStatus(0);
				result.setMsg("����ʧ��");
			}
			
		}else{
			result.setStatus(0);
			result.setMsg(bookMark.getTitle()+"�Ѵ��ڣ�����ʧ��");
		}
		return result;
	}
	@Override
	public BookMarkResult delete(BookMarkVo bookMarkVo) throws Exception {
		BookMarkResult result = new BookMarkResult();
		List list = JsonUtil.jsonToList(FileUtil.path(), null);
		Object used = null;
		for (int i = 0; i < list.size(); i++) {
			if (((Map) list.get(i)).get("title").equals(bookMarkVo.getTitle())) {
				used = list.remove(i);
			}
		}
		if (used == null) {
			result.setStatus(0);
			result.setMsg("�޷�ɾ��û�д���ǩ");
		}else{
			result.setStatus(1);
			result.setMsg("ɾ��ɹ�");
			//��ɾ����µ�listд���ļ�
			JsonUtil.lsitToFile(list,FileUtil.path());
		}
		return result;
	}
	@Override
	public BookMarkResult find(BookMarkVo bookMarkVo) {
		BookMarkResult result = new BookMarkResult();
		List list = null;
		if (bookMarkVo.getCreated() == null) {
			list = JsonUtil.jsonToList(FileUtil.path(), bookMarkVo);
		}
		if (list == null) {
			result.setStatus(0);
			result.setMsg("���ļ�û�����");
			return result;
		}
		result.setStatus(1);
		result.setMsg("��ѯ�ɹ�");
		result.setData(list);
		return result;
	}
	@Override
	public BookMarkResult findByPage(Page page, BookMarkVo bookMarkVo) {
		BookMarkResult result = new BookMarkResult();
		List list = JsonUtil.jsonToList(FileUtil.path(),bookMarkVo);
		page.setTotalRowNum(list.size());
		if (page.getPageNum() * page.getPageSize() > list.size()) {
			list = list.subList((page.getPageNum() - 1) * page.getPageSize(),list.size());
		} else {
			list = list.subList((page.getPageNum() - 1<=0?0:page.getPageNum() - 1) * page.getPageSize(), page.getPageNum() * page.getPageSize());
		}
		result.setStatus(1);
		result.setMsg("��ѯ�ɹ�");
		result.setData(list);
		result.setPage(page);
		return result;
	}
}
