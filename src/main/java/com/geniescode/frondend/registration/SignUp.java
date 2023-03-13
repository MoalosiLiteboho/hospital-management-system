package com.geniescode.frondend.registration;

import com.geniescode.backend.controllers.SignUpController;
import com.geniescode.frondend.share.components.Buttons.Button;
import com.geniescode.frondend.share.components.comboBox.ComboBox;
import com.geniescode.frondend.share.components.dateChooser.DateField;
import com.geniescode.frondend.share.components.panel.Panel;
import com.geniescode.frondend.share.components.textField.TextField;
import com.geniescode.frondend.share.components.tittleBar.ClosingBar;
import net.miginfocom.swing.MigLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class SignUp extends JFrame {
    private final List<String> genderList = List.of("Male", "Female", "Others");
    private TextField name;
    private TextField surname;
    private DateField dateOfBirth;
    private ComboBox<String> gender;
    private TextField email;
    private ComboBox<String> address;
    private Button logIn;
    private Button registration;

    public SignUp() {
        initComponents();
    }

    private void initComponents() {
        Panel background = new Panel();
        JLabel tittle = new JLabel("Registration");
        name = new TextField("Name", Color.green);
        surname = new TextField("Surname", Color.green);
        dateOfBirth = new DateField("Date Of Birth (dd/mm/yyyy)", Color.green);
        gender = new ComboBox<>("Gender", Color.green);
        email = new TextField("Email", Color.green);
        address = new ComboBox<>("Address", Color.green);
        logIn = new Button("LogIn");
        registration = new Button("Registration");

        gender.addModels(genderList);
        gender.setSelectedIndex(-1);

        address.addModels(SignUpController.getDistricts());
        address.setSelectedIndex(-1);

        tittle.setHorizontalAlignment(SwingConstants.CENTER);
        tittle.setFont(tittle.getFont().deriveFont(Font.PLAIN, 25));
        tittle.setForeground(Color.green);

        background.setLayout(new MigLayout("inset 0px, wrap"));
        background.add(new ClosingBar(), "width 100%");
        background.add(tittle, "width 100%, gap bottom 10px");
        background.add(name, "width 60%, gap left 20%");
        background.add(surname, "width 60%, gap left 20%, gap top 5px");
        background.add(dateOfBirth, "width 60%, gap left 20%, gap top 5px");
        background.add(gender, "width 60%, gap left 20%");
        background.add(email, "width 60%, gap left 20%, gap top 5px");
        background.add(address, "width 60%, gap left 20%, gap top 5px");
        background.add(registration, "width 30%, height 35px, gap left 35%, gap top 20px");
        background.add(logIn, "width 30%, height 35px, gap left 35%, gap top 6px");
        background.setBackground(Color.white);

        setLayout(new BorderLayout());
        add(background);
        setSize(new Dimension(700, 500));
        setResizable(false);
        setUndecorated(true);
        setFont(new Font("sanserif", Font.PLAIN, 15));
        setLocationRelativeTo(null);
        addController.accept(this);
    }

    public void addButtonListener(SignUpController controller) {
        logIn.addActionListener(controller);
        registration.addActionListener(controller);
    }

    private final Consumer<SignUp> addController = SignUpController::new;

    public String getAuthorities() {
        return "Patient";
    }


    public String getName() {
        return name.getText();
    }

    public String getSurname() {
        return surname.getText();
    }

    public Date getDateOfBirth() {
        return dateOfBirth.getSelectedDate();
    }

    public String getGender() {
        return gender.getSelectedIndex() != -1 || gender.getSelectedItem() != null?
                Objects.requireNonNull(gender.getSelectedItem())
                .toString() : null;
    }

    public String getEmail() {
        return email.getText();
    }

    public String getDistrictName() {
        return address.getSelectedIndex() != -1 || address.getSelectedItem() != null ?
                Objects.requireNonNull(address.getSelectedItem())
                .toString() : null ;
    }

    public void clearName() {
        this.name.setText(null);
    }

    public void clearSurname() {
        this.surname.setText(null);
    }

    public void clearDateOfBirth() {
        this.dateOfBirth.setText(null);
    }

    public void clearGender() {
        this.gender.setSelectedIndex(-1);
    }

    public void clearEmail() {
        this.email.setText(null);
    }

    public void clearAddress() {
        this.address.setSelectedIndex(-1);
    }

    public Button getLogIn() {
        return logIn;
    }

    public Button getRegistration() {
        return registration;
    }
}
