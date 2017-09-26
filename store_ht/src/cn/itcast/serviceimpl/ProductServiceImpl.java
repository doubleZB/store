package cn.itcast.serviceimpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import cn.itcast.dao.ProductDao;
import cn.itcast.daoimpl.ProductDaoImpl;
import cn.itcast.domain.CateGory;
import cn.itcast.domain.PageBean;
import cn.itcast.domain.Product;
import cn.itcast.service.ProductService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ProductServiceImpl implements ProductService {

	@Override
	public String findProduct(int pageNumber,int pageSize) throws Exception {
		// 调用dao
		ProductDao pd=new ProductDaoImpl();
		List<Map<String, Object>> mList = pd.findProduct(pageNumber,pageSize);
		
		// 页面分页要的数据
		// 1 每页显示的数据  rows
		List<Product> rows=new ArrayList<Product>();
		
		for(Map<String, Object> map:mList)
		{
			Product product = new Product();
			BeanUtils.populate(product,map);
			
			CateGory category = new CateGory();
			BeanUtils.populate(category,map);
			
			product.setCateGory(category);
			rows.add(product);
		}
		
		// 2 要总条数
		int total=pd.Count();

		// pagebean封装每页显示的数据和总条数
		PageBean pb = new PageBean();
		pb.setRows(rows);
		pb.setTotal(total);
		
		JSONObject json = JSONObject.fromObject(pb);
		return json.toString();
	}

	@Override
	public String findCategory() throws SQLException {
			ProductDao pd=new ProductDaoImpl();
			List<CateGory> list = pd.findCategory();
		
			JSONArray json = JSONArray.fromObject(list);
			return json.toString();
	}

}
