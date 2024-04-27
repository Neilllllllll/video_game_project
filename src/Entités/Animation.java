package Entit�s;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * G�re les animations pour les entit�s avec diff�rentes s�quences d'images selon l'�tat de l'entit�.
 */

public abstract class Animation {
	
    public static Image[] fill_Animation(String path) throws SlickException {
        Image [] images = new Image[Filereader.countFilesInFolder(path)];
        // Chargement des images
        for (int i = 0; i < images.length; i++) {
            String imagePath = path + "/" + i + ".png";
            images[i] = new Image(imagePath);
        }
        return images;
    }
}

