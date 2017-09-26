package cn.itcast.serviceimpl;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.dao.CategoryDao;
import cn.itcast.daoimpl.CategoryDaoImpl;
import cn.itcast.domain.CateGory;
import cn.itcast.domain.PageBean;
import cn.itcast.domain.Product;
import cn.itcast.service.CategoryService;
import cn.itcast.utils.FactoryDemo;
import net.sf.json.JSONObject;

public class CategoryServiceImpl implements CategoryService {

	@Override
	public String findCategory(int pageNumber, int pageSize) throws Exception {
		CategoryDao cd=(CategoryDao)FactoryDemo.getBean("CategoryDao");
		// 每页的数据
		List<CateGory> rows = cd.findCategory(pageNumber,pageSize);
		// 总条数
		int total=cd.Count();
		
			/*easyUI只要分页的2个数据
					1: 总条数   		要求:名称得叫total
					2: 每页显示的数据 	要求:名称得叫rows*/
		PageBean pb=new PageBean();
		pb.setRows(rows);
		pb.setTotal(total);
		
		JSONObject json = JSONObject.fromObject(pb);
		
		return json.toString();
	}

	@Override
	public void delBycid(String cid) throws Exception {
		CategoryDao cd=(CategoryDao)FactoryDemo.getBean("CategoryDao");
		cd.delBycid(cid);
	}

	@Override
	public void save(CateGory cg) throws Exception {
		CategoryDao cd=(CategoryDao)FactoryDemo.getBean("CategoryDao");
		cd.save(cg);
	}

	@Override
	public void update(CateGory cg) throws Exception {
		CategoryDao cd=(CategoryDao)FactoryDemo.getBean("CategoryDao");
		cd.update(cg);
	}

	@Override
	public void saveProduct(Product p) throws Exception {
		CategoryDao cd=(CategoryDao)FactoryDemo.getBean("CategoryDao");
		cd.saveProduct(p);
		
	}

}
