package dev.hcr.qqueue.listener;

import dev.hcr.qqueue.player.QueuePlayer;
import dev.hcr.qqueue.qQueue;
import dev.hcr.qqueue.queue.Queue;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        QueuePlayer queuePlayer = new QueuePlayer(event.getPlayer().getUniqueId(), event.getPlayer().getName());

        if (event.getPlayer().getName().equalsIgnoreCase("Hackusate_PvP")) {
            queuePlayer.setWeight(100);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        QueuePlayer queuePlayer = qQueue.getPlugin().getQueueManager().getQueuePlayer(player.getUniqueId());
        if (queuePlayer.inQueue()) {
            Queue queue = queuePlayer.getQueue();
            queue.remove(queuePlayer);
            queuePlayer.setQueue(null);
            queuePlayer.setPosition(0);
        }
        qQueue.getPlugin().getQueueManager().getQueuePlayers().remove(player.getUniqueId());
    }
}
