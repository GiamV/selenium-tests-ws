package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {
	

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
    }
    
    public void escribirLento(WebElement campo, String texto, int velocidadMs) throws InterruptedException {
        for (char c : texto.toCharArray()) {
            campo.sendKeys(String.valueOf(c));
            Thread.sleep(velocidadMs); // espera entre cada letra
        }
    }

    @Test
    public void testLoginCorrecto() throws InterruptedException {
        driver.get("http://localhost:5173");

        WebElement inputEmail = driver.findElement(By.name("login"));
        WebElement inputPassword = driver.findElement(By.name("password"));
        WebElement btnLogin = driver.findElement(By.id("btn-login"));

        Thread.sleep(2000); // Esperar que cargue la vista

        escribirLento(inputEmail, "juan@gmail.com", 200);
        Thread.sleep(1000);
        escribirLento(inputPassword, "12345", 200);
        Thread.sleep(1000);

        btnLogin.click();

        Thread.sleep(3000); // Esperar para ver la redirección
        Assertions.assertTrue(driver.getCurrentUrl().contains("/home"));
    }
    
    @Test
    public void testLoginAdminCorrecto() throws InterruptedException {
    	driver.get("http://localhost:5173/admin");

        WebElement inputEmail = driver.findElement(By.name("login"));
        WebElement inputPassword = driver.findElement(By.name("password"));
        WebElement btnLogin = driver.findElement(By.id("btn-login"));

        Thread.sleep(2000); // Esperar que cargue la vista

        escribirLento(inputEmail, "villagiam4@gmail.com", 200);
        Thread.sleep(1000);
        escribirLento(inputPassword, "12345", 200);
        Thread.sleep(1000);

        btnLogin.click();

        Thread.sleep(3000); // Esperar para ver la redirección
        Assertions.assertTrue(driver.getCurrentUrl().contains("/home"));
    }


    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
