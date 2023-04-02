package it.ohalee.velocity.announcer.config;

import it.ohalee.basementlib.api.BasementLib;
import it.ohalee.basementlib.api.config.generic.adapter.ConfigurationAdapter;
import it.ohalee.velocity.announcer.Announcer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AnnouncerConfig {

    private final ConfigurationAdapter configuration;

    public AnnouncerConfig(BasementLib basement, Announcer announcer, String fileName) {
        configuration = basement.plugin().provideConfigurationAdapter(new File(announcer.getConfigDirectory().toFile(), fileName), true);
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

    public Integer getInterval() {
        return configuration.getInteger("interval", 120);
    }

    public void reload() {
        configuration.reload();
    }
}
