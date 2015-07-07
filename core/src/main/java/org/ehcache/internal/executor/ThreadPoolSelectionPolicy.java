package org.ehcache.internal.executor;

public interface ThreadPoolSelectionPolicy extends EhcacheExecutorPolicy {

  PoolType getPoolType(TaskType taskType);
  
  enum PoolType {
    SHARED, EXCLUSIVE;
  }
}
