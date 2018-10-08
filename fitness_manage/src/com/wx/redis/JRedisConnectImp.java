package com.wx.redis;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
/**
 * 
 * @todo implements IRedisConnect interface。
 * eg: get and manage redis connect.
 *
 * @author fhr
 * @dateTime 2016 2016-11-23 下午2:14:06
 */

@Repository("jRedisConnect")
public class JRedisConnectImp implements IRedisConnect {
	private static final Logger log = Logger.getLogger(JRedisConnectImp.class);
	
	@Autowired
    private ShardedJedisPool  shardedJedisPool;
	
	@Override
	public ShardedJedis getRedisClient() {
		// TODO Auto-generated method stub
		
		try {
            ShardedJedis shardJedis = shardedJedisPool.getResource();
            return shardJedis;
        } catch (Exception e) {
            //log.error("getRedisClent error", e);
        }
        return null;
	}

	@Override
	public void returnConnect(ShardedJedis shardedJedis) {
		// TODO Auto-generated method stub
		  shardedJedisPool.returnResource(shardedJedis);
	}

	@Override
	public void returnConnect(ShardedJedis shardedJedis, boolean broken) {
		// TODO Auto-generated method stub
		if (broken) {
            shardedJedisPool.returnBrokenResource(shardedJedis);
        } else {
            shardedJedisPool.returnResource(shardedJedis);
        }
	}

}
