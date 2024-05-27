package com.stepanew;

import com.stepanew.entities.Graduation;
import com.stepanew.utilities.AlbumCoverGenerator;

public class App {

    public static void main(String[] args) {
        new AlbumCoverGenerator(new Graduation()).generateAlbumCover();
    }

}
