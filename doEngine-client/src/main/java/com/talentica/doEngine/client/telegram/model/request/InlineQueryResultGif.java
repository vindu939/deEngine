package com.talentica.doEngine.client.telegram.model.request;

/**
 * stas
 * 1/12/16.
 */
public class InlineQueryResultGif extends InlineQueryResult<InlineQueryResultGif> {

    private String gif_url;
    private String thumb_url;

    private Integer gif_width;
    private Integer gif_height;
    private String title;
    private String caption;

    public InlineQueryResultGif(String id, String gifUrl, String thumbUrl) {
        super("gif", id);
        this.gif_url = gifUrl;
        this.thumb_url = thumbUrl;
    }

    public InlineQueryResultGif gifWidth(Integer gifWidth) {
        this.gif_width = gifWidth;
        return this;
    }

    public InlineQueryResultGif gifHeight(Integer gifHeight) {
        this.gif_height = gifHeight;
        return this;
    }

    public InlineQueryResultGif title(String title) {
        this.title = title;
        return this;
    }

    public InlineQueryResultGif caption(String caption) {
        this.caption = caption;
        return this;
    }
}
