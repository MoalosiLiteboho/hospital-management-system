package com.geniescode.frondend.share.components.dateChooser;

import com.geniescode.frondend.share.components.textField.TextField;

import java.awt.*;
import java.util.Date;

public class DateField extends TextField {
    private final DateChooser dateChooser;
    public DateField(String hintText, Color bottomColor) {
        super(hintText, bottomColor);
        dateChooser = new DateChooser();
        initComponents();
    }

    private void initComponents() {
        dateChooser.setTextRefernce(this);
        setText(null);
    }

    public Date getSelectedDate() {
        return null;
    }
}
