package yuisanae2f.aml;

import java.util.Arrays;
import java.util.List;

import static yuisanae2f.aml.StatusRule.tagGlobal;

public class cStatus_r {
    private rStatus field;

    public cStatus_r() {
        field = new rStatus(new double[eStatus.LMT.ordinal()]);
        Arrays.fill(field.field(), 0);
    }

    public cStatus_r(List<String> str) {
        field = StatusRule.ruleGlobal.o(tagGlobal, str);
    }

    public cStatus_r(rStatus r) {
        field = r;
    }

    public void addself(rStatus a) {
        for (int i = 0; i < eStatus.LMT.ordinal(); i++)
            field.field()[i] += a.field()[i];
    }

    public void addself(cStatus_r a) {
        addself(a.field);
    }

    public void amplify(double a) {
        for(int i = 0; i < field.field().length; i++) {
            field.field()[i] *= a;
        }
    }

    public double get(eStatus id) {
        return field.field()[id.ordinal()];
    }

    public double set(eStatus id, double v) {
        return field.field()[id.ordinal()] = v;
    }

    public List<String> lorise() {
        return StatusRule.ruleGlobal.i(tagGlobal, this.field);
    }
}