package com.nengjun.avatar.face.task;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Henry on 2016/11/13.
 */
@Data
@Slf4j
public abstract class LoadDataTask extends AbstractTask {
    private String fileName;
    private Integer fieldNum;

    protected abstract int handleSingleLine(String[] fields);

    @Override
    public void run() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(new File(getFileName())));
            int valid = 0;
            int total = 0;
            String line;
            while ((line = reader.readLine()) != null) {
                ++total;
                String[] fields = line.split("\\t");
                if (fields.length != getFieldNum()) {
                    continue;
                }
                valid += handleSingleLine(fields);
                if (valid % 10000 == 0) {
                    log.info("Handled line num {}, valid {}", total, valid);
                }
            }
            log.info("Handled line num {}, valid {}", total, valid);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
