package com.biprom.bram.amcalapp.views;

import com.biprom.bram.amcalapp.AbstractIT;
import com.biprom.bram.amcalapp.views.dashboard.DashboardViewElement;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

public class LoginIT extends AbstractIT {

	@Test
	public void userIsRedirectedToRequestedView() {
		openLoginView(APP_URL + "#!dashboard").login("barista@vaadin.com", "barista");
		Assert.assertNotNull($(DashboardViewElement.class).first());
	}

	@Test
	public void logoutWorks() {
		loginAsBarista();
		$(MenuElement.class).first().logout();
		Assert.assertEquals("Email", findElement(By.id("login-label")).getText());
	}

}
