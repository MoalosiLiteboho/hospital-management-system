package com.geniescode.frondend.share.components.dateChooser;

import java.awt.*;
import java.util.List;

public final class Months extends javax.swing.JPanel {
    private final List<String> monthsInEnglish = List.of("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
    private Event event;
    private int m;

    public Months() {
        initComponents();
    }

    private void addEvent() {
        for (int i = 0; i < getComponentCount(); i++) {
            ((Button) getComponent(i)).setEvent(event);
        }
    }

    private void initComponents() {
        setBackground(Color.white);
        setLayout(new GridLayout(4, 4));
        addMonthButtons();
    }

    private void addMonthButtons() {
        for (int index = 0; index < 12; index++) {
            Button monthButton = new Button();
            monthButton.setBackground(Color.white);
            monthButton.setForeground(Color.gray);
            monthButton.setText(monthsInEnglish.get(index));
            monthButton.setName(String.valueOf(index + 1));
            monthButton.setOpaque(true);
            add(monthButton);
        }
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
        addEvent();
    }
}
