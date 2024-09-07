package demo;

import java.rmi.server.ExportException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.j2objc.annotations.Weak;

import java.util.logging.Level;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCases {
    ChromeDriver driver;

    public TestCases() {
        System.out.println("Constructor: TestCases");

        WebDriverManager.chromedriver().timeout(30).setup();
        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        // Set log level and type
        logs.enable(LogType.BROWSER, Level.INFO);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);

        // Connect to the chrome-window running on debugging port
        options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");

        // Set path for log file
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "chromedriver.log");

        driver = new ChromeDriver(options);

        // Set browser to maximize and wait
        driver.manage().window().maximize();
    }

    public void endTest() {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    public void testCase01() {
        System.out.println("Start Test case: testCase01");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        try {
            driver.get("https://www.google.com");
            String gmailUrl = "https://calendar.google.com/";

            if (!driver.getCurrentUrl().equals(gmailUrl)) {
                driver.get(gmailUrl);
            }
            wait.until(ExpectedConditions.urlContains("calendar"));
            System.out.println("The current URL contains \"calendar.\"");
        } catch (Exception e) {
            System.out.println("Exception occurred while verifying contains of calender url" + e.getMessage());
            e.getStackTrace();
        }
        System.out.println("end Test case: testCase01");
    }

    public void testCase02() {
        System.out.println("Start Test case: testCase02");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        Actions actions = new Actions(driver);
        try {
            WebElement arrowDropDown = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(@class, 'VfPpkd-LgbsSe') and @jsname='jnPWCc']")));
            arrowDropDown.click();

            WebElement monthOption = wait
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Month']")));
            if (monthOption.getText().contains("Month")) {
                monthOption.click();
                System.out.println("Switched to Month view successfully.");
            } else {
                throw new AssertionError("Switch to Month view failed.");
            }

            WebElement createButton = wait
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='dwlvNd Hrn1mc']")));
            createButton.click();
            System.out.println("create button clicked");

            WebElement taskLink = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[@class='JAPqpe K0NPx']/span[2]/div[2]//div[text()='Task']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", taskLink);
            // wait.until(ExpectedConditions.visibilityOf(taskLink));
            // actions.moveToElement(taskLink).click().perform();
            taskLink.click();
            System.out.println("Task link clicked");

            WebElement addTitleInput = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Add title']")));
            addTitleInput.sendKeys(("Crio INTV Task Automation"));
            String titleValue = addTitleInput.getAttribute("value");
            if (titleValue.contains("Crio INTV Task Automation")) {
                System.out.println("Title entered is correct: " + titleValue);
            } else {
                System.out.println("Title entered is incorrect: " + titleValue);

            }

            WebElement descriptionInput = wait.until(ExpectedConditions
                    .elementToBeClickable(By.xpath("//textarea[@placeholder='Add description']")));
            descriptionInput.sendKeys("Crio INTV Calendar Task Automation");
            // actions.moveToElement(descriptionInput).click().sendKeys("Crio INTV Calendar
            // Task Automation").build().perform();
            String enteredDescription = descriptionInput.getAttribute("value");
            if (enteredDescription.contains("Crio INTV Calendar Task Automation")) {
                System.out.println("Task description entered successfully.");
            } else {
                throw new AssertionError("Task description entry failed.");
            }

            WebElement saveButton = wait
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Save']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", saveButton);
            actions.moveToElement(saveButton).click().perform();
            System.out.println("Save button clicked");

        } catch (Exception e) {
            System.out.println("Exception occurred while creating task: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("end Test case: testCase02");
    }

    public void testCase03() {
        System.out.println("Start Test case: testCase03");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        Actions actions = new Actions(driver);
        try {

            WebElement taskCreated = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[@class='nHqeVd']/span[@class='meh4fc KU3dEf CPXyj GuKFM']")));
            taskCreated.click();
            System.out.println("task selected");

            WebElement editButton = wait
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='Edit task']")));
            editButton.click();
            System.out.println("edit button clicked");

            WebElement updatedDescription = wait.until(ExpectedConditions
                    .visibilityOfElementLocated(By.xpath("//textarea[@placeholder='Add description']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", updatedDescription);
            updatedDescription.clear();
            updatedDescription.sendKeys(
                    "Crio INTV Task Automation is a test suite designed for automating various tasks on the Google Calendar web application");
            String updatedDescriptionEntered = updatedDescription.getAttribute("value");
            if (updatedDescriptionEntered.contains("Crio INTV Task Automation")) {
                System.out.println("Task description updated successfully.");
            } else {
                throw new AssertionError("Task description update failed.");
            }

            WebElement saveButton = wait
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Save']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", saveButton);
            actions.moveToElement(saveButton).click().perform();
            System.out.println("Save button clicked");
            Thread.sleep(5000);

            WebElement taskUpdated = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//span[@class='nHqeVd']/span[@class='meh4fc KU3dEf CPXyj GuKFM']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", taskUpdated);
            wait.until(ExpectedConditions.elementToBeClickable(taskUpdated));
            taskUpdated.click();
            System.out.println("task selected");
            Thread.sleep(5000);

            WebElement updatedDescriptionText = wait
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='nBzcnc OcVpRe']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", updatedDescriptionText);
            String newDescription = updatedDescriptionText.getText();

            if (newDescription.contains(
                    "Crio INTV Task Automation is a test suite designed for automating various tasks on the Google Calendar web application")) {
                System.out.println("Correct updated details are displayed: " + newDescription);
            } else {
                System.out.println("Incorrect updated details are displayed: " + newDescription);
            }

        } catch (Exception e) {
            System.out.println("Exception occurred while updating task: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("end Test case: testCase03");
    }

    public void testCase04() {
        System.out.println("Start Test case: testCase04");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    
        try {
            WebElement editButton = wait
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='Edit task']")));
            editButton.click();
            System.out.println("edit button clicked");
            Thread.sleep(5000);

            WebElement addTitleInput = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Add title']")));
            String titleText = addTitleInput.getAttribute("value");
            if (titleText.equals("Crio INTV Task Automation")) {
                System.out.println("Title verified is correct: " + titleText);
            } else {
                System.out.println("Title verified is incorrect: " + titleText);
            }

            WebElement deleteButton = wait
                    .until(ExpectedConditions
                            .elementToBeClickable(By.xpath("//button[@aria-label='Delete']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", deleteButton);
           deleteButton.click();
            System.out.println("Delete button clicked");

            WebElement taskDeletedToaster = wait
                    .until(ExpectedConditions
                            .elementToBeClickable(By.xpath("//div[@class='YrbPvc']/div[text()='Task deleted']")));
            if (taskDeletedToaster.getText().contains("Task deleted")) {
                System.out.println("Task deleted alert verified.");
            } else {
                throw new AssertionError("Task deletion alert verification failed.");
            }

        } catch (Exception e) {
            System.out.println("Exception occurred while updating task: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("end Test case: testCase04");

    }

}
