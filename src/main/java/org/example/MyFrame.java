package org.example;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import javax.imageio.ImageIO;
public class MyFrame extends JFrame {

    public MyFrame(String name, int width, int height, String winCall) throws IOException {
        /*
         * Este método se utiliza para generar ventanas, sus componentes dependerán de la elección de winCall
         * */
        setTitle(name);

        setSize(width, height);

        setResizable(false);

        setLayout(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        switch (winCall){
            case "user":
                winRegistrarClientes();
                break;
            case "buy":
                winBuy();
                break;
            case "login":
                winLoginOptions();
                break;
        }

        setVisible(true);
    }
    private void winRegistrarClientes() {
        /*
         * Este método declara y añade los objetos del menú principal
         * */
        JLabel lblTitle = new JLabel("New Client");
        lblTitle.setBounds(170, 20, 200, 50);

        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(60, 70, 200, 50);

        JTextField txtName = new JTextField();
        txtName.setBounds(190, 80, 200, 30); // (x, y, width, height)

        JLabel lblLastName = new JLabel("Last Name:");
        lblLastName.setBounds(60, 120, 200, 50);

        JTextField txtLastName = new JTextField();
        txtLastName.setBounds(190, 130, 200, 30); // (x, y, width, height)

        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(60, 170, 200, 50);

        JTextField txtId = new JTextField();
        txtId.setBounds(190, 180, 200, 30);

        txtId.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE) {
                    e.consume();  // Ignore the key event
                }
            }
        });

        JLabel lblAddress = new JLabel("Address:");
        lblAddress.setBounds(60, 220, 200, 50);

        JTextField txtAddress = new JTextField();
        txtAddress.setBounds(190, 230, 200, 30);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(60, 270, 200, 50);

        JTextField txtEmail = new JTextField();
        txtEmail.setBounds(190, 280, 200, 30);

        JLabel lblMoney = new JLabel("Money Balance ($):");
        lblMoney.setBounds(60, 320, 200, 50);

        JTextField txtMoneyBalance = new JTextField();
        txtMoneyBalance.setBounds(190, 330, 200, 30);

        txtMoneyBalance.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE) {
                    e.consume();  // Ignore the key event
                }
            }
        });

        JLabel lblPaymentMethod = new JLabel("Payment Method:");
        lblPaymentMethod.setBounds(60, 370, 200, 50);

        String[] paymentMethods = {"Cash", "Bank Wire", "Credit Card"};
        JComboBox<String> cmbPaymentMethod;
        cmbPaymentMethod = new JComboBox<>(paymentMethods);
        cmbPaymentMethod.setBounds(190, 380, 200, 30);

        cmbPaymentMethod.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Method to handle item selection

            }
        });

        JButton btnCreateUser = new JButton("Create User");
        btnCreateUser.setBounds(60, 430, 130, 40);

        add(lblName);add(lblLastName);add(lblId);add(lblTitle);
        add(lblAddress);add(lblEmail);add(lblMoney);add(lblPaymentMethod);

        add(txtName);add(txtLastName);add(txtId);
        add(txtAddress);add(txtEmail);add(txtMoneyBalance);add(cmbPaymentMethod);

        add(btnCreateUser);
    }

    private void winBuy() throws IOException {
        /*
         * Este método declara y añade los objetos del menú principal
         * */
        ArrayList<Item> cart = new ArrayList<Item>();
        AtomicInteger cartCount = new AtomicInteger(0);

        BufferedImage originalImage = ImageIO.read(new File("img/shoppingcart.png"));
        Image resizedImage = originalImage.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(resizedImage);
        JLabel lblCartImage = new JLabel(imageIcon);
        lblCartImage.setBounds(380, 20, 30, 30);

        JLabel lblCartCount = new JLabel(String.valueOf(cartCount));
        lblCartCount.setBounds(425, 20, 30, 30);

        JLabel lblTitle = new JLabel("Shop");
        lblTitle.setBounds(170, 20, 200, 50);

        JLabel lblQuantity = new JLabel("Quantity:");
        lblQuantity.setBounds(60, 120, 200, 50);

        SpinnerNumberModel numberModel = new SpinnerNumberModel(1, 1, 1, 1);
        JSpinner spnQuantity = new JSpinner(numberModel);
        spnQuantity.setBounds(170, 130, 200, 30);
        spnQuantity.setVisible(false);

        JLabel lblProduct = new JLabel("Product:");
        lblProduct.setBounds(60, 70, 200, 50);

        JComboBox<String> cmbProducts;
        cmbProducts = new JComboBox<>(Item.getAllAvailableProductNames());
        cmbProducts.setBounds(170, 80, 200, 30);

        cmbProducts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                spnQuantity.setVisible(true);
                String selectedProduct = (String) cmbProducts.getSelectedItem();
                spnQuantity.setValue(1);
                numberModel.setMaximum(Item.getProductAvailability(selectedProduct));
            }
        });

        JButton btnAddProduct = new JButton("Add to Cart");
        btnAddProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cartCount.incrementAndGet();
                lblCartCount.setText(String.valueOf(cartCount));
                Item.setNewAvailability((String) cmbProducts.getSelectedItem(), (Integer) spnQuantity.getValue());
                cmbProducts.removeAllItems();
                for (String productName : Item.getAllAvailableProductNames()) {
                    cmbProducts.addItem(productName);
                }
                spnQuantity.setValue(1);
                revalidate();
                repaint();

            }
        });
        btnAddProduct.setBounds(60, 180, 100, 40);

        add(lblCartImage);
        add(lblTitle);add(lblProduct);add(lblQuantity);add(lblCartCount);
        add(cmbProducts);add(spnQuantity);
        add(btnAddProduct);
    }

    private void winLoginOptions() {
        /*
         * Este método declara y añade los objetos del menú principal
         * */
        JLabel lblTitle = new JLabel("Login");
        lblTitle.setBounds(235, 30, 200, 50);

        JButton btnAdmin = new JButton("Admin");
        btnAdmin.setBounds(50, 130, 120, 50);

        JButton btnClient = new JButton("Client");
        btnClient.setBounds(190, 130, 120, 50);

        JButton btnGuest = new JButton("Guest");
        btnGuest.setBounds(330, 130, 120, 50);

        // Add components to the JFrame
        add(lblTitle);
        add(btnAdmin);add(btnClient);add(btnGuest);
    }
}