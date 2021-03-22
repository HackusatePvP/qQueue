package dev.hcr.qqueue.queue;

import dev.hcr.qqueue.player.QueuePlayer;
import dev.hcr.qqueue.qQueue;
import net.frozenorb.qlib.util.BungeeUtils;
import net.frozenorb.qlib.util.StringUtils;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class QueueTask extends BukkitRunnable {
    private int tick;
    private int msg;

    @Override
    public void run() {
        for (Queue queue : qQueue.getPlugin().getQueueManager().getQueues()) {

            for (QueuePlayer queuePlayer : queue.getPlayers()) {
                if (!queue.isEnabled()) {
                    queuePlayer.getPlayer().sendMessage(StringUtils.format("&c" + queue.getName() + " is not enabled. Leaving queue..."));
                    queue.remove(queuePlayer);
                    continue;
                }

                if (queue.isPaused()) {
                    msg++;

                    if (msg == 10) {
                        queue.setPlayers(queue.sortPlayersWeight());
                        List<String> message = new ArrayList<>();
                        message.add("");
                        message.add("");
                        message.add("&c" + queue.getName() + " is currently paused. The queue will be opened shortly!");
                        message.add("&7Queuing: &c" + queue.getName());
                        message.add("&7Position: &c" + queuePlayer.getPosition() + "/" + queue.getPlayers().size());
                        message.add("");
                        message.add("");
                        message.forEach(msg -> queuePlayer.getPlayer().sendMessage(StringUtils.format(msg)));
                        msg = 0;
                    }
                }
            }

            if (queue.isPaused()) {
                continue;
            }


            // start the queuing process

            if (queue.getPlayers().size() == 0) {
                continue; // stop the queue from processing if there are no players in queue
            }

            tick++;
            if (tick == queue.getTick()) { // each queue can be configured to send players at a specific time
                int sent = 0; // each queue can be configured to send a specific amount of players when the tick is reached.
                do {
                    for (QueuePlayer queuePlayer : queue.getPlayers()) {
                        sent++;
                        BungeeUtils.send(queuePlayer.getPlayer(), queue.getName());
                    }
                } while (sent < queue.getSending()) ;
                queue.setPlayers(queue.sortPlayersWeight());
                tick = 0;
            }
        }
    }
}
