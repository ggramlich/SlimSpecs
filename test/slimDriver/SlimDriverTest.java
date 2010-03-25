package slimDriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static util.ListUtility.list;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fitnesse.components.CommandRunner;
import fitnesse.slim.SlimClient;

public class SlimDriverTest {
    private static final String JAVA_COMMAND_PATTERN = "java -cp fitnesse/fitnesse.jar:bin fitnesse.slim.SlimService";
    private static final int SLIM_PORT = 8098;
    private SlimClient slimClient;
    private static final Map<String, String> commandPatterns = new HashMap<String, String>();

    static {
        commandPatterns.put("Java", JAVA_COMMAND_PATTERN);
        commandPatterns.put("PHP",
                "java -cp fitnesse/phpslim.jar slim.PhpSlimService -i php");
        commandPatterns
                .put("JavaScript",
                        "java -cp fitnesse/jsSlim.jar jsSlim.JsSlimService -i javascript");
        commandPatterns
                .put(
                        "C#",
                        "fitnesse/fitSharp/Runner.exe -r fitSharp.Slim.Service.Runner,fitnesse/fitSharp/fitSharp.dll mono/SlimSpecs/bin/Release/SlimSpecs.dll");
        commandPatterns
                .put("Ruby",
                        "ruby -I ./fitnesse/rubyslim -I ./ruby ./fitnesse/rubyslim/run_ruby_slim.rb");
        commandPatterns
                .put(
                        "Python",
                        "fitnesse/waferslim/local-python3.sh -m waferslim.server --syspath ../../../python --port");
    }

    private List<Object> statements;
    private SlimDriver slimDriver;

    @Before
    public void setup() {
        slimDriver = null;
        slimClient = null;
        statements = new ArrayList<Object>();
    }

    @Test
    public void testGetCommandRunner() throws Exception {
        createSlimDriverForCommandPattern(JAVA_COMMAND_PATTERN, 1234);
        CommandRunner commandRunner = slimDriver.getSlimRunner();
        assertEquals(JAVA_COMMAND_PATTERN + " 1234", commandRunner.getCommand());
    }

    @Test
    public void testSlimDriverStartingAndStoppingJavaSlim() throws Exception {
        assertSlimDriverCanStartAndStopFor("Java");
    }

    @Test
    public void testSlimDriverStartingAndStoppingPhpSlim() throws Exception {
        assertSlimDriverCanStartAndStopFor("PHP");
    }

    @Test
    public void testSlimDriverStartingAndStoppingJavaScriptSlim()
            throws Exception {
        assertSlimDriverCanStartAndStopFor("JavaScript");
    }

    @Test
    public void testSlimDriverStartingAndStoppingWaferSlim() throws Exception {
        assertSlimDriverCanStartAndStopFor("Python", SLIM_PORT + 1);
    }

    @Test
    public void testSlimDriverStartingAndStoppingRubySlim() throws Exception {
        assertSlimDriverCanStartAndStopFor("Ruby");
    }

    @Test
    public void testInvokeEchoStringJava() throws Exception {
        assertEchoFixtureWorks("Java", SLIM_PORT);
    }

    @Test
    public void testInvokeEchoStringPhp() throws Exception {
        assertEchoFixtureWorks("PHP", SLIM_PORT);
    }

    @Test
    public void testInvokeEchoStringJavaScript() throws Exception {
        assertEchoFixtureWorks("JavaScript", SLIM_PORT);
    }

    @Test
    public void testInvokeEchoStringPython() throws Exception {
        assertEchoFixtureWorks("Python", SLIM_PORT + 2);
    }

    @Test
    public void testInvokeEchoStringRuby() throws Exception {
        assertEchoFixtureWorks("Ruby", SLIM_PORT);
    }

    // @Test
    // public void testSlimDriverStartingAndStoppingFitSharpSlim()
    // throws Exception {
    // assertSlimDriverCanStartAndStopFor("C#");
    // }
    //
    // @Test
    // public void testInvokeEchoStringFitsharp() throws Exception {
    // assertEchoFixtureWorks("C#", SLIM_PORT);
    // }

    private void assertEchoFixtureWorks(String language, int port)
            throws Exception {
        createSlimDriverForCommandPattern(commandPatterns.get(language), port);
        slimDriver.start();
        statements.add(list("i1", "import", "fixtures.basics"));
        statements.add(list("m1", "make", "echoActor", "EchoFixture"));
        statements.add(list("c1", "call", "echoActor", "echoString", "Hello"));
        Map<String, Object> response = slimDriver
                .invokeAndGetResponse(statements);
        assertEquals("OK", response.get("i1"));
        assertEquals("OK", response.get("m1"));
        assertEquals("Hello", response.get("c1"));
    }

    private void assertSlimDriverCanStartAndStopFor(String language, int port)
            throws Exception {
        assertSlimDriverCanStartAndStop(commandPatterns.get(language), port);
    }

    private void assertSlimDriverCanStartAndStopFor(String language)
            throws Exception {
        assertSlimDriverCanStartAndStopFor(language, SLIM_PORT);
    }

    private void assertSlimDriverCanStartAndStop(String commandPattern, int port)
            throws Exception {
        createSlimDriverForCommandPattern(commandPattern, port);
        assertFalse(slimDriver.isStarted());
        slimDriver.start();
        assertTrue(slimDriver.isStarted());
        slimClient = new SlimClient("localhost", port);
        slimClient.connect();
        slimClient.sendBye();
        slimDriver.stop();
        assertFalse(slimDriver.isStarted());
    }

    private void createSlimDriverForCommandPattern(String commandPattern,
            int port) {
        slimDriver = new SlimDriver(commandPattern, port);
    }

    @After
    public void tearDown() throws Exception {
        if (null != slimDriver) {
            slimDriver.stop();
        }
    }
}
