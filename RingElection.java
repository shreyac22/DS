//7

import java.util.Scanner;

public class RingElection {
    static int n;
    static int[] process;
    static boolean[] active;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        n = sc.nextInt();
        process = new int[n];
        active = new boolean[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter ID for process " + (i + 1) + ": ");
            process[i] = sc.nextInt();
            active[i] = true;
        }

        // Assume last process is initially down
        active[n - 1] = false;
        System.out.println("Assuming last process is initially down.");

        int choice;
        do {
            System.out.println("\n1. Start Election\n2. Activate Process\n3. Exit");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter initiator ID: ");
                    int initiator = sc.nextInt();
                    startElection(initiator);
                    break;
                case 2:
                    System.out.print("Enter ID to activate: ");
                    int id = sc.nextInt();
                    activateProcess(id);
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 3);

        sc.close();
    }

    static void startElection(int initiatorID) {
        int index = findIndex(initiatorID);
        if (index == -1 || !active[index]) {
            System.out.println("Invalid initiator or process is down.");
            return;
        }

        int maxID = process[index];
        int current = (index + 1) % n;

        while (current != index) {
            if (active[current]) {
                if (process[current] > maxID) {
                    maxID = process[current];
                }
            }
            current = (current + 1) % n;
        }

        System.out.println("Election complete. New Coordinator is: " + maxID);
    }

    static void activateProcess(int id) {
        int index = findIndex(id);
        if (index == -1) {
            System.out.println("Invalid process ID.");
        } else {
            active[index] = true;
            System.out.println("Process " + id + " activated.");
        }
    }

    static int findIndex(int id) {
        for (int i = 0; i < n; i++) {
            if (process[i] == id) {
                return i;
            }
        }
        return -1;
    }
}
