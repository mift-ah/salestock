package salestock;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC02Home extends TC01Opening {
	
	@Test
	//screenshot user saat mengakses halaman http dan https
	public void TC02001Home() throws Exception{
		driver.get("http://automationpractice.com/");
	    takeScreenshot ("homepage with http");
		driver.get("https://automationpractice.com/");
	    takeScreenshot ("homepage with https");
    }
	@Test
	//verifikasi klik pada contact us menuju halaman tertentu
	public void TC02002ClickContackUs() {
		driver.get(baseUrl);
		driver.findElement(By.linkText("Contact us")).click();
		Assert.assertEquals(driver.getCurrentUrl(), baseUrl + "index.php?controller=contact");
	}
	@Test
	//verifikasi url contact us berisi UI yang dimaksud
	public void TC02003VerifyContackUsPage() {
		driver.get(baseUrl + "index.php?controller=contact");
		Assert.assertTrue(driver.findElement(By.xpath("//h1")).getText().contains("CUSTOMER SERVICE - CONTACT US"));
	}
	@Test
	//verifikasi memilih customer service pada contact us
	public void TC02005CSonContactUs() {
		driver.get(baseUrl + "index.php?controller=contact");
		new Select(driver.findElement(By.id("id_contact"))).selectByVisibleText("Customer service");
		Assert.assertTrue(driver.findElement(By.id("desc_contact2")).isDisplayed());
		//id desc_contact2 mengandung text "For any question about a product, an order" muncul saat memilih Customer service 
	}
	@Test
	//verifikasi memilih webmaster pada contact us
	public void TC02006WMonContactUs() {
		driver.get(baseUrl + "index.php?controller=contact");
		new Select(driver.findElement(By.id("id_contact"))).selectByVisibleText("Webmaster");
		Assert.assertTrue(driver.findElement(By.id("desc_contact1")).isDisplayed());
		//id desc_contact1 mengandung text "If a technical problem occurs on this website" muncul saat memilih Webmaster 
	}
	@Test
	//Verifikasi tidak bisa mengirim pesan jika email salah
	public void TC02007WrongEmailContactUs() {
		driver.get(baseUrl + "index.php?controller=contact");
		new Select(driver.findElement(By.id("id_contact"))).selectByVisibleText("Webmaster");
		driver.findElement(By.id("email")).clear();
	    driver.findElement(By.id("email")).sendKeys("bukan alamat email");
		driver.findElement(By.id("message")).clear();
	    driver.findElement(By.id("mesage")).sendKeys("pesan");
	    driver.findElement(By.xpath("//button[@type='submit']")).click();
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    Assert.assertTrue(driver.findElement(By.xpath("//ol")).getText().contains("Invalid email address."));;
	}
	@Test
	//Verifikasi tidak bisa mengirim pesan jika message kosong
	public void TC02008EmptyMsgContactUs() {
		driver.get(baseUrl + "index.php?controller=contact");
		new Select(driver.findElement(By.id("id_contact"))).selectByVisibleText("Webmaster");
		driver.findElement(By.id("email")).clear();
	    driver.findElement(By.id("email")).sendKeys("alamat@email.com");
		driver.findElement(By.id("message")).clear();
	    driver.findElement(By.xpath("//button[@type='submit']")).click();
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    Assert.assertTrue(driver.findElement(By.xpath("//ol")).getText().contains("The message cannot be blank."));;
	}
	@Test
	//Verifikasi tidak bisa mengirim pesan jika email salah
	public void TC02008SuccessSendContactUs() {
		driver.get(baseUrl + "index.php?controller=contact");
		new Select(driver.findElement(By.id("id_contact"))).selectByVisibleText("Webmaster");
		driver.findElement(By.id("email")).clear();
	    driver.findElement(By.id("email")).sendKeys("alamat@email.com");
		driver.findElement(By.id("message")).clear();
	    driver.findElement(By.id("mesage")).sendKeys("pesan");
	    driver.findElement(By.xpath("//button[@type='submit']")).click();
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    Assert.assertTrue(driver.findElement(By.xpath("//body/div/div/div/div/div/p")).getText().contains("Your message has been successfully sent to our team."));;
	}
	@Test
	//Verifikasi klik Sign in menuju halaman tertentu
	public void TC02009ClickSignIn() {
		driver.get(baseUrl);
		driver.findElement(By.linkText("Sign in")).click();
		Assert.assertEquals(driver.getCurrentUrl(), baseUrl + "index.php?controller=authentication&back=my-account");
	}
	@Test
	//verifikasi halaman sign in berisi UI yang dimaksud
	public void TC02010VerifySignInPage() {
		driver.get(baseUrl + "index.php?controller=authentication&back=my-account");
		Assert.assertTrue(driver.findElement(By.xpath("//h1")).getText().contains("AUTHENTICATION"));
	}
	@Test
	//verifikasi memasukkan email yang salah di kolom create an account
	public void TC02011WrongEmailSignIn1() {
		driver.get(baseUrl + "index.php?controller=authentication&back=my-account");
		driver.findElement(By.id("email_create")).clear();
	    driver.findElement(By.id("email_create")).sendKeys("bukan alamat email");
	    driver.findElement(By.id("SubmitCreate")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//ol")).getText().contains("Invalid email address."));
	}
	@Test
	//verifikasi menuju halaman create an account
	/*karena halaman create account tidak bisa direfresh, maka test case ini jadi opening untuk tescase berikutnya.
	Jika testcase ini failed, maka dipastikan semua testcase create account akan failed juga*/
	public void TC02012ClickCreateAnAccount() {
		driver.get(baseUrl + "index.php?controller=authentication&back=my-account");
		driver.findElement(By.id("email_create")).clear();
	    driver.findElement(By.id("email_create")).sendKeys("alamat@email.com");
	    driver.findElement(By.id("SubmitCreate")).click();
		Assert.assertEquals(driver.getCurrentUrl(), baseUrl + "index.php?controller=authentication#account-creation");
	}
	@Test
	//verifikasi UI halaman create an account
	public void TC02013UICreateAnAccount() {
		Assert.assertEquals(driver.getCurrentUrl(), baseUrl + "index.php?controller=authentication#account-creation");
		Assert.assertTrue(driver.findElement(By.xpath("//h1")).getText().contains("CREATE AN ACCOUNT"));
	}
	@Test
	//Verifikasi error ketika belum mengisi nama depan
	public void TC02014RegisterNoFName() {
		driver.findElement(By.id("customer_firstname")).clear();
		driver.findElement(By.id("customer_lastname")).clear();
	    driver.findElement(By.id("customer_lastname")).sendKeys("belakang");
		driver.findElement(By.id("email")).clear();
	    driver.findElement(By.id("email")).sendKeys("alamat@email.com");
		driver.findElement(By.id("passwd")).clear();
	    driver.findElement(By.id("passwd")).sendKeys("password");
		driver.findElement(By.id("address1")).clear();
	    driver.findElement(By.id("address1")).sendKeys("alamat");
		driver.findElement(By.id("city")).clear();
	    driver.findElement(By.id("city")).sendKeys("kota");
		new Select(driver.findElement(By.id("id_state"))).selectByVisibleText("Alaska");
		driver.findElement(By.id("postcode")).clear();
	    driver.findElement(By.id("postcode")).sendKeys("12345");
		driver.findElement(By.id("phone_mobile")).clear();
	    driver.findElement(By.id("phone_mobile")).sendKeys("012345678");
	    driver.findElement(By.id("submitAccount")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//ol")).getText().contains("firstname is required."));
	}
	@Test
	//Verifikasi error ketika belum mengisi nama belakang
	public void TC02014RegisterNoLName() {
		driver.findElement(By.id("customer_firstname")).clear();
	    driver.findElement(By.id("customer_firstname")).sendKeys("depan");
		driver.findElement(By.id("customer_lastname")).clear();
		driver.findElement(By.id("email")).clear();
	    driver.findElement(By.id("email")).sendKeys("alamat@email.com");
		driver.findElement(By.id("passwd")).clear();
	    driver.findElement(By.id("passwd")).sendKeys("password");
		driver.findElement(By.id("address1")).clear();
	    driver.findElement(By.id("address1")).sendKeys("alamat");
		driver.findElement(By.id("city")).clear();
	    driver.findElement(By.id("city")).sendKeys("kota");
		new Select(driver.findElement(By.id("id_state"))).selectByVisibleText("Alaska");
		driver.findElement(By.id("postcode")).clear();
	    driver.findElement(By.id("postcode")).sendKeys("12345");
		driver.findElement(By.id("phone_mobile")).clear();
	    driver.findElement(By.id("phone_mobile")).sendKeys("012345678");
	    driver.findElement(By.id("submitAccount")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//ol")).getText().contains("lastname is required."));
	}		
	@Test
	//Verifikasi error ketika belum mengisi email
	public void TC02014RegisterNoEmail() {
		driver.findElement(By.id("customer_firstname")).clear();
	    driver.findElement(By.id("customer_firstname")).sendKeys("depan");
		driver.findElement(By.id("customer_lastname")).clear();
	    driver.findElement(By.id("customer_lastname")).sendKeys("belakang");
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("passwd")).clear();
	    driver.findElement(By.id("passwd")).sendKeys("password");
		driver.findElement(By.id("address1")).clear();
	    driver.findElement(By.id("address1")).sendKeys("alamat");
		driver.findElement(By.id("city")).clear();
	    driver.findElement(By.id("city")).sendKeys("kota");
		new Select(driver.findElement(By.id("id_state"))).selectByVisibleText("Alaska");
		driver.findElement(By.id("postcode")).clear();
	    driver.findElement(By.id("postcode")).sendKeys("12345");
		driver.findElement(By.id("phone_mobile")).clear();
	    driver.findElement(By.id("phone_mobile")).sendKeys("012345678");
	    driver.findElement(By.id("submitAccount")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//ol")).getText().contains("email is required."));
	}	
	@Test
	//Verifikasi error ketika belum mengisi password
	public void TC02014RegisterNoPass() {
		driver.findElement(By.id("customer_firstname")).clear();
	    driver.findElement(By.id("customer_firstname")).sendKeys("depan");
		driver.findElement(By.id("customer_lastname")).clear();
	    driver.findElement(By.id("customer_lastname")).sendKeys("belakang");
		driver.findElement(By.id("email")).clear();
	    driver.findElement(By.id("email")).sendKeys("alamat@email.com");
		driver.findElement(By.id("passwd")).clear();
		driver.findElement(By.id("address1")).clear();
	    driver.findElement(By.id("address1")).sendKeys("alamat");
		driver.findElement(By.id("city")).clear();
	    driver.findElement(By.id("city")).sendKeys("kota");
		new Select(driver.findElement(By.id("id_state"))).selectByVisibleText("Alaska");
		driver.findElement(By.id("postcode")).clear();
	    driver.findElement(By.id("postcode")).sendKeys("12345");
		driver.findElement(By.id("phone_mobile")).clear();
	    driver.findElement(By.id("phone_mobile")).sendKeys("012345678");
	    driver.findElement(By.id("submitAccount")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//ol")).getText().contains("passwd is required."));
	}	
	@Test
	//Verifikasi error ketika belum mengisi alamat
	public void TC02014RegisterNoAddress() {
		driver.findElement(By.id("customer_firstname")).clear();
	    driver.findElement(By.id("customer_firstname")).sendKeys("depan");
		driver.findElement(By.id("customer_lastname")).clear();
	    driver.findElement(By.id("customer_lastname")).sendKeys("belakang");
		driver.findElement(By.id("email")).clear();
	    driver.findElement(By.id("email")).sendKeys("alamat@email.com");
		driver.findElement(By.id("passwd")).clear();
	    driver.findElement(By.id("passwd")).sendKeys("password");
		driver.findElement(By.id("address1")).clear();
		driver.findElement(By.id("city")).clear();
	    driver.findElement(By.id("city")).sendKeys("kota");
		new Select(driver.findElement(By.id("id_state"))).selectByVisibleText("Alaska");
		driver.findElement(By.id("postcode")).clear();
	    driver.findElement(By.id("postcode")).sendKeys("12345");
		driver.findElement(By.id("phone_mobile")).clear();
	    driver.findElement(By.id("phone_mobile")).sendKeys("012345678");
	    driver.findElement(By.id("submitAccount")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//ol")).getText().contains("address1 is required."));
	}	
	@Test
	//Verifikasi error ketika belum mengisi kota
	public void TC02014RegisterNoCity() {
		driver.findElement(By.id("customer_firstname")).clear();
	    driver.findElement(By.id("customer_firstname")).sendKeys("depan");
		driver.findElement(By.id("customer_lastname")).clear();
	    driver.findElement(By.id("customer_lastname")).sendKeys("belakang");
		driver.findElement(By.id("email")).clear();
	    driver.findElement(By.id("email")).sendKeys("alamat@email.com");
		driver.findElement(By.id("passwd")).clear();
	    driver.findElement(By.id("passwd")).sendKeys("password");
		driver.findElement(By.id("address1")).clear();
	    driver.findElement(By.id("address1")).sendKeys("alamat");
		driver.findElement(By.id("city")).clear();
		new Select(driver.findElement(By.id("id_state"))).selectByVisibleText("Alaska");
		driver.findElement(By.id("postcode")).clear();
	    driver.findElement(By.id("postcode")).sendKeys("12345");
		driver.findElement(By.id("phone_mobile")).clear();
	    driver.findElement(By.id("phone_mobile")).sendKeys("012345678");
	    driver.findElement(By.id("submitAccount")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//ol")).getText().contains("city is required."));
	}	
	@Test
	//Verifikasi error ketika belum mengisi kodepost
	public void TC02014RegisterNoPostcode() {
		driver.findElement(By.id("customer_firstname")).clear();
	    driver.findElement(By.id("customer_firstname")).sendKeys("depan");
		driver.findElement(By.id("customer_lastname")).clear();
	    driver.findElement(By.id("customer_lastname")).sendKeys("belakang");
		driver.findElement(By.id("email")).clear();
	    driver.findElement(By.id("email")).sendKeys("alamat@email.com");
		driver.findElement(By.id("passwd")).clear();
	    driver.findElement(By.id("passwd")).sendKeys("password");
		driver.findElement(By.id("address1")).clear();
	    driver.findElement(By.id("address1")).sendKeys("alamat");
		driver.findElement(By.id("city")).clear();
	    driver.findElement(By.id("city")).sendKeys("kota");
		new Select(driver.findElement(By.id("id_state"))).selectByVisibleText("Alaska");
		driver.findElement(By.id("postcode")).clear();
		driver.findElement(By.id("phone_mobile")).clear();
	    driver.findElement(By.id("phone_mobile")).sendKeys("012345678");
	    driver.findElement(By.id("submitAccount")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//ol")).getText().contains("The Zip/Postal code you've entered is invalid. It must follow this format: 00000"));
	}	
	@Test
	//Verifikasi error ketika belum mengisi nomor telepon
	public void TC02014RegisterNoPhone() {
		driver.findElement(By.id("customer_firstname")).clear();
	    driver.findElement(By.id("customer_firstname")).sendKeys("depan");
		driver.findElement(By.id("customer_lastname")).clear();
	    driver.findElement(By.id("customer_lastname")).sendKeys("belakang");
		driver.findElement(By.id("email")).clear();
	    driver.findElement(By.id("email")).sendKeys("alamat@email.com");
		driver.findElement(By.id("passwd")).clear();
	    driver.findElement(By.id("passwd")).sendKeys("password");
		driver.findElement(By.id("address1")).clear();
	    driver.findElement(By.id("address1")).sendKeys("alamat");
		driver.findElement(By.id("city")).clear();
	    driver.findElement(By.id("city")).sendKeys("kota");
		new Select(driver.findElement(By.id("id_state"))).selectByVisibleText("Alaska");
		driver.findElement(By.id("postcode")).clear();
	    driver.findElement(By.id("postcode")).sendKeys("12345");
		driver.findElement(By.id("phone_mobile")).clear();
		driver.findElement(By.id("phone")).clear();
	    driver.findElement(By.id("submitAccount")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//ol")).getText().contains("You must register at least one phone number."));
	}
	public void TC02015SuccessRegister() {
		driver.findElement(By.id("customer_firstname")).clear();
	    driver.findElement(By.id("customer_firstname")).sendKeys("depan");
		driver.findElement(By.id("customer_lastname")).clear();
	    driver.findElement(By.id("customer_lastname")).sendKeys("belakang");
		driver.findElement(By.id("email")).clear();
	    driver.findElement(By.id("email")).sendKeys("alamat@email.com");
		driver.findElement(By.id("passwd")).clear();
	    driver.findElement(By.id("passwd")).sendKeys("password");
		driver.findElement(By.id("address1")).clear();
	    driver.findElement(By.id("address1")).sendKeys("alamat");
		driver.findElement(By.id("city")).clear();
	    driver.findElement(By.id("city")).sendKeys("kota");
		new Select(driver.findElement(By.id("id_state"))).selectByVisibleText("Alaska");
		driver.findElement(By.id("postcode")).clear();
	    driver.findElement(By.id("postcode")).sendKeys("12345");
		driver.findElement(By.id("phone_mobile")).clear();
	    driver.findElement(By.id("phone_mobile")).sendKeys("012345678");
		driver.findElement(By.id("phone")).clear();
	    driver.findElement(By.id("submitAccount")).click();
		Assert.assertEquals(driver.getCurrentUrl(), baseUrl + "index.php?controller=my-account");
		}
	
}
