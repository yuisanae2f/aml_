package yuisanae2f.aml;

import java.util.List;

public interface iStatusRule {
    List<String> i(rTags rule, rStatus field);
    rStatus o(rTags rule, List<String> src);
}