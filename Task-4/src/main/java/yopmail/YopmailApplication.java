package yopmail;

import org.testng.annotations.Test;

import yopmail.config.BaseWebYopmail;
import yopmail.page.HomePage;
import yopmail.page.InboxPage;
import yopmail.utils.Common;

public class YopmailApplication extends BaseWebYopmail{
	@Test
	public void OpenMail() {
		HomePage homePage = new HomePage(getDriver(), getExplicitWait());
		Common common = new Common(getDriver(), getExplicitWait());
		InboxPage inboxPage = new InboxPage(getDriver(), getExplicitWait());
		
		String username = "testautomation";
		String iFrame = "ifmail";//=> //iframe[@id='ifmail']
		
		homePage.inputUsername(username);
		homePage.clickBtn();
		common.switchIframe(iFrame);
		String textEmail = inboxPage.getTextEmail();
		
		System.out.println("===> EMAIL \n" + textEmail);
	}
}
