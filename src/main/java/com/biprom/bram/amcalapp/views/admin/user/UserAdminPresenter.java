package com.biprom.bram.amcalapp.views.admin.user;

import java.io.Serializable;

import com.biprom.bram.amcalapp.data.entity.User;
import com.biprom.bram.amcalapp.views.admin.AbstractCrudPresenter;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import com.biprom.bram.amcalapp.service.UserService;
import com.biprom.bram.amcalapp.ui.navigation.NavigationManager;

@SpringComponent
@ViewScope
public class UserAdminPresenter extends AbstractCrudPresenter<User, UserService, UserAdminView>
		implements Serializable {

	@Autowired
	public UserAdminPresenter(UserAdminDataProvider userAdminDataProvider, NavigationManager navigationManager,
			UserService service, BeanFactory beanFactory) {
		super(navigationManager, service, User.class, userAdminDataProvider, beanFactory);
	}

	public String encodePassword(String value) {
		return getService().encodePassword(value);
	}

	@Override
	protected void editItem(User item) {
		super.editItem(item);
		getView().setPasswordRequired(item.isNew());
	}
}