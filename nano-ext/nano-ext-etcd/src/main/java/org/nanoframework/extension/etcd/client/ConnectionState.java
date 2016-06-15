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
package org.nanoframework.extension.etcd.client;

import java.net.URI;

/**
 * Counts connection retries and current connection index
 */
public class ConnectionState {
    public int retryCount;
    public long startTime;
    public final URI[] uris;
    public int uriIndex;
    public int msBeforeRetry = 0;

    /**
     * Constructor
     *
     * @param uris to connect to
     */
    public ConnectionState(URI[] uris) {
        this.uris = uris;
    }
}
