package com.metaverse.station.back.web.gathertownApi;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GatherTownMapResponseDto {
    private String mostRecentUpdateId;
    private List<Long> dimensions;
    private String foregroundImagePath;
    private List<Object> spaces;
    private String collisions;
    private Boolean isTemplate;
    private String backgroundImagePath;
    private Long objectSizes;
    private Boolean useDrawnBG;
    private List<Object> assets;
    private String id;
    private Boolean isPublic;
    private List<Object> announcer;
    private List<Object> spawns;
    private String version;
    private List<GatherTownObject> objects;
    private List<Object> portals;


    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    static class Space{
        public int y;
        public int x;
        public String spaceId;
        public Boolean colored;
    }

    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    static class Spawn
    {
        public int x;
        public int y;
    }

    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    static class GatherTownObject {
        public long y;
        public List<String> _tags;
        public String templateId;
        public int orientation;
        public int scale;
        public String color;
        public int width;
        public int height;
        public String previewMessage;
        public String highlighted;
        public long x;
        public String id;
        public String _name;
        public Properties properties;
        public int type;
        public String normal;
        public double offsetX;
        public double offsetY;
        public int distThreshold;
        public Sound sound;
        public int key;
        public String extensionClass;
        private Object entity;

        @Getter
        @Setter
        @JsonInclude(JsonInclude.Include.NON_NULL)
        static class Sound{
            public String src;
            public int maxDistance;
            public double volume;
            public Boolean loop;
        }

        @Getter
        @Setter
        @JsonInclude(JsonInclude.Include.NON_NULL)
        static class Properties
        {
            public String hash;
            public ExtensionData extensionData;
            public Boolean closed;

            @Getter
            @Setter
            @JsonInclude(JsonInclude.Include.NON_NULL)
            static class ExtensionData
            {
                public Images images;
                public List<Entry> entries;

                @Getter
                @Setter
                @JsonInclude(JsonInclude.Include.NON_NULL)
                static class Entry
                {
                    public String value;
                    public String type;
                    public String key;
                    public String requiredLevel;
                }
                @Getter
                @Setter
                @JsonInclude(JsonInclude.Include.NON_NULL)
                static class Images
                {
                    public String closedHighlighted;
                    public String openNormal;
                    public String openHighlighted;
                    public String closedNormal;
                }
            }
        }


    }


}

