package a5_alogorithm;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class SimpleGUI extends JFrame {
    
    JLabel sessionLabel;
    JLabel frameLabel;
    JLabel plainTextLabel;
    JLabel cipherTextLabel;
    JLabel keyStreamLabel;
    JLabel StreamLabel;
    
    JTextField sessionField;
    JTextField frameField;
    JTextField plainTextField;
    JTextField cipherTextField;
    
    JButton encryptButton;
    JButton cleartButton;
    JFileChooser j;
    
    public SimpleGUI() {
        this.setVisible(true);
        this.setSize(600, 680);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("A5/1 Algorithm");
        this.setResizable(false);
        this.setLayout(null);
        
        sessionLabel = new JLabel("enter session key: ");
        frameLabel = new JLabel("enter frame key: ");
        plainTextLabel = new JLabel("enter plain text: ");
        cipherTextLabel = new JLabel("The cipher text: ");
        
        
        keyStreamLabel = new JLabel("key stream: ");
        StreamLabel = new JLabel("cipher stream: ");
        
        
        sessionField = new JTextField();
        frameField = new JTextField();
        plainTextField = new JTextField();
        cipherTextField = new JTextField();
        
        encryptButton = new JButton("encrypt");
        encryptButton.setBackground(Color.LIGHT_GRAY);
        encryptButton.setFont(new Font("Helvetica", Font.BOLD, 12));
        encryptButton.setToolTipText("Encrypt using A5/2");
        
        cleartButton = new JButton("clear");
        cleartButton.setBackground(Color.LIGHT_GRAY);
        cleartButton.setFont(new Font("Helvetica", Font.BOLD, 12));
        cleartButton.setToolTipText("Clear Fields");
        j = new JFileChooser("c:");
        
        this.add(sessionLabel);
        this.add(frameLabel);
        this.add(plainTextLabel);
        this.add(cipherTextLabel);
        this.add(sessionField);
        this.add(frameField);
        this.add(plainTextField);
        this.add(cipherTextField);
        this.add(encryptButton);
        this.add(cleartButton);
        this.add(j);
        
        sessionLabel.setBounds(20, 20, 120, 30);
        sessionField.setBounds(160, 25, 200, 25);
        frameLabel.setBounds(20, 50, 120, 30);
        frameField.setBounds(160, 60, 200, 25);
        plainTextLabel.setBounds(20, 80, 120, 30);
        plainTextField.setBounds(160, 90, 200, 70);
        cipherTextLabel.setBounds(20, 160, 120, 30);
        
        cipherTextField.setBounds(160, 170, 200, 70);
        
        encryptButton.setBounds(100, 300, 100, 24);
        cleartButton.setBounds(220, 300, 100, 24);
        j.setBounds(120, 300, 100, 24);
        
        cleartButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                sessionField.setText("");
                frameField.setText("");
                plainTextField.setText("");
                cipherTextField.setText("");
                j.showSaveDialog(null);
                
            }
        });
        
        encryptButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                String sessionKey = sessionField.getText();
                String frameKey = frameField.getText();
                String plainText = plainTextField.getText();
                
                A5 algorithm = new A5(sessionKey, frameKey);
                String ss = algorithm.encrypt(plainText);
                cipherTextField.setText(ss);
                
            }
        });
        
    }
    
}
