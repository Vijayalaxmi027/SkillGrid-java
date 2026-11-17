
import java.util.LinkedHashMap;
import java.util.Map;

public class Project {
    private final String name;

    private final LinkedHashMap<String, Integer> requirements =
            new LinkedHashMap<>();

    public Project(String name) {
        this.name = name.trim();
    }

    public String getName() {
        return name;
    }

    public Map<String, Integer> getRequirements() {
        return requirements;
    }

    public void addRequirement(String skill, int level) {
        requirements.put(skill.trim().toLowerCase(), level);
    }

    public void display() {
        System.out.println("\n  PROJECT: " + name.toUpperCase());
        System.out.println("  " + "-".repeat(48));

        System.out.printf("  %-28s %s%n",
                "REQUIRED SKILL", "LEVEL");

        System.out.println("  " + "-".repeat(48));

        for (Map.Entry<String, Integer> entry : requirements.entrySet()) {
            System.out.printf("  %-28s %d / 5%n",
                    Employee.formatSkill(entry.getKey()),
                    entry.getValue());
        }
    }
}
