import java.util.*;

public class Ring {
    static int num_pr, old_cord, new_cord, initiator, failed_process;
    static int[] isActive, arr;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of processes: ");
        num_pr = sc.nextInt();

        isActive = new int[num_pr + 1];
        Arrays.fill(isActive, 1);
        old_cord = num_pr;
        isActive[old_cord] = 0;

        System.out.print("Enter the process initiating the election: ");
        initiator = sc.nextInt();

        System.out.println("The failed coordinator is: " + old_cord);
        System.out.print("Enter another failed process (or 0 if none): ");
        failed_process = sc.nextInt();
        isActive[failed_process] = 0;

        new_cord = election(initiator);
        System.out.println("\nFinally, process " + new_cord + " became the new leader\n");

        for (int i = 1; i <= num_pr; i++) {
            if (isActive[i] == 1 && i != new_cord)
                System.out.println("Process " + new_cord + " passes Coordinator(" + new_cord + ") to process " + i);
        }
    }

    static int election(int initiator) {
        System.out.println("\nElection initiated by process " + initiator);
        arr = new int[num_pr + 1];
        int idx = 0, i = initiator;

        do {
            int receiver = (i % num_pr) + 1;
            while (isActive[receiver] == 0) receiver = (receiver % num_pr) + 1;

            if (isActive[i] == 1 && i != receiver) {
                System.out.println(i + " sends Election message to process " + receiver);
                arr[idx++] = i;
                printArray(arr, idx);
            }

            i = receiver;
        } while (i != initiator);

        new_cord = Arrays.stream(arr).max().orElse(0);
        return new_cord;
    }

    static void printArray(int[] arr, int size) {
        System.out.print("[ ");
        for (int i = 0; i < size; i++)
            if (arr[i] != 0) System.out.print(arr[i] + " ");
        System.out.println("]");
    }
}
