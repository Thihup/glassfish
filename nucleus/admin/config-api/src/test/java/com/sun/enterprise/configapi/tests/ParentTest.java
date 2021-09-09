/*
 * Copyright (c) 2009, 2018 Oracle and/or its affiliates. All rights reserved.
 * Copyright (c) 2021 Contributors to the Eclipse Foundation
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0, which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the
 * Eclipse Public License v. 2.0 are satisfied: GNU General Public License,
 * version 2 with the GNU Classpath Exception, which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 */

package com.sun.enterprise.configapi.tests;

import org.glassfish.config.api.test.ConfigApiJunit5Extension;
import org.glassfish.grizzly.config.dom.NetworkListener;
import org.glassfish.grizzly.config.dom.NetworkListeners;
import org.glassfish.hk2.api.ServiceLocator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.jvnet.hk2.config.ConfigBeanProxy;

import jakarta.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * test the getParentAPI.
 *
 * @author Jerome Dochez
 */
@ExtendWith(ConfigApiJunit5Extension.class)
public class ParentTest {

    @Inject
    private ServiceLocator locator;

    @Test
    public void parents() {

        NetworkListeners service = locator.getService(NetworkListeners.class);
        assertNotNull(service);
        NetworkListener listener = service.getNetworkListener().get(0);
        assertNotNull(listener);

        ConfigBeanProxy parent = service.getParent();
        assertNotNull(parent);

        NetworkListeners myService = listener.getParent(NetworkListeners.class);
        assertNotNull(myService);
        assertNotNull(myService.getNetworkListener().get(0).getName());
    }
}
