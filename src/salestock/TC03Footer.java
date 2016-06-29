package salestock;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC03Footer extends TC01Opening{
	
	@Test
	//Verifikasi klik women pada footer menuju halaman kategory women
	public void TC03001ClickWomenFooter() {
		driver.get(baseUrl);
		driver.findElement(By.xpath("(//a[contains(text(),'Women')])[2]")).click();
		Assert.assertTrue(driver.findElement(By.className("category-name")).getText().contains("Women"));
	}
	@Test
	//Verifikasi klik spesial pada footer menuju halaman price drop
	public void TC03002ClickSpecialFooter() {
		driver.get(baseUrl);
		driver.findElement(By.linkText("Specials")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//h1")).getText().contains("PRICE DROP"));
	}
	@Test
	//Verifikasi klik new product pada footer menuju halaman New product
	public void TC03003ClickNewProductFooter() {
		driver.get(baseUrl);
		driver.findElement(By.linkText("New products")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//h1")).getText().contains("NEW PRODUCTS"));
	}
	@Test
	//Verifikasi klik best seller pada footer menuju halaman Best seller
	public void TC03004ClickBestSellerFooter() {
		driver.get(baseUrl);
		driver.findElement(By.linkText("Best sellers")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//h1")).getText().contains("BEST SELLERS"));
	}
	@Test
	//Verifikasi klik our store pada footer menuju halaman Our Store
	public void TC03005ClickOurStoreFooter() {
		driver.get(baseUrl);
		driver.findElement(By.xpath("(//a[contains(text(),'Our stores')])[3]")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//h1")).getText().contains("OUR STORE(S)!"));
	}
	@Test
	//Verifikasi klik contac us pada footer menuju halaman customer service
	public void TC03006ClickContactUsFooter() {
		driver.get(baseUrl);
		driver.findElement(By.xpath("(//a[contains(text(),'Contact us')])[2]")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//h1")).getText().contains("CUSTOMER SERVICE - CONTACT US"));
	}
	@Test
	//Verifikasi klik term and condition of use pada footer menuju halaman T & C
	public void TC03007ClickTnCFooter() {
		driver.get(baseUrl);
		driver.findElement(By.linkText("Terms and conditions of use")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//h1")).getText().contains("TERMS AND CONDITIONS OF USE"));
	}
	@Test
	//Verifikasi klik abut us pada footer menuju halaman about us
	public void TC03008ClickAboutUsFooter() {
		driver.get(baseUrl);
		driver.findElement(By.linkText("About us")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//h1")).getText().contains("ABOUT US"));
	}
	@Test
	//Verifikasi klik site map pada footer menuju halaman sitemap
	public void TC03009ClickSiteMapFooter() {
		driver.get(baseUrl);
		driver.findElement(By.linkText("Sitemap")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//h1")).getText().contains("SITEMAP"));
	}

	
}
/*

*/
