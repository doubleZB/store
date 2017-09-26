package cn.itcast.serviceimpl;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.dao.ProductDao;
import cn.itcast.daoimpl.ProductDaoImpl;
import cn.itcast.domain.PageBean;
import cn.itcast.domain.Product;
import cn.itcast.service.ProductService;

public class ProductServiceImpl implements ProductService {

	@Override
	public PageBean findPage(String cid, int pageNumber, int pageSize) throws Exception {
		ProductDao pd=new ProductDaoImpl();
		// 封装的每页显示数据
		List<Product> list = pd.findPage(cid,pageNumber,pageSize);
		// 总条数
		int totalCount=pd.count(cid);
		// 总页数
		int totalPage=0;
		if(totalCount%pageSize==0)
		{
			totalPage=totalCount/pageSize;
		}else
		{
			totalPage=totalCount/pageSize+1;
		}
		
		// pageBean封装数据
		PageBean pb = new PageBean();
		pb.setList(list);
		pb.setPageNumber(pageNumber);
		pb.setPageSize(pageSize);
		pb.setTotalCount(totalCount);
		pb.setTotalPage(totalPage);
		return pb;
	}

	@Override
	public Product findBypid(String pid) throws SQLException {
		ProductDao pd=new ProductDaoImpl();
		return pd.findBypid(pid);
		
	}

	@Override
	public List<Product> findNew() throws SQLException {
		ProductDao pd=new ProductDaoImpl();
		return pd.findNew();
		
	}

}
