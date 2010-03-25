package slimDriver;

import java.util.List;
import java.util.Map;

import fitnesse.components.CommandRunner;
import fitnesse.slim.SlimClient;
import fitnesse.slim.SlimError;

public class SlimDriver {

    private int port;
    private String commandPattern;
    private boolean started;
    private CommandRunner slimRunner;
    private SlimClient slimClient;

    public SlimDriver(String commandPattern, int port) {
        this.commandPattern = commandPattern;
        this.port = port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setCommandPattern(String commandPattern) {
        this.commandPattern = commandPattern;
    }

    public CommandRunner getSlimRunner() {
        return new CommandRunner(getSlimCommand(), "");
    }

    private String getSlimCommand() {
        return String.format("%s %d", commandPattern, port);
    }

    public boolean isStarted() {
        return started;
    }

    public void start() throws Exception {
        if (started) {
            throw new SlimRunnerAlreadyStartedException();
        }
        slimRunner = getSlimRunner();
        slimRunner.asynchronousStart();
        slimClient = new SlimClient("localhost", port);
        waitForConnection();
        started = true;
    }

    public void stop() throws Exception {
        if (!started) {
            return;
        }
        bye();
        started = false;
    }

    private void waitForConnection() throws Exception {
        while (!isConnected()) {
            Thread.sleep(50);
        }
    }

    private boolean isConnected() throws Exception {
        try {
            slimClient.connect();
            return true;
        } catch (Exception e) {
            return false;
        } catch (SlimError e) {
            System.out.println(slimRunner.getError());
            throw e;
        }
    }

    private void bye() throws Exception {
        slimClient.sendBye();
        slimRunner.kill();
    }

    public SlimClient getSlimClient() {
        return slimClient;
    }

    public Map<String, Object> invokeAndGetResponse(List<Object> statements)
            throws Exception {
        return slimClient.invokeAndGetResponse(statements);
    }

}
