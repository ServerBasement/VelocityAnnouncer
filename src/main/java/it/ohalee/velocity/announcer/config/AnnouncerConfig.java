package it.ohalee.velocity.announcer.config;

import it.ohalee.basementlib.api.BasementLib;
import it.ohalee.basementlib.api.config.generic.adapter.ConfigurationAdapter;
import it.ohalee.velocity.announcer.Announcer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AnnouncerConfig {

    private final ConfigurationAdapter configuration;

    public AnnouncerConfig(BasementLib basement, Announcer announcer, String fileName) {
        configuration = basement.plugin().provideConfigurationAdapter(Announcer.class, new File(announcer.getConfigDirectory().toFile(), fileName), true);
    }

    public List<List<String>> getMessages() {
        List<List<String>> messages = new ArrayList<>();

        Set<String> list = configuration.getStringMap("messages", Map.of()).keySet();
        for (String key : list) {
            messages.add(getMessage(key));
        }
        return messages;
    }

    public List<String> getMessage(String key) {
        return configuration.getStringList("messages." + key, List.of());
    }

    public Integer getInterval() {
        return configuration.getInteger("interval", 60);
    }

    public void reload() {
        configuration.reload();
    }
}
