/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linkgrabber;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Jakob
 */
public class LinkMain implements Observer {

    public static void main(String[] args) throws IOException {
        LinkGrabber handler = new LinkGrabber();
        handler.addObserver(new LinkMain());
        System.in.read();
        
        
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println(arg);
    }


}
