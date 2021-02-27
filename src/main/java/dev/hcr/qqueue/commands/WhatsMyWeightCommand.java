package dev.hcr.qqueue.commands;

import dev.hcr.qqueue.player.QueuePlayer;
import dev.hcr.qqueue.qQueue;
import net.frozenorb.qlib.command.Command;
import net.frozenorb.qlib.util.StringUtils;
import org.bukkit.entity.Player;

public class WhatsMyWeightCommand {

    @Command(names = "whatsmyweight", permission = "op")
    public static void weight(Player sender) {
        QueuePlayer queuePlayer = qQueue.getPlugin().getQueueManager().getQueuePlayer(sender.getUniqueId());
        sender.sendMessage(StringUtils.format("&c" + sender.getName() + " &7has a weight of &4" + queuePlayer.getWeight()));
    }
}
