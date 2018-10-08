package com.wx.redis;

import redis.clients.jedis.ShardedJedis;

/**
 * 
 * @todo redis connect interface
 *
 * @author fhr
 * @dateTime 2016 2016-11-23 下午2:13:25
 */

public interface IRedisConnect {
	 public abstract ShardedJedis getRedisClient();
	 public void returnConnect(ShardedJedis shardedJedis);
	 public void returnConnect(ShardedJedis shardedJedis,boolean broken);
}
