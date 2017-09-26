package cn.itcast.serviceimpl;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.dao.CategoryDao;
import cn.itcast.daoimpl.CategoryDaoImpl;
import cn.itcast.domain.CateGory;
import cn.itcast.service.CategoryService;
import cn.itcast.utils.RedisUtil;
import net.sf.json.JSONArray;
import redis.clients.jedis.Jedis;

public class CategoryServiceImpl implements CategoryService {

	@Override
	public String findCateogry() throws SQLException 
	{
		CategoryDao cd=new CategoryDaoImpl();
		List<CateGory> list = cd.findCategory();
		// 把java的集合数据变成json的数据
		JSONArray json = JSONArray.fromObject(list);
		return json.toString();
		
	}

	@Override
	public String findCateogryByRedis() throws SQLException 
	{
		// 1 先从redis中获取
		Jedis jedis = RedisUtil.getJedis();
		String value = jedis.get("dht");
		if(value==null)
		{
			// 没有
			 // 去mysql中获取一份存进来
			value = findCateogry();
			// 存在redis中
			jedis.set("dht", value);
			// 从mysql中获取的
			System.out.println("从mysql中获取的");
			return value;
		}
		
		// 有--直接获取返回
		// 从redis中获取的
		System.out.println("从redis中获取的");
		return value;		
	}

}
