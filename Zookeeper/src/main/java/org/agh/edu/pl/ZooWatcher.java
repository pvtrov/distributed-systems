package org.agh.edu.pl;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.*;

public class ZooWatcher implements Watcher {
    private final String zNode;
    private final String apOpenningCmd;
    private final ZooKeeper zooKeeper;
    private Process process;

    public ZooWatcher() throws IOException {
        this.zooKeeper = new ZooKeeper("localhost:2181", 3000, this);
        this.zNode = "/a";
        this.apOpenningCmd = "libreoffice --draw";
    }

    public void launchGraphicApp() {
        System.out.println("Opening chosen app!");
        try {
            process = Runtime.getRuntime().exec(apOpenningCmd);
            System.out.println("Opened process: " + process);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handlezNodeCreation(WatchedEvent event) {
        System.out.println("zNode " + event.getPath() + " created");
        try {
            buildStructure(zNode);
        } catch (IOException | InterruptedException | KeeperException e) {
            e.printStackTrace();
        }
        if (Objects.equals(event.getPath(), zNode)) {
            launchGraphicApp();
        } else {
            try {
                System.out.println("zNode /z has " + zooKeeper.getAllChildrenNumber("/z") + " children");
            } catch (KeeperException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void handlezNodeRemoval(WatchedEvent event) {
        System.out.println("zNode " + event.getPath() + " deleted");
        if (Objects.equals(event.getPath(), zNode)) {
            System.out.println("Killing app and it's process...");
            process.destroy();
        } else {
            try {
                if (zooKeeper.exists(zNode, false) != null) {
                    System.out.println("zNode /z has " + zooKeeper.getAllChildrenNumber("/z") + " children");
                    buildStructure(zNode);
                }
            } catch (IOException | InterruptedException | KeeperException e) {
                e.printStackTrace();
            }
        }
    }

    public void run() throws InterruptedException, KeeperException {
        zooKeeper.addWatch("/z", AddWatchMode.PERSISTENT_RECURSIVE);
        while (true) {

        }
    }

    @Override
    public void process(WatchedEvent event) {
        if (event.getType() == Event.EventType.NodeCreated) {
            handlezNodeCreation(event);
        } else if (event.getType() == Event.EventType.NodeDeleted) {
            handlezNodeRemoval(event);
        }

    }

    public List<String> getTree(String nodePath) throws InterruptedException, KeeperException {
        ArrayList<String> tree = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        stack.push(nodePath);
        while (!stack.empty()) {
            String current = stack.pop();
            for (String child : zooKeeper.getChildren(current, false)) {
                stack.push(String.join("/", current, child));
            }
            tree.add(current);
        }
        return tree;
    }

    public void buildStructure(String nodePath) throws IOException, InterruptedException, KeeperException {
        List<String> zNodeTree = getTree(nodePath);
        for (String child : zNodeTree) {
            List<String> children = Arrays.asList(child.split("/"));
            int node_id = children.size() - 1;
            String deepest = children.get(node_id);
            for (int i = 0; i < node_id; i++) {
                System.out.print("\t");
            }
            System.out.println("/" + deepest);
        }
    }
}
