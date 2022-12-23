package ClientTelepass;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/////////////////////////////////////////////////////////////// MenuDemoGUI
class MenuDemoGUI extends JPanel {
    JTextArea m_editArea = new JTextArea(20, 50);

    //... Menuitems are buttons, so should be instance variables
    //    so they can be en-/disabled by various actions.
    JMenuItem m_openItem = new JMenuItem("Open"); // create new menu item
    JMenuItem m_quitItem = new JMenuItem("Quit"); // another menu item
    JMenuItem m_copyItem = new JMenuItem("Copy");
    JMenuItem m_pasteItem = new JMenuItem("Paste");

    //========================================================== constructor
    public MenuDemoGUI() {
        //... Add listeners to menu items
        m_openItem.addActionListener(new OpenAction());
        m_quitItem.addActionListener(new QuitAction());
        //... Copy and Paste don't have listeners yet, so disable them.
        m_copyItem.setEnabled(false);
        m_pasteItem.setEnabled(false);

        //... Add the single component.
        setLayout(new BorderLayout());
        add(m_editArea, BorderLayout.CENTER);
    }


    //==================================================== createMenubar
    // It's common to write a method that produces the menubar.
    // There are two reasons for this.
    // First, it gives a little better organization to the program
    //        and doesn't mix content pane and menu construction.
    // Second, it can be defined wherever you want, so that it can be
    //        put in a JPanel subclass (as here), or elsewhere.
    JMenuBar createMenubar() {
        //... Menubar, menus, menu items.  Use indentation to show structure.
        JMenuBar menubar = new JMenuBar();  // declare and create new menu bar
        JMenu fileMenu = new JMenu("File");// declare and create new menu
        menubar.add(fileMenu);        // add the menu to the menubar
        fileMenu.add(m_openItem); // add the menu item to the menu
        fileMenu.addSeparator();  // add separator line to menu
        fileMenu.add(m_quitItem);
        JMenu editMenu = new JMenu("Edit");
        menubar.add(editMenu);
        editMenu.add(m_copyItem);
        editMenu.add(m_pasteItem);
        return menubar;
    }


    ///////////////////////////////////////////////////////////// OpenAction
    class OpenAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, "Sorry, can't open anything");
        }
    }

    ///////////////////////////////////////////////////////////// QuitAction
    class QuitAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);  // terminate this program
        }
    }
}
