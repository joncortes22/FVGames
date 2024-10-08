package View;

import Controller.*;
import Model.Item;
import Model.ItemModelDB;
import Model.Package;
import Model.Sale;
import Program.Main;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;
import javax.imageio.ImageIO;
import java.util.Map;
import javax.swing.table.DefaultTableModel;
import java.util.Date;

import java.text.SimpleDateFormat; // for date formatting
public class MyFrame extends JFrame {

    static ArrayList<HashMap<String, String>> cart = new ArrayList<>();
    static ArrayList<Sale> sales = new ArrayList<>();

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
            case "editItem":
                winEditItem();
                break;
            case "buy":
                winBuy();
                break;
            case "buyPackage":
                winBuyPackage();
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
            case "client":
                winClientOptions();
                break;
            case "guest":
                winGuestView();
                break;
            case "admin":
                winAdminOptions();
                break;
            case "listSales":
                winListSales();
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

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(60, 420, 200, 50);

        JPasswordField txtPassword = new JPasswordField();
        txtPassword.setBounds(190, 430, 200, 30);

        JButton btnCreateUser = new JButton("Create User");
        btnCreateUser.setBounds(60, 480, 130, 40);

        btnCreateUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = txtName.getText();
                String lastName = txtLastName.getText();
                String id = txtId.getText();
                String address = txtAddress.getText();
                String email = txtEmail.getText();
                String moneyBalance = txtMoneyBalance.getText();
                String paymentMethod = (String) cmbPaymentMethod.getSelectedItem();
                assert paymentMethod != null;
                if (paymentMethod.isEmpty() || name.isEmpty() || lastName.isEmpty() || id.isEmpty() || address.isEmpty() || email.isEmpty() || moneyBalance.isEmpty()){
                    if (ClientController.validateExistance(Integer.parseInt(id))){
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
                char[] password = txtPassword.getPassword();
                String passwordStr = new String(password);
                ClientController.addNewClient(Integer.parseInt(id), name, lastName, address, email, Integer.parseInt(moneyBalance), paymentMethod, passwordStr);

                JOptionPane.showMessageDialog(null, "Item Added Successfully", "Item Added", JOptionPane.INFORMATION_MESSAGE);
                txtName.setText("");
                txtLastName.setText("");
                txtId.setText("");
                txtAddress.setText("");
                txtEmail.setText("");
                txtPassword.setText("");
                txtMoneyBalance.setText("");
                cmbPaymentMethod.setSelectedIndex(0);
                revalidate();
                repaint();


            }
        });

        JButton btnCancel = new JButton("Cancel");
        btnCancel.setBounds(200, 480, 130, 40);

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                try {
                    MyFrame login = new MyFrame("Login", 480, 400, "login");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });

        add(lblName);add(lblLastName);add(lblId);add(lblTitle);add(lblPassword);
        add(lblAddress);add(lblEmail);add(lblMoney);add(lblPaymentMethod);

        add(txtName);add(txtLastName);add(txtId);add(txtPassword);
        add(txtAddress);add(txtEmail);add(txtMoneyBalance);add(cmbPaymentMethod);

        add(btnCreateUser);add(btnCancel);
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
        cmbProducts = new JComboBox<>(ItemController.getAllAvailableProductNames());
        cmbProducts.setBounds(170, 130, 200, 30);

        cmbProducts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedProduct = (String) cmbProducts.getSelectedItem();

                spnQuantity.setValue(1);
                numberModel.setMaximum(ItemController.getProductAvailability(selectedProduct));
            }
        });

        JLabel lblFilter = new JLabel("Filter By:");
        lblFilter.setBounds(60, 80, 200, 30);

        JComboBox<String> cmbFilter;
        cmbFilter = new JComboBox<>(ItemController.getAvailableCategories());
        cmbFilter.setBounds(170, 80, 200, 30);

        cmbFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCategory = (String) cmbFilter.getSelectedItem();

                cmbProducts.removeAllItems();
                String[] categoryItems;
                assert selectedCategory != null;
                if (selectedCategory.equals("All")){
                    categoryItems = ItemController.getAllAvailableProductNames();
                } else {
                    categoryItems = ItemController.getAllProductNamesByCategory(selectedCategory);
                }
                for (String item : categoryItems){
                    cmbProducts.addItem(item);
                }
            }
        });

        JButton btnAddProduct = new JButton("Add");
        btnAddProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                HashMap<String, String> itemToCart = new HashMap<String, String>();

                if (cart.size() == 0){
                    itemToCart.put("name", ItemController.extractProductSelected((String) cmbProducts.getSelectedItem()).getName());
                    itemToCart.put("unitPrice", String.valueOf(ItemController.extractProductSelected((String) cmbProducts.getSelectedItem()).getUnitPrice()));
                    itemToCart.put("unitBought", String.valueOf(spnQuantity.getValue()));

                    cart.add(itemToCart);
                } else {
                    boolean productFound = false;
                    for (HashMap<String, String> item : cart){
                        String str1 = item.get("name");
                        String str2 = ItemController.extractProductSelected((String) cmbProducts.getSelectedItem()).getName();
                        if (item.get("name").equals(ItemController.extractProductSelected((String) cmbProducts.getSelectedItem()).getName())){
                            int itemBought = Integer.parseInt(item.get("unitBought"));
                            itemBought = itemBought + Integer.parseInt(String.valueOf(spnQuantity.getValue()));
                            item.put("unitBought", String.valueOf(itemBought));
                            productFound = true;
                            break;
                        }
                    }
                    if (!productFound){
                        itemToCart.put("name", ItemController.extractProductSelected((String) cmbProducts.getSelectedItem()).getName());
                        itemToCart.put("unitPrice", String.valueOf(ItemController.extractProductSelected((String) cmbProducts.getSelectedItem()).getUnitPrice()));
                        itemToCart.put("unitBought", String.valueOf(spnQuantity.getValue()));

                        cart.add(itemToCart);
                    }
                }


                cartCount.incrementAndGet();
                lblCartCount.setText(String.valueOf(cartCount));
                //TODO
                //ItemController.setNewAvailability(Main.inventory, (String) cmbProducts.getSelectedItem(), (Integer) spnQuantity.getValue());
                cmbProducts.removeAllItems();
                for (String productName : ItemController.getAllAvailableProductNames()) {
                    cmbProducts.addItem(productName);
                }
                spnQuantity.setValue(1);
                revalidate();
                repaint();

            }
        });
        btnAddProduct.setBounds(170, 230, 90, 40);

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
        btnFinishPurchase.setBounds(280, 230, 90, 40);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                try {
                    MyFrame admin = new MyFrame("Client Menu", 480, 320, "client");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btnCancel.setBounds(60, 230, 90, 40);

        add(lblCartImage);
        add(lblTitle);add(lblProduct);add(lblQuantity);add(lblCartCount);add(lblFilter);
        add(cmbProducts);add(spnQuantity);add(cmbFilter);
        add(btnAddProduct);add(btnFinishPurchase);add(btnCancel);
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

                StringBuilder itemsSb = new StringBuilder();
                int cartCounter = 0;
                for (HashMap<String, String> item : cart){
                    if (cartCounter == 0){
                        itemsSb.append(item.get("unitBought")).append(" ").append(item.get("name"));
                        cartCounter += 1;
                    } else {
                        itemsSb.append(", ").append(item.get("unitBought")).append(" ").append(item.get("name"));
                    }
                }
                Date currentDate = new Date();
                String strTotal = lblTotalAndTaxRateValue.getText();
                strTotal = strTotal.replace("$", "");
                float flTotal = Float.parseFloat(strTotal);

                SaleController.newSale(ClientController.getCurrentUser(), String.valueOf(itemsSb), currentDate, flTotal);
                JOptionPane.showMessageDialog(null, "Sale Completed Successfully", "Item Added", JOptionPane.INFORMATION_MESSAGE);
                cart.clear();

            }
        });
        btnBuy.setBounds(60, 295, 100, 40);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                try {
                    MyFrame admin = new MyFrame("Admin Menu", 480, 355, "admin");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btnCancel.setBounds(180, 295, 100, 40);



        add(lblTitle);add(scrollPane);
        add(lblTotal);add(lblTaxRate);add(lblTotalAndTaxRate);
        add(lblTotalValue);add(lblTaxRateValue);add(lblTotalAndTaxRateValue);
        add(btnBuy);add(btnCancel);
    }

    private void winBuyPackage() throws IOException {
        /** Este método declara y añade los objetos del menú principal
         * */


        JLabel lblTitle = new JLabel("Package ID:");
        lblTitle.setBounds(60, 15, 200, 50);

        JTextField txtId = new JTextField();
        txtId.setBounds(140, 25, 200, 30);

        txtId.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE) {
                    e.consume();  // Ignore the key event
                }
            }
        });

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

                StringBuilder itemsSb = new StringBuilder();
                int cartCounter = 0;
                for (HashMap<String, String> item : cart){
                    if (cartCounter == 0){
                        itemsSb.append(item.get("unitBought")).append(" ").append(item.get("name"));
                        cartCounter += 1;
                    } else {
                        itemsSb.append(", ").append(item.get("unitBought")).append(" ").append(item.get("name"));
                    }
                }
                Date currentDate = new Date();
                String strTotal = lblTotalAndTaxRateValue.getText();
                strTotal = strTotal.replace("$", "");
                float flTotal = Float.parseFloat(strTotal);

                SaleController.newSale(ClientController.getCurrentUser(), String.valueOf(itemsSb), currentDate, flTotal);
                System.out.println(itemsSb);

            }
        });
        btnBuy.setBounds(180, 295, 100, 40);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                try {
                    MyFrame admin = new MyFrame("Admin Menu", 480, 355, "admin");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btnCancel.setBounds(300, 295, 100, 40);

        JButton btnSearch = new JButton("Search");
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String idForSearch = txtId.getText();
                if (PackageController.findPackage(Integer.parseInt(idForSearch))){
                    Package searchPack = PackageController.getPackage(Integer.parseInt(txtId.getText()));

                    String[] pairs = searchPack.getItems().split(",\\s*");

                    for (String pair : pairs) {
                        HashMap<String, String> itemMap = new HashMap<>();
                        // Split the pair by the first space to separate quantity and item
                        String[] parts = pair.split("\\s+", 2);
                        if (parts.length == 2) {
                            int quantity = Integer.parseInt(parts[0].trim());
                            String item = parts[1].trim();
                            itemMap.put("name", item);
                            itemMap.put("unitBought", String.valueOf(quantity));
                            itemMap.put("unitPrice", String.valueOf(ItemController.getPriceByName(item)));
                            cart.add(itemMap);
                        }
                    }
                    String[] listData = new String[cart.size()];
                    int counter = 0, purchaseTotal = 0;
                    for (HashMap<String, String> item : cart){
                        int itemTotal = Integer.parseInt(item.get("unitPrice")) * Integer.parseInt(item.get("unitBought"));
                        purchaseTotal = purchaseTotal + itemTotal;
                        listData[counter] = item.get("name") + " x" + item.get("unitBought") + " = $" + String.valueOf(itemTotal);
                        counter++;
                    }

                } else {
                    JOptionPane.showMessageDialog(null,
                        "Package not found",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        btnSearch.setBounds(60, 295, 100, 40);



        add(lblTitle);add(scrollPane);
        add(lblTotal);add(lblTaxRate);add(lblTotalAndTaxRate);add(txtId);
        add(lblTotalValue);add(lblTaxRateValue);add(lblTotalAndTaxRateValue);
        add(btnBuy);add(btnCancel);add(btnSearch);
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
                ClientController clientController = new ClientController();
                String user = txtUser.getText();
                char[] password = txtPassword.getPassword();
                String passwordStr = new String(password);
                if (user.isEmpty() || passwordStr.isEmpty()){
                    JOptionPane.showMessageDialog(null,
                            "All fields are mandatory",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    boolean result = clientController.login(Integer.parseInt(user), passwordStr);
                    //boolean result = true;
                    if (result){
                        dispose();
                        try {
                            MyFrame admin = new MyFrame("Client Menu", 480, 320, "client");
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Incorrect credentials or user does not exist",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
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
                    MyFrame newClient = new MyFrame("Create User", 480, 600, "newClient");
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
                    MyFrame guestView = new MyFrame("Inventory", 480, 400, "guest");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        JButton btnAdmin = new JButton("Login As Admin");
        btnAdmin.setBounds(150, 300, 200, 30);
        btnAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminController adminController = new AdminController();
                String user = txtUser.getText();
                char[] password = txtPassword.getPassword();
                String passwordStr = new String(password);
                if (user.isEmpty() || passwordStr.isEmpty()){
                    JOptionPane.showMessageDialog(null,
                            "All fields are mandatory",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    boolean result = adminController.login(Integer.parseInt(user), passwordStr);
                    if (result){
                        dispose();
                        try {
                            MyFrame admin = new MyFrame("Admin Menu", 480, 355, "admin");
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Incorrect credentials or user does not exist",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
        });
        // Add components to the JFrame
        add(lblTitle);add(lblUser);add(lblPassword);
        add(txtUser);add(txtPassword);
        add(btnLogin);add(btnSignIn);add(btnGuest);add(btnAdmin);
    }

    private void winPackages() throws IOException {

        /** Este método declara y añade los objetos del menú principal
         * */


        AtomicInteger cartCount = new AtomicInteger(0);
        ItemModelDB imdb = new ItemModelDB();
        ArrayList<Item> inventory = imdb.getAllStock();
        ArrayList<Item> inventoryCopy = new ArrayList<Item>(inventory);

        ArrayList<Item> packageItems = ItemController.getProductsForPackage(inventoryCopy, cartCount.get());
        String[] packageItemNames = new String[packageItems.size()];
        int counter = 0;
        for (Item item : packageItems){
            packageItemNames[counter] = item.getName();
            counter++;
        }


        JLabel lblCount = new JLabel("Count:");
        lblCount.setBounds(450, 50, 50, 30);

        JLabel lblCartCount = new JLabel(String.valueOf(cartCount));
        lblCartCount.setBounds(500, 50, 30, 30);

        JLabel lblTitle = new JLabel("Packages");
        lblTitle.setBounds(170, 50, 200, 50);

        JLabel lblQuantity = new JLabel("Quantity:");
        lblQuantity.setBounds(60, 130, 200, 50);

        JLabel lblDiscount = new JLabel("Discount:");
        lblDiscount.setBounds(60, 170, 200, 50);

        JTextField txtDiscount = new JTextField();
        txtDiscount.setBounds(170, 180, 200, 30);

        txtDiscount.setText("15");

        txtDiscount.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
            char c = e.getKeyChar();
            if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE) {
                e.consume();
            }
            }
        });

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

        scrollPane.setBounds(450, 90, 200, 130);


        JButton btnAdd = new JButton("+");
        btnAdd.setFont(new Font("Arial", Font.BOLD, 17));
        btnAdd.setBounds(390, 130, 44, 44);

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
                ItemController.setNewAvailability(packageItems, productSelected, prdCount);
                cmbProducts.removeAllItems();
                for (Item productName : ItemController.getProductsForPackage(packageItems, cartCount.get())) {
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
        btnAddProduct.setBounds(170, 230, 100, 40);

        JButton btnFinishPurchase = new JButton("Finish");
        btnFinishPurchase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (productsAdded.size() == 0){
                    JOptionPane.showMessageDialog(null,
                        "Product Name Already Exists",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                } else if (txtDiscount.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                        "Enter a Discount Percentage",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                } else {
                    int countStr = 0;
                    StringBuilder sb = new StringBuilder();
                    for (String key : productsAdded.keySet()) {
                        Integer value = productsAdded.get(key);
                        if (countStr == 0){
                            sb.append(value).append(" ").append(key);
                            countStr += 1;
                        } else {
                            sb.append(", ").append(value).append(" ").append(key);
                        }
                    }
                    PackageController.addNewPackage(String.valueOf(sb), Integer.parseInt(txtDiscount.getText()));
                    JOptionPane.showMessageDialog(null, "Item Added Successfully", "Item Added", JOptionPane.INFORMATION_MESSAGE);
                    cartCount.set(0);
                    txtDiscount.setText("15");
                    cmbProducts.setSelectedIndex(0);
                    textArea.setText("");
                }
            }
        });
        btnFinishPurchase.setBounds(280, 230, 100, 40);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                try {
                    MyFrame admin = new MyFrame("Admin Menu", 480, 355, "admin");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btnCancel.setBounds(390, 230, 100, 40);

        add(lblTitle);add(lblProduct);add(lblQuantity);add(lblCartCount);add(lblCount);add(lblDiscount);
        add(cmbProducts);add(spnQuantity);add(txtDiscount);
        add(btnAddProduct);add(btnFinishPurchase);add(btnAdd);add(btnCancel);
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

        JButton btnAddProduct = new JButton("Add");
        btnAddProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String category = (String) cmbCategory.getSelectedItem();
                String name = txtItemName.getText();
                int availability = (int) spnQuantity.getValue();
                String unitPrice = txtPrice.getText();
                assert category != null;
                if (category.isEmpty() || name.isEmpty() || unitPrice.isEmpty() || ItemController.validateNameExistance(name)){
                    if (ItemController.validateNameExistance(name)){
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
                int unitPriceAux = Integer.parseInt(unitPrice);
                ItemController.addNewProduct(category, name, availability, unitPriceAux);

                JOptionPane.showMessageDialog(null, "Item Added Successfully", "Item Added", JOptionPane.INFORMATION_MESSAGE);
                spnQuantity.setValue(1);
                cmbCategory.setSelectedIndex(0);
                txtItemName.setText("");
                txtPrice.setText("");
                revalidate();
                repaint();


            }
        });

        btnAddProduct.setBounds(170, 280, 90, 40);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                try {
                    MyFrame admin = new MyFrame("Admin Menu", 480, 355, "admin");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btnCancel.setBounds(280, 280, 90, 40);

        add(lblTitle);add(lblProduct);add(lblQuantity);add(lblFilter);add(lblPrice);
        add(txtItemName);add(txtPrice);
        add(spnQuantity);add(cmbCategory);
        add(btnAddProduct);add(btnCancel);
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
        cmbProducts = new JComboBox<>(ItemController.getAllAvailableProductNames());
        cmbProducts.setBounds(170, 130, 200, 30);

        cmbProducts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedProduct = (String) cmbProducts.getSelectedItem();
                if (selectedProduct != null){
                    Item selectedItem = new Item(ItemController.getItemSelected(selectedProduct));
                    lblQuantityCount.setText(String.valueOf(selectedItem.getAvailability()));
                    lblUnitCostCount.setText("$" + String.valueOf(selectedItem.getUnitPrice()));
                }
            }
        });

        JLabel lblFilter = new JLabel("Filter By:");
        lblFilter.setBounds(60, 80, 200, 30);

        JComboBox<String> cmbFilter;
        cmbFilter = new JComboBox<>(ItemController.getAvailableCategories());
        cmbFilter.setBounds(170, 80, 200, 30);

        cmbFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCategory = (String) cmbFilter.getSelectedItem();

                cmbProducts.removeAllItems();
                String[] categoryItems;
                assert selectedCategory != null;
                if (selectedCategory.equals("All")){
                    categoryItems = ItemController.getAllAvailableProductNames();
                } else {
                    categoryItems = ItemController.getAllProductNamesByCategory(selectedCategory);
                }
                for (String item : categoryItems){
                    cmbProducts.addItem(item);
                }
            }
        });



        JButton btnFinishPurchase = new JButton("Back");
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

    private void winAdminOptions() {
        /*
         * Este método declara y añade los objetos del menú principal
         * */
        JLabel lblTitle = new JLabel("Admin Menu");
        lblTitle.setBounds(195, 20, 200, 50);

        JButton btnAddInventory = new JButton("Add Inventory");
        btnAddInventory.setBounds(130, 80, 200, 30);

        btnAddInventory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                try {
                    MyFrame newItem = new MyFrame("New Item", 480, 400, "newItem");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        JButton btnEditInventory = new JButton("Edit Inventory");
        btnEditInventory.setBounds(130, 120, 200, 30);

        btnEditInventory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                try {
                    MyFrame newClient = new MyFrame("Edit Item", 480, 400, "editItem");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        JButton btnPackages = new JButton("Manage Packages");
        btnPackages.setBounds(130, 160, 200, 30);

        btnPackages.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                try {
                    MyFrame Package = new MyFrame("Package", 600, 500, "packages");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        JButton btnReports = new JButton("Sales Report");
        btnReports.setBounds(130, 200, 200, 30);
        btnReports.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                try {
                    MyFrame salesReport = new MyFrame("Sales Report", 650, 500, "listSales");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });


        JButton btnLogOut = new JButton("Log Out");
        btnLogOut.setBounds(130, 240, 200, 30);

        btnLogOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                try {
                    MyFrame login = new MyFrame("Login", 480, 400, "login");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });

        // Add components to the JFrame
        add(lblTitle);
        add(btnAddInventory);add(btnEditInventory);add(btnPackages);add(btnReports);add(btnLogOut);
    }

    private void winClientOptions() {
        /*
         * Este método declara y añade los objetos del menú principal
         * */
        JLabel lblTitle = new JLabel("Client Menu");
        lblTitle.setBounds(195, 20, 200, 50);

        JButton btnAddInventory = new JButton("Inventory");
        btnAddInventory.setBounds(130, 80, 200, 30);

        btnAddInventory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                try {
                    MyFrame guestView = new MyFrame("Inventory", 480, 400, "guest");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        JButton btnEditInventory = new JButton("Buy");
        btnEditInventory.setBounds(130, 120, 200, 30);

        btnEditInventory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                try {
                    MyFrame buyItem = new MyFrame("Buy Item", 480, 360, "buy");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        JButton btnPackages = new JButton("Buy Packages");
        btnPackages.setBounds(130, 160, 200, 30);

        btnPackages.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    dispose();
                    MyFrame buyPackage = new MyFrame("Buy Package", 440, 410, "buyPackage");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        JButton btnLogOut = new JButton("Log Out");
        btnLogOut.setBounds(130, 200, 200, 30);

        btnLogOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                try {
                    MyFrame login = new MyFrame("Login", 480, 400, "login");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });

        // Add components to the JFrame
        add(lblTitle);
        add(btnAddInventory);add(btnEditInventory);add(btnPackages);add(btnLogOut);
    }

    private void winEditItem() throws IOException {
        /*
         * Este método declara y añade los objetos del menú principal
         * */
        JLabel lblTitle = new JLabel("Edit Item");
        lblTitle.setBounds(170, 20, 200, 50);
        JLabel lblProduct = new JLabel("Name:");
        lblProduct.setBounds(60, 120, 200, 50);
        JTextField txtItemName = new JTextField();
        txtItemName.setBounds(170, 130, 200, 30);
        JLabel lblFilter = new JLabel("Category:");
        lblFilter.setBounds(60, 80, 200, 30);
        SpinnerNumberModel numberModel = new SpinnerNumberModel(1, 1, null, 1);
        JSpinner spnQuantity = new JSpinner(numberModel);
        spnQuantity.setBounds(170, 180, 200, 30);
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
        JComboBox<String> cmbProducts;
        cmbProducts = new JComboBox<>(ItemController.getAllAvailableProductNames());
        cmbProducts.setBounds(170, 80, 200, 30);
        cmbProducts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedProduct = (String) cmbProducts.getSelectedItem();
                if (selectedProduct != null){
                    Item selectedItem = new Item(ItemController.getItemSelected(selectedProduct));
                    txtItemName.setText(String.valueOf(selectedItem.getName()));
                    spnQuantity.setValue(selectedItem.getAvailability());
                    txtPrice.setText(String.valueOf(selectedItem.getUnitPrice()));
                }
            }
        });
        JLabel lblQuantity = new JLabel("Quantity:");
        lblQuantity.setBounds(60, 170, 200, 50);
        JLabel lblPrice = new JLabel("Unit Price ($):");
        lblPrice.setBounds(60, 220, 200, 50);
        JButton btnAddProduct = new JButton("Update");
        btnAddProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = txtItemName.getText();
                int availability = (int) spnQuantity.getValue();
                String unitPrice = txtPrice.getText();
                String selectedProduct = (String) cmbProducts.getSelectedItem();
                Item selectedItem = new Item(ItemController.getItemSelected(selectedProduct));
                if (name.isEmpty() || unitPrice.isEmpty()){
                    if (!selectedItem.getName().equals(name) && ItemController.validateNameExistance(name)){
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
                int unitPriceAux = Integer.parseInt(unitPrice);
                ItemController.editItem(selectedItem.getId(), name, availability, unitPriceAux);
                JOptionPane.showMessageDialog(null, "Item Updated Successfully", "Item Updated", JOptionPane.INFORMATION_MESSAGE);
                spnQuantity.setValue(1);
                cmbProducts.setSelectedIndex(0);
                txtItemName.setText("");
                txtPrice.setText("");
                revalidate();
                repaint();
            }
        });
        btnAddProduct.setBounds(170, 280, 90, 40);
        JButton btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                try {
                    MyFrame admin = new MyFrame("Admin Menu", 480, 355, "admin");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btnCancel.setBounds(280, 280, 90, 40);
        add(lblTitle);add(lblProduct);add(lblQuantity);add(lblFilter);add(lblPrice);
        add(txtItemName);add(txtPrice);
        add(spnQuantity);add(cmbProducts);
        add(btnAddProduct);add(btnCancel);
    }

    private void winListSales() throws IOException {

        // Campos de filtro y botones
        JLabel lblCustomerId = new JLabel("Customer ID (Numbers):");
        lblCustomerId.setBounds(60, 30, 140, 30);
        JTextField txtCustomerId = new JTextField();
        txtCustomerId.setBounds(210, 30, 140, 30);

        JLabel lblFromDate = new JLabel("From Date (yyyy-mm-dd):");
        lblFromDate.setBounds(60, 60, 140, 30);
        JTextField txtFromDate = new JTextField();
        txtFromDate.setBounds(210, 60, 140, 30);

        JLabel lblToDate = new JLabel("To Date (yyyy-mm-dd):");
        lblToDate.setBounds(60, 90, 140, 30);
        JTextField txtToDate = new JTextField();
        txtToDate.setBounds(210, 90, 140, 30);

        JButton btnFilterById = new JButton("Filter by Customer Id");
        btnFilterById.setBounds(60, 125, 160, 40);

        JButton btnFilterByDate = new JButton("Filter by date");
        btnFilterByDate.setBounds(230, 125, 140, 40);

        JButton btnResetFilters = new JButton("Reset Filters");
        btnResetFilters.setBounds(380, 125, 140, 40);

        // Table Model
        DefaultTableModel tableModel = new DefaultTableModel();
        String[] columnNames = {"ID Cliente", "Items", "Fecha", "Total"};
        tableModel.setColumnIdentifiers(columnNames);

        // Table y JScrollPane
        JTable salesTable = new JTable(tableModel);
        salesTable.setBounds(60, 180, 460, 200);
        JScrollPane scrollPane = new JScrollPane(salesTable);
        scrollPane.setBounds(60, 180, 460, 200);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                try {
                    MyFrame admin = new MyFrame("Admin Menu", 480, 355, "admin");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btnCancel.setBounds(450, 390, 90, 40);




        btnFilterById.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int customerId = Integer.parseInt(txtCustomerId.getText());
                String fromDateString = txtFromDate.getText();
                String toDateString = txtToDate.getText();
                String filterType = "customerId";
                if (String.valueOf(customerId).isBlank() || String.valueOf(customerId).isEmpty() || String.valueOf(customerId) == null){
                    JLabel  lblIncorrectValue = new JLabel("Incorrect Customer Id");
                    lblIncorrectValue.setBounds(150, 230, 200, 30);
                    add(lblIncorrectValue);
                } else{
                    // Filtrar la lista de ventas según las entradas proporcionadas (customerId, fromDate, toDate)
                    ArrayList<Sale> filteredSales = SaleController.filterSales(customerId, fromDateString, toDateString, filterType);

                    // Limpiar los datos existentes en el modelo de tabla
                    tableModel.setRowCount(0);

                    // Agregar los datos de las ventas filtradas al modelo de tabla
                    for (Sale sale : filteredSales) {
                        Object[] rowData = {sale.getCustomerId(), sale.getItems(), SaleController.formatDate(sale.getDate()), sale.getTotal()};
                        tableModel.addRow(rowData);
                    }

                    revalidate();
                    repaint();
                }


            }
        });

        btnFilterByDate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //int customerId = Integer.parseInt(txtCustomerId.getText());
                String fromDateString = txtFromDate.getText();
                String toDateString = txtToDate.getText();
                String filterType = "date";

                if (fromDateString.isEmpty() || toDateString.isEmpty() || !fromDateString.contains("-") || !toDateString.contains("-") || fromDateString == null || toDateString == null){
                    JLabel  lblIncorrectDate = new JLabel("Incorrect Dates Format");
                    lblIncorrectDate.setBounds(150, 230, 200, 30);
                    add(lblIncorrectDate);
                } else {
                    // Filtrar la lista de ventas según las entradas proporcionadas (customerId, fromDate, toDate)
                    ArrayList<Sale> filteredSales = SaleController.filterSales(0, fromDateString, toDateString, filterType);

                    // Limpiar los datos existentes en el modelo de tabla
                    tableModel.setRowCount(0);

                    // Agregar los datos de las ventas filtradas al modelo de tabla
                    for (Sale sale : filteredSales) {
                        Object[] rowData = {sale.getCustomerId(), sale.getItems(), SaleController.formatDate(sale.getDate()), sale.getTotal()};
                        tableModel.addRow(rowData);
                    }

                    revalidate();
                    repaint();
                }

            }
        });

        // Manejar el clic del botón de restablecer filtros
        btnResetFilters.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtCustomerId.setText("");
                txtFromDate.setText("");
                txtToDate.setText("");

                // Mostrar todas las ventas en el modelo de tabla
                tableModel.setRowCount(0);
                for (Sale sale : sales) {
                    Object[] rowData = {sale.getCustomerId(), sale.getItems(), SaleController.formatDate(sale.getDate()), sale.getTotal()};
                    tableModel.addRow(rowData);
                }

                revalidate();
                repaint();
            }
        });

        // Add JFrame components
        add(lblCustomerId);
        add(txtCustomerId);
        add(lblFromDate);
        add(txtFromDate);
        add(lblToDate);
        add(txtToDate);
        add(btnFilterByDate);
        add(btnFilterById);
        add(btnResetFilters);
        add(scrollPane);
        add(btnCancel);
    }

}