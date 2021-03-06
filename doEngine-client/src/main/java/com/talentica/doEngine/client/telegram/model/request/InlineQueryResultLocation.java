package com.talentica.doEngine.client.telegram.model.request;

/**
 * Stas Parshin
 * 06 May 2016
 */
public class InlineQueryResultLocation extends InlineQueryResult<InlineQueryResultLocation> {

    private float latitude;
    private float longitude;
    private String title;

    private String thumb_url;
    private Integer thumb_width;
    private Integer thumb_height;

    public InlineQueryResultLocation(String id, float latitude, float longitude, String title) {
        super("location", id);
        this.latitude = latitude;
        this.longitude = longitude;
        this.title = title;
    }

    public InlineQueryResultLocation thumbUrl(String thumbUrl) {
        this.thumb_url = thumbUrl;
        return this;
    }

    public InlineQueryResultLocation thumbWidth(Integer thumbWidth) {
        this.thumb_width = thumbWidth;
        return this;
    }

    public InlineQueryResultLocation thumbHeight(Integer thumbHeight) {
        this.thumb_height = thumbHeight;
        return this;
    }
}
