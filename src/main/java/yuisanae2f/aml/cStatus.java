package yuisanae2f.aml;

import static yuisanae2f.aml.Aml.rd;

public class cStatus extends cStatus_r  {
    private double _attack() {
        return rd.nextDouble(this.get(eStatus.ATK_DMG_MAX), this.get(eStatus.ATK_DMG_MIN));
    }

    public boolean is_crit(cStatus target) {
        // 클라이언트가 제안한 공식
        return rd.nextDouble() < this.get(eStatus.CRIT_CHANCE) + target.get(eStatus.CRIT_REDUCE);
    }

    public double crit_final(cStatus target) {
        return
                this.is_crit(target) ?
                        this.get(eStatus.CRIT_PW) :
                        0; // 이건가?
    }

    public double attack_final() {
        return _attack() + (_attack() * this.get(eStatus.CRIT_PW));
    }

    public double armour_final(cStatus target) {
        return target.attack_final() - (target.attack_final() * this.get(eStatus.ARMOUR) - target.get(eStatus.ARMOUR_PIERCE)); // go, dart
    }

    public double vamp_final() {
        return _attack() * this.get(eStatus.VAMP_AMP) / this.get(eStatus.VAMP_DIV);
    }

    public double inst_hp_reg_on_attack(double dmg) {
        return this.get(eStatus.ABSORB) * dmg;
    }
}