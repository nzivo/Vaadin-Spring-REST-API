package com.example.demograd;

import com.example.demograd.Dao.UserDao;
import com.example.demograd.Editor.UserEditor;
import com.example.demograd.Entity.User;
import com.example.demograd.Service.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import org.springframework.util.StringUtils;


@Route
@PWA(name = "Project Base for REST API", shortName = "DEMO REST API")
public class MainView extends VerticalLayout {
    private TextField filterUsers = new TextField();
    private final UserDao userDao;
    private Grid<User> grid = new Grid<>(User.class);
    private final UserEditor userEditor;

    public MainView(UserDao userDao, UserEditor userEditor) {
//        userEditor.setUser(null);
        //Filter tab
        filterUsers.setPlaceholder("Search by Id");
        filterUsers .setClearButtonVisible(true);
        filterUsers.setValueChangeMode(ValueChangeMode.EAGER);
        Button addUserBtn = new Button("Add new User", VaadinIcon.PLUS.create());
        addUserBtn.addClickListener(e -> {
            grid.asSingleSelect().clear();
            userEditor.setUser(new User());
        });
        HorizontalLayout toolbar = new HorizontalLayout(filterUsers, addUserBtn);

        this.userDao = userDao;
        this.userEditor = userEditor;
        grid.setColumns("id", "username", "address");

        HorizontalLayout mainContent = new HorizontalLayout(grid,userEditor);
        mainContent.setSizeFull();
        add(new H1("REST API"),toolbar,mainContent);

        grid.setSizeFull();
        updateList(null);

        filterUsers.addValueChangeListener(e -> updateList(e.getValue()));

        grid.asSingleSelect().addValueChangeListener(event -> userEditor.setUser(grid.asSingleSelect().getValue()));

        userEditor.setChangeHandler(() -> {
            userEditor.setVisible(false);
            updateList(filterUsers.getValue());
        });
        updateList(null);
    }

    public void updateList(String filterUsers) {
        if (StringUtils.isEmpty( filterUsers)) {
            grid.setItems(userDao.findAll());
        }
        else {
            int id = Integer.parseInt(filterUsers);
            grid.setItems(userDao.findById(id));
        }
    }

}
