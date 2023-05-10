package fi.rpgtool.gui.element;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class HelpDialog extends JDialog {

    /** Luo ikkunan ohjetekstillä joka ladataan tiedostosta
     * @throws IOException
     */
    public HelpDialog() throws IOException {

        this.setLayout(new BorderLayout());
        this.setTitle("RPGTOOL KÄYTTÖOHJE");
        this.setSize(500, 600);
        this.setModal(true);
        this.setLocationRelativeTo(null);

        JTextPane helpText = new JTextPane();
        File file = new File("src/main/resources/helptext.txt");
        Reader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
        helpText.read(reader, helpText);
        helpText.setEditable(false);

        reader.close();

        JButton closeButton = new JButton("Sulje");
        closeButton.addActionListener(action -> this.dispose());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);

        this.add(helpText, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.PAGE_END);
        this.setVisible(true);
    }
}