package me.rndstad.hubforce.commands;

import me.rndstad.hubforce.HFPlugin;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Hub extends Command {

    private HFPlugin hfPlugin;

    public Hub(HFPlugin hfPlugin, String name) {
        super(name);
        this.hfPlugin = hfPlugin;
    }

    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer p = (ProxiedPlayer) sender;
            p.connect(ProxyServer.getInstance().getServerInfo(hfPlugin.hubName));
        }
    }
}

