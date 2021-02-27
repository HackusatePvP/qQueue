package dev.hcr.qqueue.player;

import dev.hcr.qqueue.qQueue;
import dev.hcr.qqueue.queue.Queue;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

@Getter @Setter
public class QueuePlayer implements Comparable<QueuePlayer> {
    private UUID uuid;
    private String name;
    private Queue queue;
    private int position;
    private int weight;

    public QueuePlayer(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;

        qQueue.getPlugin().getQueueManager().getQueuePlayers().put(uuid, this);
    }

    public boolean inQueue() {
        return queue != null;
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(uuid);
    }

    @Override
    public int compareTo(QueuePlayer other) {
        return Integer.compare(this.weight, other.weight);
    }
}
