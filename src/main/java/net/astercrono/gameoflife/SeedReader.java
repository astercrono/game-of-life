package net.astercrono.gameoflife;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class SeedReader {
    public List<List<Boolean>> read(String seedName) {
        try {
            InputStream in = getClass().getClassLoader().getResourceAsStream(seedName);
            BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
            List<List<Boolean>> stateLines = new ArrayList<>();
            String currentLine;

            while ((currentLine = br.readLine()) != null) {
                stateLines.add(lineToByteArray(currentLine));
            }

            return stateLines;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private List<Boolean> lineToByteArray(String line) {
        List<Boolean> bools = new ArrayList<>(line.length());

        for (int i = 0; i < line.length(); i++) {
            String s = String.valueOf(line.charAt(i));
            bools.add(s.equals("1"));
        }

        return bools;
    }
}
