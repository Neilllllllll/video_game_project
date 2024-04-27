package Entit�s;

import java.io.File;
import java.net.URL;

public abstract class Filereader {

    public static int countFilesInFolder(String folderName) {
        // Obtenir le chemin de la classe Filereader
        URL rootPathURL = Filereader.class.getResource("/");
        File rootFolder;

        try {
            // Convertir l'URL en un objet File
            rootFolder = new File(rootPathURL.toURI());
        } catch (Exception e) {
            // G�rer l'erreur de conversion
            e.printStackTrace();
            return -1;
        }

        // Construire le chemin complet avec le dossier souhait�
        File targetFolder = new File(rootFolder, folderName);
        int fileCount = 0;

        // V�rifier si le dossier cible existe et est un r�pertoire
        if (targetFolder.exists() && targetFolder.isDirectory()) {
            // Lister tous les fichiers du dossier
            File[] files = targetFolder.listFiles();

            // Compter uniquement les fichiers (exclure les dossiers)
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        fileCount++;
                    }
                }
            }
        } else {
            System.out.println("Le chemin sp�cifi� n'est pas un dossier ou n'existe pas.");
        }

        return fileCount;
    }
}


