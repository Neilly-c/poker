package mainsource.gui;

import javafx.scene.image.Image;
import mainsource.system.card.Card;

import java.io.File;

public class ImageViewAdaptor {

    public Image convertCardToImage(Card c){
        return new Image(new File("src/imgresource/" + c.toAbbreviateString() +  ".png").toURI().toString());
    }

    public Image[] convertCardToImage(Card[] c){
        Image[] images = new Image[c.length];
        for(int i=0;i<c.length;++i){
            images[i] = convertCardToImage(c[i]);
        }
        return images;
    }


}
