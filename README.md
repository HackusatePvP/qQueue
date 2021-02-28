# qQueue
A simple Queue plugin designed for HCR @ FrozenHQ Network

## API
qQueue has a very important object called QueuePlayer which will store all queue information dealing with players. To access to the object simple do the follwoing.
```java
import dev.hcr.qqueue.dev.QueueAPI

public void example(Player player) {
  QueuePlayer queuePlayer = QueueAPI.getQueuePlayer(player.getUniqueUUID());
}
```
To access queue information simply do the follwoing.
```java
boolean inQueue = queuePlayer.inQueue();
int position = queuePlayer.getPosition(); // make sure you check if they are in a queue.
int weight = queuePlayer.getWeight(); // in the hub plugin you must define the weight manually.
queuePlayer.setWeight(100); // to set a weight for a player. This weight does not save you must set everytime
```

Queues are also very simple to access the queue object do the following.
```java
import dev.hcr.qqueue.dev.QueueAPI

Queue queue = QueueAPI.getQueue("hcr");

queue.add(player);

queue.remove(player);

boolean enabled = queue.isEnabled();

boolean paused = queue.isPaused();

int playersInQueue = queue.getPlayersInQueue();
```
