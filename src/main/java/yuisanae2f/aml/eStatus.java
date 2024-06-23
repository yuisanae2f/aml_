package yuisanae2f.aml;

public enum eStatus {
    ATK_DMG_MIN,
    ATK_DMG_MAX,
    CRIT_CHANCE,
    CRIT_PW,
    ARMOUR_PIERCE,
    HIT_RATE, // 명중률
    VAMP_AMP, // 흡혈 (분자)
    VAMP_DIV, // 흡혈 (분모)
    HP,       // 추가 HP
    ARMOUR,
    CRIT_REDUCE,
    DMG_REDUCE,
    EVASION,
    ABSORB, // 황금사과 먹으면 생기는 그거. (왜 ㅆㅂ)
    HP_REG, // 재생력 (5초마다 하라고 했습니다. ㄹㅇㅋㅋ, 구현 예정이었음)
    LMT // 끝 (여기까지 존재, 여기 있는 것들 개수임)
    
    // 설명받지 못함 (ㄹㅇㅋㅋ)
    // 그래서 플러그인에서 기본적으로 제공하는 실제 때
}
