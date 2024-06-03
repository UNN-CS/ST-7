package com.stepanew;

import com.stepanew.entities.Graduation;
import com.stepanew.utilities.AlbumCoverGenerator;

/**
 * Основной класс приложения, запускающий процесс генерации обложки альбома.
 */
public class App {

    /**
     * Главный метод приложения.
     * Создает объект AlbumCoverGenerator и генерирует обложку для альбома Graduation.
     *
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        new AlbumCoverGenerator(new Graduation()).generateAlbumCover();
    }

}
