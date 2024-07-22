/*
 * Copyright (c) 2024 Contributors to the Eclipse Foundation.
 * Copyright (c) 2007-2018 Oracle and/or its affiliates. All rights reserved.
 * Copyright 2004 The Apache Software Foundation
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

package org.glassfish.grizzly.config.ssl;

import java.net.Socket;

import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLSocket;

import org.glassfish.grizzly.ssl.SSLSupport;

/**
 * @author Bill Barker
 */
public class JSSEFactory {

    /**
     * @return the ServerSocketFactory to use.
     */
    public SSLContextFactory getSocketFactory() {
        return new SSLContextFactory();
    }

    /**
     * @return the SSLSupport attached to this socket.
     * @throws IllegalArgumentException if the socket is not a {@link SSLSocket}
     */
    public SSLSupport getSSLSupport(Socket socket) {
        if (!(socket instanceof SSLSocket)) {
            throw new IllegalArgumentException("The Socket has to be SSLSocket");
        }
        return new JSSESupport((SSLSocket) socket);
    }

    /**
     * @return the SSLSupport attached to this SSLEngine.
     */
    public SSLSupport getSSLSupport(SSLEngine sslEngine) {
        return new JSSESupport(sslEngine);
    }
}
