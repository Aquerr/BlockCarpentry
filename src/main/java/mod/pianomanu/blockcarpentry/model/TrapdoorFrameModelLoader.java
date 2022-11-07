package mod.pianomanu.blockcarpentry.model;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import net.minecraftforge.client.model.geometry.IGeometryLoader;

public class TrapdoorFrameModelLoader implements IGeometryLoader<TrapdoorFrameModelGeometry> {

    @Override
    public TrapdoorFrameModelGeometry read(JsonObject modelContents, JsonDeserializationContext deserializationContext) {
        return new TrapdoorFrameModelGeometry();
    }
}
