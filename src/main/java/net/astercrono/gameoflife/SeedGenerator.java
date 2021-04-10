package net.astercrono.gameoflife;

public class SeedGenerator {
    public static void main(String[] args) {
        int width = 200;
        int height = 130;

        StringBuilder seed = new StringBuilder();

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (Math.random() > 0.5) {
                    seed.append("0");
                } else {
                    seed.append("1");
                }
            }
            seed.append("\n");
        }

        System.out.println(seed);
    }
}