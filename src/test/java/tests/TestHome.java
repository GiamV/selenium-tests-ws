package tests;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestHome {
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
    public void irALeccion1() throws InterruptedException {
        driver.get("http://localhost:5173");

        WebElement inputEmail = driver.findElement(By.name("login"));
        WebElement inputPassword = driver.findElement(By.name("password"));
        WebElement btnLogin = driver.findElement(By.id("btn-login"));

        Thread.sleep(1000);
        escribirLento(inputEmail, "juan@gmail.com", 200);
        Thread.sleep(500);
        escribirLento(inputPassword, "12345", 200);
        Thread.sleep(500);

        btnLogin.click();

        // Esperar hasta que esté en /home
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement btnLecciones = wait.until(
            ExpectedConditions.elementToBeClickable(By.id("lessons-section"))
        );
        btnLecciones.click();
        Thread.sleep(3000); // Esperar para ver la redirección
        WebElement btnLecciones2 = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("lesson-1"))
            );
            btnLecciones2.click();
            Thread.sleep(5000); // Esperar para ver la redirección
            Assertions.assertTrue(driver.getCurrentUrl().contains("/lessons/lesson/1")); 
    }


    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}