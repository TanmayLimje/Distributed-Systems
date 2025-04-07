#include <mpi.h>
#include <stdio.h>

#define N 10
int a[] = { 1,2,3,4,5,6,7,8,9,10 }, a2[1000];

int main(int argc, char* argv[]) {
    int pid, np, elems_per_proc, recv_count, i, sum = 0, tmp;
    MPI_Status status;

    MPI_Init(&argc, &argv);
    MPI_Comm_rank(MPI_COMM_WORLD, &pid);
    MPI_Comm_size(MPI_COMM_WORLD, &np);

    elems_per_proc = N / np;

    if (pid == 0) {
        for (i = 1; i < np; i++) {
            int idx = i * elems_per_proc;
            int count = (i == np - 1) ? (N - idx) : elems_per_proc;
            MPI_Send(&count, 1, MPI_INT, i, 0, MPI_COMM_WORLD);
            MPI_Send(&a[idx], count, MPI_INT, i, 0, MPI_COMM_WORLD);
            printf("Server sending elements to client %d\n", i);
        }

        for (i = 0; i < elems_per_proc; i++) sum += a[i];
        printf("Partial sum of server: %d\n", sum);

        for (i = 1; i < np; i++) {
            MPI_Recv(&tmp, 1, MPI_INT, MPI_ANY_SOURCE, 0, MPI_COMM_WORLD, &status);
            sum += tmp;
        }

        printf("Total sum: %d\n", sum);
    } else {
        MPI_Recv(&recv_count, 1, MPI_INT, 0, 0, MPI_COMM_WORLD, &status);
        MPI_Recv(a2, recv_count, MPI_INT, 0, 0, MPI_COMM_WORLD, &status);

        printf("Client %d received elements\n", pid);
        for (i = 0; i < recv_count; i++) sum += a2[i];
        printf("Partial sum for process %d: %d\n", pid, sum);

        MPI_Send(&sum, 1, MPI_INT, 0, 0, MPI_COMM_WORLD);
    }

    MPI_Finalize();
    return 0;
}





//////////////////////////////////////////////////
// sudo apt install mpich
// mpicc SumArray.c -o sumarray
// mpiexec -np 4 ./summarray
