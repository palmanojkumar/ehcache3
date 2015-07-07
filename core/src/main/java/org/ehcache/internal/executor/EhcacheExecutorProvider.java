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
package org.ehcache.internal.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;

import org.ehcache.spi.service.Service;

/**
 * 
 * @author palmanojkumar
 *
 */
public interface EhcacheExecutorProvider extends Service {

  /**
   * Returns {@link ExecutorService} of type {@link ExecutorServiceType}
   * 
   * @param excutorServiceType
   * @param taskType
   * @return {@link ExecutorService} to execute task
   */
  ExecutorService getExecutorService(ExecutorServiceType excutorServiceType,  TaskType taskType);
  
  
  /**Returns {@link ExecutorService} of specified {@link PoolConfig} config
   * @param config
   * @return
   */
  ExecutorService getExecutorService(PoolConfig config);
  
  
  /**
   * Returns {@link ScheduledExecutorService} to schedule task of type {@link TaskType}
   * 
   * @param taskType
   * @return
   */
  ScheduledExecutorService getScheduledExecutorService(TaskType taskType);  
}