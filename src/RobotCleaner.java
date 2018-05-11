import java.util.*;

public class RobotCleaner {
    public static void main(String[] args) {

    }

    // 1 - DOWN, 2 - LEFT, 3 - UP, 4 - RIGHT
    static int[][] Directions = {{1,0}, {0,-1}, {-1,0},{0,1}};

    static class Room {
        int CLEANED = 0;
        int DIRTY = 1;
        int OBSTACLE = 2;
        int ROBOT = 3;

        int[][] room;
        int[] robotAt;
        int pending;

        public Room(int[][] grid) {
            room = grid;

            // Count the pending cells to clean as well as mark the location of robot, initialize as dirty
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    if (grid[i][j] == DIRTY)
                        pending++;
                    else if (grid[i][j] == ROBOT) {
                        grid[i][j] = DIRTY;
                        robotAt = new int[]{i,j};
                    }
                }
            }
        }

        public boolean isCleaned() {
            return pending == 0;
        }

        /**
         * API for robot moving in  Room class
         * @param dir - value aligned with the direction in Direction
         * @return true - movable and move, false - obstacle or out of boundary and stay
         */
        public boolean moveRobot(int dir) {
            int m = room.length, n = room[0].length;

            int nextR = robotAt[0] + Directions[dir][0];
            int nextC = robotAt[1] + Directions[dir][1];

            if (nextR < 0 || nextR >= m || nextC < 0 || nextC >= n || room[nextR][nextC] == OBSTACLE)
                return false;

            robotAt[0] = nextR;
            robotAt[1] = nextC;
            return true;
        }

        public void clean() {
            if (room[robotAt[0]][robotAt[1]] == DIRTY) {
                room[robotAt[0]][robotAt[1]] = CLEANED;
                pending--;
            }
        }

        public int[] locateRobot() {
            return robotAt;
        }

        public void printRoom() {
            for (int i = 0; i < room.length; i++) {
                for (int j = 0; j < room[i].length; j++) {
                    System.out.print(room[i][j]);
                    System.out.print(" ");
                }
                System.out.print("\n");
            }
        }
    }

    static class Robot {
        private Room mRoom;

        // Store a local integer to mark the direction in which the robot is facing now, same as Directions
        // 0 = DOWN, 1 = LEFT, 2 = UP, 3 = RIGHT -> Direction is related to the room
        private int facing;
        public Robot(Room room) {
            mRoom = room;
            facing = 0;
        }

        public boolean move() {
            return mRoom.moveRobot(facing);
        }

        public boolean move(int toDirection) {
            facing = toDirection;
            return mRoom.moveRobot(facing);
        }

        public void turnLeft() {
            facing = (facing - 1) % 4;
        }

        public void turnRight() {
            facing = (facing + 1) % 4;
        }

        public void clean() {
            mRoom.clean();
        }

        int getFacing() {
            return facing;
        }
    }

    void robotClean(Robot robot) {
        Set<String> visited = new HashSet<>();

    }

    void robotCleanUtil(Robot robot, int r, int c, int toDirection, Set<String> visited) {
        robot.clean();
        visited.add("" + r + "," + c);
        
        return;
    }
}
