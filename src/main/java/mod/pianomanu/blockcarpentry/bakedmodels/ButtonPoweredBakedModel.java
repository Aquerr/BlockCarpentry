package mod.pianomanu.blockcarpentry.bakedmodels;

import mod.pianomanu.blockcarpentry.block.FrameBlock;
import mod.pianomanu.blockcarpentry.tileentity.FrameBlockTile;
import mod.pianomanu.blockcarpentry.util.BlockAppearanceHelper;
import mod.pianomanu.blockcarpentry.util.ModelHelper;
import mod.pianomanu.blockcarpentry.util.TextureHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.BlockModelShaper;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.WoodButtonBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraftforge.client.model.data.IDynamicBakedModel;
import net.minecraftforge.client.model.data.IModelData;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Contains all information for the block model
 * See {@link mod.pianomanu.blockcarpentry.util.ModelHelper} for more information
 *
 * @author PianoManu
 * @version 1.6 05/01/21
 */
public class ButtonPoweredBakedModel implements IDynamicBakedModel {
    public static final ResourceLocation TEXTURE = new ResourceLocation("minecraft", "block/oak_planks");

    private TextureAtlasSprite getTexture() {
        return Minecraft.getInstance().getTextureAtlas(TextureAtlas.LOCATION_BLOCKS).apply(TEXTURE);
    }

