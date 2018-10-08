package com.ssibiya.swingy.view.gui;

import com.ssibiya.swingy.control.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Window extends JFrame {
//    JFrame window;
    private final static String IMG_DIR = "src/main/java/com/ssibiya/swingy/images/";
    private final static String [] heroList = {"Saiyan", "Namekian", "Earthling"};
    private final static String [] heroImages = {"saiyan.png", "namekian.png", "earthling.png"};

    private JPanel BLayout = new JPanel();
    private JPanel centerDiv = new JPanel(); //Flowlayout
    private JPanel topDiv = new JPanel();
    private JPanel bottomDiv = new JPanel();

    private Box vBoxEast = Box.createVerticalBox();

    private JLabel lblEnterName = new JLabel("Enter Hero Name");
    private JTextField txtName = new JTextField(20);
    private JLabel lblSelectType = new JLabel("Choose Hero Type");
    private JComboBox cbHerolist = new JComboBox(heroList);
    private JButton btnCreateNew = new JButton("Create New Hero");
    private JButton btnStart = new JButton("Start Game");
    private JLabel lblAuthor = new JLabel("ssibiya @ wethinkcode_");
    private JLabel lblSelectHero = new JLabel("Select existing hero");

    //Images Section
    private ImageIcon welcomeImage = new ImageIcon(IMG_DIR + "dbzTitle.png");
    private ImageIcon heroImage = new ImageIcon(IMG_DIR + "saiyan.png");
    private JLabel lblHeroImage = new JLabel(heroImage);
    private JLabel lblWelcomeImage = new JLabel(welcomeImage);
    private JTextArea txtAStats = new JTextArea();

    GameController gc = new GameController();

    public Window(){
        setTitle("DBZ Swingy");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        BLayout.setLayout(new BorderLayout());

        landingScreen(this);
        this.setVisible(true);
    }

    private void landingScreen(Window window){
        JComboBox cbSavedHeroes = new JComboBox(addSavedHeroes().toArray());

        centerDiv.add(lblEnterName);
        centerDiv.add(txtName);
        centerDiv.add(lblSelectType);
        centerDiv.add(cbHerolist);
        centerDiv.add(lblSelectHero);
        centerDiv.add(cbSavedHeroes);
        centerDiv.add(btnCreateNew);
        centerDiv.add(btnStart);

        vBoxEast.add(lblHeroImage);
        vBoxEast.add(txtAStats);

        topDiv.add(lblWelcomeImage);
        bottomDiv.add(lblAuthor);

        BLayout.add(bottomDiv, BorderLayout.SOUTH);
        BLayout.add(topDiv, BorderLayout.NORTH);
        BLayout.add(centerDiv, BorderLayout.CENTER);
        BLayout.add(vBoxEast, BorderLayout.EAST);

        cbHerolist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                heroImage = new ImageIcon(IMG_DIR + heroImages[cbHerolist.getSelectedIndex()]);
                lblHeroImage.setIcon(heroImage);
            }
        });
        btnCreateNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //What to do when user presses the create button
            }
        });
        addSavedHeroes();
        window.setContentPane(BLayout);
    }

    private List addSavedHeroes(){
        ResultSet resultSet;
        List<String>heroList = new ArrayList<>();

        gc.getDBPlayer();
        resultSet = gc.result;
        try {
            while (resultSet.next()) {
                heroList.add(resultSet.getString("name"));
            }
        }catch (Exception e){System.out.println(e.getMessage());}
        return heroList;
    }
}
