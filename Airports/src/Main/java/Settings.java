package Main.java;

import java.io.InputStream;
import java.util.Properties;

public class Settings {
    private final Properties prs = new Properties();

    public void LoadPrs(InputStream io){
        try {
            this.prs.load(io);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String GetValue(String key){
        return this.prs.getProperty(key, "2");
    }
}
