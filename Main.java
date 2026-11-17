
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final List<Employee> employees =
            new ArrayList<>();

    private static final List<Project> projects =
            new ArrayList<>();

    private static final int WIDTH = 78;

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {

            showHeader();

            boolean running = true;

            while (running) {

                showMenu();

                int choice = readInt(
                        scanner,
                        "  Select an option (1-8): ",
                        1, 8
                );

                switch (choice) {

                    case 1:
                        addEmployee(scanner);
                        break;

                    case 2:
                        viewEmployees();
                        break;

                    case 3:
                        addProject(scanner);
                        break;

                    case 4:
                        viewProjects();
                        break;

                    case 5:
                        BestFit.find(employees, projects, scanner);
                        break;

                    case 6:
                        SkillGap.analyze(employees, projects, scanner);
                        break;

                    case 7:
                        Training.recommend(employees, projects, scanner);
                        break;

                    case 8:
                        System.out.println();

                        printSection("THANK YOU FOR USING SKILLGRID");

                        System.out.println(
                                "  Build Skills | Discover Talent | Grow Together"
                        );

                        System.out.println(
                                "=".repeat(WIDTH)
                        );

                        running = false;
                        break;
                }

                if (running) {

                    System.out.println();

                    System.out.print(
                            "  Press Enter to continue..."
                    );

                    scanner.nextLine();
                }
            }
        }
    }

    // ================= APPLICATION HEADER =================

    private static void showHeader() {

        System.out.println();

        System.out.println("=".repeat(WIDTH));

        System.out.println(
                centerText("S K I L L G R I D")
        );

        System.out.println(
                centerText("EMPLOYEE SKILL & PROJECT MATCHING")
        );

        System.out.println("=".repeat(WIDTH));

        System.out.println(
                centerText("Identify Talent | Analyse Gaps | Build Skills")
        );

        System.out.println();

        System.out.println(
                centerText("Welcome to SkillGrid!")
        );

        System.out.println();
    }

    // ================= MAIN MENU =================

    private static void showMenu() {

        printSection("MAIN MENU");

        System.out.println();

        System.out.println("   [1]  Add Employee");

        System.out.println("   [2]  View Employee Directory");

        System.out.println("   [3]  Add Project Requirements");

        System.out.println("   [4]  View Projects");

        System.out.println("   [5]  Find Best-Fit Employees");

        System.out.println("   [6]  Analyse Skill Gaps");

        System.out.println("   [7]  Generate Training Recommendations");

        System.out.println("   [8]  Exit");

        System.out.println();

        printLine();
    }

    // ================= ADD EMPLOYEE =================

    private static void addEmployee(Scanner scanner) {

        printSection("ADD NEW EMPLOYEE");

        String name = readText(
                scanner,
                "  Enter employee name: "
        );

        if (findEmployee(employees, name) != null) {

            System.out.println(
                    "\n  [ERROR] Employee already exists!"
            );

            return;
        }

        Employee employee = new Employee(name);

        int count = readInt(
                scanner,
                "  Number of skills (1-20): ",
                1, 20
        );

        for (int i = 1; i <= count; i++) {

            String skill;

            while (true) {

                skill = readText(
                        scanner,
                        "  Enter skill " + i + ": "
                ).toLowerCase();

                if (employee.getSkills().containsKey(skill)) {

                    System.out.println(
                            "  Skill already entered. Try another."
                    );

                } else {

                    break;
                }
            }

            int rating = readInt(
                    scanner,
                    "  Enter rating (1-5): ",
                    1, 5
            );

            employee.addSkill(skill, rating);
        }

        employees.add(employee);

        System.out.println();

        System.out.println(
                "  [SUCCESS] Employee added successfully!"
        );

        printLine();
    }

    // ================= VIEW EMPLOYEES =================

    private static void viewEmployees() {

        printSection("EMPLOYEE DIRECTORY");

        if (employees.isEmpty()) {

            System.out.println(
                    "  No employees available."
            );

            return;
        }

        System.out.println(
                "  Total Employees: " + employees.size()
        );

        for (Employee employee : employees) {

            employee.display();
        }

        printLine();
    }

    // ================= ADD PROJECT =================

    private static void addProject(Scanner scanner) {

        printSection("ADD PROJECT REQUIREMENTS");

        String name = readText(
                scanner,
                "  Enter project name: "
        );

        if (findProject(projects, name) != null) {

            System.out.println(
                    "\n  [ERROR] Project already exists!"
            );

            return;
        }

        Project project = new Project(name);

        int count = readInt(
                scanner,
                "  Number of required skills (1-20): ",
                1, 20
        );

        for (int i = 1; i <= count; i++) {

            String skill;

            while (true) {

                skill = readText(
                        scanner,
                        "  Enter required skill " + i + ": "
                ).toLowerCase();

                if (project.getRequirements().containsKey(skill)) {

                    System.out.println(
                            "  Skill already entered. Try another."
                    );

                } else {

                    break;
                }
            }

            int level = readInt(
                    scanner,
                    "  Enter required level (1-5): ",
                    1, 5
            );

            project.addRequirement(skill, level);
        }

        projects.add(project);

        System.out.println();

        System.out.println(
                "  [SUCCESS] Project added successfully!"
        );

        printLine();
    }

    // ================= VIEW PROJECTS =================

    private static void viewProjects() {

        printSection("PROJECT DIRECTORY");

        if (projects.isEmpty()) {

            System.out.println(
                    "  No projects available."
            );

            return;
        }

        System.out.println(
                "  Total Projects: " + projects.size()
        );

        for (Project project : projects) {

            project.display();
        }

        printLine();
    }

    // ================= TEXT INPUT VALIDATION =================

    static String readText(
            Scanner scanner,
            String prompt
    ) {

        while (true) {

            System.out.print(prompt);

            String value = scanner.nextLine().trim();

            if (!value.isEmpty()) {

                return value;
            }

            System.out.println(
                    "  Input cannot be empty. Please try again."
            );
        }
    }

    // ================= NUMBER INPUT VALIDATION =================

    static int readInt(
            Scanner scanner,
            String prompt,
            int min,
            int max
    ) {

        while (true) {

            System.out.print(prompt);

            String input = scanner.nextLine().trim();

            try {

                int value = Integer.parseInt(input);

                if (value >= min && value <= max) {

                    return value;
                }

                System.out.printf(
                        "  Enter a value between %d and %d.%n",
                        min, max
                );

            } catch (NumberFormatException exception) {

                System.out.println(
                        "  Invalid input. Enter a valid number."
                );
            }
        }
    }

    // ================= FIND PROJECT =================

    static Project findProject(
            List<Project> projectList,
            String name
    ) {

        for (Project project : projectList) {

            if (project.getName().equalsIgnoreCase(name.trim())) {

                return project;
            }
        }

        return null;
    }

    // ================= FIND EMPLOYEE =================

    private static Employee findEmployee(
            List<Employee> employeeList,
            String name
    ) {

        for (Employee employee : employeeList) {

            if (employee.getName().equalsIgnoreCase(name.trim())) {

                return employee;
            }
        }

        return null;
    }

    // ================= PROFESSIONAL SECTION HEADING =================

    static void printSection(String title) {

        System.out.println();

        System.out.println("=".repeat(WIDTH));

        System.out.println(centerText(title.toUpperCase()));

        System.out.println("=".repeat(WIDTH));
    }

    // ================= CENTER ALIGNMENT =================

    private static String centerText(String text) {

        int padding = Math.max(0, (WIDTH - text.length()) / 2);

        return " ".repeat(padding) + text;
    }

    // ================= PROFESSIONAL DIVIDER =================

    static void printLine() {

        System.out.println("-".repeat(WIDTH));
    }
}
