package me.rndstad.hubforce;

import com.google.common.io.ByteStreams;
import me.rndstad.hubforce.commands.Hub;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import org.bstats.bungeecord.Metrics;

import java.io.*;

public class HFPlugin extends Plugin {

    public String hubName;

    private Configuration config;
    private Metrics metrics;

    @Override
    public void onEnable() {
        try {
            config = loadConfig();
        } catch (IOException e) {
            e.printStackTrace();
        }

        hubName = config.getString("hub-server");

        PluginManager pm = ProxyServer.getInstance().getPluginManager();
        pm.registerCommand(this, new Hub(this,"hub"));

        metrics = new Metrics(this);

        getLogger().info("[HubForce] Plugin is enabled, created with love by rndstad.me development.");
    }

    @Override
    public void onDisable() {
        getLogger().info("[HubForce] Plugin is disabled, created with love by rndstad.me development.");
    }

    private final Configuration loadConfig() throws IOException {
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }

        File file = new File(getDataFolder(), "config.yml");

        if (!file.exists()) {
            file.createNewFile();
            try (InputStream in = getResourceAsStream("example_config.yml");
                 OutputStream out = new FileOutputStream(file)) {
                ByteStreams.copy(in, out);
            }
        }

        return ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
    }
}
