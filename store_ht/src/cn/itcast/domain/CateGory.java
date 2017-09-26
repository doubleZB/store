package cn.itcast.domain;

import java.io.Serializable;

public class CateGory implements Serializable
{
	private String cid;
	private String cname;
	// 还得有一个商品的集合
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	
}
