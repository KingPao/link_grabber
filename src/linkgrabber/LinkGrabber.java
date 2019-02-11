/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linkgrabber;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jakob
 */
public class LinkGrabber extends Observable implements Runnable {

    public LinkGrabber() {
        Thread thread = new Thread(this);
        thread.setDaemon(true);
        thread.start();
    }

    @Override
    public void run() {

        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        String last = null;
        String current = null;

        while (true) {
            try {
                Transferable data = clipboard.getContents(null);

                if (data != null && data.isDataFlavorSupported(DataFlavor.stringFlavor)) {

                    current = (String) data.getTransferData(DataFlavor.stringFlavor);

                    if (current.contains(".at") || current.contains(".com") || current.contains(".de") || current.contains(".info")) {
                        if (!current.equals(last)) {

                            last = current;
                            setChanged();
                            notifyObservers(current);
                            
                            writeEntry(last);
                        }
                    }

                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }

            // Wartezeit (1 Sekunde)
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    private void writeEntry(String s) throws FileNotFoundException {

        try (FileWriter fw = new FileWriter("./res/Links.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter pw = new PrintWriter(bw);) {

            pw.println(s);

        } catch (IOException ex) {
            Logger.getLogger(LinkGrabber.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void checkEntry(String link) throws FileNotFoundException, IOException
    {
        BufferedReader br = new BufferedReader(new FileReader("./res/Links.txt"));
        String line = "";
        
        while(( line = br.readLine()) != null)
        {
            if(link.equals(line))
            {
                writeEntry(link);
            }
        }
        
    }

    
    
}