    @Nonnull
    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @Nonnull Random rand, @Nonnull IModelData extraData) {
        BlockState mimic = extraData.getData(FrameBlockTile.MIMIC);
        if (mimic != null && !(mimic.getBlock() instanceof FrameBlock)) {
            ModelResourceLocation location = BlockModelShaper.stateToModelLocation(mimic);
            if (location != null) {
                BakedModel model = Minecraft.getInstance().getModelManager().getModel(location);
                if (model != null) {
                    return getMimicQuads(state, side, rand, extraData, model);
                }
            }
        }
        return Collections.emptyList();
    }

    public List<BakedQuad> getMimicQuads(@Nullable BlockState state, @Nullable Direction side, @Nonnull Random rand, @Nonnull IModelData extraData, BakedModel model) {
        if (side != null) {
            return Collections.emptyList();
        }
        BlockState mimic = extraData.getData(FrameBlockTile.MIMIC);
        int tex = extraData.getData(FrameBlockTile.TEXTURE);
        if (mimic != null && state != null) {
            List<TextureAtlasSprite> textureList = TextureHelper.getTextureFromModel(model, extraData, rand);
            TextureAtlasSprite texture;
            if (textureList.size() <= tex) {
                extraData.setData(FrameBlockTile.TEXTURE, 0);
                tex = 0;
            }
            if (textureList.size() == 0) {
                if (Minecraft.getInstance().player != null) {
                    Minecraft.getInstance().player.displayClientMessage(new TranslatableComponent("message.blockcarpentry.block_not_available"), true);
                }
                return Collections.emptyList();
            }
            texture = textureList.get(tex);
            int tintIndex = BlockAppearanceHelper.setTintIndex(mimic);
            float yl = 0f;
            float yh = 1 / 16f;
            if (state.getValue(WoodButtonBlock.FACE).equals(AttachFace.CEILING)) {
                yl = 15 / 16f;
                yh = 1f;
            }
            List<BakedQuad> quads = new ArrayList<>();
            switch (state.getValue(WoodButtonBlock.FACE)) {
                case WALL:
                    switch (state.getValue(WoodButtonBlock.FACING)) {
                        case NORTH:
                            quads.addAll(mod.pianomanu.blockcarpentry.util.ModelHelper.createCuboid(5 / 16f, 11 / 16f, 6 / 16f, 10 / 16f, 15 / 16f, 1f, texture, tintIndex));
                            break;
                        case EAST:
                            quads.addAll(mod.pianomanu.blockcarpentry.util.ModelHelper.createCuboid(0f, 1 / 16f, 6 / 16f, 10 / 16f, 5 / 16f, 11 / 16f, texture, tintIndex));
                            break;
                        case WEST:
                            quads.addAll(mod.pianomanu.blockcarpentry.util.ModelHelper.createCuboid(15 / 16f, 1f, 6 / 16f, 10 / 16f, 5 / 16f, 11 / 16f, texture, tintIndex));
                            break;
                        case SOUTH:
                            quads.addAll(mod.pianomanu.blockcarpentry.util.ModelHelper.createCuboid(5 / 16f, 11 / 16f, 6 / 16f, 10 / 16f, 0f, 1 / 16f, texture, tintIndex));
                            break;
                    }
                    break;
                case FLOOR:
                case CEILING:
                    switch (state.getValue(WoodButtonBlock.FACING)) {
                        case EAST:
                        case WEST:
                            quads.addAll(mod.pianomanu.blockcarpentry.util.ModelHelper.createCuboid(6 / 16f, 10 / 16f, yl, yh, 5 / 16f, 11 / 16f, texture, tintIndex));
                            break;
                        case SOUTH:
                        case NORTH:
                            quads.addAll(mod.pianomanu.blockcarpentry.util.ModelHelper.createCuboid(5 / 16f, 11 / 16f, yl, yh, 6 / 16f, 10 / 16f, texture, tintIndex));
                            break;
                    }
            }
            int overlayIndex = extraData.getData(FrameBlockTile.OVERLAY);
            if (overlayIndex != 0) {
                switch (state.getValue(WoodButtonBlock.FACE)) {
                    case WALL:
                        switch (state.getValue(WoodButtonBlock.FACING)) {
                            case NORTH:
                                quads.addAll(mod.pianomanu.blockcarpentry.util.ModelHelper.createOverlay(5 / 16f, 11 / 16f, 6 / 16f, 10 / 16f, 15 / 16f, 1f, overlayIndex));
                                break;
                            case EAST:
                                quads.addAll(mod.pianomanu.blockcarpentry.util.ModelHelper.createOverlay(0f, 1 / 16f, 6 / 16f, 10 / 16f, 5 / 16f, 11 / 16f, overlayIndex));
                                break;
                            case WEST:
                                quads.addAll(mod.pianomanu.blockcarpentry.util.ModelHelper.createOverlay(15 / 16f, 1f, 6 / 16f, 10 / 16f, 5 / 16f, 11 / 16f, overlayIndex));
                                break;
                            case SOUTH:
                                quads.addAll(mod.pianomanu.blockcarpentry.util.ModelHelper.createOverlay(5 / 16f, 11 / 16f, 6 / 16f, 10 / 16f, 0f, 1 / 16f, overlayIndex));
                                break;
                        }
                        break;
                    case FLOOR:
                    case CEILING:
                        switch (state.getValue(WoodButtonBlock.FACING)) {
                            case EAST:
                            case WEST:
                                quads.addAll(ModelHelper.createOverlay(6 / 16f, 10 / 16f, yl, yh, 5 / 16f, 11 / 16f, overlayIndex));
                                break;
                            case SOUTH:
                            case NORTH:
                                quads.addAll(ModelHelper.createOverlay(5 / 16f, 11 / 16f, yl, yh, 6 / 16f, 10 / 16f, overlayIndex));
                                break;
                        }
                }
            }
            return quads;
        }
        return Collections.emptyList();
    }

    @Override
    public boolean useAmbientOcclusion() {
        return true;
    }

    @Override
    public boolean isGui3d() {
        return false;
    }

    @Override
    public boolean usesBlockLight() {
        return false;
    }

    @Override
    public boolean isCustomRenderer() {
        return false;
    }

    @Override
    public TextureAtlasSprite getParticleIcon() {
        return getTexture();
    }

    @Override
    public ItemOverrides getOverrides() {
        return ItemOverrides.EMPTY;
    }
}
//========SOLI DEO GLORIA========//