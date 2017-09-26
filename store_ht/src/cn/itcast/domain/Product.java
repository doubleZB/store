package cn.itcast.domain;

public class Product 
{
	private String pid; 
	private String pdate;
	private Integer pflag;  // 是否上架商品:    0: 上架商品  1: 下架商品
	
	private String pname; 
	private double market_price; 
	private double shop_price;
	private String pimage; 
	 
	private Integer is_hot;  // 是否热门:       0:热门  1:不热门
	private String pdesc; 
	
	
	// 有一个分类对象,这个商品的所有信息属于这个分类对象  
	private CateGory cateGory;
	

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public double getMarket_price() {
		return market_price;
	}

	public void setMarket_price(double market_price) {
		this.market_price = market_price;
	}

	public double getShop_price() {
		return shop_price;
	}

	public void setShop_price(double shop_price) {
		this.shop_price = shop_price;
	}

	public String getPimage() {
		return pimage;
	}

	public void setPimage(String pimage) {
		this.pimage = pimage;
	}

	public String getPdate() {
		return pdate;
	}

	public void setPdate(String pdate) {
		this.pdate = pdate;
	}

	public Integer getIs_hot() {
		return is_hot;
	}

	public void setIs_hot(Integer is_hot) {
		this.is_hot = is_hot;
	}

	public String getPdesc() {
		return pdesc;
	}

	public void setPdesc(String pdesc) {
		this.pdesc = pdesc;
	}

	public Integer getPflag() {
		return pflag;
	}

	public void setPflag(Integer pflag) {
		this.pflag = pflag;
	}

	public CateGory getCateGory() {
		return cateGory;
	}

	public void setCateGory(CateGory cateGory) {
		this.cateGory = cateGory;
	}
    
    
    
}
