package com.geniescode.frondend.dashboard;

import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {
    private final Integer id;
    private String username;
    private final Color userColor;

    public Dashboard(Integer id, String username, Color userColor) throws HeadlessException {
        this.id = id;
        this.username = username;
        this.userColor = userColor;
        initComponents();
    }

    private void initComponents() {
        JLabel username =  new JLabel(this.username);

        add(username);
        setSize(new Dimension(990, 520));
        setLocationRelativeTo(null);
    }


}
