package fi.rpgtool.gui.window;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextPane;

public class HelpDialog extends JDialog {

    /** Luo ikkunan ohjetekstillä joka ladataan tiedostosta
     * @throws IOException
     */
    public HelpDialog() throws IOException {

        this.setLayout(new BorderLayout());
        this.setTitle("RPGTOOL KÄYTTÖOHJE");
        this.setSize(500, 500);
        this.setModal(true);
        this.setLocationRelativeTo(null);

        JTextPane helpText = new JTextPane();
        File file = new File("src/main/resources/helptext.txt");
        String encoding = "UTF-8";
        Reader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding));
        helpText.read(reader, helpText);

        reader.close();

        JPanel buttonPanel = new JPanel();
        JButton okButton = new JButton("OK");
        buttonPanel.add(okButton);
        this.add(helpText, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.PAGE_END);

        okButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                HelpDialog.this.dispose();
            }
        });
        this.setVisible(true);
    }
}