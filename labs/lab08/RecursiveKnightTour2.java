package labs.lab8;

import java.util.*;
import java.util.stream.Collectors;

/**
 * EGR 283 B01
 * RecursiveKnightTour.java
 * Purpose:
 *
 * @author Rafael
 * @version 1.0 2/23/2017
 */
public class RecursiveKnightTour2 {
    private final ChessBoardPos pos, target;
    private final RecursiveKnightTour2 parent;
    private static int leastSteps = -1;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter knight starting pos, followed by the target pos");
        List<RecursiveKnightTour2> list = new RecursiveKnightTour2(
                new ChessBoardPos(sc.nextLine()),
                new ChessBoardPos(sc.nextLine())
        ).findPath();

        if (list == null)
            System.out.println("No path to target pos");
        else
            System.out.println(list.stream()
                    .map(recursiveKnightTour2 -> recursiveKnightTour2.pos.boardToString())
                    .collect(Collectors.joining("\n"))
            );
    }


    public List<RecursiveKnightTour2> findPath() {
        leastSteps = -1;
        return generatePath(new HashSet<>(), 0);
    }

    /**
     * @param visitedPos a set of the visited positions to not visit again
     * @return List of RecursiveKnightTours which represent moves to the final position, null if no path to target
     */
    private List<RecursiveKnightTour2> generatePath(Set<ChessBoardPos> visitedPos, int step) {
        if (leastSteps > 0 && leastSteps < step)
            return null;
        if (parent != null && !pos.isCloserToPosThan(target, ChessPiece.KNIGHT, parent.pos)) {
            return null;
        }

        if (pos.equals(target)) {
            if (leastSteps > 0 && step < leastSteps)
                leastSteps = step;
            LinkedList<RecursiveKnightTour2> path = new LinkedList<>();
            for (RecursiveKnightTour2 tempParent = this; tempParent != null; tempParent = tempParent.getParent())
                path.add(tempParent);
            Collections.reverse(path);
            System.out.println(path);
            return path;
        } else if (!visitedPos.add(pos))
            return null;


        List<RecursiveKnightTour2> returnPath = null;

        for (ChessBoardPos pos : ChessPiece.KNIGHT.getPossiblePositions(pos)) {
            Set<ChessBoardPos> newUsedPositions = new HashSet<>(visitedPos);
            List<RecursiveKnightTour2> path = new RecursiveKnightTour2(pos, target, this).generatePath(newUsedPositions, step + 1);
            if (path != null) {
                if (returnPath == null || returnPath.size() > path.size())
                    returnPath = path;
            } else
                visitedPos.addAll(newUsedPositions);
        }
        return returnPath;
    }

    public RecursiveKnightTour2(ChessBoardPos pos, ChessBoardPos target, RecursiveKnightTour2 parent) {
        this.parent = parent;
        this.pos = pos;
        this.target = target;
    }

    public RecursiveKnightTour2(ChessBoardPos pos, ChessBoardPos target) {
        this(pos, target, null);
    }

    public RecursiveKnightTour2 getParent() {
        return parent;
    }

    @Override
    public String toString() {
        return pos.toString();
    }

    public interface ChessMovable {
        List<ChessBoardPos> getPositions(ChessBoardPos boardPos);
    }

    public enum ChessPiece {
        KNIGHT('K', currentBoardPos -> {
            final int[][] knightMoves = {{1, 2}, {-1, 2}, {2, 1}, {2, -1}, {-2, 1}, {-2, -1}, {-1, -2}, {1, -2}};
            List<ChessBoardPos> possiblePos = new LinkedList<>();
            for (int[] knightMove : knightMoves) {
                ChessBoardPos boardPos = currentBoardPos.getOffsetPos(knightMove[0], knightMove[1]);
                if (boardPos != null)
                    possiblePos.add(boardPos);
            }
            return possiblePos;
        }, 4);

        private final char letter;
        private final ChessMovable chessMovable;
        private final int minMoveDistance;

        ChessPiece(char letter, ChessMovable chessMovable, int minMoveDistance) {
            this.letter = letter;
            this.chessMovable = chessMovable;
            this.minMoveDistance = minMoveDistance;
        }

        public List<ChessBoardPos> getPossiblePositions(ChessBoardPos pos) {
            return chessMovable.getPositions(pos);
        }

        public int getMinMoveDistance() {
            return minMoveDistance;
        }
    }

    public static class ChessBoardPos {
        private final int x, y;

        public ChessBoardPos(String string) {
            this(string.charAt(0), Integer.parseInt(String.valueOf(string.charAt(1))));
        }

        public ChessBoardPos(char x, int y) {
            if (!(x + "").matches("[a-h]") || y <= 0 || y > 8)
                throw new IllegalArgumentException();
            this.x = x - 96;
            this.y = y;
        }

        public ChessBoardPos(int x, int y) {
            if (x <= 0 || x > 8 || y < 0 || y > 8)
                throw new IllegalArgumentException();
            this.x = x;
            this.y = y;
        }

        public int getY() {
            return y;
        }

        public int getX() {
            return x;
        }

        public ChessBoardPos getOffsetPos(int xOffset, int yOffset) {
            try {
                return new ChessBoardPos(x + xOffset, y + yOffset);
            } catch (IllegalArgumentException x) {
                return null;
            }
        }

        @Override
        public String toString() {
            return ((char) (x + 96)) + " " + y;
        }

        public String boardToString() {
            StringBuilder builder = new StringBuilder();
            builder.append("  a b c d e f g h \n");
            for (int iy = 8; iy >= 1; iy--) {
                builder.append(iy).append(" ");
                for (int ix = 1; ix <= 8; ix++) {
                    if (iy == y && ix == x)
                        builder.append(ChessPiece.KNIGHT.letter);
                    else builder.append(" ");
                    builder.append(" ");
                }
                builder.append("\n");
            }
            return builder.toString();
        }

        @Override
        public boolean equals(Object object) {
            if (object instanceof ChessBoardPos) {
                ChessBoardPos chessBoardPos = (ChessBoardPos) object;
                return x == chessBoardPos.x && y == chessBoardPos.y;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return (x * 10) + y;
        }

        public boolean isCloserToPosThan(ChessBoardPos target, ChessPiece piece, ChessBoardPos otherPiecePos) {
            if (piece != ChessPiece.KNIGHT)
                throw new UnsupportedOperationException("Only supports knights");
            if (piece.getMinMoveDistance() >= getDistance(this, target))
                return true;
            return getDistance(this, target)-1 <= getDistance(otherPiecePos, target);
        }

        private static int getDistance(ChessBoardPos pos1, ChessBoardPos pos2) {
            return Math.abs(pos1.x - pos2.x) + Math.abs(pos1.y + pos2.y);
        }

    }
}
