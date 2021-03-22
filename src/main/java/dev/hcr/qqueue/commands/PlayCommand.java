package dev.hcr.qqueue.commands;

import dev.hcr.qqueue.player.QueuePlayer;
import dev.hcr.qqueue.qQueue;
import dev.hcr.qqueue.queue.Queue;
import net.frozenorb.qlib.command.Command;
import net.frozenorb.qlib.command.Param;
import net.frozenorb.qlib.util.StringUtils;
import org.bukkit.entity.Player;

public class PlayCommand {

    @Command(names = "play", permission = "")
    public static void play(Player sender, @Param(name = "queue") String q) {
        Queue queue = qQueue.getPlugin().getQueueManager().getQueue(q);
        if (queue == null) {
            sender.sendMessage(StringUtils.format("&cThat queue does not exist. /queue list"));
            return;
        }
        QueuePlayer queuePlayer = qQueue.getPlugin().getQueueManager().getQueuePlayer(sender.getUniqueId());
        if (queuePlayer.inQueue()) {
            sender.sendMessage(StringUtils.format("&cYou are already queueing " + queuePlayer.getQueue().getName() + ". /leavequeue."));
            return;
        }
        queue.add(queuePlayer);
        sender.sendMessage(StringUtils.format("&aYou have queued for " + queue.getName() + "!"));
    }
}
