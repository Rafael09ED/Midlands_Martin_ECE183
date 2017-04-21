package labs.lab8;

import java.security.InvalidParameterException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * EGR 283 B01
 * RecursiveKnightTour.java
 * Purpose:
 *
 * @author Rafael
 * @version 1.1 3/14/2017
 */
public class RecursiveKnightTour {
    private static final Map<Integer, List<Integer>> KNIGHT_MOVES;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter knight starting piecePosition, followed by the target piecePosition");
        List<BoardState> list = generatePath(sc.nextInt(), sc.nextInt(), new HashSet<>());
        if (list == null)
            System.out.println("No path to target piecePosition");
        else
            System.out.println(list.stream()
                    .map(Object::toString)
                    .collect(Collectors.joining("\n"))
            );
    }

    /**
     * @param currentPos       current position
     * @param targetPos        target position
     * @param visitedPositions a set of the visited positions to not visit again
     * @return List of Board States which represent moves to the final position, null if no path to target
     */
    private static List<BoardState> generatePath(int currentPos, int targetPos, Set<Integer> visitedPositions) {
        if (currentPos < 1 || targetPos < 1 || currentPos > 9 || targetPos > 9)
            throw new InvalidParameterException("Not a valid position");
        if (currentPos == 5 && targetPos != 5)
            return null;

        if (currentPos == targetPos) return new LinkedList<>(Arrays.asList(new BoardState(currentPos)));
        else if (!visitedPositions.add(currentPos)) return null;

        List<BoardState> shortestMovesPath = null;
        for (Integer possibleMovePosition : KNIGHT_MOVES.get(currentPos)) {
            List<BoardState> childMovesPath = generatePath(possibleMovePosition, targetPos, new HashSet<>(visitedPositions));
            if (childMovesPath != null && (shortestMovesPath == null || childMovesPath.size() < shortestMovesPath.size()))
                shortestMovesPath = childMovesPath;
        }
        if (shortestMovesPath != null)
            shortestMovesPath.add(0, new BoardState(currentPos));
        return shortestMovesPath;
    }

    private static class BoardState {
        private final int piecePosition;

        public BoardState(int piecePosition) {
            this.piecePosition = piecePosition;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            for (int i = 1; i <= 9; i++) {
                if (i == piecePosition) builder.append("K");
                else builder.append(i);
                if (i % 3 == 0) builder.append("\n");
                else builder.append(" ");
            }
            return builder.toString();
        }
        public String toString2() {
            return "1 2 3 \n4 5 6 \n7 8 9 \n".replace(""+piecePosition,"K");
        }
    }

    static {
        KNIGHT_MOVES = new HashMap<>();
        KNIGHT_MOVES.put(1, Arrays.asList(8, 6));
        KNIGHT_MOVES.put(2, Arrays.asList(9, 7));
        KNIGHT_MOVES.put(3, Arrays.asList(4, 8));
        KNIGHT_MOVES.put(4, Arrays.asList(9, 3));
        KNIGHT_MOVES.put(5, null);
        KNIGHT_MOVES.put(6, Arrays.asList(1, 7));
        KNIGHT_MOVES.put(7, Arrays.asList(2, 6));
        KNIGHT_MOVES.put(8, Arrays.asList(3, 1));
        KNIGHT_MOVES.put(9, Arrays.asList(2, 4));
    }
}
