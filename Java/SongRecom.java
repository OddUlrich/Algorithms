import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;

public class SongRecom{
    static HashMap<String, String> song2style = new HashMap<>();

    static ArrayList<String> popList = new ArrayList<>();
    static ArrayList<String> blueList = new ArrayList<>();
    static ArrayList<String> rockList = new ArrayList<>();
    static ArrayList<String> unknownList = new ArrayList<>();

    static ArrayList<Integer> popScore = new ArrayList<>();
    static ArrayList<Integer> blueScore = new ArrayList<>();
    static ArrayList<Integer> rockScore = new ArrayList<>();
    static ArrayList<Integer> unknownScore = new ArrayList<>();

    static String lastPlayed = "";
    static String lastBreak = "";

    public static void insertSong(String song, String style) {
        song2style.put(song, style);
        if (style.equals("Pop")) {
            popList.add(song);
            popScore.add(0);
        } else if (style.equals("Blue")) {
            blueList.add(song);
            blueScore.add(0);
        } else if (style.equals("Rock")) {
            rockList.add(song);
            rockScore.add(0);
        } else if (style.equals("UnkownStyle")) {
            unknownList.add(song);
            unknownScore.add(0);
        } else {
            return;
        }
    }

    public static void addScore(String song, String style) {
        ArrayList<String> styleList;
        ArrayList<Integer> scoreList;

        if (style.equals("Pop")) {
            styleList = popList;
            scoreList = popScore;
        } else if (style.equals("Blue")) {
            styleList = blueList;
            scoreList = blueScore;
        } else if (style.equals("Rock")) {
            styleList = rockList;
            scoreList = rockScore;
        } else if (style.equals("UnkownStyle")) {
            styleList = unknownList;
            scoreList = unknownScore;
        } else {
            return;
        }

        if (lastPlayed.equals("")) {
            lastPlayed = style;
        } else if (!lastPlayed.equals(style)) {
            int idx = styleList.indexOf(song);
            int val = scoreList.get(idx);
            scoreList.set(idx, val+3);

            lastPlayed = style;
        } else {
            if (style.equals("UnkownStyle")) {
                int idx = styleList.indexOf(song);
                int val = scoreList.get(idx);
                scoreList.set(idx, val+3);
            } else {
                for (int i = 0; i < scoreList.size(); i++) {
                    int val = scoreList.get(i);
                    if (styleList.get(i).equals(song)) {
                        val += 2;
                    }
                    scoreList.set(i, val+1);
                }
            }
        }
    }

    public static void minusScore(String song, String style) {
        ArrayList<String> styleList;
        ArrayList<Integer> scoreList;

        if (style.equals("Pop")) {
            styleList = popList;
            scoreList = popScore;
        } else if (style.equals("Blue")) {
            styleList = blueList;
            scoreList = blueScore;
        } else if (style.equals("Rock")) {
            styleList = rockList;
            scoreList = rockScore;
        } else if (style.equals("UnkownStyle")) {
            styleList = unknownList;
            scoreList = unknownScore;
        } else {
            return;
        }

        if (lastBreak.equals("")) {
            lastBreak = style;
        } else if (!lastBreak.equals(style)) {
            int idx = styleList.indexOf(song);
            int val = scoreList.get(idx);
            scoreList.set(idx, val-2);

            lastBreak = style;
        } else {
            if (style.equals("UnkownStyle")) {
                int idx = styleList.indexOf(song);
                int val = scoreList.get(idx);
                scoreList.set(idx, val-2);
            } else {
                for (int i = 0; i < scoreList.size(); i++) {
                    int val = scoreList.get(i);
                    if (styleList.get(i).equals(song)) {
                        val--;
                    }
                    scoreList.set(i, val-1);
                }
            }
        }
    }

    public static void sortAndPrint() {
        ArrayList<String> songList = new ArrayList<>();
        ArrayList<Integer> scoreList = new ArrayList<>();

        songList.addAll(popList);
        songList.addAll(blueList);
        songList.addAll(rockList);
        songList.addAll(unknownList);

        scoreList.addAll(popScore);
        scoreList.addAll(blueScore);
        scoreList.addAll(rockScore);
        scoreList.addAll(unknownScore);

        ArrayList<Integer> sortedScore = (ArrayList<Integer>) scoreList.clone();
        Collections.sort(sortedScore);

        int i = 0;
        int k = 0;
        ArrayList<String> tmpSong = new ArrayList<>();
        while (i < sortedScore.size()) {
            int score = sortedScore.get(i);

            for (k = 0; k < sortedScore.size(); k++) {
                if (sortedScore.get(i+k) == score) {
                    int scoreIdx = scoreList.indexOf(score);
                    String song = songList.get(scoreIdx);
                    tmpSong.add(song);
                } else {
                    break;
                }
            }
            if (tmpSong.size() == 1) {
                // Have no same score.
                String style = song2style.get(tmpSong.get(0));
                System.out.println(tmpSong.get(0) + " " + style);
            } else {
                Collections.sort(tmpSong);
                for (String name: tmpSong) {
                    String style = song2style.get(name);
                    System.out.println(name + " " + style);
                }
            }

            tmpSong.clear();
            i += k;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (in.hasNext()) {
            String act = in.next();
            if (act.length() == 0) {
                break;
            }

            String song = in.next();
            if (act.equals("I")) {
                // Insert new songs.
                String style = in.next();
                insertSong(song, style);
            } else if (act.equals("P")) {
                String style = song2style.get(song);
                // Finished a song
                addScore(song, style);

            } else if (act.equals("B")) {
                String style = song2style.get(song);
                // Break a song
                minusScore(song, style);
            }
        }
        in.close();

        // Sorting and printing out.
        sortAndPrint();

    }
}