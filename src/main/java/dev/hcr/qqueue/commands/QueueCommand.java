package dev.hcr.qqueue.commands;

import dev.hcr.qqueue.qQueue;
import dev.hcr.qqueue.queue.Queue;
import net.frozenorb.qlib.command.Command;
import net.frozenorb.qlib.command.Param;
import net.frozenorb.qlib.util.StringUtils;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class QueueCommand {

    @Command(names = "queue", permission = "op")
    public static void queue(Player sender) {
        List<String> message = new ArrayList<>();
        message.add("&8&m--------------------------------------------");
        message.add("   &4&lQueue &8Commands");
        message.add("&7* &c/queue list (Lists all queues)");
        message.add("&7* &c/queue pause &7(Pauses a queue)");
        message.add("&7* &c/queue resume &7(Resumes a queue)");
        message.add("&7* &c/queue toggle &7(Toggles a queue)");
        message.add("&8&m--------------------------------------------");
        message.forEach(msg -> sender.sendMessage(StringUtils.format(msg)));
    }

    @Command(names = "queue list", permission = "")
    public static void list(Player sender) {
        List<String> message = new ArrayList<>();
        message.add("&8&m--------------------------------------------");
        for (Queue queue : qQueue.getPlugin().getQueueManager().getQueues()) {
            message.add("  &c&l" + queue.getName());
            message.add("&7Enabled: " + (queue.isEnabled() ? "&aTrue" : "&cFalse"));
            message.add("&7Paused: " + (queue.isPaused() ? "&aTrue" : "&cFalse"));
        }
        message.add("&8&m--------------------------------------------");
        message.forEach(msg -> sender.sendMessage(StringUtils.format(msg)));
    }

    @Command(names = "queue pause", permission = "op")
    public static void pause(Player sender, @Param(name = "queue") String q) {
        Queue queue = qQueue.getPlugin().getQueueManager().getQueue(q.toLowerCase());
        if (queue == null) {
            sender.sendMessage(StringUtils.format("&cThat queue does not exist. /queue list"));
            return;
        }
        queue.setPaused(true);
        qQueue.getPlugin().getConfigFile().save();
        sender.sendMessage(StringUtils.format("&cYou have paused the " + queue.getName() + " queue."));
    }

    @Command(names = "queue resume", permission = "op")
    public static void resume(Player sender, @Param(name = "queue") String q) {
        Queue queue = qQueue.getPlugin().getQueueManager().getQueue(q.toLowerCase());
        if (queue == null) {
            sender.sendMessage(StringUtils.format("&cThat queue does not exist. /queue list"));
            return;
        }
        queue.setPaused(false);
        qQueue.getPlugin().getConfigFile().save();
        sender.sendMessage(StringUtils.format("&aYou have resumed the " + queue.getName() + " queue."));
    }

    @Command(names = "queue toggle", permission = "op")
    public static void toggle(Player sender, @Param(name = "queue") String q) {
        Queue queue = qQueue.getPlugin().getQueueManager().getQueue(q.toLowerCase());
        if (queue == null) {
            sender.sendMessage(StringUtils.format("&cThat queue does not exist. /queue list"));
            return;
        }
        queue.setEnabled(!queue.isEnabled());
        qQueue.getPlugin().getConfigFile().save();
        sender.sendMessage(StringUtils.format("&7You have " + (queue.isEnabled() ? "&aenabled" : "&cdisabled") + " &7the " + queue.getName() + " queue."));
    }
}
