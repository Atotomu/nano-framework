/*
 * Copyright (c) 2015, Jurriaan Mous and contributors as indicated by the @author tags.
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
package org.nanoframework.extension.etcd.client.retry;

import java.util.Date;

import org.nanoframework.extension.etcd.client.ConnectionState;

/**
 * Retries with a delay until a timeout
 */
public class RetryWithTimeout extends RetryPolicy {
  private final long timeoutInMs;

  /**
   * Constructor
   *
   * @param msBeforeRetry milliseconds before retrying
   * @param timeoutInMs   timeout in ms
   */
  public RetryWithTimeout(int msBeforeRetry, int timeoutInMs) {
    super(msBeforeRetry);
    this.timeoutInMs = timeoutInMs;
  }

  @Override public boolean shouldRetry(ConnectionState connectionState) {
    return (new Date().getTime() - connectionState.startTime) < timeoutInMs;
  }
}