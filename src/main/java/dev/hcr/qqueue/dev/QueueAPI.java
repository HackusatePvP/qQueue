package dev.hcr.qqueue.dev;

import dev.hcr.qqueue.player.QueuePlayer;
import dev.hcr.qqueue.qQueue;
import dev.hcr.qqueue.queue.Queue;
import org.bukkit.entity.Player;

public class QueueAPI {

    public static Queue getQueue(String name) {
        return qQueue.getPlugin().getQueueManager().getQueue(name);
    }

    public static QueuePlayer getQueuePlayer(Player player) {
        return qQueue.getPlugin().getQueueManager().getQueuePlayer(player);
    }
}
