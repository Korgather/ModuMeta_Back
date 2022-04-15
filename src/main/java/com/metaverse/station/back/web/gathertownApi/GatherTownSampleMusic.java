package com.metaverse.station.back.web.gathertownApi;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class GatherTownSampleMusic {

    private String _name = "Sound Emitter (Bar/Restaurant) Ambience";
    private String templateId = "SoundEmitterBarAmbience - 3u3pREPN_1Ymg7Cx-jS5f";
    private String objectPlacerId = "mXaETfP5wkMKYWBNQzlKOKVI8KE2";
    private int height = 3;

    private Object properties = new Object();
    private int offsetY = 16;
    private int offsetX = 16;
    private int width = 2;
    private int type = 0;
    private int x = 0;
    private int y = 0;
    private String color = "#9badb7";
    private List<String> _tags = new ArrayList<>();
    private String id = "SoundEmitterBarAmbience - 3u3pREPN1Ymg7Cx-jS5f_71aded18-8e5e-41e6-b5ba-c7b00265533f";
    public Sound sound = new Sound();
    private String highlighted = "https://cdn.gather.town/storage.googleapis.com/gather-town.appspot.com/internal-dashboard/images/NPfLjUOZ89A2gGdTCHsL-";
    private String normal = "https://cdn.gather.town/storage.googleapis.com/gather-town.appspot.com/internal-dashboard/images/4uyTiJdT700i7__UEsgWL";
    private int orientation = 0;

    @Setter
    @Getter
    static class Sound
    {
        private int maxDistance;
        private String src;
        private double volume;
        private Boolean loop;
    }

}
