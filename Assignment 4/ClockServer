import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;

class ClockServer {
    private static final int PORT = 8080;
    private static final Map<String, ClientData> clientData = new ConcurrentHashMap<>();
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Clock server started on port " + PORT);

            ExecutorService executor = Executors.newCachedThreadPool();
            executor.execute(ClockServer::synchronizeAllClocks);
            
            while (true) {
                Socket clientSocket = serverSocket.accept();
                String clientAddress = clientSocket.getInetAddress().getHostAddress() + ":" + clientSocket.getPort();
                System.out.println(clientAddress + " connected.");
                executor.execute(() -> startReceivingClockTime(clientSocket, clientAddress));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void startReceivingClockTime(Socket clientSocket, String clientAddress) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            while (true) {
                String clockTimeString = in.readLine();
                if (clockTimeString == null) break;
                
                Date clientClockTime = dateFormat.parse(clockTimeString);
                long timeDiffMillis = System.currentTimeMillis() - clientClockTime.getTime();
                clientData.put(clientAddress, new ClientData(clientClockTime, timeDiffMillis, clientSocket));
                
                System.out.println("Client data updated for " + clientAddress);
                Thread.sleep(5000);
            }
        } catch (Exception e) {
            System.out.println("Error with client " + clientAddress);
        }
    }

    private static void synchronizeAllClocks() {
        while (true) {
            try {
                System.out.println("Starting synchronization cycle...");
                if (!clientData.isEmpty()) {
                    long averageTimeDiff = getAverageClockDiff();
                    Date synchronizedTime = new Date(System.currentTimeMillis() + averageTimeDiff);
                    String syncTimeString = dateFormat.format(synchronizedTime);

                    for (Map.Entry<String, ClientData> entry : clientData.entrySet()) {
                        try {
                            PrintWriter out = new PrintWriter(entry.getValue().socket.getOutputStream(), true);
                            out.println(syncTimeString);
                        } catch (IOException e) {
                            System.out.println("Error sending sync time to " + entry.getKey());
                        }
                    }
                } else {
                    System.out.println("No clients connected.");
                }
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static long getAverageClockDiff() {
        return clientData.values().stream()
                .mapToLong(client -> client.timeDifference)
                .sum() / clientData.size();
    }

    private static class ClientData {
        Date clockTime;
        long timeDifference;
        Socket socket;

        ClientData(Date clockTime, long timeDifference, Socket socket) {
            this.clockTime = clockTime;
            this.timeDifference = timeDifference;
            this.socket = socket;
        }
    }
}

