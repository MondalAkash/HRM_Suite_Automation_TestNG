package TestCases;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import setup.Base;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class LoginAsAdminCreateUser extends Base {

    @Test(description = "Verify Dashboard Appeared Successfully")
    public void tc001() throws InterruptedException {
        driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("Admin");
        driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("admin123");
        driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();
        Thread.sleep(5000);
        Assert.assertEquals(driver.findElement(By.xpath("//h6[normalize-space()='Dashboard']")).getText(), "Dashboard");
    }
    @Test(description = "Create Employee")
    public void tc002() throws InterruptedException, IOException, ParseException {
        driver.findElement(By.xpath("//span[normalize-space()='PIM']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[normalize-space()='Add']")).click();
        Thread.sleep(6000);
        driver.findElement(By.className("oxd-switch-wrapper")).click();
        String firstName = "Jhon";
        driver.findElement(By.name("firstName")).sendKeys(firstName);
        Thread.sleep(2000);
        String lastName = "Doe";
        String expectedName = firstName + lastName;
        driver.findElement(By.name("lastName")).sendKeys(lastName);
        Thread.sleep(2000);
        String employeeId = ElementActions.generateRandomNumber();
        String userName = "jhondoe"+employeeId;
        driver.findElement(By.xpath("//body/div[@id='app']/div[@class='oxd-layout']/div[@class='oxd-layout-container']/div[@class='oxd-layout-context']/div[@class='orangehrm-background-container']/div[@class='orangehrm-card-container']/form[@class='oxd-form']/div[@class='orangehrm-employee-container']/div[@class='orangehrm-employee-form']/div[@class='oxd-form-row']/div[1]/div[1]/div[1]/div[2]/input[1]")).sendKeys(userName);
        Thread.sleep(2000);
        String password = ElementActions.generateRandomPassword();
        List<WebElement> empId = driver.findElements(By.className("oxd-input"));
        empId.get(4).sendKeys(Keys.CONTROL+"a");
        Thread.sleep(2000);
        empId.get(4).sendKeys(Keys.BACK_SPACE);
        Thread.sleep(2000);
        empId.get(4).sendKeys(employeeId);
        ElementActions.writeJSONFile(firstName, lastName, employeeId, userName, password);
        List<WebElement> formElement =  driver.findElements(By.className("oxd-input--active"));
        Thread.sleep(2000);
        formElement.get(5).sendKeys(password);
        Thread.sleep(2000);
        formElement.get(6).sendKeys(password);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[normalize-space()='Save']")).click();
        Thread.sleep(20000);
        String actualText = driver.findElement(By.xpath("//h6[normalize-space()='Personal Details']")).getText();
        Thread.sleep(2000);
        System.out.println("Actual Text: "+actualText);
        String expectedText = "Personal Details";
        Assert.assertTrue(actualText.contains(expectedText));
    }

    public static class ElementActions {
        public static String generateRandomPassword()
        {
            Random rand = new Random();
            String lowerCase = String.valueOf((char)(rand.nextInt(26) + 'a'));
            String upperCase = String.valueOf((char)(rand.nextInt(26)+'A'));
            String symbol = String.valueOf((char)(rand.nextInt(13)+'!'));
            String number = String.valueOf(rand.nextInt(1000,9999));
            String password = String.valueOf(lowerCase + upperCase + number + symbol);
            //System.out.println(password);
            return password;
        }
        public static void writeJSONFile(String fname, String lname, String eid, String uname, String pw) throws IOException, ParseException {
            String fileLocation ="./src/test/resources/credential.json";
            JSONParser parser = new JSONParser();
            JSONArray credArray = (JSONArray) parser.parse(new FileReader(fileLocation));
            JSONObject userObj = new JSONObject();
            userObj.put("firstName", fname);
            userObj.put("lastName", lname);
            userObj.put("employeeId", eid);
            userObj.put("userName", uname);
            userObj.put("password", pw);
            credArray.add(userObj);
            FileWriter fileWriter = new FileWriter(fileLocation);
            fileWriter.write(credArray.toJSONString());
            fileWriter.flush();
            fileWriter.close();
        }
        public static String generateRandomNumber()
        {
            Random ran = new Random();
            String ranNum = String.valueOf(ran.nextInt(000, 999));
            return ranNum;
        }
    }
}
