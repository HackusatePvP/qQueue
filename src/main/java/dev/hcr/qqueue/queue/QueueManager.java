package dev.hcr.qqueue.queue;

import dev.hcr.qqueue.player.QueuePlayer;
import dev.hcr.qqueue.qQueue;
import lombok.Getter;
import net.frozenorb.qlib.util.ConfigFile;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class QueueManager {
    @Getter private ArrayList<Queue> queues = new ArrayList<>();
    @Getter private Map<UUID, QueuePlayer> queuePlayers = new HashMap<>();

    public QueueManager() {
        this.load();
    }

    public Queue getQueue(String name) {
        return queues.stream().filter(queue -> queue.getName().equalsIgnoreCase(name)).findAny().orElse(null);
    }

    public QueuePlayer getQueuePlayer(Player player) {
        return getQueuePlayer(player.getUniqueId());
    }

    public QueuePlayer getQueuePlayer(UUID uuid) {
        return queuePlayers.get(uuid);
    }

    private void load() {
        ConfigFile config = qQueue.getPlugin().getConfiguration();
        for (String s : config.getConfiguration().getConfigurationSection("").getKeys(false)) {
            String path = s + ".";

            String name = config.getString(path + "name");
            boolean enabled = config.getBoolean(path + "enabled");
            boolean paused = config.getBoolean(path + "paused");
            int tick = config.getInt(path + "tick");
            int sending = config.getInt(path + "sending");

            queues.add(new Queue(name, enabled, paused, tick, sending, new ArrayList<>()));
            qQueue.getPlugin().getLogger().info("Loaded queue " + name + " from config.yml");
        }
    }

}
