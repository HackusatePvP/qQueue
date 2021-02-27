package dev.hcr.qqueue;

import dev.hcr.qqueue.listener.PlayerListener;
import dev.hcr.qqueue.queue.QueueManager;
import dev.hcr.qqueue.queue.QueueTask;
import lombok.Getter;
import net.frozenorb.qlib.command.FrozenCommandHandler;
import net.frozenorb.qlib.util.ConfigFile;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class qQueue extends JavaPlugin {
    private QueueManager queueManager;
    private QueueTask queueTask;
    private ConfigFile configFile;
    @Getter private static qQueue plugin;

    @Override
    public void onEnable() {
        plugin = this;
        configFile = new ConfigFile(this, "config");
        FrozenCommandHandler.registerAll(this);
        queueManager = new QueueManager();
        queueTask = new QueueTask();
        queueTask.runTaskTimer(this, 0, 20L);
        this.getServer().getPluginManager().registerEvents(new PlayerListener(), this);
    }

    public ConfigFile getConfiguration() {
        return configFile;
    }

    @Override
    public void onDisable() {

        plugin = null;
    }
}
