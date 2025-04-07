import java.util.*;

public class Bully {
    static int num_pr, old_cord, new_cord, curr_elec, failed_process;
    static int[] isActive;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of processes: ");
        num_pr = sc.nextInt();
        isActive = new int[num_pr + 1];
        Arrays.fill(isActive, 1);

        old_cord = num_pr;
        isActive[old_cord] = 0;

        System.out.print("Enter the process that initiates the election: ");
        curr_elec = sc.nextInt();

        System.out.println("The process that failed is: " + old_cord);
        System.out.print("Enter another failed process (or 0 if none): ");
        failed_process = sc.nextInt();
        isActive[failed_process] = 0;

        new_cord = election(curr_elec);
        System.out.println("\nFinally process " + new_cord + " became the new leader\n");

        for (int i = 1; i < num_pr; i++) {
            if (isActive[i] == 1 && i != new_cord)
                System.out.println("Process " + new_cord + " passes Coordinator(" + new_cord + ") to process " + i);
        }
    }

    public static int election(int initiator) {
        int higher = initiator;
        for (int i = initiator; i <= num_pr; i++) {
            if (isActive[i] == 1) {
                for (int j = i + 1; j <= num_pr; j++) {
                    if (isActive[j] == 1)
                        System.out.println("Process " + i + " passes Election(" + initiator + ") to process " + j);
                }
                for (int j = i + 1; j <= num_pr; j++) {
                    if (isActive[j] == 1) {
                        System.out.println("Process " + j + " passes Ok(" + j + ") to process " + i);
                        if (j > higher) higher = j;
                    }
                }
                System.out.println();
            }
        }
        return higher;
    }
}
