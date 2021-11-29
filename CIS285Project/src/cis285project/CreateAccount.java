/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cis285project;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
/**
 * FXML Controller class
 *
 * @author mehakjaved
 */


public class CreateAccount extends JFrame implements ActionListener {

    JLabel username = new JLabel("Enter your username");
    JTextField enterUsername = new JTextField(null, 15);
    JLabel password = new JLabel("Enter your password");
    JPasswordField enterPassword = new JPasswordField(null, 15);
    JLabel passwordConfirm = new JLabel("Confirm your password.");
    JPasswordField enterConfirmPassword = new JPasswordField(null, 15);
    JButton okButton = new JButton("OK");

    public CreateAccount() {

        add(username);
        add(enterUsername);
        add(password);
        add(enterPassword);
        add(passwordConfirm);
        add(enterConfirmPassword);
        add(okButton);

        okButton.addActionListener(this);

        setTitle("New User Account Creation");
        setVisible(true);
        setSize(270, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        char[] pass = enterPassword.getPassword();
        String passString = new String(pass);
        char[] passConfirm = enterConfirmPassword.getPassword();
        String passStringConfirm = new String(passConfirm);

        String userName = enterUsername.getText();

        if (e.getSource() == okButton) {
            if (!passString.equals(passStringConfirm) || userName.equals(null) || passString.equals(null) || passStringConfirm.equals(null)) {
                JOptionPane.showMessageDialog(null, "Entered an invalid username or password!");
                enterUsername.setText("");
                enterPassword.setText("");
                enterConfirmPassword.setText("");
            }
        }
    }
}