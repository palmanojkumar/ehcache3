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

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

import org.ehcache.spi.service.Service;

/**
 * <p>
 * {@link EhcacheExecutorProvider} uses {@link ThreadFactoryProvider} to source platform specific {@link ThreadFactory} which is used to create
 * {@link ThreadPoolExecutor}.
 * </p>
 * 
 * Provides different types of shared or exclusive {@link ExecutorService} to submit {@link Runnable} or {@link Callable} task.
 * 
 * {@link Runnable} or {@link Callable} tasks can optionally provide an {@link TaskListener} to receive notifications of task status, through the use of {@link EhcacheManagedTask} interface.
 * <p>
 * Example:
 * <pre>
 * public class MyRunnable implements Runnable, EhcacheManagedTask {
 *   ...
 *   public void run() {
 *     ...
 *   }
 * 
 *   public TaskListener getTaskListener() {
 *     return null;
 *   }
 *   ...
 * }
 * </pre>
 * This facilitates task re-submission if it fails due to resource constraint.
 * </p>
 * 
 * 
 * 
 * @author palmanojkumar
 *
 */
public interface EhcacheExecutorProvider extends Service {

  /**
   * Returns managed instance of <em>shared</em>  {@link ExecutorService} based on {@link RequestContext}
   * <p> Global shared {@link ExecutorService} is provided for normal priority task whereas component specific shared {@link ExecutorService} is provided for high priority task.</p>
   *  
   * <p>
   * The lifecycle of shared {@link ExecutorService} is managed by {@link EhcacheExecutorProvider} and 
   * invocation of such lifecycle methods will be ignored.
   * </p>
   * 
   * @param config 
   * @param context 
   * @return
   */
  ExecutorService getExecutorService(ExecutorServiceType eType,  RequestContext context);
  
  
  /**
   * Returns unmanaged <em>exclusive</em> {@link ExecutorService} with specific configuration.
   * Client must invoke lifecycle methods of {@link ExecutorService} to release resources.
   * 
   * @param config is used to build {@link ThreadPoolExecutor} instance
   * @return unmanaged {@link ExecutorService}
   */
  ExecutorService getExecutorService(PoolConfig config);
  
  
  /**
   * Returns managed instance of <em>shared</em>  {@link ScheduledExecutorService} based on {@link RequestContext}
   * <p> Global shared {@link ExecutorService} is provided for normal priority task whereas component specific shared {@link ExecutorService} is provided for high priority task.</p>
   * 
   * The lifecycle of shared {@link ScheduledExecutorService} is managed by {@link EhcacheExecutorProvider} and 
   * invocation of such lifecycle methods by client will be ignored.
   * </p>
   * 
   * @param context 
   * @return 
   */
  ScheduledExecutorService getScheduledExecutorService(RequestContext context);
  
}