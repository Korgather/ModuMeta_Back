package com.metaverse.station.back.web.gathertownApi;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Getter
@Setter
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
    private List<Object> objects;
    private List<Object> portals;

    @Getter
    @Setter
    static class Space{
        public int y;
        public int x;
        public String spaceId;
        public Boolean colored;
    }

    @Getter
    @Setter
    static class Spawn
    {
        public int x;
        public int y;
    }

    @Getter
    @Setter
    static class GatherTownObject {
        public String distThreshold;
        public String id;
        public int offsetX;
        public String normal;
        public List<String> _tags;
        public String highlighted;
        public int width;
        public int x;
        public String _name;
        public int type;
        public String templateId;
        public Properties properties;
        public int orientation;
        public String color;
        public String previewMessage;
        public int scale;
        public int height;
        public int y;
        public int offsetY;

        @Getter
        @Setter
        static class Properties
        {
            public String hash;
            public ExtensionData extensionData;
            public Boolean closed;
            static class ExtensionData
            {
                public Images images;
                public List<Entry> entries;

                @Getter
                @Setter
                static class Entry
                {
                    public String value;
                    public String type;
                    public String key;
                    public String requiredLevel;
                }
                @Getter
                @Setter
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

