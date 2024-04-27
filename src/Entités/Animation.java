package Entités;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Gère les animations pour les entités avec différentes séquences d'images selon l'état de l'entité.
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

