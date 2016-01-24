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
package org.nanoframework.extension.etcd.etcd4j.requests;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.nanoframework.extension.etcd.client.retry.RetryPolicy;
import org.nanoframework.extension.etcd.etcd4j.promises.EtcdResponsePromise;
import org.nanoframework.extension.etcd.etcd4j.responses.EtcdKeysResponse;
import org.nanoframework.extension.etcd.etcd4j.transport.EtcdClientImpl;

import io.netty.handler.codec.http.HttpMethod;

/**
 * @author Jurriaan Mous
 * @author Luca Burgazzoli
 *
 * A basic Etcd Keys Request
 */
public class EtcdKeyRequest extends EtcdRequest<EtcdKeysResponse> {
  protected final String key;
  protected final Map<String, String> requestParams;

  /**
   * Constructs an EtcdKeysRequest
   *
   * @param clientImpl   the client to handle this request
   * @param method       to set request with
   * @param retryHandler Handles retries on fails
   */
  public EtcdKeyRequest(EtcdClientImpl clientImpl, HttpMethod method, RetryPolicy retryHandler) {
    this(clientImpl, method, retryHandler, null);
  }

  /**
   * Constructs an EtcdKeysRequest
   *
   * @param clientImpl   the client to handle this request
   * @param method       to set request with
   * @param retryHandler handles retries on fails
   * @param key          key to do action on
   */
  public EtcdKeyRequest(EtcdClientImpl clientImpl, HttpMethod method, RetryPolicy retryHandler, String key) {
    super(clientImpl, method, retryHandler, EtcdKeysResponse.DECODER);

    if (key.startsWith("/")){
      key = key.substring(1);
    }

    this.key = key;
    this.requestParams = new HashMap<String, String>();
  }

  @Override
  public EtcdKeyRequest setRetryPolicy(RetryPolicy retryPolicy) {
    super.setRetryPolicy(retryPolicy);
    return this;
  }

  @Override
  public EtcdResponsePromise<EtcdKeysResponse> send() throws IOException {
    return this.clientImpl.send(this);
  }

  @Override
  public String getUri() {
    return "/v2/keys/" + ((key != null) ? key : "");
  }

  @Override
  public Map<String, String> getRequestParams() {
    return requestParams;
  }
}