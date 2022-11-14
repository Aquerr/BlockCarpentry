package mod.pianomanu.blockcarpentry.bakedmodels;

import mod.pianomanu.blockcarpentry.block.TrapdoorFrameBlock;
import mod.pianomanu.blockcarpentry.tileentity.FrameBlockTile;
import mod.pianomanu.blockcarpentry.util.BlockAppearanceHelper;
import mod.pianomanu.blockcarpentry.util.ModelHelper;
import mod.pianomanu.blockcarpentry.util.TextureHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockModelShaper;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraftforge.client.ChunkRenderTypeSet;
import net.minecraftforge.client.model.IDynamicBakedModel;
import net.minecraftforge.client.model.data.ModelData;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Contains all information for the block model
 * See {@link mod.pianomanu.blockcarpentry.util.ModelHelper} for more information
 *
 * @author PianoManu
 * @version 1.4 11/14/22
 */
public class IllusionTrapdoorBakedModel implements IDynamicBakedModel {
    @Nonnull
    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @Nonnull RandomSource rand, @Nonnull ModelData extraData, RenderType renderType) {
        BlockState mimic = extraData.get(FrameBlockTile.MIMIC);
        if (mimic != null) {
            ModelResourceLocation location = BlockModelShaper.stateToModelLocation(mimic);
            BakedModel model = Minecraft.getInstance().getModelManager().getModel(location);
            return getIllusionQuads(state, side, rand, extraData, model);
        }
        return Collections.emptyList();
    }

    private List<BakedQuad> getIllusionQuads(@Nullable BlockState state, @Nullable Direction side, @Nonnull RandomSource rand, @Nonnull ModelData extraData, BakedModel model) {
        if (side != null) {
            return Collections.emptyList();
        }
        BlockState mimic = extraData.get(FrameBlockTile.MIMIC);
        if (mimic != null && state != null) {
            //get texture from block in tile entity and apply it to the quads
            List<TextureAtlasSprite> glassBlockList = TextureHelper.getGlassTextures();
            TextureAtlasSprite glass = glassBlockList.get(extraData.get(FrameBlockTile.GLASS_COLOR));
            int tintIndex = BlockAppearanceHelper.setTintIndex(mimic);
            int rotation = extraData.get(FrameBlockTile.ROTATION);
            List<BakedQuad> quads = new ArrayList<>();
            Direction dir = state.getValue(DoorBlock.FACING);
            boolean open = state.getValue(TrapdoorFrameBlock.OPEN);
            Half half = state.getValue(TrapDoorBlock.HALF);
            Half top = Half.TOP;
            Half bottom = Half.BOTTOM;
            Direction west = Direction.WEST;
            Direction east = Direction.EAST;
            Direction north = Direction.NORTH;
            Direction south = Direction.SOUTH;
            int design = extraData.get(FrameBlockTile.DESIGN);//int design = state.getValue(DoorFrameBlock.DESIGN);
            int desTex = extraData.get(FrameBlockTile.DESIGN_TEXTURE); //state.getValue(DoorFrameBlock.DESIGN_TEXTURE);

            if (design == 0) {
                if (dir == north && open) {
                    quads.addAll(ModelHelper.createSixFaceCuboid(0f, 1f, 0f, 1f, 13 / 16f, 1f, mimic, model, extraData, rand, tintIndex, rotation));
                } else if (dir == west && open) {
                    quads.addAll(ModelHelper.createSixFaceCuboid(13 / 16f, 1f, 0f, 1f, 0f, 1f, mimic, model, extraData, rand, tintIndex, rotation));
                } else if (dir == east && open) {
                    quads.addAll(ModelHelper.createSixFaceCuboid(0f, 3 / 16f, 0f, 1f, 0f, 1f, mimic, model, extraData, rand, tintIndex, rotation));
                } else if (dir == south && open) {
                    quads.addAll(ModelHelper.createSixFaceCuboid(0f, 1f, 0f, 1f, 0f, 3 / 16f, mimic, model, extraData, rand, tintIndex, rotation));
                } else if (half == bottom) {
                    quads.addAll(ModelHelper.createSixFaceCuboid(0f, 1f, 0f, 3 / 16f, 0f, 1f, mimic, model, extraData, rand, tintIndex, rotation));
                } else if (half == top) {
                    quads.addAll(ModelHelper.createSixFaceCuboid(0f, 1f, 13 / 16f, 1f, 0f, 1f, mimic, model, extraData, rand, tintIndex, rotation));
                }
            }
            if (design == 1 || design == 2 || design == 3) {
                if (dir == north && open) {
                    //quads.addAll(ModelHelper.createSixFaceCuboid(0f, 1f, 0f, 1f, 13 / 16f, 1f, mimic,model,extraData,rand, tintIndex, rotation));
                    quads.addAll(ModelHelper.createSixFaceCuboid(0f, 3 / 16f, 3 / 16f, 13 / 16f, 13 / 16f, 1f, mimic, model, extraData, rand, tintIndex, rotation));
                    quads.addAll(ModelHelper.createSixFaceCuboid(0f, 1f, 0f, 3 / 16f, 13 / 16f, 1f, mimic, model, extraData, rand, tintIndex, rotation));
                    quads.addAll(ModelHelper.createSixFaceCuboid(13 / 16f, 1f, 3 / 16f, 13 / 16f, 13 / 16f, 1f, mimic, model, extraData, rand, tintIndex, rotation));
                    quads.addAll(ModelHelper.createSixFaceCuboid(0f, 1f, 13 / 16f, 1f, 13 / 16f, 1f, mimic, model, extraData, rand, tintIndex, rotation));
                    quads.addAll(ModelHelper.createCuboid(3 / 16f, 13 / 16f, 3 / 16f, 13 / 16f, 14 / 16f, 15 / 16f, glass, -1));
                } else if (dir == west && open) {
                    //quads.addAll(ModelHelper.createSixFaceCuboid(13 / 16f, 1f, 0f, 1f, 0f, 1f, mimic,model,extraData,rand, tintIndex, rotation));
                    quads.addAll(ModelHelper.createSixFaceCuboid(13 / 16f, 1f, 0f, 1f, 13 / 16f, 1f, mimic, model, extraData, rand, tintIndex, rotation));
                    quads.addAll(ModelHelper.createSixFaceCuboid(13 / 16f, 1f, 0f, 1f, 0f, 3 / 16f, mimic, model, extraData, rand, tintIndex, rotation));
                    quads.addAll(ModelHelper.createSixFaceCuboid(13 / 16f, 1f, 13 / 16f, 1f, 3 / 16f, 13 / 16f, mimic, model, extraData, rand, tintIndex, rotation));
                    quads.addAll(ModelHelper.createSixFaceCuboid(13 / 16f, 1f, 0f, 3 / 16f, 3 / 16f, 13 / 16f, mimic, model, extraData, rand, tintIndex, rotation));
                    quads.addAll(ModelHelper.createCuboid(14 / 16f, 15 / 16f, 3 / 16f, 13 / 16f, 3 / 16f, 13 / 16f, glass, -1));
                } else if (dir == east && open) {
                    //quads.addAll(ModelHelper.createSixFaceCuboid(0f, 3 / 16f, 0f, 1f, 0f, 1f, mimic,model,extraData,rand, tintIndex, rotation));
                    quads.addAll(ModelHelper.createSixFaceCuboid(0f, 3 / 16f, 3 / 16f, 13 / 16f, 13 / 16f, 1f, mimic, model, extraData, rand, tintIndex, rotation));
                    quads.addAll(ModelHelper.createSixFaceCuboid(0f, 3 / 16f, 13 / 16f, 1f, 0f, 1f, mimic, model, extraData, rand, tintIndex, rotation));
                    quads.addAll(ModelHelper.createSixFaceCuboid(0f, 3 / 16f, 3 / 16f, 13 / 16f, 0f, 3 / 16f, mimic, model, extraData, rand, tintIndex, rotation));
                    quads.addAll(ModelHelper.createSixFaceCuboid(0f, 3 / 16f, 0f, 3 / 16f, 0f, 1f, mimic, model, extraData, rand, tintIndex, rotation));
                    quads.addAll(ModelHelper.createCuboid(1 / 16f, 2 / 16f, 3 / 16f, 13 / 16f, 3 / 16f, 13 / 16f, glass, -1));
                } else if (dir == south && open) {
                    //quads.addAll(ModelHelper.createSixFaceCuboid(0f, 1f, 0f, 1f, 0f, 3 / 16f, mimic,model,extraData,rand, tintIndex, rotation));
                    quads.addAll(ModelHelper.createSixFaceCuboid(0f, 3 / 16f, 3 / 16f, 13 / 16f, 0f, 3 / 16f, mimic, model, extraData, rand, tintIndex, rotation));
                    quads.addAll(ModelHelper.createSixFaceCuboid(0f, 1f, 0f, 3 / 16f, 0f, 3 / 16f, mimic, model, extraData, rand, tintIndex, rotation));
                    quads.addAll(ModelHelper.createSixFaceCuboid(13 / 16f, 1f, 3 / 16f, 13 / 16f, 0f, 3 / 16f, mimic, model, extraData, rand, tintIndex, rotation));
                    quads.addAll(ModelHelper.createSixFaceCuboid(0f, 1f, 13 / 16f, 1f, 0f, 3 / 16f, mimic, model, extraData, rand, tintIndex, rotation));
                    quads.addAll(ModelHelper.createCuboid(3 / 16f, 13 / 16f, 3 / 16f, 13 / 16f, 1 / 16f, 2 / 16f, glass, -1));
                } else if (half == bottom) {
                    //quads.addAll(ModelHelper.createSixFaceCuboid(0f, 1f, 0f, 3 / 16f, 0f, 1f, mimic,model,extraData,rand, tintIndex, rotation));
                    quads.addAll(ModelHelper.createSixFaceCuboid(0f, 1f, 0f, 3 / 16f, 13 / 16f, 1f, mimic, model, extraData, rand, tintIndex, rotation));
                    quads.addAll(ModelHelper.createSixFaceCuboid(0f, 3 / 16f, 0f, 3 / 16f, 3 / 16f, 13 / 16f, mimic, model, extraData, rand, tintIndex, rotation));
                    quads.addAll(ModelHelper.createSixFaceCuboid(0f, 1f, 0f, 3 / 16f, 0f, 3 / 16f, mimic, model, extraData, rand, tintIndex, rotation));
                    quads.addAll(ModelHelper.createSixFaceCuboid(13 / 16f, 1f, 0f, 3 / 16f, 3 / 16f, 13 / 16f, mimic, model, extraData, rand, tintIndex, rotation));
                    quads.addAll(ModelHelper.createCuboid(3 / 16f, 13 / 16f, 1 / 16f, 2 / 16f, 3 / 16f, 13 / 16f, glass, -1));
                } else if (half == top) {
                    //quads.addAll(ModelHelper.createSixFaceCuboid(0f, 1f, 13 / 16f, 1f, 0f, 1f, mimic,model,extraData,rand, tintIndex, rotation));
                    quads.addAll(ModelHelper.createSixFaceCuboid(0f, 1f, 13 / 16f, 1f, 13 / 16f, 1f, mimic, model, extraData, rand, tintIndex, rotation));
                    quads.addAll(ModelHelper.createSixFaceCuboid(0f, 3 / 16f, 13 / 16f, 1f, 3 / 16f, 13 / 16f, mimic, model, extraData, rand, tintIndex, rotation));
                    quads.addAll(ModelHelper.createSixFaceCuboid(0f, 1f, 13 / 16f, 1f, 0f, 3 / 16f, mimic, model, extraData, rand, tintIndex, rotation));
                    quads.addAll(ModelHelper.createSixFaceCuboid(13 / 16f, 1f, 13 / 16f, 1f, 3 / 16f, 13 / 16f, mimic, model, extraData, rand, tintIndex, rotation));
                    quads.addAll(ModelHelper.createCuboid(3 / 16f, 13 / 16f, 14 / 16f, 15 / 16f, 3 / 16f, 13 / 16f, glass, -1));
                }
                if (design == 2) {
                    if (dir == north && open) {
                        //quads.addAll(ModelHelper.createSixFaceCuboid(0f, 1f, 0f, 1f, 13 / 16f, 1f, mimic,model,extraData,rand, tintIndex, rotation));
                        quads.addAll(ModelHelper.createSixFaceCuboid(7 / 16f, 9 / 16f, 3 / 16f, 13 / 16f, 13 / 16f, 1f, mimic, model, extraData, rand, tintIndex, rotation));
                        quads.addAll(ModelHelper.createSixFaceCuboid(3 / 16f, 13 / 16f, 7 / 16f, 9 / 16f, 13 / 16f, 1f, mimic, model, extraData, rand, tintIndex, rotation));
                    } else if (dir == west && open) {
                        //quads.addAll(ModelHelper.createSixFaceCuboid(13 / 16f, 1f, 0f, 1f, 0f, 1f, mimic,model,extraData,rand, tintIndex, rotation));
                        quads.addAll(ModelHelper.createSixFaceCuboid(13 / 16f, 1f, 3 / 16f, 13 / 16f, 7 / 16f, 9 / 16f, mimic, model, extraData, rand, tintIndex, rotation));
                        quads.addAll(ModelHelper.createSixFaceCuboid(13 / 16f, 1f, 7 / 16f, 9 / 16f, 3 / 16f, 13 / 16f, mimic, model, extraData, rand, tintIndex, rotation));
                    } else if (dir == east && open) {
                        //quads.addAll(ModelHelper.createSixFaceCuboid(0f, 3 / 16f, 0f, 1f, 0f, 1f, mimic,model,extraData,rand, tintIndex, rotation));
                        quads.addAll(ModelHelper.createSixFaceCuboid(0f, 3 / 16f, 3 / 16f, 13 / 16f, 7 / 16f, 9 / 16f, mimic, model, extraData, rand, tintIndex, rotation));
                        quads.addAll(ModelHelper.createSixFaceCuboid(0f, 3 / 16f, 7 / 16f, 9 / 16f, 3 / 16f, 13 / 16f, mimic, model, extraData, rand, tintIndex, rotation));
                    } else if (dir == south && open) {
                        //quads.addAll(ModelHelper.createSixFaceCuboid(0f, 1f, 0f, 1f, 0f, 3 / 16f, mimic,model,extraData,rand, tintIndex, rotation));
                        quads.addAll(ModelHelper.createSixFaceCuboid(7 / 16f, 9 / 16f, 3 / 16f, 13 / 16f, 0f, 3 / 16f, mimic, model, extraData, rand, tintIndex, rotation));
                        quads.addAll(ModelHelper.createSixFaceCuboid(3 / 16f, 13 / 16f, 7 / 16f, 9 / 16f, 0f, 3 / 16f, mimic, model, extraData, rand, tintIndex, rotation));
                    } else if (half == bottom) {
                        //quads.addAll(ModelHelper.createSixFaceCuboid(0f, 1f, 0f, 3 / 16f, 0f, 1f, mimic,model,extraData,rand, tintIndex, rotation));
                        quads.addAll(ModelHelper.createSixFaceCuboid(7 / 16f, 9 / 16f, 0f, 3 / 16f, 3 / 16f, 13 / 16f, mimic, model, extraData, rand, tintIndex, rotation));
                        quads.addAll(ModelHelper.createSixFaceCuboid(3 / 16f, 13 / 16f, 0f, 3 / 16f, 7 / 16f, 9 / 16f, mimic, model, extraData, rand, tintIndex, rotation));
                    } else if (half == top) {
                        //quads.addAll(ModelHelper.createSixFaceCuboid(0f, 1f, 13 / 16f, 1f, 0f, 1f, mimic,model,extraData,rand, tintIndex, rotation));
                        quads.addAll(ModelHelper.createSixFaceCuboid(7 / 16f, 9 / 16f, 13 / 16f, 1f, 3 / 16f, 13 / 16f, mimic, model, extraData, rand, tintIndex, rotation));
                        quads.addAll(ModelHelper.createSixFaceCuboid(3 / 16f, 13 / 16f, 13 / 16f, 1f, 7 / 16f, 9 / 16f, mimic, model, extraData, rand, tintIndex, rotation));
                    }
                }
                if (design == 3) {
                    if (dir == north && open) {
                        //quads.addAll(ModelHelper.createSixFaceCuboid(0f, 1f, 0f, 1f, 13 / 16f, 1f, mimic,model,extraData,rand, tintIndex, rotation));
                        quads.addAll(ModelHelper.createSixFaceCuboid(3 / 16f, 13 / 16f, 3 / 16f, 13 / 16f, 14 / 16f, 15 / 16f, mimic, model, extraData, rand, tintIndex, rotation));
                    } else if (dir == west && open) {
                        //quads.addAll(ModelHelper.createSixFaceCuboid(13 / 16f, 1f, 0f, 1f, 0f, 1f, mimic,model,extraData,rand, tintIndex, rotation));
                        quads.addAll(ModelHelper.createSixFaceCuboid(14 / 16f, 15 / 16f, 3 / 16f, 13 / 16f, 3 / 16f, 13 / 16f, mimic, model, extraData, rand, tintIndex, rotation));
                    } else if (dir == east && open) {
                        //quads.addAll(ModelHelper.createSixFaceCuboid(0f, 3 / 16f, 0f, 1f, 0f, 1f, mimic,model,extraData,rand, tintIndex, rotation));
                        quads.addAll(ModelHelper.createSixFaceCuboid(1 / 16f, 2 / 16f, 3 / 16f, 13 / 16f, 3 / 16f, 13 / 16f, mimic, model, extraData, rand, tintIndex, rotation));
                    } else if (dir == south && open) {
                        //quads.addAll(ModelHelper.createSixFaceCuboid(0f, 1f, 0f, 1f, 0f, 3 / 16f, mimic,model,extraData,rand, tintIndex, rotation));
                        quads.addAll(ModelHelper.createSixFaceCuboid(3 / 16f, 13 / 16f, 3 / 16f, 13 / 16f, 1 / 16f, 2 / 16f, mimic, model, extraData, rand, tintIndex, rotation));
                    } else if (half == bottom) {
                        //quads.addAll(ModelHelper.createSixFaceCuboid(0f, 1f, 0f, 3 / 16f, 0f, 1f, mimic,model,extraData,rand, tintIndex, rotation));
                        quads.addAll(ModelHelper.createSixFaceCuboid(3 / 16f, 13 / 16f, 1 / 16f, 2 / 16f, 3 / 16f, 13 / 16f, mimic, model, extraData, rand, tintIndex, rotation));
                    } else if (half == top) {
                        //quads.addAll(ModelHelper.createSixFaceCuboid(0f, 1f, 13 / 16f, 1f, 0f, 1f, mimic,model,extraData,rand, tintIndex, rotation));
                        quads.addAll(ModelHelper.createSixFaceCuboid(3 / 16f, 13 / 16f, 14 / 16f, 15 / 16f, 3 / 16f, 13 / 16f, mimic, model, extraData, rand, tintIndex, rotation));
                    }
                }
            }
            if (design == 4) {
                if (!open) {
                    int yOffset = half == bottom ? 0 : 13;
                    boolean upVisible, downVisible, nVisible, eVisible, sVisible, wVisible;
                    boolean xStripe, zStripe;
                    for (int x = 0; x < 16; x++) {
                        for (int y = 0; y < 3; y++) {
                            for (int z = 0; z < 16; z++) {
                                upVisible = y == 2;
                                downVisible = y == 0;
                                xStripe = x % 3 == 0;
                                zStripe = z % 3 == 0;
                                nVisible = (zStripe && !xStripe) || z == 0;
                                sVisible = (zStripe && !xStripe) || z == 15;
                                eVisible = (xStripe && !zStripe) || x == 15;
                                wVisible = (xStripe && !zStripe) || x == 0;
                                if (xStripe || zStripe)
                                    quads.addAll(ModelHelper.createSixFaceCuboid(x / 16f, (x + 1) / 16f, (y + yOffset) / 16f, (y + yOffset + 1) / 16f, z / 16f, (z + 1) / 16f, mimic, model, extraData, rand, tintIndex, nVisible, sVisible, eVisible, wVisible, upVisible, downVisible, rotation));
                            }
                        }
                    }
                } else {
                    if (dir == west || dir == east) {
                        int xOffset = dir == east ? 0 : 13;
                        boolean upVisible, downVisible, nVisible, eVisible, sVisible, wVisible;
                        boolean yStripe, zStripe;
                        for (int x = 0; x < 3; x++) {
                            for (int y = 0; y < 16; y++) {
                                for (int z = 0; z < 16; z++) {
                                    wVisible = x == 0;
                                    eVisible = x == 2;
                                    yStripe = y % 3 == 0;
                                    zStripe = z % 3 == 0;
                                    nVisible = (zStripe && !yStripe) || z == 0;
                                    sVisible = (zStripe && !yStripe) || z == 15;
                                    upVisible = (yStripe && !zStripe) || y == 15;
                                    downVisible = (yStripe && !zStripe) || y == 0;
                                    if (yStripe || zStripe)
                                        quads.addAll(ModelHelper.createSixFaceCuboid((x + xOffset) / 16f, (x + xOffset + 1) / 16f, y / 16f, (y + 1) / 16f, z / 16f, (z + 1) / 16f, mimic, model, extraData, rand, tintIndex, nVisible, sVisible, eVisible, wVisible, upVisible, downVisible, rotation));
                                }
                            }
                        }
                    } else {
                        int zOffset = dir == south ? 0 : 13;
                        boolean upVisible, downVisible, nVisible, eVisible, sVisible, wVisible;
                        boolean yStripe, xStripe;
                        for (int x = 0; x < 16; x++) {
                            for (int y = 0; y < 16; y++) {
                                for (int z = 0; z < 3; z++) {
                                    nVisible = z == 0;
                                    sVisible = z == 2;
                                    yStripe = y % 3 == 0;
                                    xStripe = x % 3 == 0;
                                    wVisible = (xStripe && !yStripe) || x == 0;
                                    eVisible = (xStripe && !yStripe) || x == 15;
                                    upVisible = (yStripe && !xStripe) || y == 15;
                                    downVisible = (yStripe && !xStripe) || y == 0;
                                    if (yStripe || xStripe)
                                        quads.addAll(ModelHelper.createSixFaceCuboid(x / 16f, (x + 1) / 16f, y / 16f, (y + 1) / 16f, (z + zOffset) / 16f, (z + zOffset + 1) / 16f, mimic, model, extraData, rand, tintIndex, nVisible, sVisible, eVisible, wVisible, upVisible, downVisible, rotation));
                                }
                            }
                        }
                    }
                }
            }
            int overlayIndex = extraData.get(FrameBlockTile.OVERLAY);
            if (overlayIndex != 0) {
                if (dir == north && open) {
                    quads.addAll(ModelHelper.createOverlay(0f, 1f, 0f, 1f, 13 / 16f, 1f, overlayIndex));
                } else if (dir == west && open) {
                    quads.addAll(ModelHelper.createOverlay(13 / 16f, 1f, 0f, 1f, 0f, 1f, overlayIndex));
                } else if (dir == east && open) {
                    quads.addAll(ModelHelper.createOverlay(0f, 3 / 16f, 0f, 1f, 0f, 1f, overlayIndex));
                } else if (dir == south && open) {
                    quads.addAll(ModelHelper.createOverlay(0f, 1f, 0f, 1f, 0f, 3 / 16f, overlayIndex));
                } else if (half == bottom) {
                    quads.addAll(ModelHelper.createOverlay(0f, 1f, 0f, 3 / 16f, 0f, 1f, overlayIndex));
                } else if (half == top) {
                    quads.addAll(ModelHelper.createOverlay(0f, 1f, 13 / 16f, 1f, 0f, 1f, overlayIndex, true, true, true, true, true, true, false));
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
    @Nonnull
    public TextureAtlasSprite getParticleIcon() {
        return Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(new ResourceLocation("minecraft", "block/oak_planks"));
    }

    @Override
    @Nonnull
    public ItemOverrides getOverrides() {
        return ItemOverrides.EMPTY;
    }

    @Override
    @NotNull
    public ChunkRenderTypeSet getRenderTypes(@NotNull BlockState state, @NotNull RandomSource rand, @NotNull ModelData data) {
        return ChunkRenderTypeSet.of(RenderType.translucent());
    }
}
//========SOLI DEO GLORIA========//