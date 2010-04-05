package tests.fitnesse;

import org.junit.runner.RunWith;

import fitnesse.junit.FitNesseSuite;
import fitnesse.junit.FitNesseSuite.DebugMode;
import fitnesse.junit.FitNesseSuite.FitnesseDir;
import fitnesse.junit.FitNesseSuite.Name;
import fitnesse.junit.FitNesseSuite.OutputDir;

@RunWith(FitNesseSuite.class)
@FitnesseDir(".")
@Name("SlimSpecs.ConcreteTests.PharPhpTests")
@OutputDir("target/test-reports/fitnesse")
@DebugMode(false)
public class PharPhpSuiteTest {

}
