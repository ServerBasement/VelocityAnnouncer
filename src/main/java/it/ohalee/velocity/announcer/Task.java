package it.ohalee.velocity.announcer;

import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import it.ohalee.velocity.announcer.config.AnnouncerConfig;
import lombok.AllArgsConstructor;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
public class Task implements Runnable {

    private final ProxyServer proxy;
    private final AnnouncerConfig config;
    private int index;

    @Override
    public void run() {
        Collection<Player> players = proxy.getAllPlayers();

        if (players.size() == 0) return;

        List<List<String>> messages = config.getMessages();

        if (index >= messages.size()) index = 0;

        List<String> message = messages.get(index++);
        List<Component> toSend = new ArrayList<>();

        for (String row : message)
            toSend.add(MiniMessage.miniMessage().deserialize(row));

        for (Player player : players)
            for (Component row : toSend)
                player.sendMessage(row);
    }

}
