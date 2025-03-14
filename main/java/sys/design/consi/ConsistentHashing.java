package sys.design.consi;

import java.util.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ConsistentHashing {

    // A TreeSet to hold nodes, the values are the hash values of the nodes
    private TreeSet<Long> hashRing;
    private Map<Long, String> nodeMap; // Maps node hash values to their identifiers
    private int numReplicas; // Number of virtual nodes for each physical node
    
    // Constructor
    public ConsistentHashing(int numReplicas) {
        this.numReplicas = numReplicas;
        this.hashRing = new TreeSet<>();
        this.nodeMap = new HashMap<>();
    }

    // Hash function to hash a node or key to a long value
    private long hash(String value) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(value.getBytes());
            long hashValue = 0;
            for (int i = 0; i < 8; i++) { // Take first 8 bytes for a long (64-bit)
                hashValue = (hashValue << 8) | (hashBytes[i] & 0xFF);
            }
            return hashValue;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Hash algorithm not found", e);
        }
    }

    // Add a node to the consistent hashing ring
    public void addNode(String node) {
        // Add virtual nodes for the given physical node
        for (int i = 0; i < numReplicas; i++) {
            String virtualNode = node + "#" + i; // Create unique virtual node names
            long hashValue = hash(virtualNode);
            hashRing.add(hashValue);
            nodeMap.put(hashValue, node); // Map the hash value to the original node
        }
    }

    // Remove a node from the consistent hashing ring
    public void removeNode(String node) {
        // Remove all virtual nodes corresponding to the given physical node
        for (int i = 0; i < numReplicas; i++) {
            String virtualNode = node + "#" + i;
            long hashValue = hash(virtualNode);
            hashRing.remove(hashValue);
            nodeMap.remove(hashValue);
        }
    }

    // Get the node responsible for a given key
    public String getNodeForKey(String key) {
        // Hash the key to get its position in the hash ring
        long keyHash = hash(key);

        // Use the ceiling method to find the next clockwise node
        Long nodeHash = hashRing.ceiling(keyHash);

        // If no node is found (i.e., the key is larger than all node hashes), 
        // it means we have to wrap around to the first node
        if (nodeHash == null) {
            nodeHash = hashRing.first();
        }

        return nodeMap.get(nodeHash); // Return the node responsible for this key
    }

    public static void main(String[] args) {
        // Create a consistent hashing system with 3 replicas for each physical node
        ConsistentHashing ch = new ConsistentHashing(3);

        // Add nodes to the hash ring
        ch.addNode("NodeA");
        ch.addNode("NodeB");
        ch.addNode("NodeC");

        // Get the node responsible for a given key
        System.out.println("Key 'myKey' is assigned to: " + ch.getNodeForKey("myKey"));
        System.out.println("Key 'anotherKey' is assigned to: " + ch.getNodeForKey("anotherKey"));

        // Remove a node and check the key assignments again
        ch.removeNode("NodeB");

        System.out.println("Key 'myKey' is assigned to: " + ch.getNodeForKey("myKey"));
        System.out.println("Key 'anotherKey' is assigned to: " + ch.getNodeForKey("anotherKey"));
    }
}
