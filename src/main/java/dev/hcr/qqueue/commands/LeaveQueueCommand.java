package dev.hcr.qqueue.commands;

import dev.hcr.qqueue.player.QueuePlayer;
import dev.hcr.qqueue.qQueue;
import dev.hcr.qqueue.queue.Queue;
import net.frozenorb.qlib.command.Command;
import net.frozenorb.qlib.util.StringUtils;
import org.bukkit.entity.Player;

public class LeaveQueueCommand {

    @Command(names = "leavequeue", permission = "op")
    public static void leave(Player sender) {
        QueuePlayer queuePlayer = qQueue.getPlugin().getQueueManager().getQueuePlayer(sender.getUniqueId());
        if (!queuePlayer.inQueue()) {
            sender.sendMessage(StringUtils.format("&cYou are not in a queue."));
            return;
        }
        Queue queue = queuePlayer.getQueue();
        queue.getPlayers().remove(queuePlayer);
        queuePlayer.setPosition(0);
        sender.sendMessage(StringUtils.format("&cYou have left the " + queue.getName() + " queue."));
        queuePlayer.setQueue(null); // set last
    }
}
