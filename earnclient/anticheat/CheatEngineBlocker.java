package earnclient.anticheat;

import java.io.*;
import java.util.*;

public class CheatEngineBlocker extends TimerTask
{
    public CheatEngineBlocker() {
        final Timer timer = new Timer();
        timer.scheduleAtFixedRate(this, 1000L, 10000L);
    }
    
    @Override
    public void run() {
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(String.valueOf(System.getenv("windir")) + "/system32/" + "tasklist.exe");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        final Scanner scanner = new Scanner(process.getInputStream());
        while (scanner.hasNextLine()) {
            if (scanner.nextLine().contains("cheatengine")) {
                System.exit(0);
            }
        }
    }
}
