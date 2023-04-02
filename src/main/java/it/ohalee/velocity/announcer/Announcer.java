package it.ohalee.velocity.announcer;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent;
import com.velocitypowered.api.plugin.Dependency;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import it.ohalee.basementlib.api.BasementProvider;
import it.ohalee.velocity.announcer.command.ReloadCommand;
import it.ohalee.velocity.announcer.config.AnnouncerConfig;
import lombok.Getter;
import org.slf4j.Logger;

import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

@Getter
@Plugin(
        id = "announcer",
        name = "Announcer",
        version = "1.0",
        authors = "ohAlee",
        description = "Announcer",
        dependencies = {
                @Dependency(id = "basementlib")
        }
)
public class Announcer {

    private final Logger logger;
    @Inject
    private ProxyServer proxy;
    private AnnouncerConfig config;

    @Inject
    @DataDirectory
    private Path configDirectory;

    @Inject
    public Announcer(Logger logger) {
        this.logger = logger;
    }

    @Subscribe
    public void onProxyInitialize(ProxyInitializeEvent e) {
        config = new AnnouncerConfig(BasementProvider.get(), this, "config.yml");

        proxy.getScheduler().buildTask(this, new Task(proxy, config, 0))
                .repeat(config.getInterval(), TimeUnit.SECONDS)
                .schedule();

        this.proxy.getCommandManager().register("vannouncer", new ReloadCommand(this), "vann");
    }

    @Subscribe
    public void onProxyShutdown(ProxyShutdownEvent e) {
    }

}
