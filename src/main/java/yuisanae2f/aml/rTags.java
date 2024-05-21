package yuisanae2f.aml;

public record rTags(
        String[] tags
        ) {
        public rTags(rTags r) {
                this(new String[r.tags.length]);

                for(int i = 0; i < r.tags.length; i++) {
                        this.tags[i] = new String(r.tags[i]);
                }
        }
}
