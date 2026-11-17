
import java.util.LinkedHashMap;
import java.util.Map;

public class Employee {
    private final String name;
    private final LinkedHashMap<String, Integer> skills = new LinkedHashMap<>();

    public Employee(String name) {
        this.name = name.trim();
    }

    public String getName() {
        return name;
    }

    public Map<String, Integer> getSkills() {
        return skills;
    }

    public void addSkill(String skill, int rating) {
        skills.put(skill.trim().toLowerCase(), rating);
    }

    public void display() {
        System.out.println("\n  " + name.toUpperCase());
        System.out.println("  " + "-".repeat(48));
        System.out.printf("  %-28s %s%n", "SKILL", "RATING");
        System.out.println("  " + "-".repeat(48));

        for (Map.Entry<String, Integer> entry : skills.entrySet()) {
            System.out.printf("  %-28s %d / 5%n",
                    formatSkill(entry.getKey()), entry.getValue());
        }
    }

    static String formatSkill(String skill) {
        if (skill == null || skill.isEmpty()) return "";

        String[] words = skill.split("\\s+");
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            if (result.length() > 0) result.append(' ');

            result.append(Character.toUpperCase(word.charAt(0)));

            if (word.length() > 1) {
                result.append(word.substring(1));
            }
        }

        return result.toString();
    }
}
