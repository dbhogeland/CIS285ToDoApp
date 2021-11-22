package cis285project

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class CreateAccount implements ActionListener {

    JFrame frame;
    JPanel panel;
    JTextField username;
    JPasswordField password;
    JPasswordField confirmPassword;
    JLabel warningLabel;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        CreateAccount window = new CreateAccount();
        window.go();
    }

    public void go() {
        frame = new JFrame("Create a new account");
        panel = new JPanel();

        panel.setLayout(new GridBagLayout());

        frame.getContentPane().add(BorderLayout.CENTER, panel);
        panel.setBackground(Color.ORANGE);

        JLabel userLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        username = new JTextField(15);
        password = new JPasswordField(15);
        confirmPassword = new JPasswordField(15);

        GridBagConstraints right = new GridBagConstraints();
        right.anchor = GridBagConstraints.WEST;
        GridBagConstraints left = new GridBagConstraints();
        left.anchor = GridBagConstraints.EAST;

        right.weightx = (int) 2;
        right.fill = GridBagConstraints.REMAINDER;
        right.gridwidth = GridBagConstraints.REMAINDER;
        // actual GUI

        panel.add(userLabel, left);
        panel.add(username, right);
        panel.add(passwordLabel, left);
        panel.add(password, right);
        panel.add(confirmPasswordLabel, left);
        panel.add(confirmPassword, right);

        frame.setSize(300, 250);
        frame.setVisible(true);}
