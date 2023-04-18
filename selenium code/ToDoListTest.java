import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ToDoListTest {

    public static void main(String[] args) throws InterruptedException {
        // Set the system property for Chrome driver
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver.exe");

        // Create an instance of ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Navigate to the test application
        driver.get("https://todo-list-login.firebaseapp.com/");

        // Click on the login with GitHub button
        driver.findElement(By.xpath("//button[contains(text(),'Login with GitHub')]")).click();

        // Enter your GitHub username and password
        driver.findElement(By.id("login_field")).sendKeys("your_username");
        driver.findElement(By.id("password")).sendKeys("your_password");

        // Click on the Sign in button
        driver.findElement(By.name("commit")).click();

        // Wait for the page to load
        Thread.sleep(5000);

        // Create 10 to-do items with random strings
        for (int i = 1; i <= 10; i++) {
            driver.findElement(By.id("add-todo")).sendKeys("Todo Item " + i + " - " + generateRandomString());
            driver.findElement(By.id("add-todo")).submit();
            Thread.sleep(1000);
        }

        // Log out of the application
        driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();

        // Wait for the page to load
        Thread.sleep(5000);

        // Log in again with the same account
        driver.findElement(By.xpath("//button[contains(text(),'Login with GitHub')]")).click();
        driver.findElement(By.id("login_field")).sendKeys("your_username");
        driver.findElement(By.id("password")).sendKeys("your_password");
        driver.findElement(By.name("commit")).click();
        Thread.sleep(5000);

        // Delete items 5-10
        List<WebElement> todoItems = driver.findElements(By.className("view"));
        for (int i = 4; i < todoItems.size(); i++) {
            WebElement item = todoItems.get(i);
            item.findElement(By.className("destroy")).click();
            Thread.sleep(1000);
        }

        // Log out of the application
        driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();

        // Close the browser
        driver.quit();
    }

    // Helper method to generate random strings
    public static String generateRandomString() {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int length = 8;
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(index);
            sb.append(randomChar);
        }
        return sb.toString();
    }
}