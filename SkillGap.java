
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SkillGap {

    public static void analyze(List<Employee> employees,
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

        Main.printSection("SKILL GAP ANALYSIS");

        System.out.println("  Project: " + project.getName());

        for (Employee employee : employees) {

            int gaps = 0;

            System.out.println(
                    "\n  EMPLOYEE: " + employee.getName());

            System.out.printf("  %-22s %-10s %-12s %-14s%n",
                    "SKILL", "CURRENT", "REQUIRED", "STATUS");

            Main.printLine();

            for (Map.Entry<String, Integer> requirement
                    : project.getRequirements().entrySet()) {

                String skill = requirement.getKey();

                int current = employee.getSkills()
                        .getOrDefault(skill, 0);

                int required = requirement.getValue();

                if (current >= required) {

                    System.out.printf(
                            "  %-22s %-10d %-12d %-14s%n",
                            Employee.formatSkill(skill),
                            current,
                            required,
                            "MATCH");

                } else {

                    gaps++;

                    System.out.printf(
                            "  %-22s %-10d %-12d %-14s%n",
                            Employee.formatSkill(skill),
                            current,
                            required,
                            "GAP +" + (required - current));
                }
            }

            System.out.println("  Skill gaps found: " + gaps);
        }
    }
}
