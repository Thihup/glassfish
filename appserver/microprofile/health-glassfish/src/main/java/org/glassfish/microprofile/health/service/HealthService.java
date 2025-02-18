package org.glassfish.microprofile.health.service;


import jakarta.inject.Inject;

import org.glassfish.api.StartupRunLevel;
import org.glassfish.api.event.EventListener;
import org.glassfish.api.event.Events;
import org.glassfish.hk2.api.PostConstruct;
import org.glassfish.hk2.runlevel.RunLevel;
import org.glassfish.internal.api.Globals;
import org.glassfish.internal.data.ApplicationInfo;
import org.glassfish.internal.deployment.Deployment;
import org.glassfish.microprofile.health.HealthReporter;
import org.jvnet.hk2.annotations.Service;

@Service(name = "healthcheck-service")
@RunLevel(StartupRunLevel.VAL)
public class HealthService implements EventListener, PostConstruct {

    @Inject
    Events events;

    @Override
    public void postConstruct() {
        events.register(this);
    }

    @Override
    public void event(Event<?> event) {

        HealthReporter service = Globals.getDefaultHabitat().getService(HealthReporter.class);

        if (service == null) {
            return;
        }

        if (event.is(Deployment.APPLICATION_UNLOADED) && event.hook() instanceof ApplicationInfo appInfo) {
            service.removeAllHealthChecksFrom(appInfo.getName());
        }
    }
}
