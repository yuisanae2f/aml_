package yuisanae2f.aml;

import java.io.*;

public class FStream {
    public static File dir;

    public static void save(String name) throws IOException {
        File file = new File(dir, name);
        file.createNewFile();

        FileWriter fwrite = new FileWriter(file);

        for(String str : StatusRule.tagGlobal.tags()) {
            fwrite.write(str + "\r\n");
        }

        fwrite.close();
    }

    public static rTags load(String name) throws IOException {
        rTags old = new rTags(StatusRule.tagGlobal);
        File file = new File(dir, name);

        if(file.createNewFile()) {
            save(name);
            return old;
        }

        BufferedReader fread = new BufferedReader(new FileReader(file));

        String str;

        for(int i = 0; (str = fread.readLine()) != null && i < eStatus.LMT.ordinal(); i++) {
            StatusRule.tagGlobal.tags()[i] = str;
        }

        fread.close();
        return old;
    }
}
