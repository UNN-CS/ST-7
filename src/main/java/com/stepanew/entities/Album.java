package com.stepanew.entities;

import java.util.Collections;
import java.util.List;

/**
 * Представляет музыкальный альбом с названием, исполнителем и списком треков.
 * Этот класс является абстрактным и предназначен для расширения более конкретными типами альбомов.
 *
 * <p>Экземпляры этого класса являются неизменяемыми, что означает, что после создания объекта Album
 * его название, исполнитель и список треков не могут быть изменены.</p>
 */
public abstract class Album {

    private final String title;

    private final String artist;

    private final List<String> tracks;

    /**
     * Создает альбом с указанным названием, исполнителем и списком треков.
     *
     * @param title  название альбома
     * @param artist исполнитель альбома
     * @param tracks список треков в альбоме; список будет неизменяемым
     */
    public Album(String title, String artist, List<String> tracks) {
        this.title = title;
        this.artist = artist;
        this.tracks = Collections.unmodifiableList(tracks);
    }

    /**
     * Возвращает название альбома.
     *
     * @return название альбома
     */
    public String getTitle() {
        return title;
    }

    /**
     * Возвращает исполнителя альбома.
     *
     * @return исполнитель альбома
     */
    public String getArtist() {
        return artist;
    }

    /**
     * Возвращает неизменяемый список треков в альбоме.
     *
     * @return список треков в альбоме
     */
    public List<String> getTracks() {
        return tracks;
    }

}
