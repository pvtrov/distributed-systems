package org.agh.edu.pl;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.*;

public class ZooWatcher implements Watcher {
    private static final String ZNODE_PATH = "/a";
    private static final String APP_OPEN_COMMAND = "libreoffice --draw";
    private final ZooKeeper zooKeeper;
    private Process process;

    public ZooWatcher() throws IOException {
        this.zooKeeper = new ZooKeeper("localhost:2181", 3000, this);
    }

    public void launchGraphicApp() {
        System.out.println("Opening chosen app!");
        try {
            process = Runtime.getRuntime().exec(APP_OPEN_COMMAND);
            System.out.println("Opened process: " + process);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleZNodeCreation(WatchedEvent event) {
        String path = event.getPath();
        System.out.println("zNode " + path + " created");

        try {
            buildStructure(ZNODE_PATH);
        } catch (IOException | InterruptedException | KeeperException e) {
            e.printStackTrace();
        }

        if (ZNODE_PATH.equals(path)) {
            launchGraphicApp();
        } else {
            try {
                int childrenCount = zooKeeper.getAllChildrenNumber("/a");
                System.out.println("zNode /a has " + childrenCount + " children");
            } catch (KeeperException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void handleZNodeRemoval(WatchedEvent event) {
        String path = event.getPath();
        System.out.println("zNode " + path + " deleted");

        if (ZNODE_PATH.equals(path)) {
            System.out.println("Killing app and its process...");
            if (process != null) {
                process.destroy();
            }
        } else {
            try {
                if (zooKeeper.exists(ZNODE_PATH, false) != null) {
                    int childrenCount = zooKeeper.getAllChildrenNumber("/a");
                    System.out.println("zNode /a has " + childrenCount + " children");
                    buildStructure(ZNODE_PATH);
                }
            } catch (IOException | InterruptedException | KeeperException e) {
                e.printStackTrace();
            }
        }
    }

    public void run() throws InterruptedException, KeeperException {
        zooKeeper.addWatch("/a", AddWatchMode.PERSISTENT_RECURSIVE);
        synchronized (this) {
            this.wait();
        }
    }

    @Override
    public void process(WatchedEvent event) {
        switch (event.getType()) {
            case NodeCreated:
                handleZNodeCreation(event);
                break;
            case NodeDeleted:
                handleZNodeRemoval(event);
                break;
            default:
                break;
        }
    }

    public List<String> getTree(String nodePath) throws InterruptedException, KeeperException {
        List<String> tree = new ArrayList<>();
        Deque<String> stack = new ArrayDeque<>();
        stack.push(nodePath);

        while (!stack.isEmpty()) {
            String current = stack.pop();
            tree.add(current);

            List<String> children = zooKeeper.getChildren(current, false);
            for (String child : children) {
                stack.push(current + "/" + child);
            }
        }

        return tree;
    }

    public void buildStructure(String nodePath) throws IOException, InterruptedException, KeeperException {
        List<String> zNodeTree = getTree(nodePath);

        for (String child : zNodeTree) {
            String[] nodes = child.split("/");
            int depth = nodes.length - 1;

            for (int i = 0; i < depth; i++) {
                System.out.print("\t");
            }

            System.out.println("/" + nodes[depth]);
        }
    }
}
