/*
 * Copyright Terracotta, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ehcache.config.executor;

import org.ehcache.internal.executor.EhcacheExecutorProvider;
import org.ehcache.internal.executor.ThreadFactoryProvider;

/**
 * @author palmanojkumar
 *
 */
public final class DefaultEhcacheExecutorProviderConfig implements EhcacheExecutorProviderConfig {

  private ThreadPoolConfig sharedCachedThreadPoolConfig;
  private int sharedScheduledThreadPoolCoreSize;
  private Class<? extends ThreadFactoryProvider> threadFactoryProvider;
  
  @Override
  public Class<EhcacheExecutorProvider> getServiceType() {

    return EhcacheExecutorProvider.class;
  }

  @Override
  public ThreadPoolConfig getSharedCachedThreadPoolConfig() {
    return sharedCachedThreadPoolConfig;
  }

  @Override
  public int getSharedScheduledThreadPoolCoreSize() {
    return sharedScheduledThreadPoolCoreSize;
  }


  @Override
  public Class<? extends ThreadFactoryProvider> getThreadFactoryProvider() {
    return threadFactoryProvider;
  }

  public void setSharedCachedThreadPoolConfig(ThreadPoolConfig sharedCachedThreadPoolConfig) {
    this.sharedCachedThreadPoolConfig = sharedCachedThreadPoolConfig;
  }

  public void setSharedScheduledThreadPoolCoreSize(int sharedScheduledThreadPoolCoreSize) {
    this.sharedScheduledThreadPoolCoreSize = sharedScheduledThreadPoolCoreSize;
  }

  public void setThreadFactoryProvider(Class<? extends ThreadFactoryProvider> threadFactoryProvider) {
    this.threadFactoryProvider = threadFactoryProvider;
  }
}
