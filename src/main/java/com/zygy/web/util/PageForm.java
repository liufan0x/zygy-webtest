package com.zygy.web.util;

import java.io.Serializable;

public class PageForm implements Serializable {
	private static final long serialVersionUID = 1L;
    public static final int DEFAULT_PAGE_SIZE = 10;
    
    private Integer page;// ҳ�룬WebAdminʹ��
    private Integer rows;// ҳ��С��WebAdminʹ��
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
    
    
}
