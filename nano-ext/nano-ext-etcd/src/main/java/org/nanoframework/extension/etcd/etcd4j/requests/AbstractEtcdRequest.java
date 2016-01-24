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

import org.nanoframework.extension.etcd.client.retry.RetryPolicy;
import org.nanoframework.extension.etcd.etcd4j.promises.EtcdResponsePromise;
import org.nanoframework.extension.etcd.etcd4j.responses.EtcdResponseDecoder;
import org.nanoframework.extension.etcd.etcd4j.transport.EtcdClientImpl;

import io.netty.handler.codec.http.HttpMethod;

/**
 * @author Luca Burgazzoli
 */
public class AbstractEtcdRequest<R> extends EtcdRequest<R> {
  private final String uri;

  protected AbstractEtcdRequest(
    String uri,EtcdClientImpl clientImpl, HttpMethod method, RetryPolicy retryPolicy, EtcdResponseDecoder<R> decoder) {
    super(clientImpl, method, retryPolicy, decoder);

    this.uri = uri;
  }

  @Override
  public EtcdResponsePromise<R> send() throws IOException {
    return clientImpl.send(this);
  }

  @Override
  public String getUri() {
    return this.uri;
  }
}
