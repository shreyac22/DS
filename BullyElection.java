import java.util.*;

public class BullyElection {
    static int[] processes;
    static boolean[] active;
    static int coordinator = -1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        processes = new int[n];
        active = new boolean[n];

        System.out.println("Enter process IDs:");
        for (int i = 0; i < n; i++) {
            processes[i] = sc.nextInt();
            active[i] = true;
        }

        coordinator = findHighestActive();
        System.out.println("Initially, Coordinator is: " + processes[coordinator]);

        int choice;
        do {
            System.out.println("\n1. Crash process\n2. Activate process\n3. Display coordinator\n4. Exit");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter process ID to crash: ");
                    int crashID = sc.nextInt();
                    crashProcess(crashID);
                    break;
                case 2:
                    System.out.print("Enter process ID to activate: ");
                    int activateID = sc.nextInt();
                    activateProcess(activateID);
                    break;
                case 3:
                    System.out.println("Current Coordinator: " + processes[coordinator]);
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 4);

        sc.close();
    }

    static int findHighestActive() {
        int max = -1;
        for (int i = 0; i < processes.length; i++) {
            if (active[i] && (max == -1 || processes[i] > processes[max])) {
                max = i;
            }
        }
        return max;
    }

    static void crashProcess(int id) {
        for (int i = 0; i < processes.length; i++) {
            if (processes[i] == id && active[i]) {
                active[i] = false;
                System.out.println("Process " + id + " crashed.");
                if (coordinator == i) {
                    coordinator = findHighestActive();
                    System.out.println("New Coordinator is: " + processes[coordinator]);
                }
                return;
            }
        }
        System.out.println("Process " + id + " not found or already inactive.");
    }

    static void activateProcess(int id) {
        for (int i = 0; i < processes.length; i++) {
            if (processes[i] == id && !active[i]) {
                active[i] = true;
                System.out.println("Process " + id + " activated.");
                if (processes[i] > processes[coordinator]) {
                    coordinator = i;
                    System.out.println("New Coordinator is: " + processes[coordinator]);
                }
                return;
            }
        }
        System.out.println("Process " + id + " not found or already active.");
    }
}