package it.ohalee.velocity.announcer.command;

import com.velocitypowered.api.command.RawCommand;
import it.ohalee.velocity.announcer.Announcer;
import lombok.AllArgsConstructor;
import net.kyori.adventure.text.Component;

@AllArgsConstructor
public class ReloadCommand implements RawCommand {

    public final Announcer announcer;

    @Override
    public void execute(Invocation invocation) {
        announcer.getConfig().reload();

        invocation.source().sendMessage(Component.text("§b§lAnnouncer: §aConfig reloaddato"));
    }

    @Override
    public boolean hasPermission(Invocation invocation) {
        return invocation.source().hasPermission("announcer.reload");
    }

}
