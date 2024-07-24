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
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;
import javax.imageio.ImageIO;
import java.util.Map;
public class MyFrame extends JFrame {

    static ArrayList<HashMap<String, String>> cart = new ArrayList<>();

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
            case "newClient":
                winNewClient();
                break;
            case "newItem":
                winNewItem();
                break;
            case "buy":
                winBuy();
                break;
            case "login":
                winLoginOptions();
                break;
            case "finishPurchase":
                winFinishPurchase();
                break;
            case "packages":
                winPackages();
                break;
            case "guest":
                winGuestView();
                break;
        }

        setVisible(true);
    }
    private void winNewClient() {
        /*
         * Este método declara y añade los objetos del menú principal
         * */
        JLabel lblTitle = new JLabel("New Client");
        lblTitle.setBounds(170, 20, 200, 50);

        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(60, 70, 200, 50);

        JTextField txtName = new JTextField();
        txtName.setBounds(190, 80, 200, 30);

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
        lblQuantity.setBounds(60, 170, 200, 50);

        SpinnerNumberModel numberModel = new SpinnerNumberModel(1, 1, 1, 1);
        JSpinner spnQuantity = new JSpinner(numberModel);
        spnQuantity.setBounds(170, 180, 200, 30);

        JLabel lblProduct = new JLabel("Product:");
        lblProduct.setBounds(60, 120, 200, 50);

        JComboBox<String> cmbProducts;
        cmbProducts = new JComboBox<>(Item.getAllAvailableProductNames());
        cmbProducts.setBounds(170, 130, 200, 30);

        cmbProducts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedProduct = (String) cmbProducts.getSelectedItem();

                spnQuantity.setValue(1);
                numberModel.setMaximum(Item.getProductAvailability(selectedProduct));
            }
        });

        JLabel lblFilter = new JLabel("Filter By:");
        lblFilter.setBounds(60, 80, 200, 30);

        JComboBox<String> cmbFilter;
        cmbFilter = new JComboBox<>(Item.getAvailableCategories());
        cmbFilter.setBounds(170, 80, 200, 30);

        cmbFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCategory = (String) cmbFilter.getSelectedItem();

                cmbProducts.removeAllItems();
                String[] categoryItems;
                assert selectedCategory != null;
                if (selectedCategory.equals("All")){
                    categoryItems = Item.getAllAvailableProductNames();
                } else {
                    categoryItems = Item.getAllProductNamesByCategory(selectedCategory);
                }
                for (String item : categoryItems){
                    cmbProducts.addItem(item);
                }
            }
        });

        JButton btnAddProduct = new JButton("Add to Cart");
        btnAddProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                HashMap<String, String> itemToCart = new HashMap<String, String>();

                if (cart.size() == 0){
                    itemToCart.put("name", Item.extractProductSelected((String) cmbProducts.getSelectedItem()).getName());
                    itemToCart.put("unitPrice", String.valueOf(Item.extractProductSelected((String) cmbProducts.getSelectedItem()).getUnitPrice()));
                    itemToCart.put("unitBought", String.valueOf(spnQuantity.getValue()));

                    cart.add(itemToCart);
                } else {
                    boolean productFound = false;
                    for (HashMap<String, String> item : cart){
                        String str1 = item.get("name");
                        String str2 = Item.extractProductSelected((String) cmbProducts.getSelectedItem()).getName();
                        if (item.get("name").equals(Item.extractProductSelected((String) cmbProducts.getSelectedItem()).getName())){
                            int itemBought = Integer.parseInt(item.get("unitBought"));
                            itemBought = itemBought + Integer.parseInt(String.valueOf(spnQuantity.getValue()));
                            item.put("unitBought", String.valueOf(itemBought));
                            productFound = true;
                            break;
                        }
                    }
                    if (!productFound){
                        itemToCart.put("name", Item.extractProductSelected((String) cmbProducts.getSelectedItem()).getName());
                        itemToCart.put("unitPrice", String.valueOf(Item.extractProductSelected((String) cmbProducts.getSelectedItem()).getUnitPrice()));
                        itemToCart.put("unitBought", String.valueOf(spnQuantity.getValue()));

                        cart.add(itemToCart);
                    }
                }


                cartCount.incrementAndGet();
                lblCartCount.setText(String.valueOf(cartCount));
                Item.setNewAvailability(Main.inventory, (String) cmbProducts.getSelectedItem(), (Integer) spnQuantity.getValue());
                cmbProducts.removeAllItems();
                for (String productName : Item.getAllAvailableProductNames()) {
                    cmbProducts.addItem(productName);
                }
                spnQuantity.setValue(1);
                revalidate();
                repaint();

            }
        });
        btnAddProduct.setBounds(60, 230, 100, 40);

        JButton btnFinishPurchase = new JButton("Finish");
        btnFinishPurchase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (cart.isEmpty()){
                        JOptionPane.showMessageDialog(null,
                                "Cart is Empty",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                        setVisible(true);
                    } else{
                        dispose();
                        MyFrame finishPurchase = new MyFrame("Finish Purchase", 440, 410, "finishPurchase");
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btnFinishPurchase.setBounds(170, 230, 100, 40);

        add(lblCartImage);
        add(lblTitle);add(lblProduct);add(lblQuantity);add(lblCartCount);add(lblFilter);
        add(cmbProducts);add(spnQuantity);add(cmbFilter);
        add(btnAddProduct);add(btnFinishPurchase);
    }

    private void winFinishPurchase() throws IOException {
         /** Este método declara y añade los objetos del menú principal
         * */


        JLabel lblTitle = new JLabel("Shopping Cart");
        lblTitle.setBounds(170, 15, 200, 50);

        String[] listData = new String[cart.size()];
        int counter = 0, purchaseTotal = 0;
        for (HashMap<String, String> item : cart){
            int itemTotal = Integer.parseInt(item.get("unitPrice")) * Integer.parseInt(item.get("unitBought"));
            purchaseTotal = purchaseTotal + itemTotal;
            listData[counter] = item.get("name") + " x" + item.get("unitBought") + " = $" + String.valueOf(itemTotal);
            counter++;
        }

        // Step 3: Create the JList with the data
        JList<String> cartList = new JList<>(listData);

        // Step 4: Add the JList to a JScrollPane
        JScrollPane scrollPane = new JScrollPane(cartList);

        scrollPane.setBounds(60, 70, 300, 120);


        JLabel lblTotal = new JLabel("Total:");
        lblTotal.setBounds(60, 185, 200, 50);

        JLabel lblTaxRate = new JLabel("Tax Rate:");
        lblTaxRate.setBounds(60, 215, 200, 50);

        JLabel lblTotalAndTaxRate = new JLabel("Total + tax:");
        lblTotalAndTaxRate.setBounds(60, 245, 200, 50);

        JLabel lblTotalValue = new JLabel("$" + String.valueOf(purchaseTotal));
        lblTotalValue.setBounds(310, 185, 200, 50);

        JLabel lblTaxRateValue = new JLabel("13%");
        lblTaxRateValue.setBounds(310, 215, 200, 50);

        String totalPlusTax = String.valueOf((purchaseTotal*0.13)+purchaseTotal);
        JLabel lblTotalAndTaxRateValue = new JLabel("$" + totalPlusTax);
        lblTotalAndTaxRateValue.setBounds(310, 245, 200, 50);



        JButton btnBuy = new JButton("Buy");
        btnBuy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                revalidate();
                repaint();

            }
        });
        btnBuy.setBounds(60, 295, 100, 40);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                revalidate();
                repaint();

            }
        });
        btnCancel.setBounds(180, 295, 100, 40);



        add(lblTitle);add(scrollPane);
        add(lblTotal);add(lblTaxRate);add(lblTotalAndTaxRate);
        add(lblTotalValue);add(lblTaxRateValue);add(lblTotalAndTaxRateValue);
        add(btnBuy);add(btnCancel);
    }

    private void winLoginOptions() {
        /*
         * Este método declara y añade los objetos del menú principal
         * */
        JLabel lblTitle = new JLabel("Login");
        lblTitle.setBounds(215, 20, 200, 50);

        JLabel lblUser = new JLabel("Username:");
        lblUser.setBounds(60, 70, 200, 50);

        JTextField txtUser = new JTextField();
        txtUser.setBounds(150, 80, 200, 30);

        txtUser.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
            char c = e.getKeyChar();
            if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE) {
                e.consume();  // Ignore the key event
            }
            }
        });

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(60, 120, 200, 50);

        JPasswordField txtPassword = new JPasswordField();
        txtPassword.setBounds(150, 130, 200, 30);

        JButton btnLogin = new JButton("Login");
        btnLogin.setBounds(150, 180, 200, 30);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (cart.isEmpty()){
                        JOptionPane.showMessageDialog(null,
                                "Cart is Empty",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                        setVisible(true);
                    } else{
                        dispose();
                        MyFrame finishPurchase = new MyFrame("Finish Purchase", 440, 410, "finishPurchase");
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        JButton btnSignIn = new JButton("Sign In");
        btnSignIn.setBounds(150, 220, 200, 30);

        btnSignIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                try {
                    MyFrame newClient = new MyFrame("Create User", 480, 550, "newClient");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        JButton btnGuest = new JButton("Continue As Guest");
        btnGuest.setBounds(150, 260, 200, 30);

        btnGuest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                try {
                    MyFrame guestView = new MyFrame("Guest View", 480, 400, "guest");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        JButton btnAdmin = new JButton("Login As Admin");
        btnAdmin.setBounds(150, 300, 200, 30);

        // Add components to the JFrame
        add(lblTitle);add(lblUser);add(lblPassword);
        add(txtUser);add(txtPassword);
        add(btnLogin);add(btnSignIn);add(btnGuest);add(btnAdmin);
    }

    private void winPackages() throws IOException {
        /*
         * Este método declara y añade los objetos del menú principal
         * */


        AtomicInteger cartCount = new AtomicInteger(0);
        ArrayList<Item> inventoryCopy = new ArrayList<Item>(Main.inventory);

        ArrayList<Item> packageItems = Item.getProductsForPackage(inventoryCopy, cartCount.get());
        String[] packageItemNames = new String[packageItems.size()];
        int counter = 0;
        for (Item item : packageItems){
            packageItemNames[counter] = item.getName();
            counter++;
        }




        JLabel lblCartCount = new JLabel(String.valueOf(cartCount));
        lblCartCount.setBounds(425, 20, 30, 30);

        JLabel lblTitle = new JLabel("Shop");
        lblTitle.setBounds(170, 20, 200, 50);

        JLabel lblQuantity = new JLabel("Quantity:");
        lblQuantity.setBounds(60, 130, 200, 50);

        SpinnerNumberModel numberModel = new SpinnerNumberModel(1, 1, packageItems.get(0).getAvailability(), 1);
        JSpinner spnQuantity = new JSpinner(numberModel);
        spnQuantity.setBounds(170, 140, 200, 30);

        JLabel lblProduct = new JLabel("Product:");
        lblProduct.setBounds(60, 90, 200, 50);

        JComboBox<String> cmbProducts;
        cmbProducts = new JComboBox<>(packageItemNames);
        cmbProducts.setBounds(170, 100, 200, 30);

        cmbProducts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedProduct = (String) cmbProducts.getSelectedItem();

                spnQuantity.setValue(1);
                for (Item item : packageItems){
                    if (item.getName().equals(selectedProduct)){
                        numberModel.setMaximum(item.getAvailability());
                        break;
                    }
                }
            }
        });

        ArrayList<String> productNamesAdded = new ArrayList<String>();
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        // Step 4: Add the JList to a JScrollPane
        JScrollPane scrollPane = new JScrollPane(textArea);

        scrollPane.setBounds(450, 80, 200, 110);


        JButton btnAdd = new JButton("+");
        btnAdd.setFont(new Font("Arial", Font.BOLD, 17));
        btnAdd.setBounds(390, 110, 44, 44);

        HashMap<String, Integer> productsAdded = new HashMap<>();
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cartCount.get() == 5){
                    JOptionPane.showMessageDialog(null,
                            "Limit of Items is 5, finish package or close window",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int prdCount = (int) spnQuantity.getValue();
                int currentCount = cartCount.get();
                String productSelected = (String) cmbProducts.getSelectedItem();


                cartCount.set(prdCount + currentCount);
                lblCartCount.setText(String.valueOf(prdCount + currentCount));
                Item.setNewAvailability(packageItems, productSelected, prdCount);
                cmbProducts.removeAllItems();
                for (Item productName : Item.getProductsForPackage(packageItems, cartCount.get())) {
                    cmbProducts.addItem(productName.getName());
                }
                spnQuantity.setValue(1);
                StringBuilder list = new StringBuilder();
                String strTextArea = textArea.getText();
                if (strTextArea.equals("")){
                    list.append(productSelected).append(" x").append(prdCount);
                    productsAdded.put(productSelected, prdCount);
                } else {
                    boolean keyFound = false;
                    for (Map.Entry<String, Integer> entry : productsAdded.entrySet()) {
                        String key = entry.getKey();
                        Integer value = entry.getValue();
                        if (key.equals(productSelected)){
                            value = value + prdCount;
                            productsAdded.put(productSelected, value);
                            keyFound = true;
                        }
                        list.append(key).append(" x").append(value).append("\n");
                    }
                    if (!keyFound){
                        list.append(productSelected).append(" x").append(prdCount);
                        productsAdded.put(productSelected, prdCount);
                    }
                }
                textArea.setText(list.toString());
                revalidate();
                repaint();

            }
        });
        JButton btnAddProduct = new JButton("Add to Cart");
        btnAddProduct.setBounds(60, 180, 100, 40);

        JButton btnFinishPurchase = new JButton("Finish");
        btnFinishPurchase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (cart.isEmpty()){
                        JOptionPane.showMessageDialog(null,
                                "Cart is Empty",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                        setVisible(true);
                    } else{
                        dispose();
                        MyFrame finishPurchase = new MyFrame("Finish Purchase", 440, 410, "finishPurchase");
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btnFinishPurchase.setBounds(170, 180, 100, 40);

        add(lblTitle);add(lblProduct);add(lblQuantity);add(lblCartCount);
        add(cmbProducts);add(spnQuantity);
        add(btnAddProduct);add(btnFinishPurchase);add(btnAdd);
        add(scrollPane);
    }

    private void winNewItem() throws IOException {
        /*
         * Este método declara y añade los objetos del menú principal
         * */


        JLabel lblTitle = new JLabel("New Item");
        lblTitle.setBounds(170, 20, 200, 50);



        JLabel lblProduct = new JLabel("Product:");
        lblProduct.setBounds(60, 120, 200, 50);

        JTextField txtItemName = new JTextField();
        txtItemName.setBounds(170, 130, 200, 30);


        JLabel lblFilter = new JLabel("Category:");
        lblFilter.setBounds(60, 80, 200, 30);

        JComboBox<String> cmbCategory;
        String[] categories = {"Videogame", "Console", "Accesory", "Subscription"};
        cmbCategory = new JComboBox<>(categories);
        cmbCategory.setBounds(170, 80, 200, 30);

        JLabel lblQuantity = new JLabel("Quantity:");
        lblQuantity.setBounds(60, 170, 200, 50);

        SpinnerNumberModel numberModel = new SpinnerNumberModel(1, 1, null, 1);
        JSpinner spnQuantity = new JSpinner(numberModel);
        spnQuantity.setBounds(170, 180, 200, 30);

        JLabel lblPrice = new JLabel("Unit Price ($):");
        lblPrice.setBounds(60, 220, 200, 50);

        JTextField txtPrice = new JTextField();
        txtPrice.setBounds(170, 230, 200, 30);

        txtPrice.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
            char c = e.getKeyChar();
            if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE) {
                e.consume();  // Ignore the key event
            }
            }
        });

        JButton btnAddProduct = new JButton("Add to Cart");
        btnAddProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String category = (String) cmbCategory.getSelectedItem();
                String name = txtItemName.getText();
                int availability = (int) spnQuantity.getValue();
                String unitPrice = txtPrice.getText();
                assert category != null;
                if (category.equals("") || name.equals("") || unitPrice.equals("") || Item.validateNameExistance(name)){
                    if (Item.validateNameExistance(name)){
                        JOptionPane.showMessageDialog(null,
                                "Product Name Already Exists",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    JOptionPane.showMessageDialog(null,
                            "All Fields are Required",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Item newItem = new Item(Item.getNewId(), category, name, availability, Integer.parseInt(unitPrice));
                Main.inventory.add(newItem);

                JOptionPane.showMessageDialog(null, "Item Added Successfully", "Item Added", JOptionPane.INFORMATION_MESSAGE);
                spnQuantity.setValue(1);
                cmbCategory.setSelectedIndex(0);
                txtItemName.setText("");
                txtPrice.setText("");
                revalidate();
                repaint();


            }
        });
        btnAddProduct.setBounds(60, 280, 100, 40);

        JButton btnFinishPurchase = new JButton("Finish");
        btnFinishPurchase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (cart.isEmpty()){
                        JOptionPane.showMessageDialog(null,
                                "Cart is Empty",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                        setVisible(true);
                    } else{
                        dispose();
                        MyFrame finishPurchase = new MyFrame("Finish Purchase", 440, 410, "finishPurchase");
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btnFinishPurchase.setBounds(170, 280, 100, 40);

        add(lblTitle);add(lblProduct);add(lblQuantity);add(lblFilter);add(lblPrice);
        add(txtItemName);add(txtPrice);
        add(spnQuantity);add(cmbCategory);
        add(btnAddProduct);add(btnFinishPurchase);
    }

    private void winGuestView() throws IOException {
        /*
         * Este método declara y añade los objetos del menú principal
         * */


        JLabel lblTitle = new JLabel("Shop");
        lblTitle.setBounds(170, 20, 200, 50);

        JLabel lblQuantity = new JLabel("Available:");
        lblQuantity.setBounds(60, 170, 200, 50);

        JLabel lblQuantityCount = new JLabel("");
        lblQuantityCount.setBounds(170, 180, 200, 30);

        JLabel lblUnitCost = new JLabel("Unit Cost:");
        lblUnitCost.setBounds(60, 220, 200, 50);

        JLabel lblUnitCostCount = new JLabel("");
        lblUnitCostCount.setBounds(170, 230, 200, 30);

        JLabel lblProduct = new JLabel("Product:");
        lblProduct.setBounds(60, 120, 200, 50);

        JComboBox<String> cmbProducts;
        cmbProducts = new JComboBox<>(Item.getAllAvailableProductNames());
        cmbProducts.setBounds(170, 130, 200, 30);

        cmbProducts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedProduct = (String) cmbProducts.getSelectedItem();
                if (selectedProduct != null){
                    Item selectedItem = new Item(Item.getItemSelected(selectedProduct));
                    lblQuantityCount.setText(String.valueOf(selectedItem.getAvailability()));
                    lblUnitCostCount.setText("$" + String.valueOf(selectedItem.getUnitPrice()));
                }
            }
        });

        JLabel lblFilter = new JLabel("Filter By:");
        lblFilter.setBounds(60, 80, 200, 30);

        JComboBox<String> cmbFilter;
        cmbFilter = new JComboBox<>(Item.getAvailableCategories());
        cmbFilter.setBounds(170, 80, 200, 30);

        cmbFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCategory = (String) cmbFilter.getSelectedItem();

                cmbProducts.removeAllItems();
                String[] categoryItems;
                assert selectedCategory != null;
                if (selectedCategory.equals("All")){
                    categoryItems = Item.getAllAvailableProductNames();
                } else {
                    categoryItems = Item.getAllProductNamesByCategory(selectedCategory);
                }
                for (String item : categoryItems){
                    cmbProducts.addItem(item);
                }
            }
        });



        JButton btnFinishPurchase = new JButton("Sign Out");
        btnFinishPurchase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    dispose();
                    MyFrame login = new MyFrame("Login", 480, 400, "login");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btnFinishPurchase.setBounds(170, 280, 100, 40);

        add(lblTitle);add(lblProduct);add(lblQuantity);add(lblFilter);add(lblQuantityCount);
        add(lblUnitCost);add(lblUnitCostCount);
        add(cmbProducts);add(cmbFilter);
        add(btnFinishPurchase);
    }

}