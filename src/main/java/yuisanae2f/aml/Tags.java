package yuisanae2f.aml;

import org.bukkit.Bukkit;

import java.util.Arrays;
import java.util.Objects;

public class Tags {
    public static String toString(eStatus e) {
        return StatusRule.tagGlobal.tags()[e.ordinal()];
    }

    public static eStatus enumise(String e) {
        String[] arr = StatusRule.tagGlobal.tags();

        int i = 0;
        for(; i < arr.length; i++) {
            if(Objects.equals(arr[i], e)) break;
        }

        if(i == arr.length) return null;
        return eStatus.values()[i];
    }

    public static void change(eStatus e, String _new) {
        StatusRule.tagGlobal.tags()[e.ordinal()] = _new;
    }
}