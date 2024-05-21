package yuisanae2f.aml;

import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StatusRule {
    public static iStatusRule ruleGlobal = new iStatusRule() {
        @Override
        public List<String> i(rTags rule, rStatus field) {
            List<String> rtn = new ArrayList<>();

            double[] farr = field.field();
            String[] tarr = rule.tags();

            for (int i = 0; i < farr.length; i++) {
                rtn.add(tarr[i] + ": " + farr[i]);
            }

            return rtn;
        }

        @Override
        public rStatus o(rTags rule, List<String> src) {
            rStatus rtn = new rStatus(new double[eStatus.LMT.ordinal()]);

            for(int i = 0; i < src.size(); i++) {
                rtn.field()[i] =  Double.parseDouble(src.get(i).split(": ")[1]);
            }

            return rtn;
        }
    };
    public static rTags tagGlobal = new rTags(new String[] {
            "atkMin",
            "atkMax",
            "critRate",
            "critDmg",
            "armourPierce",
            "hitRate",
            "vampAmp",
            "vampDiv",
            "hp",
            "armour",
            "critReduce",
            "dmgReduce",
            "evasion",
            "absorb",
            "hpReg"
    });
}
