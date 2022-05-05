package com.insightfullogic.java8.examples.chapter3;

import com.insightfullogic.java8.examples.chapter1.Album;
import com.insightfullogic.java8.examples.chapter1.Artist;
import com.insightfullogic.java8.examples.chapter1.Track;
import org.junit.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

public class QuestionTest {

    @Test
    public void testGetNamesAndOrigins() {
        List<Artist> artists = asList(
                new Artist("A", "China"),
                new Artist("B", "Japan"),
                new Artist("C", "USA")
        );
        System.out.println(getNamesAndOrigins2(artists));
    }

    @Test
    public void testGetAlbumsWithAtMostThreeTracks() {
        List<Album> albums = asList(
                new Album("JAY",
                        asList(new Track("可爱女人", 140), new Track("完美注意", 120), new Track("黑色幽默", 220)),
                        asList(new Artist("周杰伦", "中国台湾"))),
                new Album("范特西",
                        asList(new Track("爱在西元前", 220), new Track("简单爱", 100), new Track("忍者", 110), new Track("安静", 120)),
                        asList(new Artist("周杰伦", "中国台湾")))
        );
        getAlbumsWithAtMostThreeTracks(albums).forEach(album -> System.out.println(album.getName()));
    }

    /**
     * 根据传入的艺术家列表，返回一个字符串列表，其中包含艺术家的姓名和国籍
     *
     * @param artists
     * @return
     */
    public List<String> getNamesAndOrigins2(List<Artist> artists) {
        return artists.stream()
                .flatMap(artist -> Stream.of(artist.getName(), artist.getNationality()))
                .collect(toList());
    }

    /**
     * 根据传入的艺术家列表，返回一个字符串列表，其中包含艺术家的姓名和国籍
     *
     * @param artists
     * @return
     */
    public List<String> getNamesAndOrigins(List<Artist> artists) {
        return artists.stream()
                .flatMap(artist -> Stream.of(artist.getName(), artist.getNationality()))
                .collect(toList());
    }

    /**
     * 根据传入的专辑列表，返回一个由最多包含3首歌曲的专辑组成的列表
     *
     * @param albums
     * @return
     */
    public List<Album> getAlbumsWithAtMostThreeTracks(List<Album> albums) {
        return albums.stream()
                .filter(album -> album.getTrackList().size() <= 3)
                .collect(toList());
    }

    /**
     * 重构for循环
     */
    public void refactoring() {
        List<Artist> artists = Collections.emptyList();
        artists.stream()
                .map(artist -> artist.getMembers().count())
                .reduce(0L, Long::sum);
    }

    /**
     * 一个字符串中有多少个小写字母
     */
    @Test
    public void countLowercaseLettersTest() {
        String s = "DjdkfDkfvKkfenWdn";
        System.out.println(countLowercaseLetters(s));
        System.out.println(s.chars().filter(Character::isLowerCase).count());
    }

    /**
     * 一个字符串中有多少个小写字母
     * @param string
     * @return
     */
    public static int countLowercaseLetters(String string) {
        return (int) string.chars().filter(Character::isLowerCase).count();
    }

    /**
     * 一个字符串列表中，小写字母最多的字符串
     */
    @Test
    public void mostLowercaseString() {
        List<String> strings = asList("dDKefDodfD", "sfDFGdDGthsdfD", "dfGwDGYesdF");
        System.out.println(strings.stream().max(Comparator.comparingInt(QuestionTest::countLowercaseLetters)).get());
    }

}
