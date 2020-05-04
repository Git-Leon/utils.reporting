package com.github.git_leon;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author leonhunter
 * @created 05/04/2020 - 12:31 AM
 */
public class ExtentTestLoggerFactory {
    private final ExtentHtmlReporter extentHtmlReporters;
    private final ExtentReports extentReports;
    private final List<ExtentTestLogger> extentTests;

    public ExtentTestLoggerFactory(ExtentReports extentReports, ExtentHtmlReporter extentHtmlReporters, List<ExtentTestLogger> extentTests) {
        this.extentReports = extentReports;
        this.extentHtmlReporters = extentHtmlReporters;
        this.extentTests = extentTests;
    }

    public ExtentTestLoggerFactory(String extentReporterName) {
        this(new ExtentReports(), new ExtentHtmlReporter(extentReporterName), new ArrayList<>());
    }

    public ExtentReports getExtentReports() {
        return extentReports;
    }

    public ExtentHtmlReporter getExtentHtmlReporters() {
        return extentHtmlReporters;
    }

    public List<ExtentTestLogger> getExtentTestLoggers() {
        return extentTests;
    }

    public ExtentTestLogger createExtentTestLogger(String testName, String description) {
        return getExtentTestLogger(testName).orElse(new ExtentTestLogger(extentReports.createTest(testName, description)));
    }

    public Optional<ExtentTestLogger> getExtentTestLogger(String testName) {
        return getExtentTestLoggers()
                .stream()
                .filter(extentTest -> extentTest
                        .getExtentTest()
                        .getModel()
                        .getName()
                        .equals(testName))
                .findFirst();
    }

    public void flush() {
        extentHtmlReporters.flush();
        extentReports.flush();
    }
}
