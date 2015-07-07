package org.ehcache.internal.executor;


public interface SharedThreadPoolPolicy extends EhcacheExecutorPolicy {

  PoolConfig getSharedCachedThreadPoolConfig();
  
  int getScheduleThreadPoolCoreSize();
}
