package mod.pianomanu.blockcarpentry.model;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import net.minecraftforge.client.model.geometry.IGeometryLoader;

public class ButtonPressedFrameModelLoader implements IGeometryLoader<ButtonPressedFrameModelGeometry> {

    @Override
    public ButtonPressedFrameModelGeometry read(JsonObject modelContents, JsonDeserializationContext deserializationContext) {
        return new ButtonPressedFrameModelGeometry();
    }
}
