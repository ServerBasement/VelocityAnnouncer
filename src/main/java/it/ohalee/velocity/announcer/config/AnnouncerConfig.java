package it.ohalee.velocity.announcer.config;

import it.ohalee.framework.common.config.generic.adapter.ConfigurationAdapter;
import it.ohalee.framework.velocity.VelocityConfigAdapter;
import it.ohalee.velocity.announcer.Announcer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Config {

    private final ConfigurationAdapter configuration;

    public Config(Announcer announcer, String fileName) {
        configuration = new VelocityConfigAdapter(null, new File(announcer.getConfigDirectory().toFile(), fileName).toPath());
    }

    public List<List<String>> getMessages() {
        List<List<String>> messages = new ArrayList<>();
        for (String key : getNames()) {
            messages.add(getMessage(key));
        }
        return messages;
    }

    public List<String> getNames() {
        return configuration.getStringList("names", List.of());
    }

    public List<String> getMessage(String key) {
        return configuration.getStringList("messages." + key, List.of());
    }

    public void reload() {
        configuration.reload();
    }
}
