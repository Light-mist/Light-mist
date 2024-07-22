package org.xinghai.light_mist;

import java.io.File;
import java.io.IOException;

public class RunCommand {
    static boolean run_yes = false;
    public static Process run(File rundir, String command) throws IOException {
        String osName = System.getProperty("os.name").toLowerCase();
        String run_command = "";

        if (osName.contains("windows")) {
            run_command = "cmd /c " + command;
            run_yes = true;
        } else if (osName.contains("nix") || osName.contains("nux") || osName.contains("aix")) {
            run_command = "/bin/sh -c " + command;
            run_yes = true;
        }
        Process process = Runtime.getRuntime().exec(run_command, null, rundir);
        return process;
    }
}