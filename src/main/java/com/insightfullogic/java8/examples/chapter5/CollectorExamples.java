package com.insightfullogic.java8.examples.chapter5;

import com.insightfullogic.java8.examples.chapter1.Album;
import com.insightfullogic.java8.examples.chapter1.Artist;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.nio.charset.Charset.defaultCharset;
import static java.util.Comparator.comparing;
import static java.util.Map.Entry;
import static java.util.stream.Collectors.*;

public class CollectorExamples {

    /**
     * 转换成其他集合
     */
    public void toCollectionTreeset() {
        Stream<Integer> stream = Stream.of(1, 2, 3);
        // BEGIN TO_COLLECTION_TREESET
        stream.collect(toCollection(TreeSet::new));
        // END TO_COLLECTION_TREESET
    }

    /**
     * 转换成值，找出团队成员最多的乐队
     * @param artists
     * @return
     */
    // BEGIN BIGGEST_GROUP
    public Optional<Artist> biggestGroup(Stream<Artist> artists) {
        return artists.collect(maxBy(comparing(artist -> artist.getMembers().count())));
    }
    // END BIGGEST_GROUP

    /**
     * 转换成值，计算一组专辑上的平均数
     * @param albums
     * @return
     */
    // BEGIN averagingTracks
    public double averageNumberOfTracks(List<Album> albums) {
        return albums.stream()
                .collect(averagingInt(album -> album.getTrackList().size()));
    }
    // END averagingTracks

    /**
     * 分块，根据乐队和独唱歌手分成两部分
     * @param artists
     * @return
     */
    // BEGIN BANDS_AND_SOLO
    public Map<Boolean, List<Artist>> bandsAndSolo(Stream<Artist> artists) {
        return artists.collect(partitioningBy(artist -> artist.isSolo()));
    }
    // END BANDS_AND_SOLO
    /**
     * 分块，根据乐队和独唱歌手分成两部分（方法引用）
     * @param artists
     * @return
     */
    // BEGIN BANDS_AND_SOLO_REF
    public Map<Boolean, List<Artist>> bandsAndSoloRef(Stream<Artist> artists) {
        return artists.collect(partitioningBy(Artist::isSolo));
    }
    // END BANDS_AND_SOLO_REF

    /**
     * 分组，根据主唱分组
     * @param albums
     * @return
     */
    // BEGIN ALBUMS_BY_ARTIST
    public Map<Artist, List<Album>> albumsByArtist(Stream<Album> albums) {
        return albums.collect(groupingBy(album -> album.getMainMusician()));
    }
    // END ALBUMS_BY_ARTIST

    public Map<Artist, Integer> numberOfAlbumsDumb(Stream<Album> albums) {
        // BEGIN NUMBER_OF_ALBUMS_DUMB
        Map<Artist, List<Album>> albumsByArtist
                = albums.collect(groupingBy(album -> album.getMainMusician()));

        Map<Artist, Integer> numberOfAlbums = new HashMap<>();
        for (Entry<Artist, List<Album>> entry : albumsByArtist.entrySet()) {
            numberOfAlbums.put(entry.getKey(), entry.getValue().size());
        }
        // END NUMBER_OF_ALBUMS_DUMB
        return numberOfAlbums;
    }

    // BEGIN NUMBER_OF_ALBUMS
    public Map<Artist, Long> numberOfAlbums(Stream<Album> albums) {
        return albums.collect(groupingBy(album -> album.getMainMusician(),
                counting()));
    }
    // END NUMBER_OF_ALBUMS

    // BEGIN NAME_OF_ALBUMS_DUMB
    public Map<Artist, List<String>> nameOfAlbumsDumb(Stream<Album> albums) {
        Map<Artist, List<Album>> albumsByArtist =
                albums.collect(groupingBy(album -> album.getMainMusician()));

        Map<Artist, List<String>> nameOfAlbums = new HashMap<>();
        for (Entry<Artist, List<Album>> entry : albumsByArtist.entrySet()) {
            nameOfAlbums.put(entry.getKey(), entry.getValue()
                    .stream()
                    .map(Album::getName)
                    .collect(toList()));
        }
        return nameOfAlbums;
    }
    // END NAME_OF_ALBUMS_DUMB

    // BEGIN NAME_OF_ALBUMS
    public Map<Artist, List<String>> nameOfAlbums(Stream<Album> albums) {
        return albums.collect(groupingBy(Album::getMainMusician,
                mapping(Album::getName, toList())));
    }
    // END NAME_OF_ALBUMS

    public static Map<String, Long> countWords(Stream<String> words) {
        return words.collect(groupingBy(word -> word, counting()));
    }

    private static final Pattern SPACES = Pattern.compile("\\w+");

    public static Map<String, Long> countWordsIn(Path path) throws IOException {
        Stream<String> words = Files.readAllLines(path, defaultCharset())
                .stream()
                .flatMap(line -> SPACES.splitAsStream(line));

        return countWords(words);
    }

}

