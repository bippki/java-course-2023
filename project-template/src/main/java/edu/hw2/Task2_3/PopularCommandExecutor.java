package edu.hw2.Task2_3;

public final class PopularCommandExecutor {
    private final ConnectionManager manager;
    private final int maxAttempts;

    public PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
        this.manager = manager;
        this.maxAttempts = maxAttempts;
    }

    public void updatePackages() {
        tryExecute("apt update");

    }

    void tryExecute(String command) {
        ConnectionException exc = null;
        for (int i = 0; i < maxAttempts; i++) {
            try (Connection connection = manager.getConnection()) {
                connection.execute(command);
                return;
            } catch (Exception e) {
                if (exc == null) {
                    exc = new ConnectionException();
                }
                exc.addSuppressed(e);
            }
        }
        if (exc != null) {
            throw exc;
        }
    }
}
