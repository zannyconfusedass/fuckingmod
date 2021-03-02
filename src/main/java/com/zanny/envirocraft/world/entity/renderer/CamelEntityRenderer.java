package com.zanny.envirocraft.world.entity.renderer;

import com.zanny.envirocraft.EnviroCraft;
import com.zanny.envirocraft.world.entity.CamelEntity;
import com.zanny.envirocraft.client.model.CamelEntityModel;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.renderer.geo.GeoEntityRenderer;

public class CamelEntityRenderer extends GeoEntityRenderer<CamelEntity> {

	public CamelEntityRenderer(EntityRenderDispatcher renderManager) {
		super(renderManager, new CamelEntityModel());
	}

	@Override
	public Identifier getTexture(CamelEntity entity) {
		if (entity.isBaby()) return new Identifier(EnviroCraft.MOD_ID, ("textures/entity/camel/baby_camel.png"));
		return new Identifier(EnviroCraft.MOD_ID, "textures/entity/camel/camel.png");
	}
}