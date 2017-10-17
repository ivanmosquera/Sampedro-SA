/*
 * Copyright (c) 2009-2011, EzWare
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.Redistributions
 * in binary form must reproduce the above copyright notice, this list of
 * conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.Neither the name of the
 * EzWare nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior
 * written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 */

package org.oxbow.swingbits.misc;

import static com.sun.org.apache.bcel.internal.util.SecuritySupport.getResourceAsStream;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

/**
 * A text field with search symbol painted to indicate 
 * that it is used as search field 
 * 
 * @author Eugene Ryzhikov
 *
 */
public class JSearchTextField extends JTextField {

    private static final String ICON_NAME = "search.png";
    private static final long serialVersionUID = 1L;
    
    private static ImageIcon icon;
    //tatic InputStream is = (InputStream)this.getClass().getClassLoader().getResourceAsStream("sanpedroproyect/busqueda.png");

    private static Image getScaledImage( int size ) {
        
        if (icon == null) {

            BufferedImage imagen = null;
            try {
                imagen = ImageIO.read(getResourceAsStream("Imagenes/busqueda.png"));
            } catch (IOException ex) {
                Logger.getLogger(JSearchTextField.class.getName()).log(Level.SEVERE, null, ex);
            }
            icon = new ImageIcon(imagen);
            //icon = new ImageIcon( "/Users/kleberstevendiazcoello/Documents/GitHub/Sampedro-SA/SamPedroProyect/src/Imagenes/busqueda.png");
          


        }
        return new ImageIcon(icon.getImage().getScaledInstance( size, size, Image.SCALE_SMOOTH )).getImage();
    }
    
    private static int PAD = 4;
    private static int PAD2 = PAD*2;
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int size = getHeight()-PAD2;
        g.drawImage( getScaledImage(size), getWidth()-size-PAD, PAD, null);
    }

}
