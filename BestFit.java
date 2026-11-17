
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class BestFit {

    private static class MatchResult {
        private final Employee employee;
        private final int matched;
        private final int total;
        private final double percentage;

        MatchResult(Employee employee, int matched, int total) {
            this.employee = employee;
            this.matched = matched;
            this.total = total;

            this.percentage = total == 0
                    ? 0.0 : matched * 100.0 / total;
        }
    }

    public static void find(List<Employee> employees,
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

        List<MatchResult> results = new ArrayList<>();

        Map<String, Integer> requirements =
                project.getRequirements();

        for (Employee employee : employees) {

            int matched = 0;

            for (Map.Entry<String, Integer> requirement
                    : requirements.entrySet()) {

                int employeeLevel = employee.getSkills()
                        .getOrDefault(requirement.getKey(), 0);

                if (employeeLevel >= requirement.getValue()) {
                    matched++;
                }
            }

            results.add(new MatchResult(
                    employee, matched, requirements.size()));
        }

        results.sort(Comparator.comparingDouble(
                (MatchResult result) -> result.percentage).reversed());

        Main.printSection("BEST-FIT EMPLOYEE RANKING");

        System.out.printf("  Project: %s%n%n", project.getName());

        System.out.printf("  %-4s %-24s %-12s %-12s%n",
                "RANK", "EMPLOYEE", "SKILLS MET", "MATCH SCORE");

        Main.printLine();

        int rank = 1;

        for (MatchResult result : results) {

            System.out.printf("  %-4d %-24s %-12s %10.2f%%%n",
                    rank++,
                    result.employee.getName(),
                    result.matched + "/" + result.total,
                    result.percentage);
        }

        MatchResult best = results.get(0);

        System.out.println();
        Main.printLine();

        System.out.printf("  TOP MATCH : %s%n",
                best.employee.getName());

        System.out.printf("  MATCH SCORE: %.2f%%%n",
                best.percentage);

        System.out.println(
                "  A skill matches when its rating meets or exceeds");
        System.out.println(
                "  the project's required level.");
    }
}
