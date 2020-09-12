package mod.pianomanu.blockcarpentry.bakedmodels;

import mod.pianomanu.blockcarpentry.block.FrameBlock;
import mod.pianomanu.blockcarpentry.tileentity.FrameBlockTile;
import mod.pianomanu.blockcarpentry.util.ModelHelper;
import mod.pianomanu.blockcarpentry.util.TextureHelper;
import net.minecraft.block.BlockState;
import net.minecraft.block.GrassBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockModelShapes;
import net.minecraft.client.renderer.model.*;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
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
 * @author PianoManu
 * @version 1.0 09/08/20
 */
public class IllusionStairsBakedModel implements IDynamicBakedModel {
    @Nonnull
    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @Nonnull Random rand, @Nonnull IModelData extraData) {
        BlockState mimic = extraData.getData(FrameBlockTile.MIMIC);
        if (mimic != null && !(mimic.getBlock() instanceof FrameBlock)) {
            ModelResourceLocation location = BlockModelShapes.getModelLocation(mimic);
            if (location != null) {
                IBakedModel model = Minecraft.getInstance().getModelManager().getModel(location);
                if (model != null) {
                    return getIllusionQuads(state,side,rand,extraData,model);
                }
            }
        }
        return Collections.emptyList();
    }

    private List<BakedQuad> getIllusionQuads(@Nullable BlockState state, @Nullable Direction side, @Nonnull Random rand, @Nonnull IModelData extraData, IBakedModel model) {
        BlockState mimic = extraData.getData(FrameBlockTile.MIMIC);
        if (mimic!=null && state!=null) {
            List<TextureAtlasSprite> textureList = TextureHelper.getTextureListFromBlock(mimic.getBlock());
            TextureAtlasSprite texture;
            int tex = extraData.getData(FrameBlockTile.TEXTURE);
            if(textureList.size()>tex) {
                texture = textureList.get(tex);
            }
            else {
                texture = textureList.get(0);
            }
            int tintIndex = -1;
            if (mimic.getBlock() instanceof GrassBlock) {
                tintIndex = 1;
            }
            List<BakedQuad> quads = new ArrayList<>();
            switch (state.get(StairsBlock.HALF)) {
                case BOTTOM:
                    quads.addAll(ModelHelper.createSixFaceCuboid(0f,1f,0f,0.5f,0f,1f,mimic,model,extraData,rand,tintIndex));
                    switch (state.get(StairsBlock.SHAPE)) {
                        case STRAIGHT:
                            switch (state.get(StairsBlock.FACING)) {
                                case SOUTH:
                                    quads.addAll(ModelHelper.createSixFaceCuboid(0f,1f,0.5f,1f,0.5f,1f,mimic,model,extraData,rand,tintIndex));
                                    break;
                                case NORTH:
                                    quads.addAll(ModelHelper.createSixFaceCuboid(0f,1f,0.5f,1f,0f,0.5f,mimic,model,extraData,rand,tintIndex));
                                    break;
                                case WEST:
                                    quads.addAll(ModelHelper.createSixFaceCuboid(0f,0.5f,0.5f,1f,0f,1f,mimic,model,extraData,rand,tintIndex));
                                    break;
                                case EAST:
                                    quads.addAll(ModelHelper.createSixFaceCuboid(0.5f,1f,0.5f,1f,0f,1f,mimic,model,extraData,rand,tintIndex));
                                    break;
                            }
                            break;
                        case INNER_LEFT:
                            switch (state.get(StairsBlock.FACING)) {
                                case SOUTH:
                                    quads.addAll(ModelHelper.createSixFaceCuboid(0f,1f,0.5f,1f,0.5f,1f,mimic,model,extraData,rand,tintIndex));
                                    quads.addAll(ModelHelper.createSixFaceCuboid(0.5f,1f,0.5f,1f,0f,0.5f,mimic,model,extraData,rand,tintIndex));
                                    break;
                                case NORTH:
                                    quads.addAll(ModelHelper.createSixFaceCuboid(0f,1f,0.5f,1f,0f,0.5f,mimic,model,extraData,rand,tintIndex));
                                    quads.addAll(ModelHelper.createSixFaceCuboid(0f,0.5f,0.5f,1f,0.5f,1f,mimic,model,extraData,rand,tintIndex));
                                    break;
                                case WEST:
                                    quads.addAll(ModelHelper.createSixFaceCuboid(0f,0.5f,0.5f,1f,0f,1f,mimic,model,extraData,rand,tintIndex));
                                    quads.addAll(ModelHelper.createSixFaceCuboid(0.5f,1f,0.5f,1f,0.5f,1f,mimic,model,extraData,rand,tintIndex));
                                    break;
                                case EAST:
                                    quads.addAll(ModelHelper.createSixFaceCuboid(0.5f,1f,0.5f,1f,0f,1f,mimic,model,extraData,rand,tintIndex));
                                    quads.addAll(ModelHelper.createSixFaceCuboid(0f,0.5f,0.5f,1f,0f,0.5f,mimic,model,extraData,rand,tintIndex));
                                    break;
                            }
                            break;
                        case INNER_RIGHT:
                            switch (state.get(StairsBlock.FACING)) {
                                case SOUTH:
                                    quads.addAll(ModelHelper.createSixFaceCuboid(0f,1f,0.5f,1f,0.5f,1f,mimic,model,extraData,rand,tintIndex));
                                    quads.addAll(ModelHelper.createSixFaceCuboid(0f,0.5f,0.5f,1f,0f,0.5f,mimic,model,extraData,rand,tintIndex));
                                    break;
                                case NORTH:
                                    quads.addAll(ModelHelper.createSixFaceCuboid(0f,1f,0.5f,1f,0f,0.5f,mimic,model,extraData,rand,tintIndex));
                                    quads.addAll(ModelHelper.createSixFaceCuboid(0.5f,1f,0.5f,1f,0.5f,1f,mimic,model,extraData,rand,tintIndex));
                                    break;
                                case WEST:
                                    quads.addAll(ModelHelper.createSixFaceCuboid(0f,0.5f,0.5f,1f,0f,1f,mimic,model,extraData,rand,tintIndex));
                                    quads.addAll(ModelHelper.createSixFaceCuboid(0.5f,1f,0.5f,1f,0f,0.5f,mimic,model,extraData,rand,tintIndex));
                                    break;
                                case EAST:
                                    quads.addAll(ModelHelper.createSixFaceCuboid(0.5f,1f,0.5f,1f,0f,1f,mimic,model,extraData,rand,tintIndex));
                                    quads.addAll(ModelHelper.createSixFaceCuboid(0f,0.5f,0.5f,1f,0.5f,1f,mimic,model,extraData,rand,tintIndex));
                                    break;
                            }
                            break;
                        case OUTER_LEFT:
                            switch (state.get(StairsBlock.FACING)) {
                                case SOUTH:
                                    quads.addAll(ModelHelper.createSixFaceCuboid(0.5f,1f,0.5f,1f,0.5f,1f,mimic,model,extraData,rand,tintIndex));
                                    break;
                                case NORTH:
                                    quads.addAll(ModelHelper.createSixFaceCuboid(0f,0.5f,0.5f,1f,0f,0.5f,mimic,model,extraData,rand,tintIndex));
                                    break;
                                case WEST:
                                    quads.addAll(ModelHelper.createSixFaceCuboid(0f,0.5f,0.5f,1f,0.5f,1f,mimic,model,extraData,rand,tintIndex));
                                    break;
                                case EAST:
                                    quads.addAll(ModelHelper.createSixFaceCuboid(0.5f,1f,0.5f,1f,0f,0.5f,mimic,model,extraData,rand,tintIndex));
                                    break;
                            }
                            break;
                        case OUTER_RIGHT:
                            switch (state.get(StairsBlock.FACING)) {
                                case SOUTH:
                                    quads.addAll(ModelHelper.createSixFaceCuboid(0f,0.5f,0.5f,1f,0.5f,1f,mimic,model,extraData,rand,tintIndex));
                                    break;
                                case NORTH:
                                    quads.addAll(ModelHelper.createSixFaceCuboid(0.5f,1f,0.5f,1f,0f,0.5f,mimic,model,extraData,rand,tintIndex));
                                    break;
                                case WEST:
                                    quads.addAll(ModelHelper.createSixFaceCuboid(0f,0.5f,0.5f,1f,0f,0.5f,mimic,model,extraData,rand,tintIndex));
                                    break;
                                case EAST:
                                    quads.addAll(ModelHelper.createSixFaceCuboid(0.5f,1f,0.5f,1f,0.5f,1f,mimic,model,extraData,rand,tintIndex));
                                    break;
                            }
                            break;
                    }
                    break;
                case TOP:
                    quads.addAll(ModelHelper.createSixFaceCuboid(0f,1f,0.5f,1f,0f,1f,mimic,model,extraData,rand,tintIndex));
                    switch (state.get(StairsBlock.SHAPE)) {
                        case STRAIGHT:
                            switch (state.get(StairsBlock.FACING)) {
                                case SOUTH:
                                    quads.addAll(ModelHelper.createSixFaceCuboid(0f,1f,0f,0.5f,0.5f,1f,mimic,model,extraData,rand,tintIndex));
                                    break;
                                case NORTH:
                                    quads.addAll(ModelHelper.createSixFaceCuboid(0f,1f,0f,0.5f,0f,0.5f,mimic,model,extraData,rand,tintIndex));
                                    break;
                                case WEST:
                                    quads.addAll(ModelHelper.createSixFaceCuboid(0f,0.5f,0f,0.5f,0f,1f,mimic,model,extraData,rand,tintIndex));
                                    break;
                                case EAST:
                                    quads.addAll(ModelHelper.createSixFaceCuboid(0.5f,1f,0f,0.5f,0f,1f,mimic,model,extraData,rand,tintIndex));
                                    break;
                            }
                            break;
                        case INNER_LEFT:
                            switch (state.get(StairsBlock.FACING)) {
                                case SOUTH:
                                    quads.addAll(ModelHelper.createSixFaceCuboid(0f,1f,0f,0.5f,0.5f,1f,mimic,model,extraData,rand,tintIndex));
                                    quads.addAll(ModelHelper.createSixFaceCuboid(0.5f,1f,0f,0.5f,0f,0.5f,mimic,model,extraData,rand,tintIndex));
                                    break;
                                case NORTH:
                                    quads.addAll(ModelHelper.createSixFaceCuboid(0f,1f,0f,0.5f,0f,0.5f,mimic,model,extraData,rand,tintIndex));
                                    quads.addAll(ModelHelper.createSixFaceCuboid(0f,0.5f,0f,0.5f,0.5f,1f,mimic,model,extraData,rand,tintIndex));
                                    break;
                                case WEST:
                                    quads.addAll(ModelHelper.createSixFaceCuboid(0f,0.5f,0f,0.5f,0f,1f,mimic,model,extraData,rand,tintIndex));
                                    quads.addAll(ModelHelper.createSixFaceCuboid(0.5f,1f,0f,0.5f,0.5f,1f,mimic,model,extraData,rand,tintIndex));
                                    break;
                                case EAST:
                                    quads.addAll(ModelHelper.createSixFaceCuboid(0.5f,1f,0f,0.5f,0f,1f,mimic,model,extraData,rand,tintIndex));
                                    quads.addAll(ModelHelper.createSixFaceCuboid(0f,0.5f,0f,0.5f,0f,0.5f,mimic,model,extraData,rand,tintIndex));
                                    break;
                            }
                            break;
                        case INNER_RIGHT:
                            switch (state.get(StairsBlock.FACING)) {
                                case SOUTH:
                                    quads.addAll(ModelHelper.createSixFaceCuboid(0f,1f,0f,0.5f,0.5f,1f,mimic,model,extraData,rand,tintIndex));
                                    quads.addAll(ModelHelper.createSixFaceCuboid(0f,0.5f,0f,0.5f,0f,0.5f,mimic,model,extraData,rand,tintIndex));
                                    break;
                                case NORTH:
                                    quads.addAll(ModelHelper.createSixFaceCuboid(0f,1f,0f,0.5f,0f,0.5f,mimic,model,extraData,rand,tintIndex));
                                    quads.addAll(ModelHelper.createSixFaceCuboid(0.5f,1f,0f,0.5f,0.5f,1f,mimic,model,extraData,rand,tintIndex));
                                    break;
                                case WEST:
                                    quads.addAll(ModelHelper.createSixFaceCuboid(0f,0.5f,0f,0.5f,0f,1f,mimic,model,extraData,rand,tintIndex));
                                    quads.addAll(ModelHelper.createSixFaceCuboid(0.5f,1f,0f,0.5f,0f,0.5f,mimic,model,extraData,rand,tintIndex));
                                    break;
                                case EAST:
                                    quads.addAll(ModelHelper.createSixFaceCuboid(0.5f,1f,0f,0.5f,0f,1f,mimic,model,extraData,rand,tintIndex));
                                    quads.addAll(ModelHelper.createSixFaceCuboid(0f,0.5f,0f,0.5f,0.5f,1f,mimic,model,extraData,rand,tintIndex));
                                    break;
                            }
                            break;
                        case OUTER_LEFT:
                            switch (state.get(StairsBlock.FACING)) {
                                case SOUTH:
                                    quads.addAll(ModelHelper.createSixFaceCuboid(0.5f,1f,0f,0.5f,0.5f,1f,mimic,model,extraData,rand,tintIndex));
                                    break;
                                case NORTH:
                                    quads.addAll(ModelHelper.createSixFaceCuboid(0f,0.5f,0f,0.5f,0f,0.5f,mimic,model,extraData,rand,tintIndex));
                                    break;
                                case WEST:
                                    quads.addAll(ModelHelper.createSixFaceCuboid(0f,0.5f,0f,0.5f,0.5f,1f,mimic,model,extraData,rand,tintIndex));
                                    break;
                                case EAST:
                                    quads.addAll(ModelHelper.createSixFaceCuboid(0.5f,1f,0f,0.5f,0f,0.5f,mimic,model,extraData,rand,tintIndex));
                                    break;
                            }
                            break;
                        case OUTER_RIGHT:
                            switch (state.get(StairsBlock.FACING)) {
                                case SOUTH:
                                    quads.addAll(ModelHelper.createSixFaceCuboid(0f,0.5f,0f,0.5f,0.5f,1f,mimic,model,extraData,rand,tintIndex));
                                    break;
                                case NORTH:
                                    quads.addAll(ModelHelper.createSixFaceCuboid(0.5f,1f,0f,0.5f,0f,0.5f,mimic,model,extraData,rand,tintIndex));
                                    break;
                                case WEST:
                                    quads.addAll(ModelHelper.createSixFaceCuboid(0f,0.5f,0f,0.5f,0f,0.5f,mimic,model,extraData,rand,tintIndex));
                                    break;
                                case EAST:
                                    quads.addAll(ModelHelper.createSixFaceCuboid(0.5f,1f,0f,0.5f,0.5f,1f,mimic,model,extraData,rand,tintIndex));
                                    break;
                            }
                            break;
                    }
                    break;
            }
            return quads;
            //return new ArrayList<>(ModelHelper.createCuboid(0f, 1f, 0f, 0.5f, 0f, 1f, texture, tintIndex));
        }
        return Collections.emptyList();
    }

    @Override
    public boolean isAmbientOcclusion() {
        return true;
    }

    @Override
    public boolean isGui3d() {
        return false;
    }

    @Override
    public boolean func_230044_c_() {
        return false;
    }

    @Override
    public boolean isBuiltInRenderer() {
        return false;
    }

    @Override
    public TextureAtlasSprite getParticleTexture() {
        return Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(new ResourceLocation("minecraft", "block/oak_planks"));
    }

    @Override
    public ItemOverrideList getOverrides() {
        return ItemOverrideList.EMPTY;
    }

    @Override
    public ItemCameraTransforms getItemCameraTransforms() {
        return ItemCameraTransforms.DEFAULT;
    }
}
//========SOLI DEO GLORIA========//