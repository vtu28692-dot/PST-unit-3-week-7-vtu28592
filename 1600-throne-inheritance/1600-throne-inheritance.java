import java.util.*;

class ThroneInheritance {

    private String king;
    
    // Stores parent -> children in birth order
    private Map<String, List<String>> family;
    
    // Stores dead people
    private Set<String> dead;

    public ThroneInheritance(String kingName) {
        king = kingName;
        family = new HashMap<>();
        dead = new HashSet<>();

        family.put(kingName, new ArrayList<>());
    }

    public void birth(String parentName, String childName) {
        family.get(parentName).add(childName);
        family.put(childName, new ArrayList<>());
    }

    public void death(String name) {
        dead.add(name);
    }

    public List<String> getInheritanceOrder() {
        List<String> order = new ArrayList<>();

        dfs(king, order);

        return order;
    }

    private void dfs(String person, List<String> order) {

        // Add only if the person is alive
        if (!dead.contains(person)) {
            order.add(person);
        }

        // Visit children from oldest to youngest
        for (String child : family.get(person)) {
            dfs(child, order);
        }
    }
}