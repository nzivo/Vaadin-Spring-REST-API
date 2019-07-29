package com.example.demograd;

import com.example.demograd.Dao.UserDao;
import com.example.demograd.Editor.UserEditor;
import com.example.demograd.Entity.User;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import org.springframework.util.StringUtils;


@Route
public class MainView extends VerticalLayout {
    private final UserDao repo;
    private final UserEditor userEditor;
    final TextField filter;
    private final Button addNewBtn;
    final Grid<User> grid;

    public MainView(UserDao repo, UserEditor userEditor) {
        this.repo = repo;
        this.userEditor = userEditor;
        this.grid = new Grid<>(User.class);
        this.filter = new TextField();
        this.addNewBtn = new Button("New User", VaadinIcon.PLUS.create());


        // build layout
        HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn);
        add(actions, grid, userEditor);

        grid.setHeight("300px");
        grid.setColumns("id", "username", "address");
        grid.getColumnByKey("id").setWidth("50px").setFlexGrow(0);

        filter.setPlaceholder("Filter by last name");
        // Hook logic to components

        // Replace listing with filtered content when user changes filter
        filter.setValueChangeMode(ValueChangeMode.EAGER);
//        filter.addValueChangeListener(e -> listUsers(e.getValue()));

        // Connect selected Customer to editor or hide if none is selected
        grid.asSingleSelect().addValueChangeListener(e -> {
            userEditor.editUser(e.getValue());
        });

        // Instantiate and edit new Customer the new button is clicked
        addNewBtn.addClickListener(e -> userEditor.editUser(new User("", "")));

//        // Listen changes made by the editor, refresh data from backend
//        userEditor.setChangeHandler(() -> {
//            userEditor.setVisible(false);
//            listUsers(filter.getValue());
//        });

        listUsers();

//        add(grid);
//        listUsers();
    }

    private void listUsers() {

//        if (StringUtils.isEmpty(filterText)) {
//            grid.setItems(repo.findAll());
//        }
//        else {
//            grid.setItems(repo.findByLastNameStartsWithIgnoreCase(filterText));
//        }
        grid.setItems(repo.findAll());
    }
}
