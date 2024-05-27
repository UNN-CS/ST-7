package com.stepanew;

import com.stepanew.entities.Graduation;
import com.stepanew.utilities.AlbumCoverGenerator;

public class App {

    public static void main(String[] args) {
        AlbumCoverGenerator albumCoverGenerator = new AlbumCoverGenerator(new Graduation());
        albumCoverGenerator.generateAlbumCover();
    }

}
