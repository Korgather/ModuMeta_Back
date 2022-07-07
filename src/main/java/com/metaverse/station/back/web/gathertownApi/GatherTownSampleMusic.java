package com.metaverse.station.back.web.gathertownApi;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GatherTownSampleMusic extends GatherTownMapResponseDto.GatherTownObject {

    private String _name = "Sound Emitter (Bar/Restaurant) Ambience";
    private String templateId = "SoundEmitterBarAmbience - 3u3pREPN_1Ymg7Cx-jS5f";
    private String objectPlacerId = "mXaETfP5wkMKYWBNQzlKOKVI8KE2";
    private int height = 3;

    private Properties properties;
    private double offsetY = 16;
    private double offsetX = 16;
    private int width = 2;
    private int type = 0;
    private long x = 0;
    private long y = 0;
    private String color = "#9badb7";
    private List<String> _tags = new ArrayList<>();
    private String id = "SoundEmitterBarAmbience - 3u3pREPN1Ymg7Cx-jS5f_71aded18-8e5e-41e6-b5ba-c7b00265533f";
    public Sound sound = new Sound();
    private String highlighted = "https://cdn.gather.town/storage.googleapis.com/gather-town.appspot.com/internal-dashboard/images/NPfLjUOZ89A2gGdTCHsL-";
    private String normal = "https://cdn.gather.town/storage.googleapis.com/gather-town.appspot.com/internal-dashboard/images/4uyTiJdT700i7__UEsgWL";
    private int orientation = 0;

}
