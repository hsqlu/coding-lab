package stream;

import org.junit.Test;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;

public class CollectionTest {

    @Test
    public void streamIterate() {
        List<Integer> together = Stream.of(asList(1, 2, 7), asList(3, 4, 5, 6)).flatMap(Collection::stream).collect(toList());
        System.out.println(together);
        assertEquals(asList(1, 2, 7, 3, 4, 5, 6), together);
    }

    @Test
    public void mapDemo() {

        List<String> collected = Stream.of("a", "b", "hello")
                .map(String::toUpperCase).collect(toList());

        assertEquals(asList("A", "B", "HELLO"), collected);
    }

    @Test
    public void maxMinDemo() {
        List<Track> tracks = asList(new Track("Bakai", 524),
                new Track("Violets for Your Furs", 378),
                new Track("Time Was", 451));
        Track shortestTrack = tracks.stream()
                .min(Comparator.comparing(Track::getLength))
                .get();
        assertEquals(tracks.get(1), shortestTrack);
    }

    @Test
    public void reduceDemo() {
        int count = Stream.of(1, 2, 3)
                .reduce(1, Integer::sum);
        assertEquals(7, count);
    }

    /*
    Refactoring to stream operations

    public Set<String> findLongTracks(List<Album> albums) {
        Set<String> trackNames = new HashSet<>();
        for(Album album : albums) {
            for (Track track : album.getTrackList()) {
                if (track.getLength() > 60) {
                    String name = track.getName();
                    trackNames.add(name);
                }
            }
        }
        return trackNames;
    }

    public Set<String> findLongTracks(List<Album> albums) {
        Set<String> trackNames = new HashSet<>();
        albums.stream()
                .forEach(album -> {
                    album.getTracks()
                            .forEach(track -> {
                                if (track.getLength() > 60) {
                                    String name = track.getName();
                                    trackNames.add(name);
                                }
                            });
                });
        return trackNames;
    }

    public Set<String> findLongTracks(List<Album> albums) {
        Set<String> trackNames = new HashSet<>();
        albums.stream()
                .forEach(album -> {
                    album.getTracks()
                            .filter(track -> track.getLength() > 60)
                            .map(track -> track.getName())
                            .forEach(name -> trackNames.add(name));
                });
        return trackNames;
    }

    public Set<String> findLongTracks(List<Album> albums) {
        Set<String> trackNames = new HashSet<>();
        albums.stream()
                .flatMap(album -> album.getTracks())
                .filter(track -> track.getLength() > 60)
                .map(track -> track.getName())
                .forEach(name -> trackNames.add(name));
        return trackNames;
    }

    public Set<String> findLongTracks(List<Album> albums) {
        30 ｜ 第3 章
        return albums.stream()
                .flatMap(album -> album.getTracks())
                .filter(track -> track.getLength() > 60)
                .map(track -> track.getName())
                .collect(toSet());
    }
    */
}