package dev.hcr.qqueue.queue;

import dev.hcr.qqueue.player.QueuePlayer;
import dev.hcr.qqueue.qQueue;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

import java.util.*;

@Getter @Setter
public class Queue {
    private String name;
    private boolean paused;
    private boolean enabled;
    protected int tick;
    private int sending;
    private ArrayList<QueuePlayer> players;

    public Queue(String name, boolean paused, boolean enabled, int tick, int sending, ArrayList<QueuePlayer> players) {
        this.name = name;
        this.paused = paused;
        this.enabled = enabled;
        this.tick = tick;
        this.sending = sending;
        this.players = players;
    }

    public ArrayList<QueuePlayer> sortPlayersWeight() {
        ArrayList<QueuePlayer> sortedPlayers = new ArrayList<>(this.players);
        sortedPlayers.sort(Comparator.comparingLong(QueuePlayer::getWeight));
        Collections.reverse(sortedPlayers);

        for (QueuePlayer queuePlayer : sortedPlayers) {
            queuePlayer.setPosition(sortedPlayers.indexOf(queuePlayer) + 1);
        }

        return sortedPlayers;
    }

    public void add(Player player) {
        add(qQueue.getPlugin().getQueueManager().getQueuePlayer(player.getUniqueId()));
    }

    public void add(QueuePlayer queuePlayer) {
        this.players.add(queuePlayer);
        this.players = sortPlayersWeight();

        queuePlayer.setQueue(this);
        qQueue.getPlugin().getLogger().info("Added " + queuePlayer.getName() + " to the queue. Position=" + players.indexOf(queuePlayer));
        queuePlayer.setPosition(players.indexOf(queuePlayer));
    }

    public void remove(Player player) {
        remove(qQueue.getPlugin().getQueueManager().getQueuePlayer(player.getUniqueId()));
    }

    public void remove(QueuePlayer queuePlayer) {
        this.players.remove(queuePlayer);
        queuePlayer.setQueue(null);
        queuePlayer.setPosition(0);
    }

    public int getPlayersInQueue() {
        return players.size();
    }
}
