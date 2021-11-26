package at.htl.service;

import io.quarkus.vertx.ConsumeEvent;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.context.ManagedExecutor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class GreetingService {

    @Inject
    ManagedExecutor executor;

    @ConsumeEvent("greeting")
    public Uni<String> consume(String name) {
        return Uni.createFrom().item(() -> name.toUpperCase()).emitOn(executor);
    }
}
