
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Training {

    public static void recommend(List<Employee> employees,
                                 List<Project> projects,
                                 Scanner scanner) {

        if (employees.isEmpty() || projects.isEmpty()) {
            System.out.println(
                    "\n  Add at least one employee and one project first.");
            return;
        }

        String projectName = Main.readText(
                scanner, "\n  Enter project name: ");

        Project project = Main.findProject(projects, projectName);

        if (project == null) {
            System.out.println(
                    "  Project not found. Check the project name.");
            return;
        }

        Main.printSection("PERSONALIZED TRAINING PLAN");

        System.out.println("  Project: " + project.getName());

        for (Employee employee : employees) {

            List<String> recommendations = new ArrayList<>();

            for (Map.Entry<String, Integer> requirement
                    : project.getRequirements().entrySet()) {

                int current = employee.getSkills()
                        .getOrDefault(requirement.getKey(), 0);

                int required = requirement.getValue();

                if (current < required) {

                    recommendations.add(
                            Employee.formatSkill(requirement.getKey())
                            + " (improve from " + current
                            + "/5 to " + required + "/5)");
                }
            }

            System.out.println(
                    "\n  " + employee.getName().toUpperCase());

            if (recommendations.isEmpty()) {

                System.out.println(
                        "  Excellent! No training gaps for this project.");

            } else {

                System.out.println(
                        "  Recommended learning areas:");

                for (int i = 0; i < recommendations.size(); i++) {

                    System.out.printf("   %d. %s%n",
                            i + 1,
                            recommendations.get(i));
                }
            }
        }
    }
}
