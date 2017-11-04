package com.nengjun.avatar.face.task;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * Created by Henry on 16/7/12.
 */
@Slf4j
public abstract class FileTask extends AbstractTask {
    public void run() {
        BufferedReader reader = null;
        BufferedWriter writer = null;
        File inFile = new File(getInput());
        File outFile = new File(getOutput());
        log.info(inFile.getAbsolutePath());
        log.info(outFile.getAbsolutePath());
        try {
            reader = new BufferedReader(new FileReader(inFile));
            writer = new BufferedWriter(new FileWriter(outFile));

            String rs;
            String line = reader.readLine();
            log.info("Head line: {}", line);
            int valid = 0;
            int total = 0;
            while ((line = reader.readLine()) != null) {
                ++total;
                rs = processLine(line);
                if (rs == null) {
                    continue;
                }
                writer.write(rs + "\n");
                ++valid;
                if (valid % 100000 == 0) {
                    log.info("Handled line num: {}, valid: {}", total, valid);
                }
            }
            log.info("Handled line num: {}, valid: {}", total, valid);
        } catch (FileNotFoundException e) {
            log.error("File not found.", e);
        } catch (IOException e) {
            log.error("Input & Output error.", e);
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    log.error("Input & Output error.", e);
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    log.error("Input & Output error.", e);
                }
            }
        }
    }

    private String getOutput() {
        return getArgs()[2];
    }

    private String getInput() {
        return getArgs()[1];
    }

    protected abstract String processLine(String line);
}
