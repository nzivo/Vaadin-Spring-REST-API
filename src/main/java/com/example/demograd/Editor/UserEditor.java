package com.example.demograd.Editor;

import com.example.demograd.Dao.UserDao;
import com.example.demograd.Entity.User;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
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
public class UserEditor extends VerticalLayout implements KeyNotifier {
    private final UserDao userDao;
    private User user;

    TextField username = new TextField("Username");
    TextField address = new TextField("Address");

    Button save = new Button("Save", VaadinIcon.CHECK.create());
    Button cancel = new Button("Cancel");
    Button delete = new Button("Delete", VaadinIcon.TRASH.create());

    HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);

    Binder<User> binder = new Binder<>(User.class);
    private ChangeHandler changeHandler;

    @Autowired
    public UserEditor(UserDao userDao) {
        this.userDao = userDao;

        add(username, address, actions);

        // bind using naming convention
        binder.bindInstanceFields(this);

        // Configure and style components
        setSpacing(true);

        save.getElement().getThemeList().add("primary");
        delete.getElement().getThemeList().add("error");

        addKeyPressListener(Key.ENTER, e -> save());

        // wire action buttons to save, delete and reset
        save.addClickListener(e -> save());
        delete.addClickListener(e -> delete());
        cancel.addClickListener(e -> editUser(user));
        setVisible(false);
    }

    void delete() {
        userDao.delete(user);
        changeHandler.onChange();
    }

    void save() {
        userDao.save(user);
        changeHandler.onChange();
    }

    public interface ChangeHandler {
        void onChange();
    }

    public final void editUser(User user) {
        if (user == null) {
            setVisible(false);
            return;
        }
        final int persisted = user.getId();
        if (persisted <= 1) {
            // Find fresh entity for editing
            user = userDao.findById(user.getId());
        }
        else {
            user = user;
        }
//        cancel.setVisible(persisted);

        // Bind customer properties to similarly named fields
        // Could also use annotation or "manual binding" or programmatically
        // moving values from fields to entities before saving
        binder.setBean(user);

        setVisible(true);

        // Focus first name initially
        username.focus();
    }

    public void setChangeHandler(ChangeHandler h) {
        // ChangeHandler is notified when either save or delete
        // is clicked
        changeHandler = h;
    }

}
