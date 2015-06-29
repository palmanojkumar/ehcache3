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
import java.util.concurrent.ThreadPoolExecutor;

import org.ehcache.spi.service.Service;

/**
 * 
 * Provides different types of shared or exclusive {@link ExecutorService} to submit {@link Runnable} or {@link Callable} task.
 * 
 * {@link Runnable} or {@link Callable} tasks can optionally provide an {@link TaskListener} to receive notifications of task status, through the use of {@link EhcacheManagedTask} interface.
 * <p>
 * Example:
 * <pre>
 * //forcibly binding the listener and the actual task seems weird to me...
 * //secondly:
 * //  task refusal would just trigger and exception at submit time...
 * //  task failure would cause the task to fail with an exception
 * //  task success would mean the task terminates normally.
 * //All of these can be detected through the Future.  The only thing you are
 * //achieving here is to move to a callback system, in addition to a polling
 * //one.  That is not inherently a bad idea, it's just not something that needs
 * //to be added to this task.
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
   * //does this have to be enforced to be "shared" surely that is up to the implementation?
   * Returns managed instance of <em>shared</em>  {@link ExecutorService} based on {@link RequestContext}
   * <p> Global shared {@link ExecutorService} is provided for normal priority task whereas component specific shared {@link ExecutorService} is provided for high priority task.</p>
   *  
   * <p>
   * //WHY?
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
   * //does it need to be exclusive by contract here?  My understanding is that what we need
   * here is an executor that matches this config - it doesn't necessarily have to be exclusive right?
   * 
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