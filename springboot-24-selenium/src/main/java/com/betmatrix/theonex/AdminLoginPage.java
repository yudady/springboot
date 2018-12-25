package com.betmatrix.theonex;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.Objects;

public class AdminLoginPage {

	private WebDriver driver;

	@FindBy(how = How.NAME, using = "username")
    private WebElement name;

	@FindBy(how = How.NAME, using = "password")
	private WebElement pwd;

	@FindBy(how = How.XPATH, using = "/html/body/div/div[2]/form/div[3]/div/button")
	private WebElement submitLink;

	public AdminLoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public AdminLoginPage enterFieldValue(String nameValue, String pwdValue) {
		Objects.nonNull(name);
		this.name.clear();
		this.name.sendKeys(nameValue);
		Objects.nonNull(pwd);
		this.pwd.clear();
		this.pwd.sendKeys(pwdValue);

		return this;
	}

	public void submit() {

		submitLink.click();
	}

}
