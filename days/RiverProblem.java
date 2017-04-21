package days;

import java.util.*;

/**
 * EGR 283 B01
 * days.RiverProblem.java
 * Purpose:
 *
 * @author Rafael
 * @version 1.0 4/18/2017
 */
public class RiverProblem {

    public RiverProblem() {
        List<RiverState> path = findPath(
                new RiverState(false, false, false, false),
                new RiverState(true, true, true, true));
        if (path != null)
            path.forEach(System.out::println);
        else
            System.out.println("No Path Found");
    }

    private static List<RiverState> findPath(final RiverState start, final RiverState target) {
        final Set<RiverState> visited = new HashSet<>();
        final Queue<RiverState> statesToCheck = new LinkedList<>();
        final Map<RiverState, RiverState> parents = new HashMap<>();
        statesToCheck.add(start);
        visited.add(start);

        while (!statesToCheck.isEmpty()) {
            final RiverState state = statesToCheck.poll();
            if (state == target)
                break;
            state.getPossibleNextStates().stream()
                    .filter(visited::add)
                    .forEach(nextState -> {
                        parents.put(nextState, state);
                        statesToCheck.offer(nextState);
                    });
        }

        RiverState temp = parents.get(target);
        if (temp == null) return null;
        List<RiverState> returnList = new ArrayList<>();
        returnList.add(target);
        while (temp != null) {
            returnList.add(0, temp);
            temp = parents.get(temp);
        }
        return returnList;

    }

    private static class RiverState {
        public final boolean fox, corn, goose, man;

        public RiverState(boolean fox, boolean corn, boolean goose, boolean man) {
            this.fox = fox;
            this.corn = corn;
            this.goose = goose;
            this.man = man;
        }

        public boolean isStateValid() {
            if (fox == goose && fox != man) return false;
            if (goose == corn && goose != man) return false;
            return true;
        }

        public List<RiverState> getPossibleNextStates() {
            List<RiverState> states = new LinkedList<>();

            ifValidStateAdd(fox, corn, goose, !man, states);
            if (man == fox) ifValidStateAdd(!fox, corn, goose, !man, states);
            if (man == goose) ifValidStateAdd(fox, corn, !goose, !man, states);
            if (man == corn) ifValidStateAdd(fox, !corn, goose, !man, states);
            return states;
        }

        @Override
        public String toString() {
            StringBuilder string = new StringBuilder();
            string.append(fox ? " " : "F");
            string.append(goose ? " " : "G");
            string.append(corn ? " " : "C");
            string.append(man ? " " : "M");
            string.append(" | ");
            string.append(!fox ? " " : "F");
            string.append(!goose ? " " : "G");
            string.append(!corn ? " " : "C");
            string.append(!man ? " " : "M");
            return string.toString();
        }

        private static void ifValidStateAdd(boolean fox, boolean corn, boolean goose, boolean man, List<RiverState> list) {
            RiverState tempState = new RiverState(fox, corn, goose, man);
            if (tempState.isStateValid()) list.add(tempState);
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof RiverState))
                return false;
            RiverState o = (RiverState) obj;
            return fox == o.fox && corn == o.corn && goose == o.goose && man == o.man;
        }

        @Override
        public int hashCode() {
            int num = 0;
            if (fox) num += 1;
            if (corn) num += 10;
            if (goose) num += 100;
            if (man) num += 1000;
            return num;
        }
    }

    public static void main(String[] args) {
        new RiverProblem();
    }
}
