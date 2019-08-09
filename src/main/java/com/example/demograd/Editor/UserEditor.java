package com.example.demograd.Editor;

import com.example.demograd.Dao.UserDao;
import com.example.demograd.Entity.User;
import com.example.demograd.MainView;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@UIScope
public class UserEditor extends FormLayout {
    private MainView mainView;
    private final UserDao userDao;
    private TextField username = new TextField("Username");
    private TextField address = new TextField("Address");

    private Button save = new Button("Save",VaadinIcon.CHECK.create());
    private Button delete = new Button("Delete",VaadinIcon.TRASH.create());

    private Binder<User> binder = new Binder<>(User.class);
    private ChangeHandler changeHandler;

    public UserEditor(UserDao userDao){
        this.userDao=userDao;
        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(new H3("User Details"),username, address,buttons);

        binder.bindInstanceFields(this);

        save.getElement().getThemeList().add("primary");
        delete.getElement().getThemeList().add("error");
        save.addClickListener(event -> save());
        delete.addClickListener(event -> delete());

    }

    private void delete() {
        User user = binder.getBean();
        userDao.delete(user);
        changeHandler.onChange();
//        mainView.updateList(null);
        setUser(null);
    }

    private void save() {
        User user = binder.getBean();
        userDao.save(user);
        changeHandler.onChange();
//        mainView.updateList(null);
        setUser(null);
    }

    public interface ChangeHandler {
        void onChange();
    }

    public void setUser(User user) {
        binder.setBean(user);
        if (user == null) {
            setVisible(false);
        } else {
            setVisible(true);
            username.focus();
        }
    }

    public void setChangeHandler(ChangeHandler h) {

        changeHandler = h;
    }
}
