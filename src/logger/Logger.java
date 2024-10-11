package logger;

import java.io.FileWriter;
import java.io.IOException;

public class Logger {
    FileWriter recorder;

    public Logger(String filePath) throws IOException {
        recorder = new FileWriter(filePath);
    }

    public void recordAction(String action) throws IOException {
        recorder.write(action);
    }

    public void closeLogger() throws IOException {
        recorder.close();
    }
}