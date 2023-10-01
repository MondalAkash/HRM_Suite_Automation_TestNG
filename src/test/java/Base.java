import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class Base {
    public WebDriver driver;
    @BeforeSuite
    public void setup() throws InterruptedException {
        String URL = "https://opensource-demo.orangehrmlive.com/";
        //String URL = "https://opensource-demo.orangehrmlive.com/web/index.php/pim/addEmployee";
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(URL);
        Thread.sleep(5000);
    }
//    @AfterSuite
//    public void tearDown() throws InterruptedException {
//        Thread.sleep(5000);
//        driver.close();
//    }
}

