package cn.itcast.domain;

import java.io.Serializable;
import java.util.List;

public class PageBean<T> implements Serializable
{
	// 每页显示的数据
	private List<T> rows;
	
	// 总条数
	private int total;

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
	
}
