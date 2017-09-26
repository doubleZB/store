package cn.itcast.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil 
{
	//配置对象
	static JedisPoolConfig config;	
	// 连接池
	static JedisPool pool;
	
	static
	{
		// 创建一个连接池的配置对象
		config = new JedisPoolConfig();	
		// 1可以设置初始化的连接数
		config.setMaxTotal(100);
		// 2设置空闲时期连接池的最大连接数
		config.setMaxIdle(5);
		
		// 连接池
		pool = new JedisPool(config, "192.168.2.133", 6379);
	}
	
	// 获取jedis对象的方法
	public static Jedis  getJedis()
	{
		return pool.getResource();
	}
}
