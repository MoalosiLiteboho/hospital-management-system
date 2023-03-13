package com.geniescode.frondend.share.components.dateChooser;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.GridLayout;

public final class Years extends javax.swing.JPanel {

    private Event event;
    private int startYear;

    public Years() {
        initComponents();
    }

    public int showYear(int year) {
        year = calculateYear(year);
        for (int index = 0; index < getComponentCount(); index++) {
            JButton cmd = (JButton) getComponent(index);
            cmd.setText(year + "");
            year++;
        }
        return startYear;
    }

    private int calculateYear(int year) {
        year -= year % 10;
        startYear = year;
        return year;
    }

    private void addEvent() {
        for (int i = 0; i < getComponentCount(); i++)
            ((Button) getComponent(i)).setEvent(event);
    }

    private void initComponents() {
        setBackground(Color.white);
        setLayout(new GridLayout(5, 4));
        addYearButtons();
    }
    
    private void addYearButtons() {
        int year = 2010;
        for (int index = 0; index < 20; index++) {
            Button yearButton = new Button();
            yearButton.setBackground(Color.white);
            yearButton.setForeground(Color.gray);
            yearButton.setText(String.valueOf(year++));
            yearButton.setName("year");
            yearButton.setOpaque(true);
            add(yearButton);
        }
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
        addEvent();
    }

    public int next(int year) {
        showYear(year + 20);
        return startYear;
    }

    public int back(int year) {
        showYear(year - 20);
        return startYear;
    }
}
